/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.longtremcare;

import sppbims.model.homevisit.Gender;
import sppbims.model.homevisit.M_Child_info;
import sppbims.model.longtermcare.FollowUpType;
import sppbims.model.longtermcare.L_FollowUp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
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
public class L_FollowUpService {

    @PersistenceContext
    private EntityManager em;

    // Method to get follow-ups with specific projections and ordered by id DESC
    public List<Map<String, Object>> findFollowUpsWithAlias(
            String startDate,
            String endDate,
            Gender gender
    ) {

        // Initialize CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for the L_FollowUp entity
        Root<L_FollowUp> root = criteriaQuery.from(L_FollowUp.class);

        // Join with M_Child_info (ManyToOne relationship)
        Join<L_FollowUp, M_Child_info> childMasterJoin = root.join("childMasterCode", JoinType.INNER);

        // Select columns with aliasing
        Expression<Long> idExpression = root.get("id");
        Expression<String> followUpDateExpression = root.get("followUpDate");
        Expression<String> followUpByExpression = root.get("followUpBy");
        Expression<FollowUpType> followUpTypeExpression = root.get("followUpType");
        Expression<String> remarkExpression = root.get("remark");
        Expression<String> childMasterCode = childMasterJoin.get("childMasterCode");
        Expression<Long> childMasterCodeIdExpression = childMasterJoin.get("id");
        Expression<Gender> childMasterCodeGenderExpression = childMasterJoin.get("gender");
        Expression<String> childMasterCodeNameExpression = childMasterJoin.get("name");

        // Select the tuple of multiple fields
        criteriaQuery.multiselect(
                idExpression.alias("id"),
                followUpDateExpression.alias("followUpDate"),
                followUpByExpression.alias("followUpBy"),
                followUpTypeExpression.alias("followUpType"),
                remarkExpression.alias("remark"),
                childMasterCode.alias("childMasterCode"),
                childMasterCodeIdExpression.alias("childMasterCodeId"),
                childMasterCodeNameExpression.alias("childname"),
                childMasterCodeGenderExpression.alias("gender")
        );

        List<Predicate> predicates = new ArrayList<>();

        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(root.get("followUpDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(root.get("followUpDate"), start));
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
            resultMap.put("followUpDate", tuple.get("followUpDate", String.class));
            resultMap.put("followUpBy", tuple.get("followUpBy", String.class));
            resultMap.put("followUpType", tuple.get("followUpType", FollowUpType.class));
            resultMap.put("remark", tuple.get("remark", String.class));
            resultMap.put("childMasterCode", tuple.get("childMasterCode", String.class));
            resultMap.put("childMasterCodeId", tuple.get("childMasterCodeId", Long.class));
            resultMap.put("childname", tuple.get("childname", String.class));
            resultMap.put("gender", tuple.get("gender", Gender.class));

            resultMapList.add(resultMap);
        });

        return resultMapList;
    }

}
