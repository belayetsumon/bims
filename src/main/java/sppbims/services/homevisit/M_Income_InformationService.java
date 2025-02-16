/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.homevisit;

import sppbims.model.homevisit.M_Income_Information;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
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
public class M_Income_InformationService {

    @PersistenceContext
    EntityManager em;

    public List<Tuple> motherOtherIncome(
            String monthlyIncome
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Income_Information> root = cq.from(M_Income_Information.class);
        // Define multiselect with aliases 

        Expression<Double> monthlyTotalIncome = cb.sum(root.get("monthlyIncome"), root.get("otherIncomeAmt"));
        Expression<Number> total = cb.quot(monthlyTotalIncome, root.get("totalHouseMember"));

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("motherMasterCode").get("mobileNumber").alias("mobileNumber"),
                root.get("incomeGeneratingActivites").get("name").alias("incomeGeneratingActivites"),
                root.get("monthlyIncome").alias("monthlyIncome"),
                root.get("otherIncomeSource").alias("otherIncomeSource"),
                root.get("otherIncomeAmt").alias("otherIncomeAmt"),
                total.alias("percapita"),
                root.get("totalHouseMember").alias("totalHouseMember"),
                root.get("remark").alias("remarks")
        );

        List<Predicate> predicates = new ArrayList<>();

        // Add conditions only if parameters are not null
        if (ObjectUtils.isNotEmpty(monthlyIncome)) {
            predicates.add(cb.lessThan(root.get("monthlyIncome"), monthlyIncome));
        }
//        if (ObjectUtils.isNotEmpty(houseType)) {
//            predicates.add(cb.equal(root.get("houseType"), houseType));
//        }
        // Apply 'OR' condition if both predicates are present
        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }
        cq.orderBy(cb.desc(root.get("id")));
        return em.createQuery(cq).getResultList();
    }
}
