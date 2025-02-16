/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.cmc;

import sppbims.model.rehabilitations.R_M_WorkAllocation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class R_M_WorkAllocationService {

    @PersistenceContext
    EntityManager em;

    public List<Long> motherWorkAllicationIdList() {
// Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

// Create CriteriaQuery for R_M_HousAllocation
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_M_WorkAllocation> root = cq.from(R_M_WorkAllocation.class);

// Define the selection (using tuple)
        cq.multiselect(
                //                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId")
        //                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
        //                root.get("motherMasterCode").get("motherName").alias("motherName"),// Assuming 'code' is a field in MotherMasterData
        //                root.get("houseName").get("name").alias("houseName"), // Assuming 'name' is a field in HouseName
        //                root.get("allocationDate").alias("allocationDate"),
        //                root.get("endDate").alias("endDate"),
        //                root.get("remark").alias("remark")
        );

// Add predicates to the query
        cq.orderBy(cb.desc(root.get("id")));
// Execute the query
        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();

        for (Tuple t : result) {
            Long id = t.get("motherMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;

    }

    public List<Tuple> currentMotherWorkLocation() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<R_M_WorkAllocation> root = cq.from(R_M_WorkAllocation.class);

        Path<LocalDate> startDate = root.get("allocationDate");
        Path<LocalDate> endDate = root.get("endDate");
        Path<LocalDate> extentionDate = root.get("extDate");

        // Handle null values by coalescing to default values (e.g., LocalDate.ofEpochDay(0))
        Expression<Long> totalDate = cb.diff(
                cb.function("DATEDIFF", Long.class,
                        cb.coalesce(endDate, cb.literal(0L)), // Replace null with default date
                        cb.coalesce(startDate, cb.literal(0L)) // Replace null with default date
                ),
                cb.literal(0L)
        );

        // Handle extension date similarly, assuming extensionDate may also be null
        Expression<Long> extentionDays = cb.diff(
                cb.function("DATEDIFF", Long.class,
                        cb.coalesce(extentionDate, cb.literal(0L)), // Replace null with default date
                        cb.coalesce(endDate, cb.literal(0L)) // Replace null with default date
                ),
                cb.literal(0L)
        );

        Expression<Long> grandTotalDay = cb.sum(totalDate, extentionDays);

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("sectionPlace").alias("sectionPlace"), // Assuming 'name' is a field in HouseName
                root.get("allocationDate").alias("allocationDate"),
                root.get("endDate").alias("endDate"),
                root.get("extDate").alias("extDate"),
                totalDate.alias("totaldays"),
                extentionDays.alias("extentionDays"),
                grandTotalDay.alias("grandTotalDay"),
                root.get("remark").alias("remark")
        );
        List<Predicate> predicates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        // Check if extDate is null
        Predicate extDateIsNull = cb.isNull(root.get("extDate"));
        Predicate extDateNotNull = cb.isNotNull(root.get("extDate"));

        // If extDate is null, compare endDate with currentDate
        Predicate endDateCondition = cb.greaterThan(root.get("endDate"), currentDate);

        // If extDate is not null, compare extDate with currentDate
        Predicate extDateCondition = cb.greaterThan(root.get("extDate"), currentDate);

        // Combine conditions based on extDate's nullability
        Predicate finalCondition = cb.or(
                cb.and(extDateIsNull, endDateCondition), // If extDate is null, check endDate
                cb.and(extDateNotNull, extDateCondition) // If extDate is not null, check extDate
        );

        // Add the final condition to the query
        predicates.add(finalCondition);

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<Tuple> query = em.createQuery(cq);

        return query.getResultList();
    }

    public List<Map<String, Object>> all_Mother_Work_Location_by_id(
            Long id
    ) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<R_M_WorkAllocation> root = cq.from(R_M_WorkAllocation.class);

        Path<LocalDate> startDate = root.get("allocationDate");
        Path<LocalDate> endDate = root.get("endDate");
        Path<LocalDate> extentionDate = root.get("extDate");

        // Handle null values by coalescing to default values (e.g., LocalDate.ofEpochDay(0))
        Expression<Long> totalDate = cb.diff(
                cb.function("DATEDIFF", Long.class,
                        cb.coalesce(endDate, cb.literal(0L)), // Replace null with default date
                        cb.coalesce(startDate, cb.literal(0L)) // Replace null with default date
                ),
                cb.literal(0L)
        );

        // Handle extension date similarly, assuming extensionDate may also be null
        Expression<Long> extentionDays = cb.diff(
                cb.function("DATEDIFF", Long.class,
                        cb.coalesce(extentionDate, cb.literal(0L)), // Replace null with default date
                        cb.coalesce(endDate, cb.literal(0L)) // Replace null with default date
                ),
                cb.literal(0L)
        );

        Expression<Long> grandTotalDay = cb.sum(totalDate, extentionDays);

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("sectionPlace").alias("sectionPlace"), // Assuming 'name' is a field in HouseName
                root.get("work").alias("work"),
                root.get("allocationDate").alias("allocationDate"),
                root.get("endDate").alias("endDate"),
                root.get("extDate").alias("extDate"),
                totalDate.alias("totaldays"),
                extentionDays.alias("extentionDays"),
                grandTotalDay.alias("grandTotalDay"),
                root.get("remark").alias("remark")
        );

        cq.where(cb.equal(root.get("motherMasterCode").get("id"), id));

        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> resultList = query.getResultList();

        // Initialize the result list that will hold maps
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();

            // Mapping Tuple values to the resultMap using aliases
            resultMap.put("id", tuple.get("id"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("sectionPlace", tuple.get("sectionPlace"));
            resultMap.put("work", tuple.get("work"));
            resultMap.put("allocationDate", tuple.get("allocationDate"));
            resultMap.put("endDate", tuple.get("endDate"));
            resultMap.put("extDate", tuple.get("extDate"));
            resultMap.put("totaldays", tuple.get("totaldays"));
            resultMap.put("extentionDays", tuple.get("extentionDays"));
            resultMap.put("grandTotalDay", tuple.get("grandTotalDay"));
            resultMap.put("remark", tuple.get("remark"));

            // Add the result map to the list
            resultMaps.add(resultMap);
        }

        // Return the list of maps
        return resultMaps;
    }

    public List<Map<String, Object>> all_Mother_Work_Location() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<R_M_WorkAllocation> root = cq.from(R_M_WorkAllocation.class);

        Path<LocalDate> startDate = root.get("allocationDate");
        Path<LocalDate> endDate = root.get("endDate");
        Path<LocalDate> extentionDate = root.get("extDate");

        // Handle null values by coalescing to default values (e.g., LocalDate.ofEpochDay(0))
        Expression<Long> totalDate = cb.diff(
                cb.function("DATEDIFF", Long.class,
                        cb.coalesce(endDate, cb.literal(0L)), // Replace null with default date
                        cb.coalesce(startDate, cb.literal(0L)) // Replace null with default date
                ),
                cb.literal(0L)
        );

        // Handle extension date similarly, assuming extensionDate may also be null
        Expression<Long> extentionDays = cb.diff(
                cb.function("DATEDIFF", Long.class,
                        cb.coalesce(extentionDate, cb.literal(0L)), // Replace null with default date
                        cb.coalesce(endDate, cb.literal(0L)) // Replace null with default date
                ),
                cb.literal(0L)
        );

        Expression<Long> grandTotalDay = cb.sum(totalDate, extentionDays);

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("sectionPlace").alias("sectionPlace"), // Assuming 'name' is a field in HouseName
                root.get("work").alias("work"),
                root.get("allocationDate").alias("allocationDate"),
                root.get("endDate").alias("endDate"),
                root.get("extDate").alias("extDate"),
                totalDate.alias("totaldays"),
                extentionDays.alias("extentionDays"),
                grandTotalDay.alias("grandTotalDay"),
                root.get("remark").alias("remark")
        );

        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> resultList = query.getResultList();

        // Initialize the result list that will hold maps
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();

            // Mapping Tuple values to the resultMap using aliases
            resultMap.put("id", tuple.get("id"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("sectionPlace", tuple.get("sectionPlace"));
            resultMap.put("work", tuple.get("work"));
            resultMap.put("allocationDate", tuple.get("allocationDate"));
            resultMap.put("endDate", tuple.get("endDate"));
            resultMap.put("extDate", tuple.get("extDate"));
            resultMap.put("totaldays", tuple.get("totaldays"));
            resultMap.put("extentionDays", tuple.get("extentionDays"));
            resultMap.put("grandTotalDay", tuple.get("grandTotalDay"));
            resultMap.put("remark", tuple.get("remark"));

            // Add the result map to the list
            resultMaps.add(resultMap);
        }

        // Return the list of maps
        return resultMaps;
    }

}
