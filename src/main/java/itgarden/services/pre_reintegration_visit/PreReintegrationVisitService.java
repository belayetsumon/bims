/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.pre_reintegration_visit;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_visit.PreReintegrationVisit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
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
public class PreReintegrationVisitService {

    @PersistenceContext
    EntityManager em;

    public List<Long> allPreReintegrationVisitIdList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<PreReintegrationVisit> root = cq.from(PreReintegrationVisit.class);

        cq.multiselect(
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId")
        );
        cq.orderBy(cb.desc(root.get("id")));
        List<Tuple> result = em.createQuery(cq).getResultList();
        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get("motherMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;

    }

    public List<Map<String, Object>> getPreReintegrationVisitList() {
        // Initialize CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Define the root of the query (PreReintegrationVisit)
        Root<PreReintegrationVisit> preVisitRoot = cq.from(PreReintegrationVisit.class);

        // Define joins if needed (in this case with MotherMasterData and Users)
        Join<PreReintegrationVisit, MotherMasterData> motherJoin = preVisitRoot.join("motherMasterCode", JoinType.INNER);
        Join<PreReintegrationVisit, Users> createdByJoin = preVisitRoot.join("createdBy", JoinType.LEFT);
        Join<PreReintegrationVisit, Users> updatedByJoin = preVisitRoot.join("updatedBy", JoinType.LEFT);

        // Select columns using aliases (Multiselect with alias)
        cq.multiselect(
                preVisitRoot.get("id").alias("visitId"),
                motherJoin.get("id").alias("motherMasterCodeId"),
                motherJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherJoin.get("motherName").alias("motherName"),
                preVisitRoot.get("visitOfficersName").alias("officerName"),
                preVisitRoot.get("visitDate").alias("dateOfVisit"),
                preVisitRoot.get("currentSupport").alias("currentSupport"),
                preVisitRoot.get("challengers").alias("challengers"),
                preVisitRoot.get("shortTermPlan").alias("shortTermPlan"),
                preVisitRoot.get("shortTermPlanResolveDate").alias("shortTermPlanResolveDate"),
                preVisitRoot.get("longTermPlan").alias("longTermPlan"),
                preVisitRoot.get("longTermPlanResolveDate").alias("longTermPlanResolveDate"),
                preVisitRoot.get("planForFurther").alias("planForFurther"),
                createdByJoin.get("name").alias("createdBy"), // Assuming 'userName' is a field in Users
                updatedByJoin.get("name").alias("updatedBy") // Assuming 'userName' is a field in Users
        );

        // Apply any filters (example filter by visit date)
//        Predicate visitDatePredicate = cb.greaterThanOrEqualTo(preVisitRoot.get("visitDate"), LocalDate.of(2023, 1, 1));
//        cq.where(visitDatePredicate);
        // Execute the query and transfer result to a List<Map<String, Object>>
        List<Tuple> result = em.createQuery(cq).getResultList();

        // Transfer data to List<Map<String, Object>> for easier use
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("visitId", tuple.get("visitId"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));
            rowMap.put("officerName", tuple.get("officerName"));
            rowMap.put("dateOfVisit", tuple.get("dateOfVisit"));
            rowMap.put("currentSupport", tuple.get("currentSupport"));
            rowMap.put("challengers", tuple.get("challengers"));
            rowMap.put("shortTermPlan", tuple.get("shortTermPlan"));
            rowMap.put("shortTermPlanResolveDate", tuple.get("shortTermPlanResolveDate"));
            rowMap.put("longTermPlan", tuple.get("longTermPlan"));
            rowMap.put("longTermPlanResolveDate", tuple.get("longTermPlanResolveDate"));
            rowMap.put("planForFurther", tuple.get("planForFurther"));

            rowMap.put("createdBy", tuple.get("createdBy"));
            rowMap.put("updatedBy", tuple.get("updatedBy"));

            resultList.add(rowMap);
        }

        return resultList;
    }
}
