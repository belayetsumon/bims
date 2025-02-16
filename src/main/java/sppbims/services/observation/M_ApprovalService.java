/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.observation;

import sppbims.model.homevisit.Decision;
import sppbims.model.homevisit.Eligibility;
import sppbims.model.homevisit.M_Approval;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
public class M_ApprovalService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> findAllBydecissionOrderByIdDesc(
            Decision decission) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> query = cb.createTupleQuery();

        // Define the root of the query
        Root<M_Approval> root = query.from(M_Approval.class);

        // Create the multiselect query with aliases
        query.multiselect(
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("motherMasterCode").get("dateOfBirth").alias("dateOfBirth"),
                root.get("motherMasterCode").get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
                root.get("motherMasterCode").get("eligibility").alias("eligibility"),
                root.get("id").alias("approvalId"),
                root.get("decission").alias("decission"),
                root.get("approveDate").alias("approveDate")
        )
                .where(cb.equal(root.get("decission"), decission))
                .orderBy(cb.desc(root.get("id")));

        // Execute the query
        List<Tuple> results = em.createQuery(query).getResultList();

        // Initialize a list to hold the maps
        List<Map<String, Object>> maps = new ArrayList<>();

        // Manual loop to populate the list of maps
        for (Tuple tuple : results) {

            Map<String, Object> map = new HashMap<>();

            map.put("motherMasterCodeId", tuple.get("motherMasterCodeId", Long.class));

            map.put("motherMasterCode", tuple.get("motherMasterCode", String.class));

            map.put("homeVisitDate", tuple.get("homeVisitDate", LocalDate.class));

            map.put("motherName", tuple.get("motherName", String.class));

            map.put("dateOfBirth", tuple.get("dateOfBirth", LocalDate.class));

            map.put("numberOfEligibleChildren", tuple.get("numberOfEligibleChildren", Integer.class));

            map.put("eligibility", tuple.get("eligibility", Eligibility.class));

            map.put("approvalId", tuple.get("approvalId", Long.class));

            map.put("decission", tuple.get("decission", Decision.class));

            map.put("approveDate", tuple.get("approveDate", LocalDate.class));

            maps.add(map);
        }

        return maps;
    }

}
