/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.observation;

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
import sppbims.model.observation.Child_image;

/**
 *
 * @author libertyerp_local
 */
@Service
public class ChildImageService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> all_Child_Images() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Child_image> root = cq.from(Child_image.class);

        // Selecting multiple fields with aliases
        cq.multiselect(
                root.get("id").alias("imageId"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"), // Assuming 'code' exists in MotherMasterData
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("childMasterCode").get("id").alias("childMasterCodeId"),
                root.get("imageName").alias("imageName"),
                root.get("remarks").alias("remarks")
        );

        cq.orderBy(cb.desc(root.get("id")));

        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the result into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple result : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("imageId", result.get("imageId"));
            resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", result.get("motherMasterCode"));
            resultMap.put("motherName", result.get("motherName"));
            resultMap.put("childMasterCodeId", result.get("childMasterCodeId"));
            resultMap.put("name", result.get("name"));
            resultMap.put("childMasterCode", result.get("childMasterCode"));
            resultMap.put("imageName", result.get("imageName"));
            resultMap.put("remarks", result.get("remarks"));
            resultMaps.add(resultMap);
        }

        return resultMaps;
    }
}
