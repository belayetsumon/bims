/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.homevisit;

import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.EducationType;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.Gender;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.L_HigherStudy;
import itgarden.model.observation.O_ChildAdmission;
import itgarden.model.reintegration_release.ReleaseChild;
import itgarden.model.school.Discontinuity;
import itgarden.model.school.EligibilityStudent;
import itgarden.model.school.S_RegularAdmissionClass;
import itgarden.services.observation.O_ChildAdmissionService;
import itgarden.services.reintegration_release.ReleaseChildService;
import itgarden.services.school.DiscontinuityService;
import itgarden.services.school.EligibilityStudentService;
import itgarden.services.school.S_RegularAdmissionClassService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_Child_infoService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    O_ChildAdmissionService o_ChildAdmissionService;

    @Autowired
    EligibilityStudentService eligibilityStudentService;

    @Autowired
    S_RegularAdmissionClassService s_RegularAdmissionClassService;

    @Autowired
    DiscontinuityService discontinuityService;

    @Autowired
    ReleaseChildService releaseChildService;

    public List<Map<String, Object>> childSearchResult(
            String startDate,
            String endDate,
            String motherMasterCode,
            String childMasterCode,
            String motherName,
            String childName,
            String dateOfBirth,
            Gender gender,
            EducationLevel educationLevel,
            EducationType educationType,
            Eligibility eligibility
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<M_Child_info> root = cq.from(M_Child_info.class);

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

        if (StringUtils.isNoneEmpty(motherMasterCode)) {
            predicates.add(cb.equal(root.get("motherMasterCode"), motherMasterCode));
        }

        if (StringUtils.isNoneEmpty(childMasterCode)) {
            predicates.add(cb.equal(root.get("childMasterCode"), childMasterCode));
        }

        if (StringUtils.isNoneEmpty(motherName)) {
            predicates.add(cb.equal(root.get("motherName"), motherName));
        }
        if (ObjectUtils.isNotEmpty(dateOfBirth)) {
            LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
            predicates.add(cb.equal(root.get("dateOfBirth"), dob));
        }

        if (ObjectUtils.isNotEmpty(educationLevel)) {
            predicates.add(cb.equal(root.get("educationLevel"), educationLevel));
        }
        if (ObjectUtils.isNotEmpty(educationType)) {
            predicates.add(cb.equal(root.get("educationType"), educationType));
        }

        if (ObjectUtils.isNotEmpty(eligibility)) {
            predicates.add(cb.equal(root.get("eligibility"), eligibility));
        }

        // Multi-select with aliases
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("childMasterCode").alias("childMasterCode"),
                root.get("motherName").alias("motherFullName"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                root.get("age").alias("ageYears"),
                root.get("name").alias("name"),
                root.get("gender").alias("gender"),
                root.get("primeFamilyMemberName").alias("familyMemberName"),
                root.get("ethnicIdentity").get("name").alias("ethnicity"),
                root.get("educationLevel").get("name").alias("educationLevelDetails"),
                root.get("educationType").get("name").alias("educationTypeDetails"),
                root.get("physicalStatus").alias("physicalCondition"),
                root.get("immunization").alias("immunizationStatus"),
                root.get("majorFindings").alias("findings"),
                root.get("eligibility").alias("eligibilityStatus"),
                root.get("otherRemarks").alias("otherRemarks"),
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
            aliasMap.put("motherMasterCode", result.get("motherMasterCode"));
            aliasMap.put("childMasterCode", result.get("childMasterCode"));
            aliasMap.put("motherName", result.get("motherFullName"));
            aliasMap.put("dateOfBirth", result.get("dateOfBirth"));
            aliasMap.put("age", result.get("ageYears"));
            aliasMap.put("name", result.get("name"));
            aliasMap.put("gender", result.get("gender"));
            aliasMap.put("primeFamilyMemberName", result.get("familyMemberName"));
            aliasMap.put("ethnicIdentity", result.get("ethnicity"));
            aliasMap.put("educationLevel", result.get("educationLevelDetails"));
            aliasMap.put("educationType", result.get("educationTypeDetails"));
            aliasMap.put("physicalStatus", result.get("physicalCondition"));
            aliasMap.put("immunization", result.get("immunizationStatus"));
            aliasMap.put("majorFindings", result.get("findings"));
            aliasMap.put("otherRemarks", result.get("otherRemarks"));
            aliasMap.put("eligibility", result.get("eligibilityStatus"));
            aliasMap.put("created", result.get("created"));
            mappedResults.add(aliasMap);
        }
        return mappedResults;
    }

    public List<Map<String, Object>> findNotSchoolAdmitedChild() {
        // Obtain CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root of the query (representing the M_Child_info entity)
        Root<M_Child_info> root = cq.from(M_Child_info.class);

        // Multi-select fields with aliases (use alias names)
        // Get the current date
        Expression<java.sql.Date> currentDate = cb.currentDate();

        Path<Date> birthDate = root.get("dateOfBirth");
        // Calculate the difference in years (age)

        // Calculate the difference in years (age)
        Expression<Integer> age = cb.diff(
                cb.function("year", Integer.class, currentDate),
                cb.function("year", Integer.class, birthDate)
        );

        List<Predicate> predicates = new ArrayList<>();
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").alias("childMasterCode"),
                root.get("name").alias("name"),
                root.get("dateOfBirth").alias("dateOfBirth"),
                age.alias("age"),
                root.get("gender").alias("gender"),
                root.get("educationLevel").get("name").alias("educationLevel"),
                root.get("educationType").get("name").alias("educationType"),
                root.get("interstedSkillArea").alias("interstedSkillArea")
        );

        predicates.add(root.get("id").in(o_ChildAdmissionService.addmitedChildIdList()));
        predicates.add(root.get("id").in(eligibilityStudentService.notEligibleChildIdList()).not());
        predicates.add(root.get("id").in(s_RegularAdmissionClassService.S_regular_AddmitedChildIdList()).not());
        predicates.add(root.get("id").in(releaseChildService.allReleasedChildIdList()).not());
        predicates.add(root.get("id").in(discontinuityService.discontinuityChildIdList()).not());

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        cq.orderBy(cb.desc(root.get("id")));

        // Execute the query
        TypedQuery<Tuple> query = em.createQuery(cq);

        // Convert the result to a list of maps
        List<Tuple> resultList = query.getResultList();
        List<Map<String, Object>> resultMapList = new ArrayList<>();

        // Iterate through the result and populate the list of maps
        for (Tuple tuple : resultList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherName", tuple.get("motherName"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("name", tuple.get("name"));
            map.put("dateOfBirth", tuple.get("dateOfBirth"));
             map.put("age", tuple.get("age"));
            map.put("gender", tuple.get("gender"));
            map.put("educationLevel", tuple.get("educationLevel"));
            map.put("educationType", tuple.get("educationType"));
            map.put("interstedSkillArea", tuple.get("interstedSkillArea"));
            resultMapList.add(map);
        }
        return resultMapList;
    }

}
