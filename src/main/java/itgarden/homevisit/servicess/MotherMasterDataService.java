/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.homevisit.servicess;

import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class MotherMasterDataService {

    @PersistenceContext
    EntityManager em;

    public List<MotherMasterDataDTO> allInsertedMotherList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("visitOfficersName").alias("visitOfficersName"),
                root.join("dateReferral").alias("dateReferral"),
                root.join("referredFrom").alias("referredFrom"),
                root.join("resons").get("name").alias("resons"),
                root.join("homeVisitDate").alias("homeVisitDate"),
                root.get("motherName").alias("motherName"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                root.get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
                root.get("eligibility").alias("eligibility")
        );

        List<Predicate> predicates = new ArrayList<>();

//        if (StringUtils.hasText(inReference)) {
//            predicates.add(cb.like(root.get("inReference"), inReference));
//        }
//
//        if (StringUtils.hasText(priceType)) {
//            predicates.add(cb.like(root.get("priceType"), priceType));
//        }
//
//        if (customers != null) {
//            predicates.add(cb.equal(root.get("customers"), customers));
//        }
//
//        if (fromStkLoc != null) {
//            predicates.add(cb.equal(root.get("fromStkLoc"), fromStkLoc));
//        }
//
//        if (invoiceStartDate != null && invoiceEndDate != null) {
//            predicates.add(cb.between(root.get("invoiceDate"), invoiceStartDate, invoiceEndDate));
//        }
//
//        if (invoiceStartDate != null && invoiceEndDate == null) {
//            predicates.add(cb.equal(root.get("invoiceDate"), invoiceStartDate));
//        }
//
        cq.where(predicates.toArray(new Predicate[0]));
//  
        cq.orderBy(cb.desc(root.get("id")));
//
        TypedQuery<Tuple> typedQuery = em.createQuery(cq);
//
        List<MotherMasterDataDTO> motherMasterDataDTOList = new ArrayList<>();
//
        for (Tuple mothermasterData : typedQuery.getResultList()) {

            MotherMasterDataDTO motherMasterDataDTO = new MotherMasterDataDTO();
          
            motherMasterDataDTO.setId(mothermasterData.get("id", Long.class));
            
//            salesInvoiceDTO.setInvoiceno(salesInvoice.get("invoiceno", Long.class));
//            salesInvoiceDTO.setInReference(salesInvoice.get("inReference", String.class));
//            salesInvoiceDTO.setOtherOrderNo(salesInvoice.get("otherOrderNo", String.class));
//            salesInvoiceDTO.setCustomersId(salesInvoice.get("customersId", Long.class));
//            salesInvoiceDTO.setCustomers(salesInvoice.get("customerName", String.class));
//            salesInvoiceDTO.setSalesPersonsId(salesInvoice.get("salesPersonId", Long.class));
//            salesInvoiceDTO.setSalesPersons(salesInvoice.get("salesPersonName", String.class));
//            salesInvoiceDTO.setInvoiceDate(salesInvoice.get("invoiceDate", Date.class));
//            salesInvoiceDTO.setDueDate(salesInvoice.get("dueDate", Date.class));
//            salesInvoiceDTO.setGrandTotal(salesInvoice.get("grandTotal", BigDecimal.class));
//            salesInvoiceDTO.setAlloc(salesInvoice.get("alloc", BigDecimal.class));
            motherMasterDataDTOList.add(motherMasterDataDTO);
        } //end loop

        return motherMasterDataDTOList;
    } // end motherList()

}// end class

