/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.observation;

import itgarden.model.homevisit.Gender;
import itgarden.model.observation.O_ChildAdmission;
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
public class O_ChildAdmissionService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> allAdmitedChildPeriodicReportList(
            String startDate,
            String endDate,
            Gender gender
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_ChildAdmission> root = cq.from(O_ChildAdmission.class);

        List<Predicate> predicates = new ArrayList<>();

        // Define the date formatter
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

        // Apply gender condition if gender is not null
//        if (gender != null) {
//            predicates.add(cb.equal(root.get("childMasterCode").get("gender"), gender));
//        }
         if (ObjectUtils.isNotEmpty(gender)) {
         predicates.add(cb.equal(root.get("childMasterCode").get("gender"), gender));
         }
        // Define the fields to select in the query
        cq.multiselect(
                root.get("id").alias("admissionId"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("childMasterCode").get("gender").alias("gender"),
                root.get("dateArrival").alias("arrivalDate"),
                root.get("dateAdmission").alias("admissionDate"),
                root.get("remarks").alias("remarks")
        );

        // Apply the where clause with the combined predicates
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sort the results by ID in descending order
        cq.orderBy(cb.desc(root.get("id")));

        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the results into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple result : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("admissionId", result.get("admissionId"));
            resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", result.get("motherMasterCode"));
            resultMap.put("motherName", result.get("motherName"));
            resultMap.put("childMasterCode", result.get("childMasterCode"));
            resultMap.put("name", result.get("name"));
            resultMap.put("gender", result.get("gender"));
            resultMap.put("arrivalDate", result.get("arrivalDate"));
            resultMap.put("admissionDate", result.get("admissionDate"));
            resultMap.put("remarks", result.get("remarks"));
            resultMaps.add(resultMap);
        }
        
        return resultMaps;
    }
    
    
    public List<Map<String, Object>> allAdmitedChildReportList(
//            String startDate,
//            String endDate,
//            Gender gender
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_ChildAdmission> root = cq.from(O_ChildAdmission.class);

        List<Predicate> predicates = new ArrayList<>();

        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
//        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
//            LocalDate start = LocalDate.parse(startDate, formatter);
//            LocalDate end = LocalDate.parse(endDate, formatter);
//            predicates.add(cb.between(root.get("dateAdmission"), start, end));
//        } else if (ObjectUtils.isNotEmpty(startDate)) {
//            LocalDate start = LocalDate.parse(startDate, formatter);
//            predicates.add(cb.equal(root.get("dateAdmission"), start));
//        }

        // Apply gender condition if gender is not null
//        if (gender != null) {
//            predicates.add(cb.equal(root.get("childMasterCode").get("gender"), gender));
//        }
//         if (ObjectUtils.isNotEmpty(gender)) {
//         predicates.add(cb.equal(root.get("childMasterCode").get("gender"), gender));
//         }
        // Define the fields to select in the query
        cq.multiselect(
                root.get("id").alias("admissionId"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("childMasterCode").get("gender").alias("gender"),
                root.get("dateArrival").alias("arrivalDate"),
                root.get("dateAdmission").alias("admissionDate"),
                root.get("remarks").alias("remarks")
        );

        // Apply the where clause with the combined predicates
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sort the results by ID in descending order
        cq.orderBy(cb.desc(root.get("id")));

        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the results into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple result : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("admissionId", result.get("admissionId"));
            resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", result.get("motherMasterCode"));
            resultMap.put("motherName", result.get("motherName"));
            resultMap.put("childMasterCode", result.get("childMasterCode"));
            resultMap.put("name", result.get("name"));
            resultMap.put("gender", result.get("gender"));
            resultMap.put("arrivalDate", result.get("arrivalDate"));
            resultMap.put("admissionDate", result.get("admissionDate"));
            resultMap.put("remarks", result.get("remarks"));
            resultMaps.add(resultMap);
        }
        
        return resultMaps;
    }
    
    
    
    
}
