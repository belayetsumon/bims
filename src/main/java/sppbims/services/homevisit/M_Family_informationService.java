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
import sppbims.model.homevisit.M_Family_information;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Occupation;
import sppbims.model.homevisit.Relations;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_Family_informationService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> getFamilyInfoList(
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Family_information> root = cq.from(M_Family_information.class);

        // Joins
        Join<M_Family_information, MotherMasterData> motherJoin = root.join("motherMasterCode", JoinType.INNER);
        Join<M_Family_information, Occupation> occupationJoin = root.join("occopation", JoinType.INNER);
        Join<M_Family_information, Relations> relationJoin = root.join("relation", JoinType.INNER);

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

        // Select with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                motherJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherJoin.get("motherName").alias("motherName"),
                root.get("name").alias("name"),
                root.get("dateofBirth").alias("dob"),
                root.get("sex").alias("sex"),
                occupationJoin.get("name").alias("occupationName"),
                relationJoin.get("name").alias("relationName"),
                root.get("cellNo").alias("cellNo"),
                root.get("remarks").alias("remarks"),
                root.get("created").alias("created"),
                root.get("updated").alias("updated")
        );

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        // Order by ID descending
        cq.orderBy(cb.desc(root.get("id")));

        // Execute
        List<Tuple> results = em.createQuery(cq).getResultList();

        // Convert to List<Map<String, Object>> using for-each
        List<Map<String, Object>> mappedList = new ArrayList<>();
        for (Tuple tuple : results) {
            Map<String, Object> map = new LinkedHashMap<>();
            for (TupleElement<?> element : tuple.getElements()) {
                map.put(element.getAlias(), tuple.get(element));
            }
            mappedList.add(map);
        }

        return mappedList;
    }

}
