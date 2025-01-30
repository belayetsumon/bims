/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.school;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.school.Discontinuity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
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
public class DiscontinuityService {

    @PersistenceContext
    EntityManager em;

    public List<Long> discontinuityChildIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<Discontinuity> root = cq.from(Discontinuity.class);

        cq.multiselect(root.get("childMasterCode").get("id"));

        cq.orderBy(cb.desc(root.get("childMasterCode").get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get(0, Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> allDiscontinuitiesList(
         
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        Root<Discontinuity> discontinuity = query.from(Discontinuity.class);

        // Create aliases for fields
        Path<Long> id = discontinuity.get("id");
        Path<String> discontinuityReason = discontinuity.get("discontinuityReason");
        Path<LocalDate> dateDismissal = discontinuity.get("dateDismissal");
        Path<String> remark = discontinuity.get("remark");
        Path<M_Child_info> childMasterCode = discontinuity.get("childMasterCode");
        Path<M_Child_info> childMasterCodeid = discontinuity.get("childMasterCode").get("id");

        

        // Build the query (multi-select)
        query.multiselect(
                id.alias("id"),
                discontinuityReason.alias("discontinuityReason"),
                dateDismissal.alias("dateDismissal"),
                remark.alias("remark"),
                childMasterCode.get("childMasterCode").alias("childMasterCode"),
                childMasterCodeid.alias("childMasterCodeId")// assuming you want childMasterCode.id
        );

        // Order by id in descending order
        query.orderBy(cb.desc(id));

        // Execute the query and collect the results
        List<Tuple> result = em.createQuery(query).getResultList();

        // Map the result (each Tuple) to a map using the alias as key
        // Create a List of Maps using a forEach loop
        List<Map<String, Object>> resultList = new ArrayList<>();
        result.forEach(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id", Long.class));
            map.put("discontinuityReason", tuple.get("discontinuityReason", String.class));
            map.put("dateDismissal", tuple.get("dateDismissal", LocalDate.class));
            map.put("remark", tuple.get("remark", String.class));
            map.put("childMasterCode", tuple.get("childMasterCode", String.class));
            map.put("childMasterCodeId", tuple.get("childMasterCodeId", Long.class));
            resultList.add(map);
        });

        return resultList;
    }
    
    
    public List<Map<String, Object>> allDiscontinuitiesListReport(
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);
        Root<Discontinuity> discontinuity = query.from(Discontinuity.class);

        // Create aliases for fields
        Path<Long> id = discontinuity.get("id");
        Path<String> discontinuityReason = discontinuity.get("discontinuityReason");
        Path<LocalDate> dateDismissal = discontinuity.get("dateDismissal");
        Path<String> remark = discontinuity.get("remark");
        Path<M_Child_info> childMasterCode = discontinuity.get("childMasterCode");
        Path<M_Child_info> childMasterCodeid = discontinuity.get("childMasterCode").get("id");

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(discontinuity.get("dateDismissal"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(discontinuity.get("dateDismissal"), start));
        }

        // Build the query (multi-select)
        query.multiselect(
                id.alias("id"),
                discontinuityReason.alias("discontinuityReason"),
                dateDismissal.alias("dateDismissal"),
                remark.alias("remark"),
                childMasterCode.get("childMasterCode").alias("childMasterCode"),
                childMasterCodeid.alias("childMasterCodeId")// assuming you want childMasterCode.id
        );

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // Order by id in descending order
        query.orderBy(cb.desc(id));

        // Execute the query and collect the results
        List<Tuple> result = em.createQuery(query).getResultList();

        // Map the result (each Tuple) to a map using the alias as key
        // Create a List of Maps using a forEach loop
        List<Map<String, Object>> resultList = new ArrayList<>();
        result.forEach(tuple -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id", Long.class));
            map.put("discontinuityReason", tuple.get("discontinuityReason", String.class));
            map.put("dateDismissal", tuple.get("dateDismissal", LocalDate.class));
            map.put("remark", tuple.get("remark", String.class));
            map.put("childMasterCode", tuple.get("childMasterCode", String.class));
            map.put("childMasterCodeId", tuple.get("childMasterCodeId", Long.class));
            resultList.add(map);
        });

        return resultList;
    }


}
