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
import sppbims.model.homevisit.Cooking_Fuel_Type;
import sppbims.model.homevisit.Drinking_Water_Source;
import sppbims.model.homevisit.Leisure_facilities;
import sppbims.model.homevisit.Light_Source_type;
import sppbims.model.homevisit.M_Lifestyle;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Personal_Habits;
import sppbims.model.homevisit.Toilet_Type;
import sppbims.model.homevisit.Water_Source;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_LifestyleService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> getLifestyleInfoList(
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Lifestyle> root = cq.from(M_Lifestyle.class);

        // Joins (assuming all related entities have a `name` field)
        Join<M_Lifestyle, MotherMasterData> motherJoin = root.join("motherMasterCode", JoinType.INNER);
        Join<M_Lifestyle, Water_Source> waterSourceJoin = root.join("waterSource", JoinType.INNER);
        Join<M_Lifestyle, Drinking_Water_Source> drinkingWaterJoin = root.join("drinkingWaterSource", JoinType.INNER);
        Join<M_Lifestyle, Toilet_Type> toiletJoin = root.join("toilet_type", JoinType.INNER);
        Join<M_Lifestyle, Cooking_Fuel_Type> cookingFuelJoin = root.join("cookingFuel", JoinType.INNER);
        Join<M_Lifestyle, Light_Source_type> lightJoin = root.join("lightSource", JoinType.INNER);
        Join<M_Lifestyle, Leisure_facilities> leisureJoin = root.join("leisurFacilities", JoinType.INNER);
        Join<M_Lifestyle, Personal_Habits> habitsJoin = root.join("personalHabits", JoinType.INNER);

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
                waterSourceJoin.get("name").alias("waterSource"),
                drinkingWaterJoin.get("name").alias("drinkingWaterSource"),
                toiletJoin.get("name").alias("toiletType"),
                cookingFuelJoin.get("name").alias("cookingFuel"),
                lightJoin.get("name").alias("lightSource"),
                leisureJoin.get("name").alias("leisureFacilities"),
                root.get("leisureEngagment").alias("leisureEngagement"),
                habitsJoin.get("name").alias("personalHabits"),
                root.get("craftsManShip").alias("craftsmanship"),
                root.get("remarks").alias("remarks"),
                root.get("created").alias("created"),
                root.get("updated").alias("updated")
        );
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        // Order by id DESC
        cq.orderBy(cb.desc(root.get("id")));

        // Execute query
        List<Tuple> results = em.createQuery(cq).getResultList();

        // Convert Tuple -> List<Map<String, Object>> using for-each
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
