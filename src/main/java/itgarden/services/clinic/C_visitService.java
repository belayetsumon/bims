/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.clinic;

import itgarden.model.auth.Users;
import itgarden.model.clinic.C_visit;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class C_visitService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> all_visit_list() {
        // Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery and set result type as Tuple
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for the C_visit entity
        Root<C_visit> cVisitRoot = criteriaQuery.from(C_visit.class);

        // Alias for C_visit columns
        Path<Long> idPath = cVisitRoot.get("id");
        Path<LocalDate> visitDatePath = cVisitRoot.get("visitDate");
        Path<String> problemPath = cVisitRoot.get("problem");
        Path<String> primaryDiagnosisPath = cVisitRoot.get("primaryDiagnosis");
        Path<String> medicinePath = cVisitRoot.get("medicine");
        Path<String> prescribedByPath = cVisitRoot.get("prescribedBy");
        Path<String> remarkPath = cVisitRoot.get("remark");

        // Join with MotherMasterData and Users (assuming they are present in the entity)
        Join<C_visit, MotherMasterData> motherMasterJoin = cVisitRoot.join("motherMasterCode", JoinType.LEFT);
        Join<C_visit, Users> createdByJoin = cVisitRoot.join("createdBy", JoinType.LEFT);

        // Select fields using multiselect
        criteriaQuery.multiselect(
                idPath.alias("visitId"),
                visitDatePath.alias("visitDate"),
                problemPath.alias("problem"),
                primaryDiagnosisPath.alias("primaryDiagnosis"),
                medicinePath.alias("medicine"),
                prescribedByPath.alias("prescribedBy"),
                remarkPath.alias("remark"),
                motherMasterJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterJoin.get("id").alias("motherMasterCodeId"),
                createdByJoin.get("name").alias("createdBy") // Assuming 'username' exists in Users

        );

        // Order by visitDate descending
        criteriaQuery.orderBy(criteriaBuilder.desc(idPath));

        // Execute the query and get result as List<Tuple>
        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        // Convert result from Tuple to Map
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new java.util.HashMap<>();
            resultMap.put("visitId", tuple.get("visitId"));
            resultMap.put("visitDate", tuple.get("visitDate"));
            resultMap.put("problem", tuple.get("problem"));
            resultMap.put("primaryDiagnosis", tuple.get("primaryDiagnosis"));
            resultMap.put("medicine", tuple.get("medicine"));
            resultMap.put("prescribedBy", tuple.get("prescribedBy"));
            resultMap.put("remark", tuple.get("remark"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMap.put("createdBy", tuple.get("createdBy"));
            resultMapList.add(resultMap);
        }
        return resultMapList;
    }

    public List<Map<String, Object>> all_visit_list_By_Date_report(
            String startDate,
            String endDate
            ) {
        // Create CriteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery and set result type as Tuple
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

        // Root for the C_visit entity
        Root<C_visit> cVisitRoot = criteriaQuery.from(C_visit.class);

        // Alias for C_visit columns
        Path<Long> idPath = cVisitRoot.get("id");
        Path<LocalDate> visitDatePath = cVisitRoot.get("visitDate");
        Path<String> problemPath = cVisitRoot.get("problem");
        Path<String> primaryDiagnosisPath = cVisitRoot.get("primaryDiagnosis");
        Path<String> medicinePath = cVisitRoot.get("medicine");
        Path<String> prescribedByPath = cVisitRoot.get("prescribedBy");
        Path<String> remarkPath = cVisitRoot.get("remark");

        // Join with MotherMasterData and Users (assuming they are present in the entity)
        Join<C_visit, MotherMasterData> motherMasterJoin = cVisitRoot.join("motherMasterCode", JoinType.LEFT);
        Join<C_visit, Users> createdByJoin = cVisitRoot.join("createdBy", JoinType.LEFT);

        
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(cVisitRoot.get("visitDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(cVisitRoot.get("visitDate"), start));
        }
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));       
        
        // Select fields using multiselect
        criteriaQuery.multiselect(
                idPath.alias("visitId"),
                visitDatePath.alias("visitDate"),
                problemPath.alias("problem"),
                primaryDiagnosisPath.alias("primaryDiagnosis"),
                medicinePath.alias("medicine"),
                prescribedByPath.alias("prescribedBy"),
                remarkPath.alias("remark"),
                cVisitRoot.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                cVisitRoot.get("motherMasterCode").get("id").alias("motherMasterCodeId")
                 // Assuming 'username' exists in Users

        );

        // Order by visitDate descending
        criteriaQuery.orderBy(cb.desc(idPath));

        // Execute the query and get result as List<Tuple>
        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        // Convert result from Tuple to Map
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new java.util.HashMap<>();
            resultMap.put("visitId", tuple.get("visitId"));
            resultMap.put("visitDate", tuple.get("visitDate"));
            resultMap.put("problem", tuple.get("problem"));
            resultMap.put("primaryDiagnosis", tuple.get("primaryDiagnosis"));
            resultMap.put("medicine", tuple.get("medicine"));
            resultMap.put("prescribedBy", tuple.get("prescribedBy"));
            resultMap.put("remark", tuple.get("remark"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
//            resultMap.put("createdBy", tuple.get("createdBy"));
            resultMapList.add(resultMap);
        }

        return resultMapList;
    }

}
