/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.school;

import sppbims.model.homevisit.M_Child_info;
import sppbims.model.school.Discontinuity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
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
import sppbims.model.homevisit.EducationLevel;
import sppbims.model.homevisit.EducationType;

/**
 *
 * @author libertyerp_local
 */
@Service
public class DiscontinuityService {

    @PersistenceContext
    EntityManager em;

    public List<Long> discontinuityChildIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<Discontinuity> root = cq.from(Discontinuity.class);

        cq.multiselect(root.get("childMasterCode").get("id"));

        cq.orderBy(cb.desc(root.get("childMasterCode").get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get(0, Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> allDiscontinuitiesList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        Root<Discontinuity> discontinuity = query.from(Discontinuity.class);

        // Create aliases for fields
        // Build the query (multi-select)
        Join<Discontinuity, EducationLevel> lastAttendedClass = discontinuity.join("lastAttendedClass", JoinType.LEFT);
        Join<Discontinuity, EducationType> lastAttendedEducationType = discontinuity.join("lastAttendedEducationType", JoinType.LEFT);
        Join<Discontinuity, M_Child_info> childMasterCode = discontinuity.join("childMasterCode", JoinType.LEFT);

        query.multiselect(
                discontinuity.get("id").alias("id"),
                discontinuity.get("discontinuityReason").alias("discontinuityReason"),
                discontinuity.get("dateDismissal").alias("dateDismissal"),
                discontinuity.get("remark").alias("remark"),
                discontinuity.get("lastAttendedSession").alias("lastAttendedSession"),
                lastAttendedClass.get("name").alias("lastAttendedClass"),
                lastAttendedEducationType.get("name").alias("lastAttendedEducationType"),
                childMasterCode.get("childMasterCode").alias("childMasterCode"),
                childMasterCode.get("name").alias("name"),
                childMasterCode.get("id").alias("childMasterCodeId")
        );

        // Order by id in descending order
        query.orderBy(cb.desc(discontinuity.get("id")));

        // Execute the query and collect the results
        List<Tuple> result = em.createQuery(query).getResultList();

        // Map the result (each Tuple) to a map using the alias as key
        // Create a List of Maps using a forEach loop
        List<Map<String, Object>> resultList = new ArrayList<>();
        result.forEach(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id", Long.class));
            map.put("discontinuityReason", tuple.get("discontinuityReason", String.class));
            map.put("dateDismissal", tuple.get("dateDismissal", LocalDate.class));
            map.put("remark", tuple.get("remark", String.class));
            map.put("childMasterCode", tuple.get("childMasterCode", String.class));
            map.put("name", tuple.get("name", String.class));
            map.put("childMasterCodeId", tuple.get("childMasterCodeId", Long.class));
            map.put("lastAttendedSession", tuple.get("lastAttendedSession"));
            map.put("lastAttendedClass", tuple.get("lastAttendedClass"));
            map.put("lastAttendedEducationType", tuple.get("lastAttendedEducationType"));
            resultList.add(map);
        });

        return resultList;
    }

    public List<Map<String, Object>> allDiscontinuitiesListReport(
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        Root<Discontinuity> discontinuity = query.from(Discontinuity.class);

        // Create aliases for fields
        Join<Discontinuity, EducationLevel> lastAttendedClass = discontinuity.join("lastAttendedClass", JoinType.LEFT);
        Join<Discontinuity, EducationType> lastAttendedEducationType = discontinuity.join("lastAttendedEducationType", JoinType.LEFT);
        Join<Discontinuity, M_Child_info> childMasterCode = discontinuity.join("childMasterCode", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(discontinuity.get("dateDismissal"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(discontinuity.get("dateDismissal"), start));
        }

        // Build the query (multi-select)
        query.multiselect(
                discontinuity.get("id").alias("id"),
                discontinuity.get("discontinuityReason").alias("discontinuityReason"),
                discontinuity.get("dateDismissal").alias("dateDismissal"),
                discontinuity.get("remark").alias("remark"),
                discontinuity.get("lastAttendedSession").alias("lastAttendedSession"),
                lastAttendedClass.get("name").alias("lastAttendedClass"),
                lastAttendedEducationType.get("name").alias("lastAttendedEducationType"),
                childMasterCode.get("childMasterCode").alias("childMasterCode"),
                childMasterCode.get("name").alias("name"),
                childMasterCode.get("id").alias("childMasterCodeId")
        );

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // Order by id in descending order
        query.orderBy(cb.desc(discontinuity.get("id")));

        // Execute the query and collect the results
        List<Tuple> result = em.createQuery(query).getResultList();

        // Map the result (each Tuple) to a map using the alias as key
        // Create a List of Maps using a forEach loop
        List<Map<String, Object>> resultList = new ArrayList<>();
        result.forEach(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id", Long.class));
            map.put("discontinuityReason", tuple.get("discontinuityReason", String.class));
            map.put("dateDismissal", tuple.get("dateDismissal", LocalDate.class));
            map.put("remark", tuple.get("remark", String.class));
            map.put("childMasterCode", tuple.get("childMasterCode", String.class));
            map.put("name", tuple.get("name", String.class));
            map.put("childMasterCodeId", tuple.get("childMasterCodeId", Long.class));
            map.put("lastAttendedSession", tuple.get("lastAttendedSession"));
            map.put("lastAttendedClass", tuple.get("lastAttendedClass"));
            map.put("lastAttendedEducationType", tuple.get("lastAttendedEducationType"));
            resultList.add(map);
        });

        return resultList;
    }

}
