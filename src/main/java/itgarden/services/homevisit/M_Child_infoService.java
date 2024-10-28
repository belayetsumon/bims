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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
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
public class M_Child_infoService {

    @PersistenceContext
    EntityManager em;

    public List<Map<String, Object>> childSearchResult(
            LocalDate startDate,
            LocalDate endDate,
            String motherMasterCode,
            String childMasterCode,
            String motherName,
            String childName,
            LocalDate dateOfBirth,
            Gender gender,
            EducationLevel educationLevel,
            EducationType educationType,
            Eligibility eligibility
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<M_Child_info> root = cq.from(M_Child_info.class);

        List<Predicate> predicates = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            predicates.add(cb.between(root.get("created"), startDate, endDate));
        }

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isEmpty(endDate)) {
            predicates.add(cb.equal(root.get("created"), startDate));
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
            predicates.add(cb.equal(root.get("dateOfBirth"), dateOfBirth));
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
}
