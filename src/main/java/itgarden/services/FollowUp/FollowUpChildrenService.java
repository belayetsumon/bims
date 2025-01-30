/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.FollowUp;

import itgarden.model.follow_up_report.FollowUpChildren;
import itgarden.model.homevisit.M_Child_info;
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
public class FollowUpChildrenService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> find_All_Child_follow_up(
            String startDate,
            String endDate
    ) {
        // Step 1: Get the CriteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Step 2: Create a CriteriaQuery for a Tuple (a group of selected results)
        CriteriaQuery<Tuple> query = cb.createQuery(Tuple.class);

        // Step 3: Define the root entity (FollowUpChildren)
        Root<FollowUpChildren> followUpChildrenRoot = query.from(FollowUpChildren.class);

        // Step 4: Create Aliases for the entities to select
        Join<FollowUpChildren, MotherMasterData> motherMasterCodeJoin = followUpChildrenRoot.join("motherMasterCode", JoinType.LEFT);
        Join<FollowUpChildren, M_Child_info> childMasterCodeJoin = followUpChildrenRoot.join("childMasterCode", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(followUpChildrenRoot.get("visitDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(followUpChildrenRoot.get("visitDate"), start));
        }

        // Step 5: Define multi-select for the tuple
        query.multiselect(
                followUpChildrenRoot.get("id").alias("id"),
                followUpChildrenRoot.get("visitedStaffName").alias("visitedStaffName"),
                followUpChildrenRoot.get("releaseDate").alias("releaseDate"),
                followUpChildrenRoot.get("visitDate").alias("visitDate"),
                followUpChildrenRoot.get("involvedWork").alias("involvedWork"),
                followUpChildrenRoot.get("workNote").alias("workNote"),
                followUpChildrenRoot.get("childClass").alias("childClass"),
                followUpChildrenRoot.get("remarks").alias("remarks"),
                motherMasterCodeJoin.get("motherMasterCode").alias("motherMasterCode"),
                childMasterCodeJoin.get("childMasterCode").alias("childMasterCode")
        );
        query.where(cb.and(predicates.toArray(new Predicate[0])));
        // Step 6: Set the order by `id` descending
        query.orderBy(cb.desc(followUpChildrenRoot.get("id")));

        // Step 7: Execute the query and get the result as a List of Maps (or Tuples)
        List<Tuple> result = entityManager.createQuery(query).getResultList();

        // Convert Tuple to Map to allow easy access by alias
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", tuple.get("id"));
            map.put("visitedStaffName", tuple.get("visitedStaffName"));
            map.put("releaseDate", tuple.get("releaseDate"));
            map.put("visitDate", tuple.get("visitDate"));
            map.put("involvedWork", tuple.get("involvedWork"));
            map.put("workNote", tuple.get("workNote"));
            map.put("childClass", tuple.get("childClass"));
            map.put("remarks", tuple.get("remarks"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
//            map.put("createdBy", tuple.get("createdBy"));
//            map.put("updatedBy", tuple.get("updatedBy"));
            resultList.add(map);
        }

        return resultList;
    }
}
