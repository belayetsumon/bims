/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.observation;

import itgarden.model.observation.O_Induction;
import itgarden.model.observation.O_MHealthConditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class O_InductionService {

    @PersistenceContext
    EntityManager em;

    public List<Long> induction_complete_mother_Id_List() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<O_Induction> root = cq.from(O_Induction.class);

        cq.multiselect(root.get("motherMasterCode").get("id").alias("motherMasterCodeId"));

        cq.orderBy(cb.desc(root.get("motherMasterCode").get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get("motherMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> getAllInductions() {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Define the root of the query
        Root<O_Induction> root = criteriaQuery.from(O_Induction.class);

        // Multiselect to select the fields from O_Induction
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("startDate").alias("startDate"),
                root.get("endDate").alias("endDate"),
                root.get("immediateSupportOn").alias("immediateSupportOn"),
                root.get("challagesOfCandidare").alias("challagesOfCandidare"),
                root.get("possibleLength").alias("possibleLength"),
                root.get("remark").alias("remark")
        );

        // Order by id descending
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

        // Execute the query and get results
        List<Tuple> resultList = em.createQuery(criteriaQuery).getResultList();

        // Convert Tuple result to List of Maps
        return resultList.stream().map(tuple -> {
            return Map.of(
                    "id", tuple.get("id"),
                    "motherMasterCode", tuple.get("motherMasterCode"),
                    "startDate", tuple.get("startDate"),
                    "endDate", tuple.get("endDate"),
                    "immediateSupportOn", tuple.get("immediateSupportOn"),
                    "challagesOfCandidare", tuple.get("challagesOfCandidare"),
                    "possibleLength", tuple.get("possibleLength"),
                    "remark", tuple.get("remark")
            );
        }).collect(Collectors.toList());
    }
}
