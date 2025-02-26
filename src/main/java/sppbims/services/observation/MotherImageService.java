/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.observation;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.observation.MotherImage;
import sppbims.model.observation.O_MAddmission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import sppbims.model.observation.O_ChildAdmission;

/**
 *
 * @author libertyerp_local
 */
@Service
public class MotherImageService {

    @PersistenceContext
    EntityManager em;
    
    
    
     public List<Long> motherImageid() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<MotherImage> root = cq.from(MotherImage.class);

        cq.multiselect(root.get("motherMasterCode").get("id").alias("id"));

        cq.orderBy(cb.desc(root.get("motherMasterCode").get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple tuple : result) {
            Long id = tuple.get("id", Long.class);
            idList.add(id);
        }
        return idList;
    }


    public List<Map<String, Object>> allMotherImages() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<MotherImage> root = cq.from(MotherImage.class);
        // Joining related entities
        Join<MotherImage, MotherMasterData> motherJoin = root.join("motherMasterCode");
        Join<MotherImage, O_MAddmission> admissionJoin = root.join("addmission");

        // Selecting multiple fields with aliases
        cq.multiselect(
                root.get("id").alias("imageId"),
                motherJoin.get("id").alias("motherMasterCodeId"),
                motherJoin.get("motherMasterCode").alias("motherMasterCode"), // Assuming 'code' exists in MotherMasterData
                motherJoin.get("motherName").alias("motherName"),
                motherJoin.get("mobileNumber").alias("mobileNumber"),
                admissionJoin.get("id").alias("admissionId"),
                admissionJoin.get("dateAdmission").alias("dateAdmission"),
                root.get("imagetype").alias("imageType"),
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
            resultMap.put("mobileNumber", result.get("mobileNumber"));
            resultMap.put("admissionId", result.get("admissionId"));
            resultMap.put("dateAdmission", result.get("dateAdmission"));
            resultMap.put("imageType", result.get("imageType"));
            resultMap.put("imageName", result.get("imageName"));
            resultMap.put("remarks", result.get("remarks"));
            resultMaps.add(resultMap);
        }

        return resultMaps;
    }

}
