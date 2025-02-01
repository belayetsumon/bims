/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.psychology;

import itgarden.model.rehabilitations.R_OtChild;
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
public class R_OtChildService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> childOtCompletedListReport(
            String startDate,
            String endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_OtChild> root = cq.from(R_OtChild.class);
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
        // Use multiselect to select all fields with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("childMasterCode").alias("childMasterCode"),
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

        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("id")));

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
