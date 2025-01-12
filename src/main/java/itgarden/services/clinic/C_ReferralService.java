/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.clinic;

import itgarden.model.auth.Users;
import itgarden.model.clinic.C_Referral;
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
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class C_ReferralService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> allReferralAll() {
        // Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery and set result type as Tuple
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for the C_Referral entity
        Root<C_Referral> cReferralRoot = criteriaQuery.from(C_Referral.class);

        // Alias for C_Referral columns
        Path<Long> idPath = cReferralRoot.get("id");
        Path<LocalDate> referralDatePath = cReferralRoot.get("referralDate");
        Path<String> reffrredToPath = cReferralRoot.get("reffrredTo");
        Path<String> reasonsPath = cReferralRoot.get("reasons");
        Path<String> remarksPath = cReferralRoot.get("remarks");

        // Join with MotherMasterData and Users (assuming they are present in the entity)
        Join<C_Referral, MotherMasterData> motherMasterJoin = cReferralRoot.join("motherMasterCode", JoinType.LEFT);
        Join<C_Referral, Users> createdByJoin = cReferralRoot.join("createdBy", JoinType.LEFT);

        // Select fields using multiselect
        criteriaQuery.multiselect(
                idPath.alias("referralId"),
                referralDatePath.alias("referralDate"),
                reffrredToPath.alias("reffrredTo"),
                reasonsPath.alias("reasons"),
                remarksPath.alias("remarks"),
                motherMasterJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterJoin.get("id").alias("motherMasterCodeId"),
                createdByJoin.get("name").alias("createdBy") // Assuming 'username' exists in Users
        );

        // Order by referralDate descending
        criteriaQuery.orderBy(criteriaBuilder.desc(referralDatePath));
        // Execute the query and get result as List<Tuple>
        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        // Convert result from Tuple to Map
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new java.util.HashMap<>();
            resultMap.put("referralId", tuple.get("referralId"));
            resultMap.put("referralDate", tuple.get("referralDate"));
            resultMap.put("reffrredTo", tuple.get("reffrredTo"));
            resultMap.put("reasons", tuple.get("reasons"));
            resultMap.put("remarks", tuple.get("remarks"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMap.put("createdBy", tuple.get("createdBy"));
            resultMapList.add(resultMap);
        }
        return resultMapList;
    }
}