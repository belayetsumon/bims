/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.literacy;

import sppbims.model.homevisit.DTO.MotherMasterDataDTO;
import sppbims.model.homevisit.EducationLevel;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.literacy.LiteracyHigherEducationAdmission;
import sppbims.model.literacy.ResultEnum;
import sppbims.services.observation.O_MAddmissionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class LiteracyHigherEducationAdmissionService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    O_MAddmissionService addmissionService;

    public List<Map<String, Object>> getMotherMasterDataList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        List<Long> admitedMotherList = addmissionService.admitedMotherButNotReleasedIdList();

        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        // Select fields from MotherMasterData
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherName").alias("motherName")
        );

        // Define the predicates for filtering
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(root.get("id").in(admitedMotherList));
        cq.where(predicates.toArray(new Predicate[0]));

        // Execute query
        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> results = query.getResultList();

        // Convert to List<Map<String, Object>> using a traditional loop
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Tuple tuple : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id", Long.class));
            map.put("motherMasterCode", tuple.get("motherMasterCode", String.class));
            map.put("motherName", tuple.get("motherName", String.class));
            dataList.add(map);
        }
        return dataList;
    }

    public List<Long> literacyHigherEducationAdmissionMotherIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<LiteracyHigherEducationAdmission> root = cq.from(LiteracyHigherEducationAdmission.class);

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

    public List<Map<String, Object>> getAllHigherEducationAdmissionData() {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<LiteracyHigherEducationAdmission> root = criteriaQuery.from(LiteracyHigherEducationAdmission.class);

        // Create Aliases for the fields you want to select
        Expression<Long> idAlias = root.get("id");
        Expression<LocalDate> admissionDateAlias = root.get("admissionDate");
        Expression<LocalDate> endDateAlias = root.get("endDate");
        Expression<ResultEnum> resultAlias = root.get("result");
        Expression<String> remarkAlias = root.get("remark");
        Expression<String> motherMasterCodeIdAlias = root.get("motherMasterCode").get("id");
        Expression<String> motherMasterCodeAlias = root.get("motherMasterCode").get("motherMasterCode");// Assuming id for motherMasterCode
        Expression<String> motherNameAlias = root.get("motherMasterCode").get("motherName");// Assuming id for motherMasterCode
        Expression<String> lastEducationLevelAlias = root.get("lastEducationleve").get("name");  // Assuming id for EducationLevel
        Expression<String> admissionClassAlias = root.get("admissionClass").get("name");  // Assuming id for EducationLevel

        // Add the SELECT clause (multi-select)
        criteriaQuery.multiselect(
                idAlias,
                admissionDateAlias,
                endDateAlias,
                resultAlias,
                remarkAlias,
                motherMasterCodeAlias,
                motherMasterCodeIdAlias,
                motherNameAlias,
                lastEducationLevelAlias,
                admissionClassAlias
        );
        criteriaQuery.orderBy(criteriaBuilder.desc(idAlias));  // Order by id desc

        // Create the query and get the result as List of Tuples
        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();

        // Convert List<Tuple> to List<Map<String, Object>>
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("id", tuple.get(idAlias));
            rowMap.put("admissionDate", tuple.get(admissionDateAlias));
            rowMap.put("endDate", tuple.get(endDateAlias));
            rowMap.put("result", tuple.get(resultAlias));
            rowMap.put("remark", tuple.get(remarkAlias));
            rowMap.put("motherMasterCodeId", tuple.get(motherMasterCodeAlias));
            rowMap.put("motherMasterCode", tuple.get(motherMasterCodeAlias));
            rowMap.put("motherName", tuple.get(motherNameAlias));
            rowMap.put("lastEducationLevelId", tuple.get(lastEducationLevelAlias));
            rowMap.put("admissionClassId", tuple.get(admissionClassAlias));

            resultList.add(rowMap);
        }

        return resultList;
    }

    public List<Map<String, Object>> getAllHigherEducationAdmissionDataReport(
            String startDate,
            String endDate,
            ResultEnum result,
            EducationLevel admissionLevel
    ) {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<LiteracyHigherEducationAdmission> root = criteriaQuery.from(LiteracyHigherEducationAdmission.class);

        // Create Aliases for the fields you want to select
        // Assuming id for EducationLevel
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(root.get("admissionDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(root.get("admissionDate"), start));
        }

        if (ObjectUtils.isNotEmpty(result)) {
            predicates.add(criteriaBuilder.equal(root.get("result"), result));
        }

        if (ObjectUtils.isNotEmpty(admissionLevel)) {
            predicates.add(criteriaBuilder.equal(root.get("admissionClass"), admissionLevel));
        }

        // Add the SELECT clause (multi-select)
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("admissionDate").alias("admissionDate"),
                root.get("endDate").alias("endDate"),
                root.get("result").alias("result"),
                root.get("remark").alias("remark"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),// Assuming id for motherMasterCode
                root.get("lastEducationleve").get("name").alias("lastEducationlevel"), // Assuming id for EducationLevel
                root.get("admissionClass").get("name").alias("admissionClass")
        );

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));  // Order by id desc

        // Create the query and get the result as List of Tuples
        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();

        // Convert List<Tuple> to List<Map<String, Object>>
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("id", tuple.get("id"));
            rowMap.put("admissionDate", tuple.get("admissionDate"));
            rowMap.put("endDate", tuple.get("endDate"));
            rowMap.put("result", tuple.get("result"));
            rowMap.put("remark", tuple.get("remark"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));
            rowMap.put("lastEducationlevel", tuple.get("lastEducationlevel"));
            rowMap.put("admissionClass", tuple.get("admissionClass"));
            resultList.add(rowMap);
        }
        return resultList;
    }

}
