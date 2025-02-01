/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.FollowUp;

import itgarden.model.follow_up_report.FollowUpMother;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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
public class FollowUpMotherService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> find_Follow_Up_Mother_List(
            String startDate,
            String endDate
    ) {
        // Step 1: Get the CriteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Step 2: Create a CriteriaQuery for a Tuple (to select multiple columns)
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

        // Step 3: Define the root entity (FollowUpMother)
        Root<FollowUpMother> followUpMotherRoot = query.from(FollowUpMother.class);

        // Step 4: Create a Join for the related entity MotherMasterData
        Join<FollowUpMother, MotherMasterData> motherMasterCodeJoin = followUpMotherRoot.join("motherMasterCode", JoinType.LEFT);

        // Step 5: Define multi-select for the tuple
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(followUpMotherRoot.get("FollowUpDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(followUpMotherRoot.get("FollowUpDate"), start));
        }

        query.multiselect(
                followUpMotherRoot.get("id").alias("id"),
                followUpMotherRoot.get("visitedStaffName").alias("visitedStaffName"),
                followUpMotherRoot.get("releaseDate").alias("releaseDate"),
                followUpMotherRoot.get("FollowUpDate").alias("followUpDate"),
                followUpMotherRoot.get("lastFollowUpDate").alias("lastFollowUpDate"),
                followUpMotherRoot.get("numberOfFollowUp").alias("numberOfFollowUp"),
                followUpMotherRoot.get("durationOfStayCommunity").alias("durationOfStayCommunity"),
                followUpMotherRoot.get("addressWithContact").alias("addressWithContact"),
                followUpMotherRoot.get("homeEnvironment").alias("homeEnvironment"),
                followUpMotherRoot.get("worksiteEnvironment").alias("worksiteEnvironment"),
                followUpMotherRoot.get("currentProperty").alias("currentProperty"),
                followUpMotherRoot.get("goNgoSupport").alias("goNgoSupport"),
                followUpMotherRoot.get("sppsavingMoney").alias("sppsavingMoney"),
                followUpMotherRoot.get("presentIga").alias("presentIga"),
                followUpMotherRoot.get("presentChallenges").alias("presentChallenges"),
                followUpMotherRoot.get("resolvedChallenges").alias("resolvedChallenges"),
                followUpMotherRoot.get("expectationsFromSpp").alias("expectationsFromSpp"),
                followUpMotherRoot.get("clinic").alias("clinic"),
                followUpMotherRoot.get("handWash").alias("handWash"),
                followUpMotherRoot.get("recommendation").alias("recommendation"),
                followUpMotherRoot.get("remarks").alias("remarks"),
                motherMasterCodeJoin.get("motherMasterCode").alias("motherMasterCode")
        );

        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // Step 6: Set the order by `id` descending
        query.orderBy(cb.desc(followUpMotherRoot.get("id")));

        // Step 7: Execute the query and get the result as a List of Tuples
        List<Tuple> result = entityManager.createQuery(query).getResultList();

        // Step 8: Convert the result from Tuple to Map for easier access by alias
        List<Map<String, Object>> resultList = new ArrayList<>();

        // Step 9: Loop through the results and manually build the map
        for (Tuple tuple : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("visitedStaffName", tuple.get("visitedStaffName"));
            map.put("releaseDate", tuple.get("releaseDate"));
            map.put("followUpDate", tuple.get("followUpDate"));
            map.put("lastFollowUpDate", tuple.get("lastFollowUpDate"));
            map.put("numberOfFollowUp", tuple.get("numberOfFollowUp"));
            map.put("durationOfStayCommunity", tuple.get("durationOfStayCommunity"));
            map.put("addressWithContact", tuple.get("addressWithContact"));
            map.put("homeEnvironment", tuple.get("homeEnvironment"));
            map.put("worksiteEnvironment", tuple.get("worksiteEnvironment"));
            map.put("currentProperty", tuple.get("currentProperty"));
            map.put("goNgoSupport", tuple.get("goNgoSupport"));
            map.put("sppsavingMoney", tuple.get("sppsavingMoney"));
            map.put("presentIga", tuple.get("presentIga"));
            map.put("presentChallenges", tuple.get("presentChallenges"));
            map.put("resolvedChallenges", tuple.get("resolvedChallenges"));
            map.put("expectationsFromSpp", tuple.get("expectationsFromSpp"));
            map.put("clinic", tuple.get("clinic"));
            map.put("handWash", tuple.get("handWash"));
            map.put("recommendation", tuple.get("recommendation"));
            map.put("remarks", tuple.get("remarks"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));

            resultList.add(map);
        }

        return resultList;
    }
}
