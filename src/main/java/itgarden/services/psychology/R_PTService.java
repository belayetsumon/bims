/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.psychology;

import itgarden.model.rehabilitations.R_PT;
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
public class R_PTService {

    @PersistenceContext
    private EntityManager em;

    public List<Map<String, Object>> ptcomplete_mother_list(
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_PT> root = cq.from(R_PT.class);

        // Build the select clause with multiselect and aliases for all fields
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("therapeuticSessionDate").alias("therapeuticSessionDate"),
                root.get("pain").alias("pain"),
                root.get("painNote").alias("painNote"),
                root.get("Tenderness").alias("Tenderness"),
                root.get("PhysicalDisability").alias("PhysicalDisability"),
                root.get("physicalDisabilityNote").alias("physicalDisabilityNote"),
                root.get("jointMobility").alias("jointMobility"),
                root.get("musculoskeletal").alias("musculoskeletal"),
                root.get("degenerativeDiseases").alias("degenerativeDiseases"),
                root.get("previouslyTherapyTaken").alias("previouslyTherapyTaken"),
                root.get("previouslyTherapyTakenNote").alias("previouslyTherapyTakenNote"),
                root.get("remark").alias("remark"),
                root.get("created").alias("created"),
                root.get("createdBy").alias("createdBy"),
                root.get("updated").alias("updated"),
                root.get("updatedBy").alias("updatedBy")
        );

        // Build the where clause with optional date conditions
        List<Predicate> predicates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("therapeuticSessionDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("therapeuticSessionDate"), start));
        }

        // Execute query and get the result list of Tuple
        List<Tuple> tuples = em.createQuery(cq).getResultList();

        // Convert Tuple list to List of Maps using for-each loop
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            map.put("motherName", tuple.get("motherName"));
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
            map.put("createdBy", tuple.get("createdBy"));
            map.put("updated", tuple.get("updated"));
            map.put("updatedBy", tuple.get("updatedBy"));
            result.add(map);
        }

        return result;
    }

}
