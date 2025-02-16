/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.reintegration_release;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.observation.O_ChildAdmission;
import sppbims.model.reintegration_release.ReleaseMother;
import sppbims.services.observation.O_ChildAdmissionService;
import jakarta.el.Expression;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class ReleaseMotherService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    O_ChildAdmissionService o_ChildAdmissionService;

    public List<Long> allReleasedMotherIdList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<ReleaseMother> root = cq.from(ReleaseMother.class);

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

    public List<Map<String, Object>> all_Release_MotherList() {
        // Get CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Define the root entity for the query
        Root<ReleaseMother> releaseMotherRoot = criteriaQuery.from(ReleaseMother.class);

        // Define joins for related entities
        Join<ReleaseMother, MotherMasterData> motherMasterDataJoin = releaseMotherRoot.join("motherMasterCode", JoinType.LEFT);
        Join<ReleaseMother, Users> createdByJoin = releaseMotherRoot.join("createdBy", JoinType.LEFT);
        Join<ReleaseMother, Users> updatedByJoin = releaseMotherRoot.join("updatedBy", JoinType.LEFT);

        // Instead of calling someMethod(motherId), use a subquery
        Subquery<Long> admited_Child_By_mother = criteriaQuery.subquery(Long.class);
        Root<O_ChildAdmission> childRoot = admited_Child_By_mother.from(O_ChildAdmission.class);
        admited_Child_By_mother.select(criteriaBuilder.count(childRoot))
                .where(criteriaBuilder.equal(
                        childRoot.get("motherMasterCode").get("id"),
                        releaseMotherRoot.get("motherMasterCode").get("id")));

        // Select the required fields (multi-select)
        criteriaQuery.multiselect(
                releaseMotherRoot.get("id").alias("releaseMotherId"),
                motherMasterDataJoin.get("id").alias("motherMasterCodeId"),
                motherMasterDataJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterDataJoin.get("motherName").alias("motherName"),
                motherMasterDataJoin.get("numberOfEligibleChildren").alias("numberOfEligibleChildren"),
                admited_Child_By_mother.alias("totalAdmitedChild"),
                releaseMotherRoot.get("releaseDate").alias("releaseDate"),
                releaseMotherRoot.get("address").alias("address"),
                releaseMotherRoot.get("postDischargeVisitDate").alias("postDischargeVisitDate"),
                releaseMotherRoot.get("remark").alias("remark"),
                releaseMotherRoot.get("created").alias("created"),
                createdByJoin.get("name").alias("createdBy"),
                releaseMotherRoot.get("updated").alias("updated"),
                updatedByJoin.get("name").alias("updatedBy")
        );

        criteriaQuery.orderBy(criteriaBuilder.desc(releaseMotherRoot.get("id")));
        // Execute the query and get the result as a list of Tuples
        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();

        // Convert Tuple results to a List of Maps
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("releaseMotherId", tuple.get("releaseMotherId"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));
            rowMap.put("numberOfEligibleChildren", tuple.get("numberOfEligibleChildren"));
            rowMap.put("totalAdmitedChild", tuple.get("totalAdmitedChild"));
            rowMap.put("releaseDate", tuple.get("releaseDate"));
            rowMap.put("address", tuple.get("address"));
            rowMap.put("postDischargeVisitDate", tuple.get("postDischargeVisitDate"));
            rowMap.put("remark", tuple.get("remark"));
            rowMap.put("created", tuple.get("created"));
            rowMap.put("createdBy", tuple.get("createdBy"));
            rowMap.put("updated", tuple.get("updated"));
            rowMap.put("updatedBy", tuple.get("updatedBy"));
            result.add(rowMap);
        }

        return result;
    }

    public List<Map<String, Object>> getReleaseMotherListwithsearch(
            String startDate,
            String endDate,
            String postVisitstartDate,
            String postVisitendDate
    ) {
        // Get CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Define the root entity for the query
        Root<ReleaseMother> releaseMotherRoot = criteriaQuery.from(ReleaseMother.class);

        // Define joins for related entities
        Join<ReleaseMother, MotherMasterData> motherMasterDataJoin = releaseMotherRoot.join("motherMasterCode", JoinType.LEFT);
        Join<ReleaseMother, Users> createdByJoin = releaseMotherRoot.join("createdBy", JoinType.LEFT);
        Join<ReleaseMother, Users> updatedByJoin = releaseMotherRoot.join("updatedBy", JoinType.LEFT);

        // Select the required fields (multi-select)
        criteriaQuery.multiselect(
                releaseMotherRoot.get("id").alias("releaseMotherId"),
                motherMasterDataJoin.get("id").alias("motherMasterCodeId"),
                motherMasterDataJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterDataJoin.get("motherName").alias("motherName"),
                releaseMotherRoot.get("releaseDate").alias("releaseDate"),
                releaseMotherRoot.get("address").alias("address"),
                releaseMotherRoot.get("postDischargeVisitDate").alias("postDischargeVisitDate"),
                releaseMotherRoot.get("remark").alias("remark"),
                releaseMotherRoot.get("created").alias("created"),
                createdByJoin.get("name").alias("createdBy"),
                releaseMotherRoot.get("updated").alias("updated"),
                updatedByJoin.get("name").alias("updatedBy")
        );
        List<Predicate> predicates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(releaseMotherRoot.get("releaseDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(releaseMotherRoot.get("releaseDate"), start));
        }

        if (ObjectUtils.isNotEmpty(postVisitstartDate) && ObjectUtils.isNotEmpty(postVisitendDate)) {
            LocalDate post_start = LocalDate.parse(postVisitstartDate, formatter);
            LocalDate post_end = LocalDate.parse(postVisitendDate, formatter);
            predicates.add(criteriaBuilder.between(releaseMotherRoot.get("postDischargeVisitDate"), post_start, post_end));
        } else if (ObjectUtils.isNotEmpty(postVisitstartDate)) {
            LocalDate post_start = LocalDate.parse(postVisitstartDate, formatter);
            predicates.add(criteriaBuilder.equal(releaseMotherRoot.get("postDischargeVisitDate"), post_start));
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        criteriaQuery.orderBy(criteriaBuilder.desc(releaseMotherRoot.get("id")));
        // Execute the query and get the result as a list of Tuples
        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();

        // Convert Tuple results to a List of Maps
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("releaseMotherId", tuple.get("releaseMotherId"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));
            rowMap.put("releaseDate", tuple.get("releaseDate"));
            rowMap.put("address", tuple.get("address"));
            rowMap.put("postDischargeVisitDate", tuple.get("postDischargeVisitDate"));
            rowMap.put("remark", tuple.get("remark"));
            rowMap.put("created", tuple.get("created"));
            rowMap.put("createdBy", tuple.get("createdBy"));
            rowMap.put("updated", tuple.get("updated"));
            rowMap.put("updatedBy", tuple.get("updatedBy"));
            result.add(rowMap);
        }

        return result;
    }

}
