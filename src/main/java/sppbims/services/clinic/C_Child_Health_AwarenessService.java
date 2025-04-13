/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.clinic;

import sppbims.model.clinic.C_Child_Health_Awareness;
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
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class C_Child_Health_AwarenessService {

    @PersistenceContext
    private EntityManager em;

    public List<Map<String, Object>> getAllChildHealthAwarenessData_report(
            String startDate,
            String endDate
    ) {
        // Initialize CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // Create CriteriaQuery for Tuple
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Define the root entity for the query (C_Child_Health_Awareness)
        Root<C_Child_Health_Awareness> root = cq.from(C_Child_Health_Awareness.class);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("insdate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("insdate"), start));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Select fields with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("insdate").alias("insdate"),
                root.get("remark").alias("remark"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"), // Alias for childMasterCode
                root.get("childMasterCode").get("name").alias("name"),
                root.get("healthAwareness").get("name").alias("healthAwareness") // Alias for healthAwareness
        );

        // Apply ordering by id in descending order
        cq.orderBy(cb.desc(root.get("id")));

        // Create a query using the CriteriaQuery
        List<Tuple> results = em.createQuery(cq).getResultList();

        // Execute the query and get the result list of Tuples
        // Create a List to store the results as Maps
        List<Map<String, Object>> resultMapList = new ArrayList<>();

        // Loop through the results and convert each Tuple to a Map
        for (Tuple tuple : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("insdate", tuple.get("insdate"));
            map.put("remark", tuple.get("remark"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("name", tuple.get("name"));
            map.put("healthAwareness", tuple.get("healthAwareness"));
            resultMapList.add(map);
        }

        return resultMapList;
    }

}
