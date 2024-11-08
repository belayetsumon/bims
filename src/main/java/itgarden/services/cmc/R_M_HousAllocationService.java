/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.cmc;

import itgarden.model.rehabilitations.HouseName;
import itgarden.model.rehabilitations.R_M_HousAllocation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class R_M_HousAllocationService {

    @PersistenceContext
    EntityManager em;

    public List<Long> motherHouseAllicationIdList() {
// Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

// Create CriteriaQuery for R_M_HousAllocation
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_M_HousAllocation> root = cq.from(R_M_HousAllocation.class);

// Define the selection (using tuple)
        cq.multiselect(
                //                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId")
        //                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
        //                root.get("motherMasterCode").get("motherName").alias("motherName"),// Assuming 'code' is a field in MotherMasterData
        //                root.get("houseName").get("name").alias("houseName"), // Assuming 'name' is a field in HouseName
        //                root.get("allocationDate").alias("allocationDate"),
        //                root.get("endDate").alias("endDate"),
        //                root.get("remark").alias("remark")
        );

// Add predicates to the query
        cq.orderBy(cb.desc(root.get("id")));
// Execute the query
        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();

        for (Tuple t : result) {
            Long id = t.get("motherMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;

    }

    public List<Tuple> AllMotherHouseAllocation() {
// Create CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

// Create CriteriaQuery for R_M_HousAllocation
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<R_M_HousAllocation> root = cq.from(R_M_HousAllocation.class);

// Define the selection (using tuple)
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),// Assuming 'code' is a field in MotherMasterData
                root.get("houseName").get("name").alias("houseName"), // Assuming 'name' is a field in HouseName
                root.get("allocationDate").alias("allocationDate"),
                root.get("endDate").alias("endDate"),
                root.get("remark").alias("remark")
        );

// Add predicates to the query
        cq.orderBy(cb.desc(root.get("id")));
// Execute the query
        TypedQuery<Tuple> query = em.createQuery(cq);
        return query.getResultList();
    }

    public List<Tuple> currentMotherHouseLocation(
            HouseName houseName
    ) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<R_M_HousAllocation> root = cq.from(R_M_HousAllocation.class);

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),// Assuming 'code' is a field in MotherMasterData
                root.get("houseName").get("name").alias("houseName"), // Assuming 'name' is a field in HouseName
                root.get("allocationDate").alias("allocationDate"),
                root.get("endDate").alias("endDate"),
                root.get("extDate").alias("extDate"),
                root.get("remark").alias("remark")
        );
        List<Predicate> predicates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        // Check if extDate is null
        Predicate extDateIsNull = cb.isNull(root.get("extDate"));
        Predicate extDateNotNull = cb.isNotNull(root.get("extDate"));

        // If extDate is null, compare endDate with currentDate
        Predicate endDateCondition = cb.greaterThan(root.get("endDate"), currentDate);

        // If extDate is not null, compare extDate with currentDate
        Predicate extDateCondition = cb.greaterThan(root.get("extDate"), currentDate);

        // Combine conditions based on extDate's nullability
        Predicate finalCondition = cb.or(
                cb.and(extDateIsNull, endDateCondition), // If extDate is null, check endDate
                cb.and(extDateNotNull, extDateCondition) // If extDate is not null, check extDate
        );

        if (ObjectUtils.isNotEmpty(houseName)) {
            predicates.add(cb.equal(root.get("houseName"), houseName));
        }

        // Add the final condition to the query
        predicates.add(finalCondition);

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        cq.orderBy(cb.desc(root.get("id")));

        TypedQuery<Tuple> query = em.createQuery(cq);

        return query.getResultList();
    }

}
