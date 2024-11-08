/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.observation;

import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.MotherImage;
import itgarden.model.observation.O_MAddmission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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
public class O_MAddmissionService {

    @PersistenceContext
    EntityManager em;

    public List<MotherMasterDataDTO> getMotherMasterDataDTOs() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        List<Long> admitedMotherList = addmissionMotherIdList();

        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        // Select fields from MotherMasterData and related entities
        cq.multiselect(
                root.get("id"),
                root.get("motherMasterCode"),
                root.get("motherName")
        // Converting Enum to String
        );
        List<Predicate> predicates = new ArrayList<Predicate>();
        // Define the predicates for the conditions

        predicates.add(root.get("id").in(admitedMotherList));

        cq.where(predicates.toArray(new Predicate[]{}));
        // Create the query
        TypedQuery<Tuple> query = em.createQuery(cq);

        // Execute the query and map results to DTO
        List<MotherMasterDataDTO> result = query.getResultList().stream().map(tuple -> {
            MotherMasterDataDTO dto = new MotherMasterDataDTO();
            dto.setId(tuple.get(0, Long.class));
            dto.setMotherMasterCode(tuple.get(1, String.class));
            dto.setMotherName(tuple.get(2, String.class));

            // Enum converted to String
            return dto;
        }).toList();

        return result;
    }

    public List<Long> addmissionMotherIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<O_MAddmission> root = cq.from(O_MAddmission.class);

        cq.multiselect(root.get("motherMasterCode").get("id"));

//        List<Predicate> predicates = new ArrayList<Predicate>();
//
//      
//        predicates.add(cb.between(rootSm.get("starttime"), thirtyMinutesAgo, currentTime));
//
//        cq.where(predicates.toArray(new Predicate[]{}));
        cq.orderBy(cb.desc(root.get("motherMasterCode").get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get(0, Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> allAdmitedMotherPeriodicReportList(
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_MAddmission> root = cq.from(O_MAddmission.class);
        List<Predicate> predicates = new ArrayList<>();

        // Define the date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("dateAdmission"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("dateAdmission"), start));
        }

        // Apply gender condition if gender is not null
//        if (gender != null) {
//            predicates.add(cb.equal(root.get("childMasterCode").get("gender"), gender));
//        }
        cq.multiselect(
                root.get("id").alias("admissionId"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),// Assuming 'code' exists in MotherMasterData
                root.get("motherMasterCode").get("mobileNumber").alias("mobileNumber"),// Assuming 'code' exists in MotherMasterData
                root.get("dateArrival").alias("arrivalDate"),
                root.get("dateAdmission").alias("admissionDate"),
                root.get("remarks").alias("remarks")
        );

        // Apply the where clause with the combined predicates
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sort the results by ID in descending order
        cq.orderBy(cb.desc(root.get("id")));
        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the result into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple result : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("admissionId", result.get("admissionId"));
            resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", result.get("motherMasterCode"));
            resultMap.put("motherName", result.get("motherName"));
            resultMap.put("mobileNumber", result.get("mobileNumber"));
            resultMap.put("arrivalDate", result.get("arrivalDate"));
            resultMap.put("admissionDate", result.get("admissionDate"));
            resultMap.put("remarks", result.get("remarks"));
            resultMaps.add(resultMap);
        }
        return resultMaps;
    }

    public List<Map<String, Object>> allAdmitedMotherList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_MAddmission> root = cq.from(O_MAddmission.class);

        // Selecting multiple fields with aliases
        cq.multiselect(
                root.get("id").alias("admissionId"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),// Assuming 'code' exists in MotherMasterData
                root.get("motherMasterCode").get("mobileNumber").alias("mobileNumber"),
                root.get("dateArrival").alias("arrivalDate"),
                root.get("dateAdmission").alias("admissionDate"),
                root.get("remarks").alias("remarks")
        );
        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the result into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple result : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("admissionId", result.get("admissionId"));
            resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", result.get("motherMasterCode"));
            resultMap.put("motherName", result.get("motherName"));
            resultMap.put("mobileNumber", result.get("mobileNumber"));
            resultMap.put("arrivalDate", result.get("arrivalDate"));
            resultMap.put("admissionDate", result.get("admissionDate"));
            resultMap.put("remarks", result.get("remarks"));
            resultMaps.add(resultMap);
        }
        return resultMaps;
    }

// mother Image null
    public List<Map<String, Object>> findMotherImageNull() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<O_MAddmission> root = cq.from(O_MAddmission.class);

        // Left join with MotherImage
        Join<O_MAddmission, MotherImage> motherImageJoin = root.join("motherImage", JoinType.LEFT);

        // Adding a condition to filter where motherImage is null
        Predicate condition = cb.isNull(motherImageJoin);

        // Selecting multiple fields with aliases
        cq.multiselect(
                root.get("id").alias("admissionId"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("mMothersName").alias("mMothersName"),
                root.get("motherMasterCode").get("mobileNumber").alias("mobileNumber"),
                root.get("dateAdmission").alias("dateAdmission")
        ).where(condition);  // Applying the condition

        // Execute the query
        List<Tuple> resultList = em.createQuery(cq).getResultList();

        // Transform the result into a list of maps using aliases
        List<Map<String, Object>> resultMaps = new ArrayList<>();
        for (Tuple result : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("admissionId", result.get("admissionId"));
            resultMap.put("motherMasterCodeId", result.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", result.get("motherMasterCode"));
            resultMap.put("mMothersName", result.get("mMothersName"));
            resultMap.put("mobileNumber", result.get("mobileNumber"));
            resultMap.put("dateAdmission", result.get("dateAdmission"));

            resultMaps.add(resultMap);
        }
        return resultMaps;
    }

}
