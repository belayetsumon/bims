/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.homevisit;

import sppbims.model.homevisit.Decision;
import sppbims.model.homevisit.M_Approval;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_ApprovalServices {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> allApprovalMother(Decision decision) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root for the entity M_Approval
        Root<M_Approval> approvalRoot = cq.from(M_Approval.class);

        // Create aliases for the fields you want to select
        Expression<Long> idExpression = approvalRoot.get("id");
        Expression<Long> motherMasterCodeId = approvalRoot.get("motherMasterCode").get("id");
        Expression<String> motherMasterCode = approvalRoot.get("motherMasterCode").get("motherMasterCode");
        Expression<String> motherName = approvalRoot.get("motherMasterCode").get("motherName");
        Expression<LocalDate> approveDateExpression = approvalRoot.get("approveDate");
        Expression<String> approveByExpression = approvalRoot.get("approveBy");
        Expression<Decision> decisionExpression = approvalRoot.get("decission");
        Expression<String> remarksExpression = approvalRoot.get("REMARKS");

        // Select multiple fields using Multiselect
        cq.multiselect(
                motherMasterCodeId.alias("motherMasterCodeId"),
                motherMasterCode.alias("motherMasterCode"),
                motherName.alias("motherName"),
                idExpression.alias("approvalId"),
                approveDateExpression.alias("approvalDate"),
                approveByExpression.alias("approver"),
                decisionExpression.alias("decision"),
                remarksExpression.alias("remarks")
        );
        cq.where(cb.equal(approvalRoot.get("decission"), decision));
        cq.orderBy(cb.desc(approvalRoot.get("id")));
        // Execute the query
        List<Tuple> result = entityManager.createQuery(cq).getResultList();

        // Convert the Tuple results into a List of Maps
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherName", tuple.get("motherName"));
            resultMap.put("approvalId", tuple.get("approvalId"));
            resultMap.put("approvalDate", tuple.get("approvalDate"));
            resultMap.put("approver", tuple.get("approver"));
            resultMap.put("decision", tuple.get("decision"));
            resultMap.put("remarks", tuple.get("remarks"));
            resultList.add(resultMap);
        }

        return resultList;
    }

    public List<Map<String, Object>> allApprovalMotherReport(
            Decision decision,
            String startDate,
            String endDate
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root for the entity M_Approval
        Root<M_Approval> approvalRoot = cq.from(M_Approval.class);

        // Create aliases for the fields you want to select
        Expression<Long> idExpression = approvalRoot.get("id");
        Expression<Long> motherMasterCodeId = approvalRoot.get("motherMasterCode").get("id");
        Expression<String> motherMasterCode = approvalRoot.get("motherMasterCode").get("motherMasterCode");
        Expression<String> motherName = approvalRoot.get("motherMasterCode").get("motherName");
        Expression<LocalDate> approveDateExpression = approvalRoot.get("approveDate");
        Expression<String> approveByExpression = approvalRoot.get("approveBy");
        Expression<Decision> decisionExpression = approvalRoot.get("decission");
        Expression<String> remarksExpression = approvalRoot.get("REMARKS");

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(approvalRoot.get("approveDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(approvalRoot.get("approveDate"), start));
        }

        predicates.add(cb.equal(approvalRoot.get("decission"), decision));

        // Select multiple fields using Multiselect
        cq.multiselect(
                motherMasterCodeId.alias("motherMasterCodeId"),
                motherMasterCode.alias("motherMasterCode"),
                motherName.alias("motherName"),
                idExpression.alias("approvalId"),
                approveDateExpression.alias("approvalDate"),
                approveByExpression.alias("approver"),
                decisionExpression.alias("decision"),
                remarksExpression.alias("remarks")
        );
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        cq.orderBy(cb.desc(approvalRoot.get("id")));
        // Execute the query
        List<Tuple> result = entityManager.createQuery(cq).getResultList();

        // Convert the Tuple results into a List of Maps
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherName", tuple.get("motherName"));
            resultMap.put("approvalId", tuple.get("approvalId"));
            resultMap.put("approvalDate", tuple.get("approvalDate"));
            resultMap.put("approver", tuple.get("approver"));
            resultMap.put("decision", tuple.get("decision"));
            resultMap.put("remarks", tuple.get("remarks"));
            resultList.add(resultMap);
        }

        return resultList;
    }

}
