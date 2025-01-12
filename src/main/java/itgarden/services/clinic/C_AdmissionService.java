/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.clinic;

import itgarden.model.clinic.C_Admission;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class C_AdmissionService {

    @PersistenceContext
    private EntityManager entityManager;
    public List<Map<String, Object>> findAdmissionDetails() {
        // Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery and set result type as Tuple
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for the C_Admission entity
        Root<C_Admission> cAdmissionRoot = criteriaQuery.from(C_Admission.class);

        // Alias for C_Admission columns
        Path<Long> idPath = cAdmissionRoot.get("id");
        Path<LocalDate> admissionDatePath = cAdmissionRoot.get("admissionDate");
        Path<String> admittedToPath = cAdmissionRoot.get("admittedTo");
        Path<String> reasonPath = cAdmissionRoot.get("reason");
        Path<String> remarkPath = cAdmissionRoot.get("remark");

        // Join with MotherMasterData (assuming it is present in the entity)
        Join<C_Admission, MotherMasterData> motherMasterJoin = cAdmissionRoot.join("motherMasterCode", JoinType.LEFT);

        // Select fields using multiselect
        criteriaQuery.multiselect(
                idPath.alias("admissionId"),
                admissionDatePath.alias("admissionDate"),
                admittedToPath.alias("admittedTo"),
                reasonPath.alias("reason"),
                remarkPath.alias("remark"),
                motherMasterJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterJoin.get("id").alias("motherMasterCodeId")
        );

        // Order by admissionDate descending
        criteriaQuery.orderBy(criteriaBuilder.desc(idPath));

        // Execute the query and get result as List<Tuple>
        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        // Convert result from Tuple to Map
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("admissionId", tuple.get("admissionId"));
            resultMap.put("admissionDate", tuple.get("admissionDate"));
            resultMap.put("admittedTo", tuple.get("admittedTo"));
            resultMap.put("reason", tuple.get("reason"));
            resultMap.put("remark", tuple.get("remark"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMapList.add(resultMap);
        }

        return resultMapList;
    }
}