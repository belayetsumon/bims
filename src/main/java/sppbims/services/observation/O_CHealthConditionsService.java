/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.observation;

import sppbims.model.homevisit.Yes_No;
import sppbims.model.observation.O_CHealthConditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
public class O_CHealthConditionsService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> childHelthConditionsReport(
            Yes_No bloodPressure,
            Yes_No eyeProblem,
            Yes_No earProblem,
            Yes_No tt,
            Yes_No heart_disease,
            Yes_No disability,
            Yes_No bonyFracture,
            Yes_No neurologicalDisease,
            Yes_No resporatoryProblem,
            Yes_No uti
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_CHealthConditions> root = cq.from(O_CHealthConditions.class);

        List<Predicate> predicates = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(bloodPressure)) {
            predicates.add(cb.equal(root.get("bloodPressure"), bloodPressure));
        }
        if (ObjectUtils.isNotEmpty(eyeProblem)) {
            predicates.add(cb.equal(root.get("eyeProblem"), eyeProblem));
        }
        if (ObjectUtils.isNotEmpty(earProblem)) {
            predicates.add(cb.equal(root.get("earProblem"), earProblem));
        }

        if (ObjectUtils.isNotEmpty(tt)) {
            predicates.add(cb.equal(root.get("tt"), tt));
        }
        if (ObjectUtils.isNotEmpty(heart_disease)) {
            predicates.add(cb.equal(root.get("heart_disease"), heart_disease));
        }
        if (ObjectUtils.isNotEmpty(disability)) {
            predicates.add(cb.equal(root.get("disability"), disability));
        }
        if (ObjectUtils.isNotEmpty(bonyFracture)) {
            predicates.add(cb.equal(root.get("bonyFracture"), bonyFracture));
        }
        if (ObjectUtils.isNotEmpty(neurologicalDisease)) {
            predicates.add(cb.equal(root.get("neurologicalDisease"), neurologicalDisease));
        }
        if (ObjectUtils.isNotEmpty(resporatoryProblem)) {
            predicates.add(cb.equal(root.get("resporatoryProblem"), resporatoryProblem));
        }
        if (ObjectUtils.isNotEmpty(uti)) {
            predicates.add(cb.equal(root.get("uti"), uti));
        }

        cq.multiselect(
                root.get("id").alias("healthConditionId"),
                root.get("childMasterCode").get("id").alias("childMasterCodeId"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("observationDuration").alias("observationDuration"),
                root.get("previousDisease").alias("previousDisease"),
                root.get("previousTreatment").alias("previousTreatment"),
                root.get("preasentHealtsStatus").alias("presentHealthStatus"),
                root.get("nutritionStatus").alias("nutritionStatus"),
                root.get("height").alias("height"),
                root.get("weight").alias("weight"),
                root.get("bloodPressure").alias("bloodPressure"),
                root.get("eyeProblem").alias("eyeProblem"),
                root.get("earProblem").alias("earProblem"),
                root.get("tt").alias("tt"),
                root.get("heart_disease").alias("heartDisease"),
                root.get("disability").alias("disability"),
                root.get("bonyFracture").alias("bonyFracture"),
                root.get("note").alias("note"),
                root.get("neurologicalDisease").alias("neurologicalDisease"),
                root.get("resporatoryProblem").alias("respiratoryProblem"),
                root.get("uti").alias("uti"),
                root.get("previousReport").alias("previousReport"),
                root.get("planForFurtherAction").alias("planForFurtherAction"),
                root.get("otherImportantFindings").alias("otherImportantFindings")
        );
        // Apply 'OR' condition if both predicates are present
        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }
        cq.orderBy(cb.desc(root.get("id")));
        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the result into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();

        for (Tuple result : resultList) {

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("healthConditionId", result.get("healthConditionId"));
            resultMap.put("childMasterCodeId", result.get("childMasterCodeId"));
            resultMap.put("childMasterCode", result.get("childMasterCode"));
            resultMap.put("name", result.get("name"));
            resultMap.put("observationDuration", result.get("observationDuration"));
            resultMap.put("previousDisease", result.get("previousDisease"));
            resultMap.put("previousTreatment", result.get("previousTreatment"));
            resultMap.put("presentHealthStatus", result.get("presentHealthStatus"));
            resultMap.put("nutritionStatus", result.get("nutritionStatus"));
            resultMap.put("height", result.get("height"));
            resultMap.put("weight", result.get("weight"));
            resultMap.put("bloodPressure", result.get("bloodPressure"));
            resultMap.put("eyeProblem", result.get("eyeProblem"));
            resultMap.put("earProblem", result.get("earProblem"));
            resultMap.put("tt", result.get("tt"));
            resultMap.put("heartDisease", result.get("heartDisease"));
            resultMap.put("disability", result.get("disability"));
            resultMap.put("bonyFracture", result.get("bonyFracture"));
            resultMap.put("note", result.get("note"));
            resultMap.put("neurologicalDisease", result.get("neurologicalDisease"));
            resultMap.put("respiratoryProblem", result.get("respiratoryProblem"));
            resultMap.put("uti", result.get("uti"));
            resultMap.put("previousReport", result.get("previousReport"));
            resultMap.put("planForFurtherAction", result.get("planForFurtherAction"));
            resultMap.put("otherImportantFindings", result.get("otherImportantFindings"));

            resultMaps.add(resultMap);

        }
        return resultMaps;
    }

}
