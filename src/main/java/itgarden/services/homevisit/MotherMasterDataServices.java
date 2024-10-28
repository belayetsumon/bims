package itgarden.services.homevisit;

import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.M_Address;
import itgarden.model.homevisit.M_Approval;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Reasons;
import itgarden.model.observation.O_Induction;
import itgarden.model.observation.O_MHealthConditions;
import itgarden.services.DateConverter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.time.LocalDate;
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
public class MotherMasterDataServices {

    @PersistenceContext
    EntityManager em;

    private final DateConverter dateConverter;

    public MotherMasterDataServices(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }

    public List<Long> motherMasterDataIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        cq.multiselect(root.get("id"));

//        List<Predicate> predicates = new ArrayList<Predicate>();
//
//      
//        predicates.add(cb.between(rootSm.get("starttime"), thirtyMinutesAgo, currentTime));
//
//        cq.where(predicates.toArray(new Predicate[]{}));
        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get(0, Long.class);
            idList.add(id);
        }
        return idList;
    }

    //   Pending  Mother List for add details
    public List<MotherMasterDataDTO> findAllByEligibilityAndMaddressIsNullOrderByIdDesc(Eligibility eligibility) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<MotherMasterData> root = criteriaQuery.from(MotherMasterData.class);

        // Use a left join to check for children
        Join<MotherMasterData, M_Address> childrenJoin = root.join("mchildinfo", JoinType.LEFT);

        // Create predicates
        Predicate eligibilityPredicate = criteriaBuilder.equal(root.get("eligibility"), eligibility);

        Predicate addressIsNullPredicate = criteriaBuilder.isNull(childrenJoin.get("id"));

        // Combine predicates
        criteriaQuery.where(criteriaBuilder.and(eligibilityPredicate, addressIsNullPredicate));

        // Set the order
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

        // Use multiselect to select scalar fields
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("eligibility").alias("eligibility"),
                root.get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("dateReferral").alias("dateReferral"),
                root.get("mobileNumber").alias("mobileNumber"),
                root.get("referredFrom").alias("referredFrom"),
                root.get("resons").alias("resons"),
                root.get("motherName").alias("motherName"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                root.get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
                root.get("created").alias("created")
        );

        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);
        List<Tuple> results = query.getResultList();

        // Prepare DTOs
        List<MotherMasterDataDTO> motherList = new ArrayList<>();
        for (Tuple tuple : results) {
            MotherMasterDataDTO motherDTO = new MotherMasterDataDTO();

            motherDTO.setId(tuple.get("id", Long.class));

            motherDTO.setEligibility(tuple.get("eligibility", Eligibility.class));

            motherDTO.setHomeVisitDate(tuple.get("homeVisitDate", LocalDate.class));

            motherDTO.setMotherMasterCode(tuple.get("motherMasterCode", String.class));

            motherDTO.setDateReferral(tuple.get("dateReferral", LocalDate.class));

            motherDTO.setResons(tuple.get("resons", Reasons.class));

            motherDTO.setHomeVisitDate(tuple.get("homeVisitDate", LocalDate.class));

            motherDTO.setMotherName(tuple.get("motherName", String.class));

            motherDTO.setDateOfBirth(tuple.get("dateOfBirth", LocalDate.class));

            motherDTO.setNumberOfEligibleChildren(tuple.get("numberOfEligibleChildren", Integer.class));

            motherDTO.setCreated(tuple.get("created", LocalDate.class));

            motherList.add(motherDTO);
        }

        return motherList;
    }

    // All details complete mother List
    public List<MotherMasterDataDTO> findAllByOrderByIdDesc() {
        // Step 1: Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        // Step 2: Define the root
        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        // Step 3: Specify fields to select (using multiselect if needed)
        // Step 3: Specify fields to select (multiselect)
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("eligibility").alias("eligibility"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("dateReferral").alias("dateReferral"),
                root.get("mobileNumber").alias("mobileNumber"),
                root.get("referredFrom").alias("referredFrom"),
                root.get("resons").alias("resons"),
                root.get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherName").alias("motherName"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                root.get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
                root.get("created").alias("created")
        );

        // Step 4: Order by ID in descending order
        cq.orderBy(cb.desc(root.get("id")));

        // Step 5: Create and execute the query
        TypedQuery<Tuple> query = em.createQuery(cq);
        List<Tuple> results = query.getResultList();
        List<MotherMasterDataDTO> motherList = new ArrayList<>();
        for (Tuple tuple : results) {
            MotherMasterDataDTO motherDTO = new MotherMasterDataDTO();
            motherDTO.setId(tuple.get("id", Long.class));

            motherDTO.setEligibility(tuple.get("eligibility", Eligibility.class));

            motherDTO.setMotherMasterCode(tuple.get("motherMasterCode", String.class));

            motherDTO.setDateReferral(tuple.get("dateReferral", LocalDate.class));

            motherDTO.setResons(tuple.get("resons", Reasons.class));

            motherDTO.setHomeVisitDate(tuple.get("homeVisitDate", LocalDate.class));

            motherDTO.setMotherName(tuple.get("motherName", String.class));

            motherDTO.setDateOfBirth(tuple.get("dateOfBirth", LocalDate.class));

            motherDTO.setNumberOfEligibleChildren(tuple.get("numberOfEligibleChildren", Integer.class));

            motherDTO.setCreated(tuple.get("created", LocalDate.class));

            motherList.add(motherDTO);
        }

        return motherList;
    }

    // new mother child list search
    public List<MotherMasterDataDTO> findAllByMchildinfoIsNullAndNumberOfEligibleChildrenGreaterThanAndMapprovalDecissionOrderByIdDesc() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<MotherMasterData> motherRoot = criteriaQuery.from(MotherMasterData.class);

        // Join with Approval
        Join<MotherMasterData, M_Approval> approvalJoin = motherRoot.join("mapproval", JoinType.LEFT);
        // Not joining childinfo since we're checking if it's null
        // Join<MotherMasterData, M_Child_info> childinfo = motherRoot.join("mchildinfo");

        // Multiselect to retrieve required fields
        criteriaQuery.multiselect(
                motherRoot.get("id").alias("id"),
                motherRoot.get("homeVisitDate").alias("homeVisitDate"),
                motherRoot.get("motherMasterCode").alias("motherMasterCode"),
                motherRoot.get("motherName").alias("motherName"),
                motherRoot.get("mobileNumber").alias("mobileNumber"),
                motherRoot.get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
                approvalJoin.get("decission").alias("decission"),
                approvalJoin.get("approveDate").alias("approveDate"),
                approvalJoin.get("approveBy").alias("approveBy")
        );

        // Apply conditions
        criteriaQuery.where(
                //            criteriaBuilder.isNull(motherRoot.join("mchildinfo")), // Ensure this join doesn't affect the result
                criteriaBuilder.isEmpty(motherRoot.get("mchildinfo")),
                criteriaBuilder.gt(motherRoot.get("numberOfEligibleChildren"), 0),
                criteriaBuilder.equal(approvalJoin.get("decission"), Decision.Approve)
        );

        // Order by ID in descending order
        criteriaQuery.orderBy(criteriaBuilder.desc(motherRoot.get("id")));

        // Execute query
        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);
        List<Tuple> results = query.getResultList();

        // Map results to DTO
        List<MotherMasterDataDTO> motherList = new ArrayList<>();
        for (Tuple tuple : results) {

            MotherMasterDataDTO motherDTO = new MotherMasterDataDTO();

            motherDTO.setId(tuple.get("id", Long.class));

            motherDTO.setHomeVisitDate(tuple.get("homeVisitDate", LocalDate.class));

            motherDTO.setMotherMasterCode(tuple.get("motherMasterCode", String.class));

            motherDTO.setMotherName(tuple.get("motherName", String.class));

            motherDTO.setMobileNumber(tuple.get("mobileNumber", String.class));

            motherDTO.setNumberOfEligibleChildren(tuple.get("numberOfEligibleChildren", Integer.class));

            motherDTO.setEducationLevel(tuple.get("approveBy", String.class));

            motherDTO.setDateOfBirth(tuple.get("approveDate", LocalDate.class));

            motherList.add(motherDTO);
        }

        return motherList;
    }

// All Child List
    public List<Map<String, Object>> findByMchildinfoIsNotNullOrderByIdDesc() {
        // Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // Create CriteriaQuery for Tuple
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

        // Define the root
        Root<MotherMasterData> root = criteriaQuery.from(MotherMasterData.class);

        // Join M_Child_info
        Join<MotherMasterData, M_Child_info> childJoin = root.join("mchildinfo", JoinType.LEFT);

        // Create a subquery for counting M_Child_info
        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<M_Child_info> childInfoRoot = subquery.from(M_Child_info.class);

        subquery.select(criteriaBuilder.count(childInfoRoot))
                .where(criteriaBuilder.equal(childInfoRoot.get("motherMasterCode"), root));

        // Set the select clause
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherName").alias("motherName"),
                root.get("mobileNumber").alias("mobileNumber"),
                root.get("mapproval").get("approveDate").alias("approveDate"),
                root.get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
                subquery.alias("childCount") // Use subquery for child count
        );

        // Set the where clause to filter out mothers with no children
        criteriaQuery.where(criteriaBuilder.greaterThan(subquery, 0L));

        criteriaQuery.groupBy(root.get("id"));

        // Set the order by clause
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

        // Execute the query
        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);
        List<Tuple> results = query.getResultList();

        // Convert results to a list of maps
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Tuple tuple : results) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", tuple.get("id", Long.class));
            resultMap.put("homeVisitDate", dateConverter.convertDateFormat(tuple.get("homeVisitDate", LocalDate.class)));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode", String.class));
            resultMap.put("motherName", tuple.get("motherName", String.class));
            resultMap.put("mobileNumber", tuple.get("mobileNumber", String.class));
            resultMap.put("approveDate", dateConverter.convertDateFormat(tuple.get("approveDate", LocalDate.class)));
            resultMap.put("numberOfEligibleChildren", tuple.get("numberOfEligibleChildren", Integer.class));
            resultMap.put("insertedchild", tuple.get("childCount", Long.class)); // This should now be Long
            resultList.add(resultMap);
        }

        return resultList;
    }

    // Mother List For Induction
    public List<Map<String, Object>> findMotherDataWithApprovalAndInductionNull() { // Field from M_Approval

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<MotherMasterData> root = criteriaQuery.from(MotherMasterData.class);

        // Join with M_Approval and left join O_Induction
        Join<MotherMasterData, M_Approval> approvalJoin = root.join("mapproval", JoinType.LEFT);

        Join<MotherMasterData, O_Induction> inductionJoin = root.join("oinduction", JoinType.LEFT);

        // Create predicates for each condition
        List<Predicate> predicates = new ArrayList<>();

        // Condition for M_Approval
        predicates.add(criteriaBuilder.equal(root.get("mapproval").get("decission"), Decision.Approve)); // Adjust as needed

        // Condition for O_Induction to be null
        predicates.add(criteriaBuilder.isNull(inductionJoin)); // Check if O_Induction is null

        // Combine all predicates
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Specify the fields to select with aliases
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherName").alias("motherName"),
                root.get("mobileNumber").alias("mobileNumber"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                root.get("mapproval").get("decission").alias("decission"),
                root.get("mapproval").get("approveDate").alias("approveDate")
        );

        // Execute the query
        List<Tuple> results = em.createQuery(criteriaQuery).getResultList();

        // Convert results to List<Map<String, Object>>
        List<Map<String, Object>> mappedResults = new ArrayList<>();
        for (Tuple result : results) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", result.get("id", Long.class));

            map.put("motherMasterCode", result.get("motherMasterCode", String.class));

            map.put("homeVisitDate", dateConverter.convertDateFormat(result.get("homeVisitDate", LocalDate.class)));

            map.put("motherName", result.get("motherName", String.class));

            map.put("mobileNumber", result.get("mobileNumber", String.class));

            map.put("dateOfBirth", dateConverter.convertDateFormat(result.get("dateOfBirth", LocalDate.class)));

            map.put("decission", result.get("decission", Decision.class));

            map.put("approveDate", dateConverter.convertDateFormat(result.get("approveDate", LocalDate.class)));

            mappedResults.add(map);
        }

        return mappedResults;
    }

    // Pending Mother List for health chekup
    public List<Map<String, Object>> findByOinductionIsNotNullAndOinductionOmHealthConditionsIsNullOrderByIdDesc() { // Field from M_Approval

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<MotherMasterData> root = criteriaQuery.from(MotherMasterData.class);

        // Join with M_Approval and left join O_Induction
        Join<MotherMasterData, M_Approval> approvalJoin = root.join("mapproval", JoinType.LEFT);

        Join<MotherMasterData, O_Induction> inductionJoin = root.join("oinduction", JoinType.LEFT);

        // Create predicates for each condition
        List<Predicate> predicates = new ArrayList<>();

        // Condition for M_Approval
        predicates.add(criteriaBuilder.equal(root.get("mapproval").get("decission"), Decision.Approve)); // Adjust as needed

        // Condition for O_Induction to be null
        predicates.add(criteriaBuilder.isNull(inductionJoin)); // Check if O_Induction is null

        // Combine all predicates
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Specify the fields to select with aliases
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherName").alias("motherName"),
                root.get("mobileNumber").alias("mobileNumber"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                root.get("mapproval").get("decission").alias("decission"),
                root.get("mapproval").get("approveDate").alias("approveDate")
        );

        // Execute the query
        List<Tuple> results = em.createQuery(criteriaQuery).getResultList();

        // Convert results to List<Map<String, Object>>
        List<Map<String, Object>> mappedResults = new ArrayList<>();
        for (Tuple result : results) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", result.get("id", Long.class));

            map.put("motherMasterCode", result.get("motherMasterCode", String.class));

            map.put("homeVisitDate", dateConverter.convertDateFormat(result.get("homeVisitDate", LocalDate.class)));

            map.put("motherName", result.get("motherName", String.class));

            map.put("mobileNumber", result.get("mobileNumber", String.class));

            map.put("dateOfBirth", dateConverter.convertDateFormat(result.get("dateOfBirth", LocalDate.class)));

            map.put("decission", result.get("decission", Decision.class));

            map.put("approveDate", dateConverter.convertDateFormat(result.get("approveDate", LocalDate.class)));

            mappedResults.add(map);
        }

        return mappedResults;
    }

// pending mother List for helthchek up
    public List<Map<String, Object>> findMotherMasterDataWithInductionNotNullAndHealthConditionsNull() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

        Root<MotherMasterData> root = criteriaQuery.from(MotherMasterData.class);

        // Join with O_Induction and O_MHealthConditions
//    Join<MotherMasterData, M_Approval> approvalJoin = root.join("mapproval", JoinType.LEFT);
        Join<MotherMasterData, O_Induction> inductionJoin = root.join("oinduction",JoinType.LEFT);
        Join<MotherMasterData, O_MHealthConditions> healthConditionsJoin = root.join("omHealthConditions", JoinType.LEFT);

        // Create predicates for the conditions
        List<Predicate> predicates = new ArrayList<>();

        // predicates.add(criteriaBuilder.equal(root.get("mapproval").get("decission"), Decision.Approve));
        predicates.add(criteriaBuilder.isNotNull(inductionJoin)); // O_Induction is not null

        predicates.add(criteriaBuilder.isNull(healthConditionsJoin)); // O_MHealthConditions is null

        // Combine predicates
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Specify the fields to select with aliases
        criteriaQuery.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherName").alias("motherName"),
                root.get("mobileNumber").alias("mobileNumber"),
                root.get("oinduction").get("startDate").alias("oinductionStartDate"),
                root.get("oinduction").get("endDate").alias("oinductionEndDate")
        ).orderBy(criteriaBuilder.desc(root.get("id"))); // Order by id descending

        // Execute the query
        List<Tuple> results = em.createQuery(criteriaQuery).getResultList();

        // Convert results to List<Map<String, Object>>
        List<Map<String, Object>> mappedResults = new ArrayList<>();
        for (Tuple tuple : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id",Long.class));
            map.put("motherMasterCode", tuple.get("motherMasterCode",String.class));
            map.put("homeVisitDate", dateConverter.convertDateFormat(tuple.get("homeVisitDate", LocalDate.class)));
            map.put("motherName", tuple.get("motherName",String.class));
            map.put("mobileNumber", tuple.get("mobileNumber",String.class));
            map.put("startDate",  dateConverter.convertDateFormat(tuple.get("oinductionStartDate",LocalDate.class)));
            map.put("endDate", tuple.get("oinductionEndDate",LocalDate.class));
            mappedResults.add(map);
        }
        return mappedResults;
    }

    
    
    
    
    
//    public List<MotherMasterDataDTO> findByOinductionIsNullAndMapprovalDecission() {
//        // Create CriteriaBuilder
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//
//        // Create CriteriaQuery for MotherMasterData
//        CriteriaQuery<MotherMasterData> criteriaQuery = criteriaBuilder.createQuery(MotherMasterData.class);
//
//        // Define the root
//        Root<MotherMasterData> root = criteriaQuery.from(MotherMasterData.class);
//
//        // Create predicates for the conditions
//        Predicate oinductionIsNull = criteriaBuilder.isNull(root.get("oinduction"));
//        Predicate mapprovalDecisionEquals = criteriaBuilder.equal(root.get("mapprovalDecission"), Decision.Approve);
//
//        // Combine predicates using AND
//        criteriaQuery.where(criteriaBuilder.and(oinductionIsNull, mapprovalDecisionEquals));
//
//        // Set the order by clause
//        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
//
//        // Execute the query
//        TypedQuery<MotherMasterData> query = em.createQuery(criteriaQuery);
//        List<MotherMasterData> results = query.getResultList();
//
//        // Convert results to DTOs
//        List<MotherMasterDataDTO> motherList = new ArrayList<>();
//        for (MotherMasterData mother : results) {
//            MotherMasterDataDTO motherDTO = new MotherMasterDataDTO();
//            motherDTO.setId(mother.getId());
//            motherDTO.setMotherName(mother.getMotherName());
//            // Map other properties as needed
//            motherList.add(motherDTO);
//        }
//
//        return motherList;
//    }
//    
//    public List<MotherMasterDataDTO> findAllByEligibilityAndMaddressIsNullOrderByIdDesc(Eligibility eligibility) {
//
//        // Create CriteriaBuilder
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//
//        // Create CriteriaQuery for Tuple
//        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
//
//        // Define the root
//        Root<MotherMasterData> root = criteriaQuery.from(MotherMasterData.class);
//
//        // Create predicates
//        Predicate eligibilityPredicate = criteriaBuilder.equal(root.get("eligibility"), eligibility);
//       
//        Predicate addressIsNullPredicate = criteriaBuilder.isNull(root.get("maddress"));
//
//        // Combine predicates
//        criteriaQuery.where(criteriaBuilder.and(eligibilityPredicate, addressIsNullPredicate));
//
//        // Set the order
//        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
//
//        // Use multiselect to select multiple fields
//        criteriaQuery.multiselect(
//                root.get("id").alias("id"),
//                root.get("homeVisitDate").alias("homeVisitDate"),
//                root.get("motherMasterCode").alias("motherMasterCode"),
//                root.get("motherName").alias("motherName"),
//                root.get("dateOfBirth").alias("dateOfBirth"),
//                root.get("eligibility").alias("eligibility")
//                
//        //                root.get("visitOfficersName").alias("visitOfficersName"),
//        //                root.get("dateReferral").alias("dateReferral"),
//        //                root.get("referredFrom").alias("referredFrom"),
//        //                root.get("resons").alias("resons"),
//        //
//        //                root.get("age").alias("age"),
//        //                root.get("mMothersName").alias("mMothersName"),
//        //                root.get("fathersName").alias("fathersName"),
//        //                root.get("mobileNumber").alias("mobileNumber"),
//        //                root.get("religion").alias("religion"),
//        //                root.get("religionId").alias("religionId"),
//        //                root.get("maritalStatus").alias("maritalStatus"),
//        //                root.get("maritalStatusId").alias("maritalStatusId"),
//        //                root.get("husbandsName").alias("husbandsName"),
//        //                root.get("husbandsStatus").alias("husbandsStatus"),
//        //                root.get("husbandsStatusId").alias("husbandsStatusId"),
//        //                root.get("primeFamilyMemberName").alias("primeFamilyMemberName"),
//        //                root.get("relationWithPfm").alias("relationWithPfm"),
//        //                root.get("relationWithPfmId").alias("relationWithPfmId"),
//        //                root.get("ethnicIdentity").alias("ethnicIdentity"),
//        //                root.get("ethnicIdentityId").alias("ethnicIdentityId"),
//        //                root.get("educationLevel").alias("educationLevel"),
//        //                root.get("educationLevelId").alias("educationLevelId"),
//        //                root.get("educationType").alias("educationType"),
//        //                root.get("educationTypeId").alias("educationTypeId"),
//        //                root.get("occupation").alias("occupation"),
//        //                root.get("occupationId").alias("occupationId"),
//        //                root.get("physicalStatus").alias("physicalStatus"),
//        //                root.get("immunization").alias("immunization"),
//        //                root.get("numberOfSons").alias("numberOfSons"),
//        //                root.get("numberOfDaughters").alias("numberOfDaughters"),
//        //                root.get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
//        //                root.get("majorFindings").alias("majorFindings"),
//        //                root.get("socialviolence").alias("socialviolence"),
//        //                root.get("childrenFacedSocialViolence").alias("childrenFacedSocialViolence"),
//        //                root.get("sexualAbuse").alias("sexualAbuse"),
//        //                root.get("childrenSexualAbuse").alias("childrenSexualAbuse"),
//        //                root.get("earlyMarriage").alias("earlyMarriage"),
//        //                root.get("pregnancyAfterBeingRaped").alias("pregnancyAfterBeingRaped"),
//        //                root.get("facedDowryAbuse").alias("facedDowryAbuse"),
//        //                root.get("otherRemarks").alias("otherRemarks"),
//        //               
//        );
//
//        // Create and execute the query
//        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);
//        List<Tuple> results = query.getResultList();
//
//        // Prepare a list to hold DTOs
//        List<MotherMasterDataDTO> motherList = new ArrayList<>();
//
//        // Use forEach to map tuples to DTOs
//        for (Tuple tuple : results) {
//            MotherMasterDataDTO motherDTO = new MotherMasterDataDTO();
//
//            motherDTO.setId(tuple.get("id", Long.class));
//            motherDTO.setHomeVisitDate(tuple.get("homeVisitDate", LocalDate.class));
//            motherDTO.setMotherMasterCode(tuple.get("motherMasterCode", String.class));
//            motherDTO.setMotherName(tuple.get("motherName", String.class));
//            motherDTO.setDateOfBirth(tuple.get("dateOfBirth", LocalDate.class));
//            motherDTO.setEligibility(tuple.get("eligibility", String.class));
//            motherList.add(motherDTO);
//        }
//
////        results.forEach(tuple -> {
////            MotherMasterDataDTO motherDTO = new MotherMasterDataDTO(
////                    tuple.get("id", Long.class),
////                    tuple.get("homeVisitDate", LocalDate.class),
////                    tuple.get("motherMasterCode", String.class),
////                    tuple.get("motherName", String.class)
////            tuple.get("dateOfBirth", LocalDate.class)
////            ,
////                    tuple.get("eligibility", String.class) //                    tuple.get("visitOfficersName", String.class),
////                    //                    tuple.get("dateReferral", LocalDate.class),
////                    //                    tuple.get("referredFrom", String.class),
////                    //                    tuple.get("resons", Reasons.class),                        
////                    //                    tuple.get("age", String.class),
////                    //                    tuple.get("mMothersName", String.class),
////                    //                    tuple.get("fathersName", String.class),
////                    //                    tuple.get("mobileNumber", String.class),
////                    //                    tuple.get("religion", String.class),
////                    //                    tuple.get("religionId", Long.class),
////                    //                    tuple.get("maritalStatus", String.class),
////                    //                    tuple.get("maritalStatusId", Long.class),
////                    //                    tuple.get("husbandsName", String.class),
////                    //                    tuple.get("husbandsStatus", String.class),
////                    //                    tuple.get("husbandsStatusId", Long.class),
////                    //                    tuple.get("primeFamilyMemberName", String.class),
////                    //                    tuple.get("relationWithPfm", String.class),
////                    //                    tuple.get("relationWithPfmId", Long.class),
////                    //                    tuple.get("ethnicIdentity", String.class),
////                    //                    tuple.get("ethnicIdentityId", Long.class),
////                    //                    tuple.get("educationLevel", String.class),
////                    //                    tuple.get("educationLevelId", Long.class),
////                    //                    tuple.get("educationType", String.class),
////                    //                    tuple.get("educationTypeId", Long.class),
////                    //                    tuple.get("occupation", String.class),
////                    //                    tuple.get("occupationId", Long.class),
////                    //                    tuple.get("physicalStatus", String.class),
////                    //                    tuple.get("immunization", String.class),
////                    //                    tuple.get("numberOfSons", Integer.class),
////                    //                    tuple.get("numberOfDaughters", Integer.class),
////                    //                    tuple.get("numberOfEligibleChildren", Integer.class),
////                    //                    tuple.get("majorFindings", String.class),
////                    //                    tuple.get("socialviolence", Yes_No.class),
////                    //                    tuple.get("childrenFacedSocialViolence", Yes_No.class),
////                    //                    tuple.get("sexualAbuse", Yes_No.class),
////                    //                    tuple.get("childrenSexualAbuse", Yes_No.class),
////                    //                    tuple.get("earlyMarriage", Yes_No.class),
////                    //                    tuple.get("pregnancyAfterBeingRaped", Yes_No.class),
////                    //                    tuple.get("facedDowryAbuse", Yes_No.class),
////                    //                    tuple.get("otherRemarks", String.class)
////                    //             
////            );
////            motherList.add(motherDTO);
////        });
//
//        return motherList;
//    }
}
