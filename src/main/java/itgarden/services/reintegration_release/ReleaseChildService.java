/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.reintegration_release;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.Gender;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.reintegration_release.ReleaseChild;
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
public class ReleaseChildService {

    @PersistenceContext
    EntityManager em;

    public List<Long> allReleasedChildIdList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<ReleaseChild> root = cq.from(ReleaseChild.class);

        cq.multiselect(
                root.get("childMasterCode").get("id").alias("childMasterCodeId")
        );
        cq.orderBy(cb.desc(root.get("id")));
        List<Tuple> result = em.createQuery(cq).getResultList();
        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get("childMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;

    }

    public List<Long> longTremCareReleasedChildIdList(
            Long motherId
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<ReleaseChild> root = cq.from(ReleaseChild.class);

        cq.multiselect(
                root.get("childMasterCode").get("id").alias("childMasterCodeId")
        );
        
        cq.where(cb.equal(root.get("motherMasterCode").get("id"), motherId));
        cq.orderBy(cb.desc(root.get("id")));
        List<Tuple> result = em.createQuery(cq).getResultList();
        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get("childMasterCodeId", Long.class);
            idList.add(id);
        }
        return idList;

    }

    public List<Map<String, Object>> getReleaseChildList() {
        // Get CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Define the root entity for the query
        Root<ReleaseChild> releaseChilRoot = criteriaQuery.from(ReleaseChild.class);

        // Define joins for related entities
        Join<ReleaseChild, MotherMasterData> motherMasterDataJoin = releaseChilRoot.join("motherMasterCode", JoinType.LEFT);
        Join<ReleaseChild, M_Child_info> childMasterCodeJoin = releaseChilRoot.join("childMasterCode", JoinType.LEFT);
        Join<ReleaseChild, Users> createdByJoin = releaseChilRoot.join("createdBy", JoinType.LEFT);
        Join<ReleaseChild, Users> updatedByJoin = releaseChilRoot.join("updatedBy", JoinType.LEFT);

        // Select the required fields (multi-select)
        criteriaQuery.multiselect(
                releaseChilRoot.get("id").alias("releaseChildId"),
                motherMasterDataJoin.get("id").alias("motherMasterCodeId"),
                motherMasterDataJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterDataJoin.get("motherName").alias("motherName"),
                childMasterCodeJoin.get("id").alias("childMasterCodeId"),
                childMasterCodeJoin.get("childMasterCode").alias("childMasterCode"),
                childMasterCodeJoin.get("name").alias("name"),
                childMasterCodeJoin.get("gender").alias("gender"),
                releaseChilRoot.get("releaseDate").alias("releaseDate"),
                releaseChilRoot.get("address").alias("address"),
                releaseChilRoot.get("training").alias("training"),
                releaseChilRoot.get("remark").alias("remark"),
                releaseChilRoot.get("created").alias("created"),
                createdByJoin.get("name").alias("createdBy"),
                releaseChilRoot.get("updated").alias("updated"),
                updatedByJoin.get("name").alias("updatedBy")
        );

        criteriaQuery.orderBy(criteriaBuilder.desc(releaseChilRoot.get("id")));

        // Execute the query and get the result as a list of Tuples
        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();

        // Convert Tuple results to a List of Maps
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("releaseChildId", tuple.get("releaseChildId"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));
            rowMap.put("childMasterCodeId", tuple.get("childMasterCodeId"));
            rowMap.put("childMasterCode", tuple.get("childMasterCode"));
            rowMap.put("name", tuple.get("name"));
            rowMap.put("gender", tuple.get("gender"));
            rowMap.put("releaseDate", tuple.get("releaseDate"));
            rowMap.put("training", tuple.get("training"));
            rowMap.put("address", tuple.get("address"));
            rowMap.put("remark", tuple.get("remark"));
            rowMap.put("created", tuple.get("created"));
            rowMap.put("createdBy", tuple.get("createdBy"));
            rowMap.put("updated", tuple.get("updated"));
            rowMap.put("updatedBy", tuple.get("updatedBy"));
            result.add(rowMap);
        }

        return result;
    }

    public List<Map<String, Object>> getReleaseChildListByMotherLongTremCare(
            Long motherId,
            Gender gender,
            String startDate,
            String endDate
    ) {
        // Get CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Define the root entity for the query
        Root<ReleaseChild> releaseChilRoot = criteriaQuery.from(ReleaseChild.class);

        // Define joins for related entities
        Join<ReleaseChild, MotherMasterData> motherMasterDataJoin = releaseChilRoot.join("motherMasterCode", JoinType.LEFT);
        Join<ReleaseChild, M_Child_info> childMasterCodeJoin = releaseChilRoot.join("childMasterCode", JoinType.LEFT);
        Join<ReleaseChild, Users> createdByJoin = releaseChilRoot.join("createdBy", JoinType.LEFT);
        Join<ReleaseChild, Users> updatedByJoin = releaseChilRoot.join("updatedBy", JoinType.LEFT);

        // Select the required fields (multi-select)
        criteriaQuery.multiselect(
                releaseChilRoot.get("id").alias("releaseChildId"),
                motherMasterDataJoin.get("id").alias("motherMasterCodeId"),
                motherMasterDataJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterDataJoin.get("motherName").alias("motherName"),
                childMasterCodeJoin.get("id").alias("childMasterCodeId"),
                childMasterCodeJoin.get("childMasterCode").alias("childMasterCode"),
                childMasterCodeJoin.get("name").alias("name"),
                childMasterCodeJoin.get("gender").alias("gender"),
                releaseChilRoot.get("releaseDate").alias("releaseDate"),
                releaseChilRoot.get("address").alias("address"),
                releaseChilRoot.get("training").alias("training"),
                releaseChilRoot.get("remark").alias("remark"),
                releaseChilRoot.get("created").alias("created"),
                createdByJoin.get("name").alias("createdBy"),
                releaseChilRoot.get("updated").alias("updated"),
                updatedByJoin.get("name").alias("updatedBy")
        );

        List<Predicate> predicates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(releaseChilRoot.get("releaseDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(releaseChilRoot.get("releaseDate"), start));
        }

        predicates.add(criteriaBuilder.equal(motherMasterDataJoin.get("id"), motherId));

        if (ObjectUtils.isNotEmpty(gender)) {
            predicates.add(criteriaBuilder.equal(releaseChilRoot.get("childMasterCode").get("gender"), gender));
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        criteriaQuery.orderBy(criteriaBuilder.desc(releaseChilRoot.get("id")));

        // Execute the query and get the result as a list of Tuples
        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();

        // Convert Tuple results to a List of Maps
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("releaseChildId", tuple.get("releaseChildId"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));
            rowMap.put("childMasterCodeId", tuple.get("childMasterCodeId"));
            rowMap.put("childMasterCode", tuple.get("childMasterCode"));
            rowMap.put("name", tuple.get("name"));
            rowMap.put("gender", tuple.get("gender"));
            rowMap.put("releaseDate", tuple.get("releaseDate"));
            rowMap.put("training", tuple.get("training"));
            rowMap.put("address", tuple.get("address"));
            rowMap.put("remark", tuple.get("remark"));
            rowMap.put("created", tuple.get("created"));
            rowMap.put("createdBy", tuple.get("createdBy"));
            rowMap.put("updated", tuple.get("updated"));
            rowMap.put("updatedBy", tuple.get("updatedBy"));
            result.add(rowMap);
        }
        return result;
    }
    
    public List<Map<String, Object>> getReleaseChildListwithsearch(
            Gender gender,
            String startDate,
            String endDate
    ) {
        // Get CriteriaBuilder and CriteriaQuery
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Define the root entity for the query
        Root<ReleaseChild> releaseChilRoot = criteriaQuery.from(ReleaseChild.class);

        // Define joins for related entities
        Join<ReleaseChild, MotherMasterData> motherMasterDataJoin = releaseChilRoot.join("motherMasterCode", JoinType.LEFT);
        Join<ReleaseChild, M_Child_info> childMasterCodeJoin = releaseChilRoot.join("childMasterCode", JoinType.LEFT);
        Join<ReleaseChild, Users> createdByJoin = releaseChilRoot.join("createdBy", JoinType.LEFT);
        Join<ReleaseChild, Users> updatedByJoin = releaseChilRoot.join("updatedBy", JoinType.LEFT);

        // Select the required fields (multi-select)
        criteriaQuery.multiselect(
                releaseChilRoot.get("id").alias("releaseChildId"),
                motherMasterDataJoin.get("id").alias("motherMasterCodeId"),
                motherMasterDataJoin.get("motherMasterCode").alias("motherMasterCode"),
                motherMasterDataJoin.get("motherName").alias("motherName"),
                childMasterCodeJoin.get("id").alias("childMasterCodeId"),
                childMasterCodeJoin.get("childMasterCode").alias("childMasterCode"),
                childMasterCodeJoin.get("name").alias("name"),
                childMasterCodeJoin.get("gender").alias("gender"),
                releaseChilRoot.get("releaseDate").alias("releaseDate"),
                releaseChilRoot.get("address").alias("address"),
                releaseChilRoot.get("training").alias("training"),
                releaseChilRoot.get("remark").alias("remark"),
                releaseChilRoot.get("created").alias("created"),
                createdByJoin.get("name").alias("createdBy"),
                releaseChilRoot.get("updated").alias("updated"),
                updatedByJoin.get("name").alias("updatedBy")
        );

        List<Predicate> predicates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(releaseChilRoot.get("releaseDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(releaseChilRoot.get("releaseDate"), start));
        }

        if (ObjectUtils.isNotEmpty(gender)) {
            predicates.add(criteriaBuilder.equal(releaseChilRoot.get("childMasterCode").get("gender"), gender));
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        criteriaQuery.orderBy(criteriaBuilder.desc(releaseChilRoot.get("id")));

        // Execute the query and get the result as a list of Tuples
        List<Tuple> tuples = em.createQuery(criteriaQuery).getResultList();

        // Convert Tuple results to a List of Maps
        List<Map<String, Object>> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("releaseChildId", tuple.get("releaseChildId"));
            rowMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            rowMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            rowMap.put("motherName", tuple.get("motherName"));
            rowMap.put("childMasterCodeId", tuple.get("childMasterCodeId"));
            rowMap.put("childMasterCode", tuple.get("childMasterCode"));
            rowMap.put("name", tuple.get("name"));
            rowMap.put("gender", tuple.get("gender"));
            rowMap.put("releaseDate", tuple.get("releaseDate"));
            rowMap.put("training", tuple.get("training"));
            rowMap.put("address", tuple.get("address"));
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
