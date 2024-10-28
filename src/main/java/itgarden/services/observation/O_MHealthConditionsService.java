/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.observation;

import itgarden.model.observation.O_MHealthConditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
public class O_MHealthConditionsService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> findAllmHealthConditions() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_MHealthConditions> root = cq.from(O_MHealthConditions.class);

        cq.multiselect(
                root.get("id").alias("healthConditionId"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("observationDuration").alias("observationDuration"),
                //                root.get("previousDisease").alias("previousDisease"),
                //                root.get("previousTreatment").alias("previousTreatment"),
                //                root.get("preasentHealtsStatus").alias("presentHealthStatus"),
                //                root.get("nutritionStatus").alias("nutritionStatus"),
                root.get("height").alias("height"),
                root.get("weight").alias("weight"),
                //                root.get("bloodPressure").alias("bloodPressure"),
                //                root.get("eyeProblem").alias("eyeProblem"),
                //                root.get("earProblem").alias("earProblem"),
                //                root.get("gynologicalProblem").alias("gynologicalProblem"),
                //                root.get("tt").alias("tt"),
                //                root.get("heart_disease").alias("heartDisease"),
                //                root.get("diabetes").alias("diabetes"),
                //                root.get("bonyFracture").alias("bonyFracture"),
                root.get("note").alias("note")
        //                root.get("neurologicalDisease").alias("neurologicalDisease"),
        //                root.get("resporatoryProblem").alias("respiratoryProblem"),
        //                root.get("uti").alias("uti"),
        //                root.get("previousReport").alias("previousReport"),
        //                root.get("planForFurtherAction").alias("planForFurtherAction"),
        //                root.get("otherImportantFindings").alias("otherImportantFindings"),
        //                root.get("created").alias("created"),
        //                root.get("createdBy").alias("createdBy"),
        //                root.get("updated").alias("updated"),
        //                root.get("updatedBy").alias("updatedBy")      
        );

        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the result into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();

        for (Tuple result : resultList) {

            Map<String, Object> resultMap = new HashMap<>();

            resultMap.put("healthConditionId", result.get("healthConditionId"));
             resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", result.get("motherMasterCode"));
               resultMap.put("motherName", result.get("motherName"));

            resultMap.put("observationDuration", result.get("observationDuration"));
//            resultMap.put("previousDisease", result.get("previousDisease"));
//            resultMap.put("previousTreatment", result.get("previousTreatment"));
//            resultMap.put("presentHealthStatus", result.get("presentHealthStatus"));
//            resultMap.put("nutritionStatus", result.get("nutritionStatus"));
            resultMap.put("height", result.get("height"));
            resultMap.put("weight", result.get("weight"));
//            resultMap.put("bloodPressure", result.get("bloodPressure"));
//            resultMap.put("eyeProblem", result.get("eyeProblem"));
//            resultMap.put("earProblem", result.get("earProblem"));
//            resultMap.put("gynologicalProblem", result.get("gynologicalProblem"));
//            resultMap.put("tt", result.get("tt"));
//            resultMap.put("heartDisease", result.get("heartDisease"));
//            resultMap.put("diabetes", result.get("diabetes"));
//            resultMap.put("bonyFracture", result.get("bonyFracture"));
            resultMap.put("note", result.get("note"));
//            resultMap.put("neurologicalDisease", result.get("neurologicalDisease"));
//            resultMap.put("respiratoryProblem", result.get("respiratoryProblem"));
//            resultMap.put("uti", result.get("uti"));
//            resultMap.put("previousReport", result.get("previousReport"));
//            resultMap.put("planForFurtherAction", result.get("planForFurtherAction"));
//            resultMap.put("otherImportantFindings", result.get("otherImportantFindings"));
//            resultMap.put("created", result.get("created"));
//            resultMap.put("createdBy", result.get("createdBy"));
//            resultMap.put("updated", result.get("updated"));
//            resultMap.put("updatedBy", result.get("updatedBy"));
            resultMaps.add(resultMap);

        }

        return resultMaps;
    }

}
