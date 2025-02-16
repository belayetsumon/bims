package sppbims.services.school;

import sppbims.model.observation.O_ChildAdmission;
import sppbims.model.school.S_RegularAdmissionClass;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class S_RegularAdmissionClassService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    DiscontinuityService discontinuityService;

    public List<Long> s_regular_AddmitedChildIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<S_RegularAdmissionClass> root = cq.from(S_RegularAdmissionClass.class);

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

    public List<Map<String, Object>> alladmitedchildList(
            String startDate,
            String endDate
    ) {
        // Obtain CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root of the query (representing the S_RegularAdmissionClass entity)
        Root<S_RegularAdmissionClass> root = cq.from(S_RegularAdmissionClass.class);

        // Join with M_Child_info (One-to-One relationship)
        // Join<S_RegularAdmissionClass, M_Child_info> childMasterCodeJoin = root.join("childMasterCode", JoinType.LEFT);
        // Multi-select fields with aliases (use alias names)
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("dateAdmission"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("dateAdmission"), start));
        }

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("childMasterCode").get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("childMasterCode").get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("childMasterCode").get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("id").alias("childMasterCodeId"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("dateAdmission").alias("dateAdmission"),
                root.get("admissionSession").alias("admissionSession"),
                root.get("admissionClass").get("name").alias("admissionClass"),
                root.get("lastAttendedSession").alias("lastAttendedSession"),
                root.get("lastAttendedClass").get("name").alias("lastAttendedClass"),
                root.get("lastAttendedEducationType").get("name").alias("lastAttendedEducationType"),
                root.get("specialNeed").alias("specialNeed"),
                root.get("remark").alias("remark")
        );
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        cq.orderBy(cb.desc(root.get("id")));
        // Execute the query and get the result as a List of Tuples
        List<Tuple> result = em.createQuery(cq).getResultList();

        // Convert Tuple list to List<Map<String, Object>>
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            map.put("motherName", tuple.get("motherName"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("childMasterCodeId", tuple.get("childMasterCodeId"));
            map.put("name", tuple.get("name"));
            map.put("dateAdmission", tuple.get("dateAdmission"));
            map.put("admissionSession", tuple.get("admissionSession"));
            map.put("admissionClass", tuple.get("admissionClass"));
            map.put("lastAttendedSession", tuple.get("lastAttendedSession"));
            map.put("lastAttendedClass", tuple.get("lastAttendedClass"));
            map.put("lastAttendedEducationType", tuple.get("lastAttendedEducationType"));
            map.put("specialNeed", tuple.get("specialNeed"));
            map.put("remark", tuple.get("remark"));
            resultList.add(map);
        }

        return resultList;
    }

    public List<Map<String, Object>> alladmitedchildList_exclude_DiscontinueList() {
        // Obtain CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root of the query (representing the S_RegularAdmissionClass entity)
        Root<S_RegularAdmissionClass> root = cq.from(S_RegularAdmissionClass.class);

        // Join with M_Child_info (One-to-One relationship)
        // Join<S_RegularAdmissionClass, M_Child_info> childMasterCodeJoin = root.join("childMasterCode", JoinType.LEFT);
        // Multi-select fields with aliases (use alias names)
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("childMasterCode").get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("childMasterCode").get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("childMasterCode").get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("id").alias("childMasterCodeId"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("dateAdmission").alias("dateAdmission"),
                root.get("admissionSession").alias("admissionSession"),
                root.get("admissionClass").get("name").alias("admissionClass"),
                root.get("lastAttendedSession").alias("lastAttendedSession"),
                root.get("lastAttendedClass").get("name").alias("lastAttendedClass"),
                root.get("lastAttendedEducationType").get("name").alias("lastAttendedEducationType"),
                root.get("specialNeed").alias("specialNeed"),
                root.get("remark").alias("remark")
        );

        List<Predicate> predicates = new ArrayList<Predicate>();
        // Define the predicates for the conditions

        predicates.add(root.get("childMasterCode").get("id").in(discontinuityService.discontinuityChildIdList()).not());

        cq.where(predicates.toArray(new Predicate[]{}));

        cq.orderBy(cb.desc(root.get("id")));
        // Execute the query and get the result as a List of Tuples
        List<Tuple> result = em.createQuery(cq).getResultList();

        // Convert Tuple list to List<Map<String, Object>>
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            map.put("motherName", tuple.get("motherName"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("childMasterCodeId", tuple.get("childMasterCodeId"));
            map.put("name", tuple.get("name"));
            map.put("dateAdmission", tuple.get("dateAdmission"));
            map.put("admissionSession", tuple.get("admissionSession"));
            map.put("admissionClass", tuple.get("admissionClass"));
            map.put("lastAttendedSession", tuple.get("lastAttendedSession"));
            map.put("lastAttendedClass", tuple.get("lastAttendedClass"));
            map.put("lastAttendedEducationType", tuple.get("lastAttendedEducationType"));
            map.put("specialNeed", tuple.get("specialNeed"));
            map.put("remark", tuple.get("remark"));
            resultList.add(map);
        }

        return resultList;
    }

}
