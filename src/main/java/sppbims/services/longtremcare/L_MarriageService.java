/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.longtremcare;

import sppbims.model.homevisit.Gender;
import sppbims.model.homevisit.M_Child_info;
import sppbims.model.longtermcare.L_Marriage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class L_MarriageService {

    @PersistenceContext
    private EntityManager em;

    // Method to get follow-ups with specific projections and ordered by id DESC
    public List<Map<String, Object>> findl_MarriageWithAlias(
            String startDate,
            String endDate,
            Gender gender
    ) {

        // Initialize CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        // Root for the L_FollowUp entity
        Root<L_Marriage> root = criteriaQuery.from(L_Marriage.class);
        // Join with M_Child_info (ManyToOne relationship)
        Join<L_Marriage, M_Child_info> childMasterJoin = root.join("childMasterCode", JoinType.INNER);
        // Select the tuple of multiple fields
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                childMasterJoin.get("id").alias("childMasterCodeId"),
                childMasterJoin.get("childMasterCode").alias("childMasterCode"),
                childMasterJoin.get("gender").alias("gender"),
                childMasterJoin.get("name").alias("name"),
                root.get("marriageDate").alias("marriageDate"),
                root.get("hasbandName").alias("hasbandName"),
                root.get("address").alias("address"),
                root.get("remark").alias("remark")
        );

        List<Predicate> predicates = new ArrayList<>();

        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(root.get("marriageDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(root.get("marriageDate"), start));
        }

        if (ObjectUtils.isNotEmpty(gender)) {
            predicates.add(criteriaBuilder.equal(root.get("childMasterCode").get("gender"), gender));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

        // Execute the query and get the results as a List of Tuples
        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);
        List<Tuple> resultList = query.getResultList();

        // Convert the result list into a List of Maps using forEach
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        resultList.forEach(tuple -> {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id", Long.class));
            resultMap.put("childMasterCode", tuple.get("childMasterCode", String.class));
            resultMap.put("childMasterCodeId", tuple.get("childMasterCodeId", Long.class));
            resultMap.put("gender", tuple.get("gender", Gender.class));
            resultMap.put("name", tuple.get("name", String.class));
            resultMap.put("marriageDate", tuple.get("marriageDate", LocalDate.class));
            resultMap.put("address", tuple.get("address", String.class));
            resultMap.put("hasbandName", tuple.get("hasbandName", String.class));
            resultMap.put("remark", tuple.get("remark", String.class));
            resultMapList.add(resultMap);
        });

        return resultMapList;
    }

}
