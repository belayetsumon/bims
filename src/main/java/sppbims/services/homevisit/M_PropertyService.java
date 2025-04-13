/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.homevisit;

import sppbims.model.homevisit.Address_Type;
import sppbims.model.homevisit.District;
import sppbims.model.homevisit.M_Property;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
public class M_PropertyService {

    @PersistenceContext
    EntityManager em;

    public List<Tuple> motherproperty(
            String motherMasterCode,
            Float minSavingMoney,
            Float minHomelandQuantity,
            Float minCultivableLandQuantity,
            Float minJewelryQuantity,
            Float minAnimalsQuantity,
            Float minInvestmentsSharesQuantity,
            Float minLoanPersonQuantity,
            Float minOrganizationsLoanQuantity
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Property> root = cq.from(M_Property.class);
        // Define multiselect with aliases     
        // Define multiselect with aliases for each field
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                 root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("bankAccount").alias("bankAccount"),
                root.get("savingMoney").alias("savingMoney"),
                root.get("homelandQuantity").alias("homelandQuantity"),
                root.get("homeLandValue").alias("homeLandValue"),
                root.get("cultivableLandQuantity").alias("cultivableLandQuantity"),
                root.get("cultivableLandValue").alias("cultivableLandValue"),
                //                root.get("jewelryQuentity").alias("jewelryQuentity"),
                root.get("jewelryValue").alias("jewelryValue"),
                root.get("animalsQuantity").alias("animalsQuantity"),
                root.get("animalsValue").alias("animalsValue"),
                //                root.get("investmentsSharesQuantity").alias("investmentsSharesQuantity"),
                root.get("investmentsSharesValue").alias("investmentsSharesValue"),
                root.get("loanPersonQuantity").alias("loanPersonQuantity"),
                root.get("loanPersonName").alias("loanPersonName"),
                root.get("organizationsLoanQuantity").alias("organizationsLoanQuantity"),
                root.get("organizationName").alias("organizationName"),
                root.get("remark").alias("remark")
        );

        // Build dynamic predicates based on filter values
        List<Predicate> predicates = new ArrayList<>();

        // Add filters based on method arguments
        if (minSavingMoney != null && minSavingMoney == 1) {
            predicates.add(cb.greaterThan(root.get("savingMoney"), 0));
        }

        if (minHomelandQuantity != null && minHomelandQuantity == 1) {
            predicates.add(cb.greaterThan(root.get("homelandQuantity"), 0));
        }

        if (minCultivableLandQuantity != null && minCultivableLandQuantity == 1) {
            predicates.add(cb.greaterThan(root.get("cultivableLandQuantity"), 0));
        }
//
//        if (minJewelryQuantity != null) {
//            if (minJewelryQuantity == 1) {
//                predicates.add(cb.greaterThan(root.get("jewelryQuentity"), 0));
//            } else if (minJewelryQuantity > 1) {
//                predicates.add(cb.greaterThanOrEqualTo(root.get("jewelryQuentity"), minJewelryQuantity));
//            }
//        }

        if (minAnimalsQuantity != null && minAnimalsQuantity == 1) {
            predicates.add(cb.greaterThan(root.get("animalsQuantity"), 0));
        }

//        if (minInvestmentsSharesQuantity != null && minInvestmentsSharesQuantity == 1) {
//            predicates.add(cb.greaterThan(root.get("investmentsSharesQuantity"), 0));
//        }
        if (minLoanPersonQuantity != null && minLoanPersonQuantity == 1) {
            predicates.add(cb.greaterThan(root.get("loanPersonQuantity"), 0));
        }

        if (minOrganizationsLoanQuantity != null) {
            if (minOrganizationsLoanQuantity == 1) {
                predicates.add(cb.greaterThan(root.get("organizationsLoanQuantity"), 0));
            } else if (minOrganizationsLoanQuantity > 1) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("organizationsLoanQuantity"), minOrganizationsLoanQuantity));
            }
        }

        // Apply 'OR' condition if both predicates are present
        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }

        cq.orderBy(cb.desc(root.get("id")));
        return em.createQuery(cq).getResultList();
    }

}
