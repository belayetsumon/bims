/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.psychology;

import sppbims.model.homevisit.Yes_No;
import sppbims.model.observation.O_Professional_Obserbations_Child;
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
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class O_Professional_Obserbations_Child_Service {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> professional_Obserbations_Completed_Child_List_Report(
            String startDate,
            String endDate,
            Yes_No physicalDisability,
            Yes_No therapy,
            Yes_No adlPerformance,
            Yes_No psychocialAssesmentNeeds
    ) {
        // Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // Create CriteriaQuery with Tuple
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        // Define the root for the query
        Root<O_Professional_Obserbations_Child> root = cq.from(O_Professional_Obserbations_Child.class);

        // Add conditions (if any)
        List<Predicate> predicates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("created"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("created"), start));
        }

        if (ObjectUtils.isNotEmpty(physicalDisability)) {
            predicates.add(cb.equal(root.get("physicalDisability"), physicalDisability));
        }

        if (ObjectUtils.isNotEmpty(therapy)) {
            predicates.add(cb.equal(root.get("therapy"), therapy));
        }

        if (ObjectUtils.isNotEmpty(adlPerformance)) {
            predicates.add(cb.equal(root.get("adlPerformance"), adlPerformance));
        }

        if (ObjectUtils.isNotEmpty(psychocialAssesmentNeeds)) {
            predicates.add(cb.equal(root.get("psychocialAssesmentNeeds"), psychocialAssesmentNeeds));
        }

        // Multi-selection with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("obStartDate").alias("obStartDate"),
                root.get("inductionStartDate").alias("inductionStartDate"),
                root.get("physicalDisability").alias("physicalDisability"),
                root.get("physicalDisabilityNote").alias("physicalDisabilityNote"),
                root.get("therapy").alias("therapy"),
                root.get("therapyNote").alias("therapyNote"),
                root.get("adlPerformance").alias("adlPerformance"),
                root.get("adlPerformanceNote").alias("adlPerformanceNote"),
                root.get("psychocialAssesmentNeeds").alias("psychocialAssesmentNeeds"),
                root.get("psychocialAssesmentNeedsNote").alias("psychocialAssesmentNeedsNote"),
                root.get("remarks").alias("remarks"),
                root.get("created").alias("created")
        );

        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        // Execute the query
        List<Tuple> results = em.createQuery(cq).getResultList();

        // Transform results to List<Map<String, Object>> using aliases
        List<Map<String, Object>> resultList = results.stream().map(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            map.put("motherName", tuple.get("motherName"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
//            map.put("induction", tuple.get("induction"));
            map.put("obStartDate", tuple.get("obStartDate"));
            map.put("inductionStartDate", tuple.get("inductionStartDate"));
            map.put("physicalDisability", tuple.get("physicalDisability"));
            map.put("physicalDisabilityNote", tuple.get("physicalDisabilityNote"));
            map.put("therapy", tuple.get("therapy"));
            map.put("therapyNote", tuple.get("therapyNote"));
            map.put("adlPerformance", tuple.get("adlPerformance"));
            map.put("adlPerformanceNote", tuple.get("adlPerformanceNote"));
            map.put("psychocialAssesmentNeeds", tuple.get("psychocialAssesmentNeeds"));
            map.put("psychocialAssesmentNeedsNote", tuple.get("psychocialAssesmentNeedsNote"));
            map.put("remarks", tuple.get("remarks"));
            map.put("created", tuple.get("created"));
            return map;
        }).collect(Collectors.toList());

        return resultList;
    }

}
