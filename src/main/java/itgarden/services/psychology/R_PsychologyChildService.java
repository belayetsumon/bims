/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.psychology;

import itgarden.model.rehabilitations.R_PsychologyChild;
import itgarden.model.rehabilitations.R_PsychologyMother;
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
public class R_PsychologyChildService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> psychologyCompletedChildListReport(
            String startDate,
            String endDate
    ) {
// Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

// Create CriteriaQuery for R_M_HousAllocation
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_PsychologyChild> root = cq.from(R_PsychologyChild.class);

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
                root.get("anger").alias("anger"),
                root.get("sad").alias("sad"),
                root.get("guilt").alias("guilt"),
                root.get("happy").alias("happy"),
                root.get("tensed").alias("tensed"),
                root.get("stress").alias("stress"),
                root.get("emotionStatus").alias("emotionStatus"),
                // Behavioral properties
                root.get("selfHerm").alias("selfHerm"),
                root.get("copingStrategy").alias("copingStrategy"),
                root.get("presenceTrauma").alias("presenceTrauma"),
                root.get("familyRelationship").alias("familyRelationship"),
                root.get("hallucination").alias("hallucination"),
                root.get("hallucinationNote").alias("hallucinationNote"),
                root.get("delusion").alias("delusion"),
                root.get("delusionNote").alias("delusionNote"),
                root.get("sleepDisturbance").alias("sleepDisturbance"),
                root.get("iQ").alias("iQ"),
                // Other behavioral descriptions
                root.get("fivebBasicNeed").alias("fivebBasicNeed"),
                root.get("protection").alias("protection"),
                root.get("performanceWorkingSection").alias("performanceWorkingSection"),
                root.get("performanceLiteracy").alias("performanceLiteracy"),
                root.get("performanceEconomy").alias("performanceEconomy")
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
            map.put("anger", tuple.get("anger"));
            map.put("sad", tuple.get("sad"));
            map.put("guilt", tuple.get("guilt"));
            map.put("happy", tuple.get("happy"));
            map.put("tensed", tuple.get("tensed"));
            map.put("stress", tuple.get("stress"));
            map.put("emotionStatus", tuple.get("emotionStatus"));

            // Behavioral properties
            map.put("selfHerm", tuple.get("selfHerm"));
            map.put("copingStrategy", tuple.get("copingStrategy"));
            map.put("presenceTrauma", tuple.get("presenceTrauma"));
            map.put("familyRelationship", tuple.get("familyRelationship"));
            map.put("hallucination", tuple.get("hallucination"));
            map.put("hallucinationNote", tuple.get("hallucinationNote"));
            map.put("delusion", tuple.get("delusion"));
            map.put("delusionNote", tuple.get("delusionNote"));
            map.put("sleepDisturbance", tuple.get("sleepDisturbance"));
            map.put("iQ", tuple.get("iQ"));

            // Other behavioral descriptions
            map.put("fivebBasicNeed", tuple.get("fivebBasicNeed"));
            map.put("protection", tuple.get("protection"));
            map.put("performanceWorkingSection", tuple.get("performanceWorkingSection"));
            map.put("performanceLiteracy", tuple.get("performanceLiteracy"));
            map.put("performanceEconomy", tuple.get("performanceEconomy"));

            results.add(map);
        }

        return results;
    }

}
