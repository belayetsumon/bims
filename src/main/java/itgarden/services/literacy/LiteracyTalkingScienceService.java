/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.literacy;

import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.literacy.LiteracyTalkingScience;
import itgarden.model.literacy.ResultEnum;
import itgarden.services.observation.O_MAddmissionService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class LiteracyTalkingScienceService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    O_MAddmissionService addmissionService;

    public List<MotherMasterDataDTO> getMotherMasterDataDTOs() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        List<Long> admitedMotherList = addmissionService.addmissionMotherIdList();

        List<Long> talkingScienceMotherIdList = talkingScienceMotherIdList();

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

        if (!talkingScienceMotherIdList.isEmpty()) {
            predicates.add(cb.not(root.get("id").in(talkingScienceMotherIdList)));
        }
        cq.where(predicates.toArray(new Predicate[]{}));

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

    public List<Long> talkingScienceMotherIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<LiteracyTalkingScience> root = cq.from(LiteracyTalkingScience.class);

        cq.multiselect(root.get("motherMasterCode").get("id"));

        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get(0, Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> getLiteracyTalkingScienceData() {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root for the LiteracyTalkingScience entity
        Root<LiteracyTalkingScience> literacyRoot = cq.from(LiteracyTalkingScience.class);

        // Alias for columns
        Join<LiteracyTalkingScience, MotherMasterData> motherMasterDataJoin = literacyRoot.join("motherMasterCode", JoinType.INNER);

        // Selecting multiple fields
        cq.multiselect(
                literacyRoot.get("id").alias("id"),
                literacyRoot.get("admissionDate").alias("admissionDate"),
                literacyRoot.get("endDate").alias("endDate"),
                literacyRoot.get("result").alias("result"),
                literacyRoot.get("remark").alias("remark"),
                motherMasterDataJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterDataJoin.get("id").alias("motherMasterCodeId")// Assuming some field exists in the MotherMasterData entity
        );

        // Applying where conditions (if needed, e.g., filtering by some condition)
        // cq.where(cb.equal(literacyRoot.get("someField"), someValue));
        // Order by id in descending order
        cq.orderBy(cb.desc(literacyRoot.get("id")));

        // Execute the query
        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> results = query.getResultList();

        // Convert Tuple results into List of Maps
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : results) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id"));
            resultMap.put("admissionDate", tuple.get("admissionDate"));
            resultMap.put("endDate", tuple.get("endDate"));
            resultMap.put("result", tuple.get("result"));
            resultMap.put("remark", tuple.get("remark"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultList.add(resultMap);
        }

        return resultList;

    }

    public List<Map<String, Object>> getLiteracyTalkingScienceDataReport(
            String startDate,
            String endDate,
            ResultEnum result
    ) {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root for the LiteracyTalkingScience entity
        Root<LiteracyTalkingScience> literacyRoot = cq.from(LiteracyTalkingScience.class);

        // Alias for columns
        Join<LiteracyTalkingScience, MotherMasterData> motherMasterDataJoin = literacyRoot.join("motherMasterCode", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(literacyRoot.get("admissionDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(literacyRoot.get("admissionDate"), start));
        }

        if (ObjectUtils.isNotEmpty(result)) {
            predicates.add(cb.equal(literacyRoot.get("result"), result));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        // Selecting multiple fields
        cq.multiselect(
                literacyRoot.get("id").alias("id"),
                literacyRoot.get("admissionDate").alias("admissionDate"),
                literacyRoot.get("endDate").alias("endDate"),
                literacyRoot.get("result").alias("result"),
                literacyRoot.get("remark").alias("remark"),
                motherMasterDataJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterDataJoin.get("id").alias("motherMasterCodeId") // Assuming some field exists in the MotherMasterData entity
        );

        // Applying where conditions (if needed, e.g., filtering by some condition)
        // cq.where(cb.equal(literacyRoot.get("someField"), someValue));
        // Order by id in descending order
        cq.orderBy(cb.desc(literacyRoot.get("id")));

        // Execute the query
        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> results = query.getResultList();

        // Convert Tuple results into List of Maps
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : results) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id"));
            resultMap.put("admissionDate", tuple.get("admissionDate"));
            resultMap.put("endDate", tuple.get("endDate"));
            resultMap.put("result", tuple.get("result"));
            resultMap.put("remark", tuple.get("remark"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultList.add(resultMap);
        }
        return resultList;

    }

}
