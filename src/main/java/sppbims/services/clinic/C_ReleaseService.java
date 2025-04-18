package sppbims.services.clinic;

import sppbims.model.auth.Users;
import sppbims.model.clinic.C_Release;
import sppbims.model.homevisit.MotherMasterData;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class C_ReleaseService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> findReleaseDetails() {
        // Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery and set result type as Tuple
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for the C_Release entity
        Root<C_Release> cReleaseRoot = criteriaQuery.from(C_Release.class);

        // Alias for C_Release columns
        Path<Long> idPath = cReleaseRoot.get("id");
        Path<LocalDate> releaseDatePath = cReleaseRoot.get("releaseDate");
        Path<String> releaseByPath = cReleaseRoot.get("releaseBy");
        Path<String> nextFollowUpDatePath = cReleaseRoot.get("nextFollowUpDate");
        Path<String> remarksPath = cReleaseRoot.get("remarks");

        Path<String> motherMasterCode = cReleaseRoot.get("motherMasterCode").get("motherMasterCode");
        Path<String> motherName = cReleaseRoot.get("motherMasterCode").get("motherName");
        Path<String> motherMasterCodeId = cReleaseRoot.get("motherMasterCode").get("id");

        // Join with MotherMasterData and Users (assuming they are present in the entity)
        Join<C_Release, MotherMasterData> motherMasterJoin = cReleaseRoot.join("motherMasterCode", JoinType.LEFT);
        Join<C_Release, Users> createdByJoin = cReleaseRoot.join("createdBy", JoinType.LEFT);

        // Select fields using multiselect
        criteriaQuery.multiselect(
                idPath.alias("releaseId"),
                releaseDatePath.alias("releaseDate"),
                releaseByPath.alias("releaseBy"),
                nextFollowUpDatePath.alias("nextFollowUpDate"),
                remarksPath.alias("remarks"),
                motherMasterCode.alias("motherMasterCode"),
                motherMasterCodeId.alias("motherMasterCodeId"),
                motherName.alias("motherName"),
                createdByJoin.get("name").alias("createdBy") // Assuming 'username' exists in Users
        );

        // Order by releaseDate descending
        criteriaQuery.orderBy(criteriaBuilder.desc(idPath));

        // Execute the query and get result as List<Tuple>
        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        // Convert result from Tuple to Map
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new java.util.HashMap<>();
            resultMap.put("releaseId", tuple.get("releaseId"));
            resultMap.put("releaseDate", tuple.get("releaseDate"));
            resultMap.put("releaseBy", tuple.get("releaseBy"));
            resultMap.put("nextFollowUpDate", tuple.get("nextFollowUpDate"));
            resultMap.put("remarks", tuple.get("remarks"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
             resultMap.put("motherName", tuple.get("motherName"));
            resultMap.put("createdBy", tuple.get("createdBy"));
            resultMapList.add(resultMap);
        }

        return resultMapList;
    }

    public List<Map<String, Object>> find_Release_list_date_report(
            String startDate,
            String endDate
    ) {
        // Create CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Create CriteriaQuery and set result type as Tuple
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);

        // Root for the C_Release entity
        Root<C_Release> cReleaseRoot = criteriaQuery.from(C_Release.class);

        // Alias for C_Release columns
        Path<Long> idPath = cReleaseRoot.get("id");
        Path<LocalDate> releaseDatePath = cReleaseRoot.get("releaseDate");
        Path<String> releaseByPath = cReleaseRoot.get("releaseBy");
        Path<String> nextFollowUpDatePath = cReleaseRoot.get("nextFollowUpDate");
        Path<String> remarksPath = cReleaseRoot.get("remarks");

        // Join with MotherMasterData and Users (assuming they are present in the entity)
        Join<C_Release, MotherMasterData> motherMasterJoin = cReleaseRoot.join("motherMasterCode", JoinType.LEFT);
        Join<C_Release, Users> createdByJoin = cReleaseRoot.join("createdBy", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(criteriaBuilder.between(cReleaseRoot.get("releaseDate"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(criteriaBuilder.equal(cReleaseRoot.get("releaseDate"), start));
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Select fields using multiselect
        criteriaQuery.multiselect(
                idPath.alias("releaseId"),
                releaseDatePath.alias("releaseDate"),
                releaseByPath.alias("releaseBy"),
                nextFollowUpDatePath.alias("nextFollowUpDate"),
                remarksPath.alias("remarks"),
                cReleaseRoot.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                 cReleaseRoot.get("motherMasterCode").get("motherName").alias("motherName"),
                cReleaseRoot.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                createdByJoin.get("name").alias("createdBy") // Assuming 'username' exists in Users
        );

        // Order by releaseDate descending
        criteriaQuery.orderBy(criteriaBuilder.desc(idPath));

        // Execute the query and get result as List<Tuple>
        List<Tuple> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        // Convert result from Tuple to Map
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> resultMap = new java.util.HashMap<>();
            resultMap.put("releaseId", tuple.get("releaseId"));
            resultMap.put("releaseDate", tuple.get("releaseDate"));
            resultMap.put("releaseBy", tuple.get("releaseBy"));
            resultMap.put("nextFollowUpDate", tuple.get("nextFollowUpDate"));
            resultMap.put("remarks", tuple.get("remarks"));
            resultMap.put("motherMasterCode", tuple.get("motherMasterCode"));
            resultMap.put("motherName", tuple.get("motherName"));
            resultMap.put("motherMasterCodeId", tuple.get("motherMasterCodeId"));
            resultMap.put("createdBy", tuple.get("createdBy"));
            resultMapList.add(resultMap);
        }

        return resultMapList;
    }

}
