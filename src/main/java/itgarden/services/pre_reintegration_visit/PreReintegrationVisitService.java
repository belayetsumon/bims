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
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class PreReintegrationVisitService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    M_Accessibility_ReintegrationVisitService m_Accessibility_ReintegrationVisitService;

    @Autowired
    M_Lifestyle_ReintegrationVisitService m_Lifestyle_ReintegrationVisitService;

    @Autowired
    M_Family_information_ReintegrationVisitService m_Family_information_ReintegrationVisitService;

    @Autowired
    M_Community_Information_ReintegrationVisitService m_Community_Information_ReintegrationVisitService;

    @Autowired
    M_Address_ReintegrationVisitService m_Address_ReintegrationVisitService;

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
    
    
     public List<Long> all_Pre_Reintegration_Visit_Complete_mother_Id_List() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<PreReintegrationVisit> root = cq.from(PreReintegrationVisit.class);

        cq.multiselect(
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId")
        );
        
         List<Predicate> predicates = new ArrayList<Predicate>();
        // Define the predicates for the conditions

        predicates.add(root.get("motherMasterCode").get("id").in(m_Accessibility_ReintegrationVisitService.all_m_Accessibility_Reintegration_Visit_Mother_Id_List()));
        predicates.add(root.get("motherMasterCode").get("id").in(m_Lifestyle_ReintegrationVisitService.all_M_Lifestyle_Reintegration_Visit_Mother_Id_List()));
        predicates.add(root.get("motherMasterCode").get("id").in(m_Family_information_ReintegrationVisitService.all_M_Family_information_Reintegration_Visit_Mother_Id_List()));
        predicates.add(root.get("motherMasterCode").get("id").in(m_Community_Information_ReintegrationVisitService.all_M_Community_Information_Reintegration_Visit_Mother_Id_List()));
        predicates.add(root.get("motherMasterCode").get("id").in(m_Address_ReintegrationVisitService.all_M_Address_Reintegration_Visit_Mother_Id_List()));

        cq.where(predicates.toArray(new Predicate[]{}));
        
        cq.orderBy(cb.desc(root.get("id")));
        List<Tuple> result = em.createQuery(cq).getResultList();
        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get("motherMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;

    }
     
    
    
    

    public List<Map<String, Object>> get_Complete_PreReintegrationVisitList() {
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

        List<Predicate> predicates = new ArrayList<Predicate>();
        // Define the predicates for the conditions

        predicates.add(preVisitRoot.get("motherMasterCode").get("id").in(m_Accessibility_ReintegrationVisitService.all_m_Accessibility_Reintegration_Visit_Mother_Id_List()));
        predicates.add(preVisitRoot.get("motherMasterCode").get("id").in(m_Lifestyle_ReintegrationVisitService.all_M_Lifestyle_Reintegration_Visit_Mother_Id_List()));
        predicates.add(preVisitRoot.get("motherMasterCode").get("id").in(m_Family_information_ReintegrationVisitService.all_M_Family_information_Reintegration_Visit_Mother_Id_List()));
        predicates.add(preVisitRoot.get("motherMasterCode").get("id").in(m_Community_Information_ReintegrationVisitService.all_M_Community_Information_Reintegration_Visit_Mother_Id_List()));
        predicates.add(preVisitRoot.get("motherMasterCode").get("id").in(m_Address_ReintegrationVisitService.all_M_Address_Reintegration_Visit_Mother_Id_List()));

        cq.where(predicates.toArray(new Predicate[]{}));

        cq.orderBy(cb.desc(preVisitRoot.get("id")));

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

    public List<Map<String, Object>> get_Incomplete_PreReintegrationVisitList() {
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

        List<Predicate> predicates = new ArrayList<Predicate>();
        // Define the predicates for the conditions
        predicates.add(preVisitRoot.get("motherMasterCode").get("id")
                .in(m_Accessibility_ReintegrationVisitService.all_m_Accessibility_Reintegration_Visit_Mother_Id_List())
                .not());
        
        predicates.add(preVisitRoot.get("motherMasterCode").get("id")        
                .in(m_Lifestyle_ReintegrationVisitService.all_M_Lifestyle_Reintegration_Visit_Mother_Id_List())
                .not());
        predicates.add(preVisitRoot.get("motherMasterCode").get("id")
                .in(m_Family_information_ReintegrationVisitService.all_M_Family_information_Reintegration_Visit_Mother_Id_List())
                .not());
        predicates.add(preVisitRoot.get("motherMasterCode").get("id")
                .in(m_Community_Information_ReintegrationVisitService.all_M_Community_Information_Reintegration_Visit_Mother_Id_List())
                .not());
        predicates.add(preVisitRoot.get("motherMasterCode").get("id")
                .in(m_Address_ReintegrationVisitService.all_M_Address_Reintegration_Visit_Mother_Id_List())
                .not());
        
        Predicate orPredicate = cb.or(predicates.toArray(new Predicate[0]));
        cq.where(orPredicate);

        cq.orderBy(cb.desc(preVisitRoot.get("id")));

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
