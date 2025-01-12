/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.training;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.rehabilitations.GraduateStatus;
import itgarden.model.rehabilitations.R_Swimming;
import itgarden.model.rehabilitations.TrainingName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class R_SwimmingService {

    @PersistenceContext
    private EntityManager em;
    
    public List<Long> swimmingCompletedChildIdList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_Swimming> root = cq.from(R_Swimming.class);

        cq.multiselect(
                root.get("childMasterCode").get("id").alias("childMasterCodeId")
        );
        cq.orderBy(cb.desc(root.get("id")));
        List<Tuple> result = em.createQuery(cq).getResultList();
        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get("childMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;

    }
    
    
    
    

    public List<Map<String, Object>> swimmingCompletedList() {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for R_Swimming entity
        Root<R_Swimming> swimmingRoot = criteriaQuery.from(R_Swimming.class);

        // Define aliases
        Join<R_Swimming, M_Child_info> childMasterJoin = swimmingRoot.join("childMasterCode", JoinType.INNER);

        Join<R_Swimming, Users> createdByJoin = swimmingRoot.join("createdBy", JoinType.LEFT);

        Join<R_Swimming, Users> updatedByJoin = swimmingRoot.join("updatedBy", JoinType.LEFT);

        // Select multiple columns with aliases
        criteriaQuery.multiselect(
                swimmingRoot.get("id").alias("swimmingId"),
                swimmingRoot.get("name").alias("swimmingName"),
                swimmingRoot.get("graduateStatus").alias("status"),
                swimmingRoot.get("startDate").alias("startDate"),
                swimmingRoot.get("endDate").alias("endDate"),
                childMasterJoin.get("childMasterCode").alias("childMasterCode"),
                createdByJoin.get("name").alias("createdByUsername"),
                updatedByJoin.get("name").alias("updatedByUsername"),
                swimmingRoot.get("created").alias("createdDate"),
                swimmingRoot.get("updated").alias("updatedDate")
        );

        // Order by 'id' (if needed)
        criteriaQuery.orderBy(criteriaBuilder.asc(swimmingRoot.get("id")));

        // Execute query
        List<Tuple> result = em.createQuery(criteriaQuery).getResultList();

        // Transform result into a List of Maps
        List<Map<String, Object>> resultMap = result.stream().map(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("swimmingId", tuple.get("swimmingId"));
            map.put("swimmingName", tuple.get("swimmingName"));
            map.put("status", tuple.get("status"));
            map.put("startDate", tuple.get("startDate"));
            map.put("endDate", tuple.get("endDate"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("createdByUsername", tuple.get("createdByUsername"));
            map.put("updatedByUsername", tuple.get("updatedByUsername"));
            map.put("createdDate", tuple.get("createdDate"));
            map.put("updatedDate", tuple.get("updatedDate"));
            return map;
        }).toList();

        return resultMap;
    }

    public List<Map<String, Object>> swimmingCompletedReportList(
            String childMasterCode,
            GraduateStatus graduateStatus,
            String startDate,
            String endDate
    ) {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for R_Swimming entity
        Root<R_Swimming> swimmingRoot = criteriaQuery.from(R_Swimming.class);

        // Define aliases
        Join<R_Swimming, M_Child_info> childMasterJoin = swimmingRoot.join("childMasterCode", JoinType.INNER);

        Join<R_Swimming, Users> createdByJoin = swimmingRoot.join("createdBy", JoinType.LEFT);

        Join<R_Swimming, Users> updatedByJoin = swimmingRoot.join("updatedBy", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(swimmingRoot.get("startDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(swimmingRoot.get("startDate"), start));
        }

        if (StringUtils.isNoneEmpty(childMasterCode)) {
            predicates.add(criteriaBuilder.equal(swimmingRoot.get("childMasterCode"), childMasterCode));
        }

        if (ObjectUtils.isNotEmpty(graduateStatus)) {
            predicates.add(criteriaBuilder.equal(swimmingRoot.get("graduateStatus"), graduateStatus));
        }

        // Select multiple columns with aliases
        criteriaQuery.multiselect(
                swimmingRoot.get("id").alias("swimmingId"),
                swimmingRoot.get("name").alias("swimmingName"),
                swimmingRoot.get("graduateStatus").alias("status"),
                swimmingRoot.get("startDate").alias("startDate"),
                swimmingRoot.get("endDate").alias("endDate"),
                childMasterJoin.get("childMasterCode").alias("childMasterCode"),
                createdByJoin.get("name").alias("createdByUsername"),
                updatedByJoin.get("name").alias("updatedByUsername"),
                swimmingRoot.get("created").alias("createdDate"),
                swimmingRoot.get("updated").alias("updatedDate")
        );
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        criteriaQuery.orderBy(criteriaBuilder.desc(swimmingRoot.get("id")));

        // Execute query
        List<Tuple> result = em.createQuery(criteriaQuery).getResultList();

        // Transform result into a List of Maps
        List<Map<String, Object>> resultMap = result.stream().map(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("swimmingId", tuple.get("swimmingId"));
            map.put("swimmingName", tuple.get("swimmingName"));
            map.put("status", tuple.get("status"));
            map.put("startDate", tuple.get("startDate"));
            map.put("endDate", tuple.get("endDate"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("createdByUsername", tuple.get("createdByUsername"));
            map.put("updatedByUsername", tuple.get("updatedByUsername"));
            map.put("createdDate", tuple.get("createdDate"));
            map.put("updatedDate", tuple.get("updatedDate"));
            return map;
        }).toList();

        return resultMap;
    }
}
