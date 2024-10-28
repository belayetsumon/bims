/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.observation;

import itgarden.model.observation.O_Induction;
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
public class InductionoutcomeService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> allinductionData() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_Induction> root = cq.from(O_Induction.class);

        // Selecting multiple fields with aliases
        cq.multiselect(
                root.get("id").alias("inductionId"),
                root.get("startDate").alias("inductionStartDate"),
                root.get("endDate").alias("inductionEndDate"),
                root.get("possibleLength").alias("inductionPossibleLength"),
                root.get("immediateSupportOn").alias("immediateSupportOn"),
                root.get("challagesOfCandidare").alias("challagesOfCandidare"),
                root.get("remark").alias("inductionRemark"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"), // Assuming there's a 'code' field in MotherMasterData
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId")
        );

        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the result into a list of maps
        List<Map<String, Object>> resultMaps = new ArrayList<>();

        for (Tuple result : resultList) {
            Map<String, Object> resultMap = new HashMap<>();

            resultMap.put("inductionId", result.get("inductionId"));

            resultMap.put("inductionStartDate", result.get("inductionStartDate"));

            resultMap.put("inductionEndDate", result.get("inductionEndDate"));

            resultMap.put("inductionPossibleLength", result.get("inductionPossibleLength"));
            resultMap.put("immediateSupportOn", result.get("immediateSupportOn"));
            resultMap.put("challagesOfCandidare", result.get("challagesOfCandidare"));

            resultMap.put("inductionRemark", result.get("inductionRemark"));

            resultMap.put("motherMasterCode", result.get("motherMasterCode"));

            resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMaps.add(resultMap);
        }

        return resultMaps;
    }

}
