/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.cmc;

import itgarden.model.auth.Users;
import itgarden.model.rehabilitations.FoodByHouse;
import itgarden.model.rehabilitations.R_Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
public class R_FoodService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> getFoodData() {
        // EntityManager setup
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // Create a CriteriaQuery object
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

        // Root for the entity
        Root<R_Food> root = criteriaQuery.from(R_Food.class);

        // Create aliases for fields
        Path<Long> idPath = root.get("id");
        Path<LocalDate> todayDatePath = root.get("todaydate");
        Path<FoodByHouse> foodByHousePath = root.get("foodByHouse");
        Path<Integer> totalPersonsPath = root.get("totalPersons");
        Path<Integer> sickPath = root.get("sick");
        Path<Integer> onLeavePath = root.get("onLeave");
        Path<Integer> underObservationPath = root.get("underObservation");
        Path<Integer> onJobPath = root.get("onJob");
        Path<Integer> othersPath = root.get("others");
//        Path<LocalDate> createdPath = root.get("created");
//        Path<Users> createdByPath = root.get("createdBy");
//        Path<Date> updatedPath = root.get("updated");
//        Path<Users> updatedByPath = root.get("updatedBy");

        // Sum all fields together using a list of expressions
        Expression<Integer> totalAbsent = cb.sum(
                cb.sum(
                        cb.sum(
                                cb.sum(sickPath, onLeavePath),
                                underObservationPath
                        ),
                        onJobPath
                ),
                othersPath
        );

        Expression<Integer> totalpresent = cb.diff(totalPersonsPath, totalAbsent);

        // Use CriteriaBuilder to create the selection (multiselect)
        criteriaQuery.multiselect(
                idPath.alias("id"),
                todayDatePath.alias("todaydate"),
                foodByHousePath.alias("foodByHouse"),
                totalPersonsPath.alias("totalPersons"),
                sickPath.alias("sick"),
                onLeavePath.alias("onLeave"),
                underObservationPath.alias("underObservation"),
                onJobPath.alias("onJob"),
                othersPath.alias("others"),
                totalpresent.alias("totalPresent")
        //                createdPath.alias("created"),
        //                createdByPath.alias("createdBy"),
        //                updatedPath.alias("updated"),
        //                updatedByPath.alias("updatedBy")
        );

        // Order by id
        criteriaQuery.orderBy(cb.desc(idPath));

        // Execute the query and get the result as a list of tuples
        List<Tuple> resultList = em.createQuery(criteriaQuery).getResultList();

        // Transform the result list of Tuples into a List<Map<String, Object>>
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id"));
            resultMap.put("todaydate", tuple.get("todaydate"));
            resultMap.put("foodByHouse", tuple.get("foodByHouse"));
            resultMap.put("totalPersons", tuple.get("totalPersons"));
            resultMap.put("sick", tuple.get("sick"));
            resultMap.put("onLeave", tuple.get("onLeave"));
            resultMap.put("underObservation", tuple.get("underObservation"));
            resultMap.put("onJob", tuple.get("onJob"));
            resultMap.put("others", tuple.get("others"));
            resultMap.put("totalPresent", tuple.get("totalPresent"));
//            resultMap.put("created", tuple.get("created"));
//            resultMap.put("createdBy", tuple.get("createdBy"));
//            resultMap.put("updated", tuple.get("updated"));
//            resultMap.put("updatedBy", tuple.get("updatedBy"));
            resultMapList.add(resultMap);
        }

        return resultMapList;
    }

    public List<Map<String, Object>> get_All_FoodData_Report_By_filter(
            String startDate,
            String endDate
            ) {
        // EntityManager setup
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // Create a CriteriaQuery object
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

        // Root for the entity
        Root<R_Food> root = criteriaQuery.from(R_Food.class);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("todaydate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("todaydate"), start));
        }

        // Create aliases for fields
        Path<Long> idPath = root.get("id");
        Path<LocalDate> todayDatePath = root.get("todaydate");
        Path<FoodByHouse> foodByHousePath = root.get("foodByHouse");
        Path<Integer> totalPersonsPath = root.get("totalPersons");
        Path<Integer> sickPath = root.get("sick");
        Path<Integer> onLeavePath = root.get("onLeave");
        Path<Integer> underObservationPath = root.get("underObservation");
        Path<Integer> onJobPath = root.get("onJob");
        Path<Integer> othersPath = root.get("others");
//        Path<LocalDate> createdPath = root.get("created");
//        Path<Users> createdByPath = root.get("createdBy");
//        Path<Date> updatedPath = root.get("updated");
//        Path<Users> updatedByPath = root.get("updatedBy");

        // Sum all fields together using a list of expressions
        Expression<Integer> totalAbsent = cb.sum(
                cb.sum(
                        cb.sum(
                                cb.sum(sickPath, onLeavePath),
                                underObservationPath
                        ),
                        onJobPath
                ),
                othersPath
        );

        Expression<Integer> totalpresent = cb.diff(totalPersonsPath, totalAbsent);

        // Use CriteriaBuilder to create the selection (multiselect)
        criteriaQuery.multiselect(
                idPath.alias("id"),
                todayDatePath.alias("todaydate"),
                foodByHousePath.alias("foodByHouse"),
                totalPersonsPath.alias("totalPersons"),
                sickPath.alias("sick"),
                onLeavePath.alias("onLeave"),
                underObservationPath.alias("underObservation"),
                onJobPath.alias("onJob"),
                othersPath.alias("others"),
                totalpresent.alias("totalPresent")
        //                createdPath.alias("created"),
        //                createdByPath.alias("createdBy"),
        //                updatedPath.alias("updated"),
        //                updatedByPath.alias("updatedBy")
        );

        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));

        // Order by id
        criteriaQuery.orderBy(cb.desc(idPath));

        // Execute the query and get the result as a list of tuples
        List<Tuple> resultList = em.createQuery(criteriaQuery).getResultList();

        // Transform the result list of Tuples into a List<Map<String, Object>>
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id"));
            resultMap.put("todaydate", tuple.get("todaydate"));
            resultMap.put("foodByHouse", tuple.get("foodByHouse"));
            resultMap.put("totalPersons", tuple.get("totalPersons"));
            resultMap.put("sick", tuple.get("sick"));
            resultMap.put("onLeave", tuple.get("onLeave"));
            resultMap.put("underObservation", tuple.get("underObservation"));
            resultMap.put("onJob", tuple.get("onJob"));
            resultMap.put("others", tuple.get("others"));
            resultMap.put("totalPresent", tuple.get("totalPresent"));
//            resultMap.put("created", tuple.get("created"));
//            resultMap.put("createdBy", tuple.get("createdBy"));
//            resultMap.put("updated", tuple.get("updated"));
//            resultMap.put("updatedBy", tuple.get("updatedBy"));
            resultMapList.add(resultMap);
        }

        return resultMapList;
    }

}
