/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.homevisit;

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
import sppbims.model.homevisit.M_Accessibility;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_AccessibilityService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> findAllWithDateFilter(
            String startDate,
            String endDate
    ) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Accessibility> root = cq.from(M_Accessibility.class);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("created"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("created"), start));
        }

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("roadType").get("name").alias("roadType"), // Assuming 'name' field in Road_Type
                root.get("transportType").get("name").alias("transportType"), // Assuming 'name' in Transport_Type

                root.get("distanceToMainRoad").alias("distanceToMainRoad"),
                root.get("primarySchool").alias("primarySchool"),
                root.get("primarySchoolDistance").alias("primarySchoolDistance"),
                root.get("secondarySchool").alias("secondarySchool"),
                root.get("secondarySchoolDistance").alias("secondarySchoolDistance"),
                root.get("hospital").alias("hospital"),
                root.get("hospitalDistance").alias("hospitalDistance"),
                root.get("marketPlace").alias("marketPlace"),
                root.get("marketPlaceDistance").alias("marketPlaceDistance"),
                root.get("bank").alias("bank"),
                root.get("bankDistance").alias("bankDistance"),
                root.get("ngo").alias("ngo"),
                root.get("ngoDistance").alias("ngoDistance"),
                root.get("wellfareInstitutions").alias("wellfareInstitutions"),
                root.get("wellfareInstitutionsDistance").alias("wellfareInstitutionsDistance"),
                root.get("technicalInstiute").alias("technicalInstiute"),
                root.get("technicalInstiuteDistance").alias("technicalInstiuteDistance"),
                root.get("REMARKS").alias("REMARKS"),
                root.get("created").alias("created"),
                root.get("updated").alias("updated")
        );

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> resultList = em.createQuery(cq).getResultList();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Tuple tuple : resultList) {
            Map<String, Object> row = new HashMap<>();

            row.put("id", tuple.get("id"));
            row.put("motherMasterCode", tuple.get("motherMasterCode"));
            row.put("roadType", tuple.get("roadType"));
            row.put("transportType", tuple.get("transportType"));
            row.put("distanceToMainRoad", tuple.get("distanceToMainRoad"));
            row.put("primarySchool", tuple.get("primarySchool"));
            row.put("primarySchoolDistance", tuple.get("primarySchoolDistance"));
            row.put("secondarySchool", tuple.get("secondarySchool"));
            row.put("secondarySchoolDistance", tuple.get("secondarySchoolDistance"));
            row.put("hospital", tuple.get("hospital"));
            row.put("hospitalDistance", tuple.get("hospitalDistance"));
            row.put("marketPlace", tuple.get("marketPlace"));
            row.put("marketPlaceDistance", tuple.get("marketPlaceDistance"));
            row.put("bank", tuple.get("bank"));
            row.put("bankDistance", tuple.get("bankDistance"));
            row.put("ngo", tuple.get("ngo"));
            row.put("ngoDistance", tuple.get("ngoDistance"));
            row.put("wellfareInstitutions", tuple.get("wellfareInstitutions"));
            row.put("wellfareInstitutionsDistance", tuple.get("wellfareInstitutionsDistance"));
            row.put("technicalInstiute", tuple.get("technicalInstiute"));
            row.put("technicalInstiuteDistance", tuple.get("technicalInstiuteDistance"));
            row.put("REMARKS", tuple.get("REMARKS"));
            row.put("created", tuple.get("created"));
            row.put("updated", tuple.get("updated"));

            result.add(row);
        }

        return result;
    }

}
