/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.psychology;

import jakarta.persistence.EntityManager;
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
import sppbims.model.rehabilitations.R_PT;

/**
 *
 * @author libertyerp_local
 */
@Service
public class R_PTService {

    @Autowired
    private EntityManager em;

    public List<Map<String, Object>> getReportsByDate(
            String startDate,
            String endDate
    ) {
// Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

// Create CriteriaQuery for R_M_HousAllocation
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_PT> root = cq.from(R_PT.class);

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

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                // Mood/Emotion properties
                root.get("therapeuticSessionDate").alias("therapeuticSessionDate"),
                root.get("pain").alias("pain"),
                root.get("painNote").alias("painNote"),
                root.get("Tenderness").get("name").alias("Tenderness"),
                root.get("PhysicalDisability").alias("PhysicalDisability"),
                root.get("physicalDisabilityNote").alias("physicalDisabilityNote"),
                root.get("jointMobility").get("name").alias("jointMobility"),
                root.get("musculoskeletal").get("name").alias("musculoskeletal"),
                root.get("degenerativeDiseases").get("name").alias("degenerativeDiseases"),
                root.get("previouslyTherapyTaken").alias("previouslyTherapyTaken"),
                root.get("previouslyTherapyTakenNote").alias("previouslyTherapyTakenNote"),
                root.get("remark").alias("remark"),
                root.get("created").alias("created")
        );

        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> tuples = em.createQuery(cq).getResultList();
        List<Map<String, Object>> results = new ArrayList<>();

        // Convert each Tuple to a Map<String, Object>
        for (Tuple tuple : tuples) {

            Map<String, Object> map = new HashMap<>();

            // Manually map each alias to its corresponding value from the tuple
            map.put("id", tuple.get("id"));
            map.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherName", tuple.get("motherName"));

            // Mood/Emotion properties
            map.put("therapeuticSessionDate", tuple.get("therapeuticSessionDate"));
            map.put("pain", tuple.get("pain"));
            map.put("painNote", tuple.get("painNote"));
            map.put("Tenderness", tuple.get("Tenderness"));
            map.put("PhysicalDisability", tuple.get("PhysicalDisability"));
            map.put("physicalDisabilityNote", tuple.get("physicalDisabilityNote"));
            map.put("jointMobility", tuple.get("jointMobility"));
            map.put("musculoskeletal", tuple.get("musculoskeletal"));
            map.put("degenerativeDiseases", tuple.get("degenerativeDiseases"));
            map.put("previouslyTherapyTaken", tuple.get("previouslyTherapyTaken"));
            map.put("previouslyTherapyTakenNote", tuple.get("previouslyTherapyTakenNote"));
            map.put("remark", tuple.get("remark"));
            map.put("created", tuple.get("created"));

            results.add(map);
        }

        return results;
    }

}
