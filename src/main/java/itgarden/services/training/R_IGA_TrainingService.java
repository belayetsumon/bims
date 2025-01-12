/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.training;

import itgarden.model.rehabilitations.R_IGA_Training;
import itgarden.model.rehabilitations.TrainingName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class R_IGA_TrainingService {
     @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> igaSkillTrainingsList() {
        // Create a CriteriaBuilder instance from the EntityManager
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        // Define the root entity of the query
        Root<R_IGA_Training> root = cq.from(R_IGA_Training.class);

        // Select the fields you want to retrieve and alias them
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("name").alias("name"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"), // assuming `code` is a property in `MotherMasterData`
                root.get("trainingName").get("name").alias("trainingName"), // assuming `name` is a property in `TrainingName`
                root.get("prerequisiteSkill").alias("prerequisiteSkill"),
                root.get("location").alias("location"),
                root.get("startDate").alias("startDate"),
                root.get("endDate").alias("endDate"),
                root.get("extentionDate").alias("extentionDate"),
                root.get("conductedBy").alias("conductedBy")
        );

        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> resultList = em.createQuery(cq).getResultList();

        List<Map<String, Object>> resultMaps = new ArrayList<>();

        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id", Long.class));
            resultMap.put("name", tuple.get("name", String.class));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode", String.class)); // Assuming it's a string representation
            resultMap.put("trainingName", tuple.get("trainingName", String.class)); // Assuming it's a string representation
            resultMap.put("prerequisiteSkill", tuple.get("prerequisiteSkill", String.class));
            resultMap.put("location", tuple.get("location", String.class));
            resultMap.put("startDate", tuple.get("startDate", LocalDate.class));
            resultMap.put("endDate", tuple.get("endDate", LocalDate.class));
            resultMap.put("extentionDate", tuple.get("extentionDate", LocalDate.class));
            resultMap.put("conductedBy", tuple.get("conductedBy", String.class));
            resultMaps.add(resultMap);
        }
        return resultMaps;
    }

    public List<Map<String, Object>> iga_training_report_by_skill(
            String motherMasterCode,
            TrainingName trainingName,
            String startDate,
            String endDate
    ) {
        // Create a CriteriaBuilder instance from the EntityManager
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        // Define the root entity of the query
        Root<R_IGA_Training> root = cq.from(R_IGA_Training.class);

        List<Predicate> predicates = new ArrayList<>();
        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("startDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("startDate"), start));
        }

        if (StringUtils.isNoneEmpty(motherMasterCode)) {
            predicates.add(cb.equal(root.get("motherMasterCode"), motherMasterCode));
        }

        if (ObjectUtils.isNotEmpty(trainingName)) {
            predicates.add(cb.equal(root.get("trainingName"), trainingName));
        }

        // Select the fields you want to retrieve and alias them
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("name").alias("name"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"), // assuming `code` is a property in `MotherMasterData`
                root.get("trainingName").get("name").alias("trainingName"), // assuming `name` is a property in `TrainingName`
                root.get("prerequisiteSkill").alias("prerequisiteSkill"),
                root.get("location").alias("location"),
                root.get("startDate").alias("startDate"),
                root.get("endDate").alias("endDate"),
                root.get("extentionDate").alias("extentionDate"),
                root.get("conductedBy").alias("conductedBy")
        );

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> resultList = em.createQuery(cq).getResultList();

        List<Map<String, Object>> resultMaps = new ArrayList<>();

        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id", Long.class));
            resultMap.put("name", tuple.get("name", String.class));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode", String.class)); // Assuming it's a string representation
            resultMap.put("trainingName", tuple.get("trainingName", String.class)); // Assuming it's a string representation
            resultMap.put("prerequisiteSkill", tuple.get("prerequisiteSkill", String.class));
            resultMap.put("location", tuple.get("location", String.class));
            resultMap.put("startDate", tuple.get("startDate", LocalDate.class));
            resultMap.put("endDate", tuple.get("endDate", LocalDate.class));
            resultMap.put("extentionDate", tuple.get("extentionDate", LocalDate.class));
            resultMap.put("conductedBy", tuple.get("conductedBy", String.class));
            resultMaps.add(resultMap);
        }
        return resultMaps;
    }

    public List<Map<String, Object>> iga_training_report_by_mother(
            String motherMasterCode,
            TrainingName trainingName,
            String startDate,
            String endDate
    ) {
        // Create a CriteriaBuilder instance from the EntityManager
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        // Define the root entity of the query
        Root<R_IGA_Training> root = cq.from(R_IGA_Training.class);

        List<Predicate> predicates = new ArrayList<>();
        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("startDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("startDate"), start));
        }

        if (StringUtils.isNotEmpty(motherMasterCode)) {
            predicates.add(cb.like(root.get("motherMasterCode"), motherMasterCode));
        }

        if (ObjectUtils.isNotEmpty(trainingName)) {
            predicates.add(cb.equal(root.get("trainingName"), trainingName));
        }

        Expression<Long> totalTraining = cb.count(root.get("motherMasterCode").get("id"));

        // Select the fields you want to retrieve and alias them
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("name").alias("name"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"), // assuming `code` is a property in `MotherMasterData`
                root.get("trainingName").get("name").alias("trainingName"),
                totalTraining.alias("totalTraining")
        );

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        cq.groupBy(root.get("motherMasterCode").get("id"));

        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> resultList = em.createQuery(cq).getResultList();

        List<Map<String, Object>> resultMaps = new ArrayList<>();

        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id", Long.class));
            resultMap.put("name", tuple.get("name", String.class));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode", String.class)); // Assuming it's a string representation
            resultMap.put("totalTraining", tuple.get("totalTraining", Long.class));
            resultMaps.add(resultMap);
        }
        return resultMaps;
    }

}
