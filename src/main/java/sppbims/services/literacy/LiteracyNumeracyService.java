/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.literacy;

import sppbims.model.homevisit.DTO.MotherMasterDataDTO;
import sppbims.model.homevisit.EducationLevel;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.literacy.LiteracyNumeracy;
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
public class LiteracyNumeracyService {

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

    public List<Long> literacyNumeracyMotherIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<LiteracyNumeracy> root = cq.from(LiteracyNumeracy.class);

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

    public List<Map<String, Object>> getAllLiteracyNumeracyData() {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<LiteracyNumeracy> root = cq.from(LiteracyNumeracy.class);

        // Create Aliases for the fields you want to select
        Expression<Long> idAlias = root.get("id");
        Expression<LocalDate> admissionDateAlias = root.get("admissionDate");
        Expression<EducationLevel> admissionLevelAlias = root.get("admissionLevel").get("name");
        Expression<EducationLevel> presentliteracylevelAlias = root.get("presentliteracylevel").get("name");
        Expression<LocalDate> endDateAlias = root.get("endDate");
        Expression<ResultEnum> resultAlias = root.get("result");
        Expression<String> remarkAlias = root.get("remark");
        Expression<Long> motherMasterCodeIdAlias = root.get("motherMasterCode").get("id");
        Expression<String> motherMasterCodeAlias = root.get("motherMasterCode").get("motherMasterCode");// Assuming id for motherMasterCode
        Expression<String> motherNameCodeAlias = root.get("motherMasterCode").get("motherName");// Assuming id for motherMasterCode

        // Add the SELECT clause (multi-select)
        cq.multiselect(
                idAlias,
                admissionDateAlias,
                admissionLevelAlias,
                presentliteracylevelAlias,
                endDateAlias,
                resultAlias,
                remarkAlias,
                motherMasterCodeIdAlias,
                motherNameCodeAlias,
                motherMasterCodeAlias
        );

        cq.orderBy(cb.desc(idAlias));  // Order by id desc

        // Create the query and get the result as List of Tuples
        List<Tuple> tuples = em.createQuery(cq).getResultList();

        // Convert List<Tuple> to List<Map<String, Object>>
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("id", tuple.get(idAlias));
            rowMap.put("admissionDate", tuple.get(admissionDateAlias));
            rowMap.put("admissionLevel", tuple.get(admissionLevelAlias));
            rowMap.put("presentliteracylevel", tuple.get(presentliteracylevelAlias));
            rowMap.put("endDate", tuple.get(endDateAlias));
            rowMap.put("result", tuple.get(resultAlias));
            rowMap.put("remark", tuple.get(remarkAlias));
            rowMap.put("motherMasterCodeId", tuple.get(motherMasterCodeIdAlias));
            rowMap.put("motherMasterCode", tuple.get(motherMasterCodeAlias));
            rowMap.put("motherName", tuple.get(motherNameCodeAlias));

            resultList.add(rowMap);
        }

        return resultList;
    }

    public List<Map<String, Object>> getAllLiteracyNumeracyDataReport(
            String startDate,
            String endDate,
            ResultEnum result,
            EducationLevel admissionLevel
    ) {
        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<LiteracyNumeracy> root = cq.from(LiteracyNumeracy.class);

        // Create Aliases for the fields you want to select
//        Expression<Long> idAlias = root.get("id");
//        Expression<LocalDate> admissionDateAlias = root.get("admissionDate");
//        Expression<EducationLevel> admissionLevelAlias = root.get("admissionLevel").get("name");
//        Expression<EducationLevel> presentliteracylevelAlias = root.get("admissionLevel").get("name");
//        Expression<LocalDate> endDateAlias = root.get("endDate");
//        Expression<ResultEnum> resultAlias = root.get("result");
//        Expression<Long> motherMasterCodeIdAlias = root.get("motherMasterCode").get("id");
//        Expression<Long> motherMasterCodeAlias = root.get("motherMasterCode").get("motherMasterCode");// Assuming id for motherMasterCode
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("admissionDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("admissionDate"), start));
        }

        if (ObjectUtils.isNotEmpty(result)) {
            predicates.add(cb.equal(root.get("result"), result));
        }

        if (ObjectUtils.isNotEmpty(admissionLevel)) {
            predicates.add(cb.equal(root.get("admissionLevel"), admissionLevel));
        }

        // Add the SELECT clause (multi-select)
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("admissionDate").alias("admissionDate"),
                root.get("admissionLevel").get("name").alias("admissionLevel"),
                root.get("presentliteracylevel").get("name").alias("presentliteracylevel"),
                root.get("result").alias("result"),
                root.get("remark").alias("remark"),
                root.get("endDate").alias("endDate")
        );
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        cq.orderBy(cb.desc(root.get("id")));  // Order by id desc

        // Create the query and get the result as List of Tuples
        List<Tuple> tuples = em.createQuery(cq).getResultList();

        // Convert List<Tuple> to List<Map<String, Object>>
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("id", tuple.get("id"));
            rowMap.put("admissionDate", tuple.get("admissionDate"));
            rowMap.put("admissionLevel", tuple.get("admissionLevel"));
            rowMap.put("presentliteracylevel", tuple.get("presentliteracylevel"));
            rowMap.put("endDate", tuple.get("endDate"));
            rowMap.put("result", tuple.get("result"));
            rowMap.put("remark", tuple.get("remark"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));

            resultList.add(rowMap);
        }

        return resultList;
    }

}
