/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.homevisit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import sppbims.model.homevisit.Economic_Type;
import sppbims.model.homevisit.M_Community_Information;
import sppbims.model.homevisit.MotherMasterData;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_Community_InformationService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Map<String, Object>> getCommunityInfoList(
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Community_Information> root = cq.from(M_Community_Information.class);

        // Join if needed (example for motherMasterCode and economyType)
        Join<M_Community_Information, MotherMasterData> motherJoin = root.join("motherMasterCode", JoinType.INNER);
        Join<M_Community_Information, Economic_Type> economyJoin = root.join("economyType", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("created"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("created"), start));
        }

        // Multi-select with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                motherJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherJoin.get("motherName").alias("motherName"),
                economyJoin.get("name").alias("economyTypeName"),
                root.get("avilableIga").alias("avilableIga"),
                root.get("avilableIgaNote").alias("avilableIgaNote"),
                root.get("prospectiveIga").alias("prospectiveIga"),
                root.get("prospectiveIgaNote").alias("prospectiveIgaNote"),
                root.get("unemploymentSession").alias("unemploymentSession"),
                root.get("REMARKS").alias("remarks"),
                root.get("created").alias("created"),
                root.get("updated").alias("updated")
        );

        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }

        // Order by id DESC
        cq.orderBy(cb.desc(root.get("id")));
        List<Tuple> results = entityManager.createQuery(cq).getResultList();
        List<Map<String, Object>> mappedList = new ArrayList<>();

        for (Tuple tuple : results) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (TupleElement<?> element : tuple.getElements()) {
                String alias = element.getAlias();
                Object value = tuple.get(element);
                map.put(alias, value);
            }
            mappedList.add(map);
        }

        return mappedList;
    }

}
