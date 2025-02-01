/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.homevisit;

import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.EducationType;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.EthinicIdentity;
import itgarden.model.homevisit.HusbandsStatus;
import itgarden.model.homevisit.MaritalStatus;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Occupation;
import itgarden.model.homevisit.Reasons;
import itgarden.model.homevisit.Relations;
import itgarden.model.homevisit.Religion;
import itgarden.model.homevisit.Yes_No;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class MotherMasterDataReportService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> matherMasterdataSearchResult(
            String startDate,
            String endDate,
            Reasons resons,
            String motherMasterCode,
            String motherName,
            String dateOfBirth,
            String mobileNumber,
            Religion religion,
            MaritalStatus maritalStatus,
            HusbandsStatus husbandsStatus,
            Relations relationWithPfm,
            EthinicIdentity ethnicIdentity,
            EducationLevel educationLevel,
            EducationType educationType,
            Occupation occupation,
            String numberOfSons,
            String numberOfDaughters,
            String numberOfEligibleChildren,
            Yes_No socialviolence,
            Yes_No childrenFacedSocialViolence,
            Yes_No sexualAbuse,
            Yes_No childrenSexualAbuse,
            Yes_No earlyMarriage,
            Yes_No pregnancyAfterBeingRaped,
            Yes_No facedDowryAbuse,
            Eligibility eligibility
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("created"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("created"), start));
        }

        if (ObjectUtils.isNotEmpty(resons)) {
            predicates.add(cb.equal(root.get("resons"), resons));
        }

        if (StringUtils.isNoneEmpty(motherMasterCode)) {
            predicates.add(cb.equal(root.get("motherMasterCode"), motherMasterCode));
        }
        if (StringUtils.isNoneEmpty(motherName)) {
            predicates.add(cb.equal(root.get("motherName"), motherName));
        }
        if (ObjectUtils.isNotEmpty(dateOfBirth)) {
          LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
            predicates.add(cb.equal(root.get("dateOfBirth"), dob));
        }

        if (StringUtils.isNoneEmpty(mobileNumber)) {
            predicates.add(cb.equal(root.get("mobileNumber"), mobileNumber));
        }

        if (ObjectUtils.isNotEmpty(religion)) {
            predicates.add(cb.equal(root.get("religion"), religion));
        }
        if (ObjectUtils.isNotEmpty(maritalStatus)) {
            predicates.add(cb.equal(root.get("maritalStatus"), maritalStatus));
        }
        if (ObjectUtils.isNotEmpty(husbandsStatus)) {
            predicates.add(cb.equal(root.get("husbandsStatus"), husbandsStatus));
        }
        if (ObjectUtils.isNotEmpty(relationWithPfm)) {
            predicates.add(cb.equal(root.get("relationWithPfm"), relationWithPfm));
        }
        if (ObjectUtils.isNotEmpty(ethnicIdentity)) {
            predicates.add(cb.equal(root.get("ethnicIdentity"), ethnicIdentity));
        }
        if (ObjectUtils.isNotEmpty(educationLevel)) {
            predicates.add(cb.equal(root.get("educationLevel"), educationLevel));
        }
        if (ObjectUtils.isNotEmpty(educationType)) {
            predicates.add(cb.equal(root.get("educationType"), educationType));
        }
        if (ObjectUtils.isNotEmpty(occupation)) {
            predicates.add(cb.equal(root.get("occupation"), occupation));
        }

        if (ObjectUtils.isNotEmpty(numberOfSons)) {
            predicates.add(cb.equal(root.get("numberOfSons"), numberOfSons));
        }

        if (ObjectUtils.isNotEmpty(numberOfDaughters)) {
            predicates.add(cb.equal(root.get("numberOfDaughters"), numberOfDaughters));
        }

        if (ObjectUtils.isNotEmpty(numberOfEligibleChildren)) {
            predicates.add(cb.equal(root.get("numberOfEligibleChildren"), numberOfEligibleChildren));
        }

        if (ObjectUtils.isNotEmpty(socialviolence)) {
            predicates.add(cb.equal(root.get("socialviolence"), socialviolence));
        }
        if (ObjectUtils.isNotEmpty(childrenFacedSocialViolence)) {
            predicates.add(cb.equal(root.get("childrenFacedSocialViolence"), childrenFacedSocialViolence));
        }

        if (ObjectUtils.isNotEmpty(sexualAbuse)) {
            predicates.add(cb.equal(root.get("sexualAbuse"), sexualAbuse));
        }

        if (ObjectUtils.isNotEmpty(childrenSexualAbuse)) {
            predicates.add(cb.equal(root.get("childrenSexualAbuse"), childrenSexualAbuse));
        }

        if (ObjectUtils.isNotEmpty(earlyMarriage)) {
            predicates.add(cb.equal(root.get("earlyMarriage"), earlyMarriage));
        }
        if (ObjectUtils.isNotEmpty(pregnancyAfterBeingRaped)) {
            predicates.add(cb.equal(root.get("pregnancyAfterBeingRaped"), pregnancyAfterBeingRaped));
        }

        if (ObjectUtils.isNotEmpty(facedDowryAbuse)) {
            predicates.add(cb.equal(root.get("facedDowryAbuse"), facedDowryAbuse));
        }

        if (ObjectUtils.isNotEmpty(eligibility)) {
            predicates.add(cb.equal(root.get("eligibility"), eligibility));
        }

//        
//
//        // Combine all predicates with AND
        // Multi-select with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("visitOfficersName").alias("officersName"),
                root.get("dateReferral").alias("referralDate"),
                root.get("referredFrom").alias("referredFrom"),
                root.get("resons").get("name").alias("resons"),
                root.get("homeVisitDate").alias("homeVisitDate"),
                root.get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherName").alias("motherFullName"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                root.get("age").alias("ageYears"),
                root.get("mMothersName").alias("mmothersName"),
                root.get("mobileNumber").alias("mobileNumber"),
                root.get("religion").get("name").alias("religion"),
                root.get("maritalStatus").get("name").alias("maritalStatus"),
                root.get("husbandsName").alias("husbandName"),
                root.get("husbandsStatus").get("name").alias("husbandStatus"),
                root.get("primeFamilyMemberName").alias("familyMemberName"),
                root.get("relationWithPfm").alias("relationToPfm"),
                root.get("ethnicIdentity").get("name").alias("ethnicity"),
                root.get("educationLevel").get("name").alias("educationLevelDetails"),
                root.get("educationType").get("name").alias("educationTypeDetails"),
                root.get("occupation").get("name").alias("occupationDetails"),
                root.get("physicalStatus").alias("physicalCondition"),
                root.get("immunization").alias("immunizationStatus"),
                root.get("numberOfSons").alias("sonsCount"),
                root.get("numberOfDaughters").alias("daughtersCount"),
                root.get("numberOfEligibleChildren").alias("eligibleChildrenCount"),
                root.get("majorFindings").alias("findings"),
                root.get("socialviolence").alias("socialViolenceStatus"),
                root.get("childrenFacedSocialViolence").alias("childrenSocialViolenceStatus"),
                root.get("sexualAbuse").alias("sexualAbuseStatus"),
                root.get("childrenSexualAbuse").alias("childrenSexualAbuseStatus"),
                root.get("earlyMarriage").alias("earlyMarriageStatus"),
                root.get("pregnancyAfterBeingRaped").alias("pregnancyStatus"),
                root.get("facedDowryAbuse").alias("dowryAbuseStatus"),
                root.get("otherRemarks").alias("additionalRemarks"),
                root.get("eligibility").alias("eligibilityStatus"),
                root.get("created").alias("created")
        );
        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        cq.orderBy(cb.desc(root.get("id")));

        // Execute the query
        // Execute the query
        List<Tuple> results = em.createQuery(cq).getResultList();

        System.out.println("bfdgdfgdf----------------------------" + results.size());
        // Convert results to List<Map<String, Object>>
        List<Map<String, Object>> mappedResults = new ArrayList<>();

        for (Tuple result : results) {

            Map<String, Object> aliasMap = new HashMap<>();

            aliasMap.put("id", result.get("id"));
            aliasMap.put("visitOfficersName", result.get("officersName"));
            aliasMap.put("dateReferral", result.get("referralDate"));
            aliasMap.put("referredFrom", result.get("referredFrom"));
            aliasMap.put("resons", result.get("resons"));
            aliasMap.put("homeVisitDate", result.get("homeVisitDate"));
            aliasMap.put("motherMasterCode", result.get("motherMasterCode"));
            aliasMap.put("motherName", result.get("motherFullName"));
            aliasMap.put("dateOfBirth", result.get("dateOfBirth"));
            aliasMap.put("age", result.get("ageYears"));
            aliasMap.put("mMothersName", result.get("mmothersName"));
            aliasMap.put("mobileNumber", result.get("mobileNumber"));
            aliasMap.put("religion", result.get("religion"));
            aliasMap.put("maritalStatus", result.get("maritalStatus"));
            aliasMap.put("husbandsName", result.get("husbandName"));
            aliasMap.put("husbandsStatus", result.get("husbandStatus"));
            aliasMap.put("primeFamilyMemberName", result.get("familyMemberName"));
            aliasMap.put("relationWithPfm", result.get("relationToPfm"));
            aliasMap.put("ethnicIdentity", result.get("ethnicity"));
            aliasMap.put("educationLevel", result.get("educationLevelDetails"));
            aliasMap.put("educationType", result.get("educationTypeDetails"));
            aliasMap.put("occupation", result.get("occupationDetails"));
            aliasMap.put("physicalStatus", result.get("physicalCondition"));
            aliasMap.put("immunization", result.get("immunizationStatus"));
            aliasMap.put("numberOfSons", result.get("sonsCount"));
            aliasMap.put("numberOfDaughters", result.get("daughtersCount"));
            aliasMap.put("numberOfEligibleChildren", result.get("eligibleChildrenCount"));
            aliasMap.put("majorFindings", result.get("findings"));
            aliasMap.put("socialviolence", result.get("socialViolenceStatus"));
            aliasMap.put("childrenFacedSocialViolence", result.get("childrenSocialViolenceStatus"));
            aliasMap.put("sexualAbuse", result.get("sexualAbuseStatus"));
            aliasMap.put("childrenSexualAbuse", result.get("childrenSexualAbuseStatus"));
            aliasMap.put("earlyMarriage", result.get("earlyMarriageStatus"));
            aliasMap.put("pregnancyAfterBeingRaped", result.get("pregnancyStatus"));
            aliasMap.put("facedDowryAbuse", result.get("dowryAbuseStatus"));
            aliasMap.put("otherRemarks", result.get("additionalRemarks"));
            aliasMap.put("eligibility", result.get("eligibilityStatus"));
            aliasMap.put("created", result.get("created"));
            mappedResults.add(aliasMap);
        }
        return mappedResults;
    }

}
