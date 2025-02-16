/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.psychology;

import sppbims.model.rehabilitations.R_OT;
import sppbims.model.rehabilitations.R_PT;
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
public class R_OtMotherService {

    @PersistenceContext
    EntityManager em;

    public List<Long> motherOtCompletedList() {
// Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

// Create CriteriaQuery for R_M_HousAllocation
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_OT> root = cq.from(R_OT.class);

// Define the selection (using tuple)
        cq.multiselect(
                //                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId")
        //                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
        //                root.get("motherMasterCode").get("motherName").alias("motherName"),// Assuming 'code' is a field in MotherMasterData
        //                root.get("houseName").get("name").alias("houseName"), // Assuming 'name' is a field in HouseName
        //                root.get("allocationDate").alias("allocationDate"),
        //                root.get("endDate").alias("endDate"),
        //                root.get("remark").alias("remark")
        );

// Add predicates to the query
        cq.orderBy(cb.desc(root.get("id")));
// Execute the query
        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();

        for (Tuple t : result) {
            Long id = t.get("motherMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> motherOtCompletedListReport(
            String startDate,
            String endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_OT> root = cq.from(R_OT.class);

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

        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

        // Use multiselect to select all fields with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("therapeuticSessionDate").alias("therapeuticSessionDate"),
                root.get("sessionType").alias("sessionType"),
                root.get("diagonosis").alias("diagonosis"),
                root.get("treatment").alias("treatment"),
                root.get("conductedBy").alias("conductedBy"),
                root.get("remarks").alias("remarks"),
                root.get("created").alias("created"),
                root.get("createdBy").alias("createdBy"),
                root.get("updated").alias("updated"),
                root.get("updatedBy").alias("updatedBy")
        );

        // Execute query and get the result list of Tuple
        List<Tuple> tuples = em.createQuery(cq).getResultList();

        // Convert Tuple list to List of Maps
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("therapeuticSessionDate", tuple.get("therapeuticSessionDate"));
            map.put("sessionType", tuple.get("sessionType"));
            map.put("diagonosis", tuple.get("diagonosis"));
            map.put("treatment", tuple.get("treatment"));
            map.put("conductedBy", tuple.get("conductedBy"));
            map.put("remarks", tuple.get("remarks"));
            map.put("created", tuple.get("created"));
            map.put("createdBy", tuple.get("createdBy"));
            map.put("updated", tuple.get("updated"));
            map.put("updatedBy", tuple.get("updatedBy"));
            result.add(map);
        }
        return result;
    }
}
