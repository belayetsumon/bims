/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.homevisit;

import sppbims.model.homevisit.M_Current_Help;
import sppbims.model.homevisit.MotherMasterData;
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
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_Current_HelpService {

    @PersistenceContext
    EntityManager em;

//    public List<Tuple> motherList() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
//        Root<M_Current_Help> root = cq.from(M_Current_Help.class);
//        // Define multiselect with aliases     
//        cq.multiselect(
//                root.get("id").alias("id"),
//                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
//                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
//                root.get("motherMasterCode").get("motherName").alias("motherName"),
//                root.get("motherMasterCode").get("mobileNumber").alias("mobileNumber")
//        );
//        cq.groupBy(root.get("motherMasterCode"));
//        cq.orderBy(cb.desc(root.get("id")));
//        return em.createQuery(cq).getResultList();
//    }
//    public List<Tuple> motherList() {
//    CriteriaBuilder cb = em.getCriteriaBuilder();
//    CriteriaQuery<Tuple> cq = cb.createTupleQuery();
//    
//    // Root for the main entity
//    Root<M_Current_Help> root = cq.from(M_Current_Help.class);
//    
//    // Define joins with the related entity (MotherMasterData)
//    Join<M_Current_Help, MotherMasterData> motherMasterJoin = root.join("motherMasterCode", JoinType.LEFT);
//    
//    // Define multiselect with aliases (selecting necessary fields and grouping)
//    cq.multiselect(
//            root.get("id").alias("id"),
//            motherMasterJoin.get("id").alias("motherMasterCodeId"),
//            motherMasterJoin.get("motherMasterCode").alias("motherMasterCode"),
//            motherMasterJoin.get("motherName").alias("motherName"),
//            motherMasterJoin.get("mobileNumber").alias("mobileNumber")
//    );
//    
//    // Grouping by motherMasterCodeId (instead of the entire entity)
//    cq.groupBy(motherMasterJoin.get("id"));
//    
//    // Ordering by id in descending order
//    cq.orderBy(cb.desc(root.get("id")));
//    
//    // Execute and return the result
//    return em.createQuery(cq).getResultList();
//}
    public List<Tuple> motherList() {
        // Initialize CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        // Root entity (M_Current_Help)
        Root<M_Current_Help> mCurrentHelp = cq.from(M_Current_Help.class);

        // Join with MotherMasterData (LEFT JOIN)
        Join<M_Current_Help, MotherMasterData> motherMasterJoin = mCurrentHelp.join("motherMasterCode", JoinType.LEFT);

        // Define the fields to be selected
        cq.multiselect(
                mCurrentHelp.get("id").alias("id"), // m1_0.id
                mCurrentHelp.get("motherMasterCode").get("id").alias("motherMasterCodeId"), // m1_0.mother_master_code_id
                motherMasterJoin.get("motherMasterCode").alias("motherMasterCode"), // m2_0.mother_master_code
                motherMasterJoin.get("motherName").alias("motherName"), // m2_0.mother_name
                motherMasterJoin.get("mobileNumber").alias("mobileNumber") // m2_0.mobile_number
        );

        // Group by all selected fields (to match the SQL GROUP BY)
        cq.groupBy(
                mCurrentHelp.get("id"),
                mCurrentHelp.get("motherMasterCode").get("id"),
                motherMasterJoin.get("motherMasterCode"),
                motherMasterJoin.get("motherName"),
                motherMasterJoin.get("mobileNumber")
        );

        // Order by m1_0.id in descending order (as per SQL)
        cq.orderBy(cb.desc(mCurrentHelp.get("id")));

        // Execute the query and return the result
        return em.createQuery(cq).getResultList();
    }

}
