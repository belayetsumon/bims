/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.reintegration_checklist;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.pre_reintegration_checklist.ReintegrationCheckList;
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
public class ReintegrationCheckListService {

    @PersistenceContext
    EntityManager em;

    public List<Long> allReintegrationCheckListList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<ReintegrationCheckList> root = cq.from(ReintegrationCheckList.class);
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

    public List<Long> allReintegrationCheckListListYes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<ReintegrationCheckList> root = cq.from(ReintegrationCheckList.class);
        cq.multiselect(
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId")
        );

        cq.where(cb.equal(root.get("reintegration"), Yes_No.Yes));

        cq.orderBy(cb.desc(root.get("id")));
        List<Tuple> result = em.createQuery(cq).getResultList();
        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get("motherMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> getReintegrationCheckListData( //            LocalDate fromDate, LocalDate toDate
            ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

        // Define the root entity of the query (ReintegrationCheckList)
        Root<ReintegrationCheckList> reintegrationRoot = query.from(ReintegrationCheckList.class);

        // Join with MotherMasterData if needed (based on motherMasterCode)
        Join<ReintegrationCheckList, MotherMasterData> motherMasterJoin = reintegrationRoot.join("motherMasterCode", JoinType.INNER);
        Join<ReintegrationCheckList, Users> createdByJoin = reintegrationRoot.join("createdBy", JoinType.LEFT);
        Join<ReintegrationCheckList, Users> updatedByJoin = reintegrationRoot.join("updatedBy", JoinType.LEFT);
        // Select all fields from ReintegrationCheckList entity
        query.multiselect(
                reintegrationRoot.get("id").alias("id"),
                motherMasterJoin.get("id").alias("motherMasterId"),
                 motherMasterJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterJoin.get("motherName").alias("motherName"),
                reintegrationRoot.get("date").alias("date"),
                reintegrationRoot.get("tentativeDate").alias("tentativeDate"),
                reintegrationRoot.get("reintegrationEmployment").alias("reintegrationEmployment"),
                reintegrationRoot.get("addresss").alias("addresss"),
                reintegrationRoot.get("individualRehabilitationPlanning").alias("individualRehabilitationPlanning"),
                reintegrationRoot.get("individualRehabilitationPlanningNote").alias("individualRehabilitationPlanningNote"),
                reintegrationRoot.get("talkOverMother").alias("talkOverMother"),
                reintegrationRoot.get("savingAmoun").alias("savingAmoun"),
                reintegrationRoot.get("savingUsed").alias("savingUsed"),
                reintegrationRoot.get("sppOtherSupport").alias("sppOtherSupport"),
                reintegrationRoot.get("financialTrainingReceived").alias("financialTrainingReceived"),
                reintegrationRoot.get("financialTrainingReceivedNote").alias("financialTrainingReceivedNote"),
                reintegrationRoot.get("financialPlanningDone").alias("financialPlanningDone"),
                reintegrationRoot.get("financialPlanningDoneNote").alias("financialPlanningDoneNote"),
                reintegrationRoot.get("bankAccountOened").alias("bankAccountOened"),
                reintegrationRoot.get("bankAccountOenedNote").alias("bankAccountOenedNote"),
                reintegrationRoot.get("incomeGeneratingTrainingReceived").alias("incomeGeneratingTrainingReceived"),
                reintegrationRoot.get("incomeGeneratingTrainingReceivedNote").alias("incomeGeneratingTrainingReceivedNote"),
                reintegrationRoot.get("allNecessarySkillTrainingReceived").alias("allNecessarySkillTrainingReceived"),
                reintegrationRoot.get("allNecessarySkillTrainingReceivedNote").alias("allNecessarySkillTrainingReceivedNote"),
                reintegrationRoot.get("receivedMedicalDocuments").alias("receivedMedicalDocuments"),
                reintegrationRoot.get("receivedMedicalDocumentsNote").alias("receivedMedicalDocumentsNote"),
                reintegrationRoot.get("preReintegrationHomeVisit").alias("preReintegrationHomeVisit"),
                reintegrationRoot.get("preReintegrationHomeVisitNote").alias("preReintegrationHomeVisitNote"),
                reintegrationRoot.get("workSiteAssessment").alias("workSiteAssessment"),
                reintegrationRoot.get("workSiteAssessmentNote").alias("workSiteAssessmentNote"),
                reintegrationRoot.get("healthOnDischarge").alias("healthOnDischarge"),
                reintegrationRoot.get("healthOnDischargeNote").alias("healthOnDischargeNote"),
                reintegrationRoot.get("reintegration").alias("reintegration"),
                reintegrationRoot.get("created").alias("created"),
                createdByJoin.get("name").alias("createdBy"),
                reintegrationRoot.get("updated").alias("updated"),
                updatedByJoin.get("name").alias("updatedBy")
        );

        // Apply filters (e.g., filtering by date range)
//        Predicate dateRangePredicate = cb.between(reintegrationRoot.get("date"), fromDate, toDate);
//        query.where(dateRangePredicate);
        // Order the result by `date` in descending order
        query.orderBy(cb.desc(reintegrationRoot.get("id")));

        // Execute the query
        List<Tuple> tuples = em.createQuery(query).getResultList();

        System.out.println("cccccccccccccccccccccccccc" + tuples.size());
        List<Map<String, Object>> results = new ArrayList<>();

        // Convert Tuple results into a list of maps for easier handling
        for (Tuple tuple : tuples) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", tuple.get("id"));
            map.put("motherMasterId", tuple.get("motherMasterId"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherName", tuple.get("motherName"));
            map.put("date", tuple.get("date"));
            map.put("tentativeDate", tuple.get("tentativeDate"));
            map.put("reintegrationEmployment", tuple.get("reintegrationEmployment"));
            map.put("addresss", tuple.get("addresss"));
            map.put("individualRehabilitationPlanning", tuple.get("individualRehabilitationPlanning"));
            map.put("individualRehabilitationPlanningNote", tuple.get("individualRehabilitationPlanningNote"));
            map.put("talkOverMother", tuple.get("talkOverMother"));
            map.put("savingAmoun", tuple.get("savingAmoun"));
            map.put("savingUsed", tuple.get("savingUsed"));
            map.put("sppOtherSupport", tuple.get("sppOtherSupport"));
            map.put("financialTrainingReceived", tuple.get("financialTrainingReceived"));
            map.put("financialTrainingReceivedNote", tuple.get("financialTrainingReceivedNote"));
            map.put("financialPlanningDone", tuple.get("financialPlanningDone"));
            map.put("financialPlanningDoneNote", tuple.get("financialPlanningDoneNote"));
            map.put("bankAccountOened", tuple.get("bankAccountOened"));
            map.put("bankAccountOenedNote", tuple.get("bankAccountOenedNote"));
            map.put("incomeGeneratingTrainingReceived", tuple.get("incomeGeneratingTrainingReceived"));
            map.put("incomeGeneratingTrainingReceivedNote", tuple.get("incomeGeneratingTrainingReceivedNote"));
            map.put("allNecessarySkillTrainingReceived", tuple.get("allNecessarySkillTrainingReceived"));
            map.put("allNecessarySkillTrainingReceivedNote", tuple.get("allNecessarySkillTrainingReceivedNote"));
            map.put("receivedMedicalDocuments", tuple.get("receivedMedicalDocuments"));
            map.put("receivedMedicalDocumentsNote", tuple.get("receivedMedicalDocumentsNote"));
            map.put("preReintegrationHomeVisit", tuple.get("preReintegrationHomeVisit"));
            map.put("preReintegrationHomeVisitNote", tuple.get("preReintegrationHomeVisitNote"));
            map.put("workSiteAssessment", tuple.get("workSiteAssessment"));
            map.put("workSiteAssessmentNote", tuple.get("workSiteAssessmentNote"));
            map.put("healthOnDischarge", tuple.get("healthOnDischarge"));
            map.put("healthOnDischargeNote", tuple.get("healthOnDischargeNote"));
            map.put("reintegration", tuple.get("reintegration"));
            map.put("created", tuple.get("created"));
            map.put("createdBy", tuple.get("createdBy"));
            map.put("updated", tuple.get("updated"));
            map.put("updatedBy", tuple.get("updatedBy"));
            results.add(map);
        }
        return results;
    }
}
