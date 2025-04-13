/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.leave;

import sppbims.model.homevisit.M_Child_info;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.leave.LeaveChild;
import sppbims.model.leave.LeaveTypeEnum;
import sppbims.repository.leave.LeaveChildRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class LeaveChildService {

    @Autowired
    LeaveChildRepository leaveChildRepository;

    @PersistenceContext
    private EntityManager em;

    public List<Map<String, Object>> leaveChildList(
            LeaveTypeEnum leaveType
    ) {

        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

        // Define the root for the query (LeaveMother entity)
        Root<LeaveChild> root = criteriaQuery.from(LeaveChild.class);

        Join<LeaveChild, MotherMasterData> motherMasterJoin = root.join("motherMasterCode", JoinType.INNER);

        Join<LeaveChild, M_Child_info> m_Child_info_join = root.join("childMasterCode", JoinType.INNER);

        // List to hold the predicates (conditions)
        List<Predicate> predicates = new ArrayList<>();

        Predicate joinningDateNull = cb.isNull(root.get("joinningDate"));

        Predicate leaveToDateNotNull = cb.isNotNull(root.get("leaveTo"));

        Expression<Long> totalDayDiff = cb.function("DATEDIFF", Long.class,
                cb.literal(LocalDate.now()), root.get("leaveTo"));

        if (ObjectUtils.isNotEmpty(leaveType)) { // empty check

            if (leaveType == LeaveTypeEnum.Authorize) {
                predicates.add(joinningDateNull);
                predicates.add(leaveToDateNotNull);

                Predicate dateDiffLesThanZero = cb.lessThanOrEqualTo(totalDayDiff, 0L);
                predicates.add(dateDiffLesThanZero);
            } else if (leaveType == LeaveTypeEnum.Unauthorize) {

                predicates.add(joinningDateNull);
                predicates.add(leaveToDateNotNull);

                Predicate dateDiffLessThan90 = cb.lessThanOrEqualTo(totalDayDiff, 90L);
//
                Predicate dateDiffGreaterThanZero = cb.greaterThan(totalDayDiff, 0L);
//
                Predicate finalPredicate = cb.and(dateDiffGreaterThanZero, dateDiffLessThan90);
//
                predicates.add(finalPredicate);

            } else if (leaveType == LeaveTypeEnum.Long_Leave) {

                predicates.add(joinningDateNull);
                predicates.add(leaveToDateNotNull);
                Predicate dateDiffLessThan90 = cb.greaterThan(totalDayDiff, 90L);
                predicates.add(dateDiffLessThan90);

            }

        }
        predicates.add(cb.isNull(root.get("joinningDate")));
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));

        // Create the select clause with aliasing
        criteriaQuery.multiselect(
                root.get("id").alias("leaveMotherId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("sectionName").alias("sectionName"),
                root.get("leaveFrom").alias("leaveFrom"),
                root.get("leaveTo").alias("leaveTo"),
                root.get("careof").alias("careof"),
                root.get("contactNumber").alias("contactNumber"),
                root.get("address").alias("address"),
                root.get("joinningDate").alias("joinningDate"),
                totalDayDiff.alias("extraDay"),
                root.get("remark").alias("remark")
        );

        // Order by ID in descending order
        criteriaQuery.orderBy(cb.desc(root.get("id")));

        // Execute the query and return results as a list of maps
        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> resultList = query.getResultList();

        // Convert Tuple result to List<Map<String, Object>>
        List<Map<String, Object>> resultMap = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> map = new HashMap<>();
            map.put("leaveMotherId", tuple.get("leaveMotherId"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherName", tuple.get("motherName"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("name", tuple.get("name"));
            map.put("sectionName", tuple.get("sectionName"));
            map.put("leaveFrom", tuple.get("leaveFrom"));
            map.put("leaveTo", tuple.get("leaveTo"));
            map.put("careof", tuple.get("careof"));
            map.put("contactNumber", tuple.get("contactNumber"));
            map.put("address", tuple.get("address"));
            map.put("joinningDate", tuple.get("joinningDate"));
            map.put("extraDay", tuple.get("extraDay"));
            map.put("remark", tuple.get("remark"));
            resultMap.add(map);
        }

        return resultMap;
    }

    public List<Map<String, Object>> authorizedleaveChildList(
            String startDate,
            String endDate
    ) {

        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

        // Define the root for the query (LeaveMother entity)
        Root<LeaveChild> root = criteriaQuery.from(LeaveChild.class);

        Join<LeaveChild, MotherMasterData> motherMasterJoin = root.join("motherMasterCode", JoinType.INNER);

        Join<LeaveChild, M_Child_info> m_Child_info_join = root.join("childMasterCode", JoinType.INNER);

        // List to hold the predicates (conditions)
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("leaveFrom"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("leaveFrom"), start));
        }

        Expression<LocalDate> joinningDate = root.get("joinningDate");
        Expression<LocalDate> leaveTo = root.get("leaveTo");

        Expression<Long> totaldaydiff = cb.function("DATEDIFF", Long.class, joinningDate, leaveTo);

        Predicate dateDiffLesThanZero = cb.lessThanOrEqualTo(totaldaydiff, 0L);
        predicates.add(dateDiffLesThanZero);

        Predicate joinningDateNotNull = cb.isNotNull(root.get("joinningDate"));
        predicates.add(joinningDateNotNull);
        Predicate leaveToDate = cb.isNotNull(root.get("leaveTo"));
        predicates.add(leaveToDate);

        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));

        // Create the select clause with aliasing
        criteriaQuery.multiselect(
                root.get("id").alias("leaveMotherId"),
               root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("sectionName").alias("sectionName"),
                root.get("leaveFrom").alias("leaveFrom"),
                root.get("leaveTo").alias("leaveTo"),
                root.get("careof").alias("careof"),
                root.get("contactNumber").alias("contactNumber"),
                root.get("address").alias("address"),
                root.get("joinningDate").alias("joinningDate"),
                totaldaydiff.alias("extraDay"),
                root.get("remark").alias("remark")
        );

        // Order by ID in descending order
        criteriaQuery.orderBy(cb.desc(root.get("id")));

        // Execute the query and return results as a list of maps
        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> resultList = query.getResultList();

        // Convert Tuple result to List<Map<String, Object>>
        List<Map<String, Object>> resultMap = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> map = new HashMap<>();
            map.put("leaveMotherId", tuple.get("leaveMotherId"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherName", tuple.get("motherName"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("name", tuple.get("name"));
            map.put("sectionName", tuple.get("sectionName"));
            map.put("leaveFrom", tuple.get("leaveFrom"));
            map.put("leaveTo", tuple.get("leaveTo"));
            map.put("careof", tuple.get("careof"));
            map.put("contactNumber", tuple.get("contactNumber"));
            map.put("address", tuple.get("address"));
            map.put("joinningDate", tuple.get("joinningDate"));
            map.put("extraDay", tuple.get("extraDay"));
            map.put("remark", tuple.get("remark"));
            resultMap.add(map);
        }

        return resultMap;
    }

    public List<Map<String, Object>> unauthorizedleaveChildList(
            String startDate,
            String endDate
    ) {

        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

        // Define the root for the query (LeaveMother entity)
        Root<LeaveChild> root = criteriaQuery.from(LeaveChild.class);

        Join<LeaveChild, MotherMasterData> motherMasterJoin = root.join("motherMasterCode", JoinType.INNER);

        Join<LeaveChild, M_Child_info> m_Child_info_join = root.join("childMasterCode", JoinType.INNER);

        // List to hold the predicates (conditions)
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("leaveFrom"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("leaveFrom"), start));
        }

        Expression<LocalDate> joinningDate = root.get("joinningDate");
        Expression<LocalDate> leaveTo = root.get("leaveTo");

        Predicate joinningDateNotNull = cb.isNotNull(joinningDate);
        predicates.add(joinningDateNotNull);
        Predicate leaveToNotNull = cb.isNotNull(leaveTo);
        predicates.add(leaveToNotNull);
//
        Expression<Long> totaldaydiff = cb.function("DATEDIFF", Long.class, joinningDate, leaveTo);

        Predicate dateDiffLessThan90 = cb.lessThanOrEqualTo(totaldaydiff, 90L);
//
        Predicate dateDiffGreaterThanZero = cb.greaterThan(totaldaydiff, 0L);
//
        Predicate finalPredicate = cb.and(dateDiffGreaterThanZero, dateDiffLessThan90);
//
        predicates.add(finalPredicate);
        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));

        // Create the select clause with aliasing
        criteriaQuery.multiselect(
                root.get("id").alias("leaveMotherId"),
       root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("sectionName").alias("sectionName"),
                root.get("leaveFrom").alias("leaveFrom"),
                root.get("leaveTo").alias("leaveTo"),
                root.get("careof").alias("careof"),
                root.get("contactNumber").alias("contactNumber"),
                root.get("address").alias("address"),
                root.get("joinningDate").alias("joinningDate"),
                totaldaydiff.alias("extraDay"),
                root.get("remark").alias("remark")
        );

        // Order by ID in descending order
        criteriaQuery.orderBy(cb.desc(root.get("id")));

        // Execute the query and return results as a list of maps
        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> resultList = query.getResultList();

        // Convert Tuple result to List<Map<String, Object>>
        List<Map<String, Object>> resultMap = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> map = new HashMap<>();
            map.put("leaveMotherId", tuple.get("leaveMotherId"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherName", tuple.get("motherName"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("name", tuple.get("name"));
            map.put("sectionName", tuple.get("sectionName"));
            map.put("leaveFrom", tuple.get("leaveFrom"));
            map.put("leaveTo", tuple.get("leaveTo"));
            map.put("careof", tuple.get("careof"));
            map.put("contactNumber", tuple.get("contactNumber"));
            map.put("address", tuple.get("address"));
            map.put("joinningDate", tuple.get("joinningDate"));
            map.put("extraDay", tuple.get("extraDay"));
            map.put("remark", tuple.get("remark"));
            resultMap.add(map);
        }

        return resultMap;
    }

    public List<Map<String, Object>> longleaveChildList(
            String startDate,
            String endDate
    ) {

        // Create CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

        // Define the root for the query (LeaveMother entity)
        Root<LeaveChild> root = criteriaQuery.from(LeaveChild.class);

        Join<LeaveChild, MotherMasterData> motherMasterJoin = root.join("motherMasterCode", JoinType.INNER);

        Join<LeaveChild, M_Child_info> m_Child_info_join = root.join("childMasterCode", JoinType.INNER);

        // List to hold the predicates (conditions)
        List<Predicate> predicates = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse and apply date range if startDate and endDate are provided
        if (ObjectUtils.isNotEmpty(startDate) && ObjectUtils.isNotEmpty(endDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            predicates.add(cb.between(root.get("leaveFrom"), start, end));
        } else if (ObjectUtils.isNotEmpty(startDate)) {
            LocalDate start = LocalDate.parse(startDate, formatter);
            predicates.add(cb.equal(root.get("leaveFrom"), start));
        }

        Expression<LocalDate> joinningDate = root.get("joinningDate");
        Expression<LocalDate> leaveTo = root.get("leaveTo");

        Predicate joinningDateNotNull = cb.isNotNull(joinningDate);
        predicates.add(joinningDateNotNull);
        Predicate leaveToNotNull = cb.isNotNull(leaveTo);
        predicates.add(leaveToNotNull);

        Expression<Long> totaldaydiff = cb.function("DATEDIFF", Long.class, joinningDate, leaveTo);

        Predicate dateDiffLessThan90 = cb.greaterThan(totaldaydiff, 90L);

        predicates.add(dateDiffLessThan90);

        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));

        // Create the select clause with aliasing
        criteriaQuery.multiselect(
                root.get("id").alias("leaveMotherId"),
       root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("childMasterCode").get("childMasterCode").alias("childMasterCode"),
                root.get("childMasterCode").get("name").alias("name"),
                root.get("sectionName").alias("sectionName"),
                root.get("leaveFrom").alias("leaveFrom"),
                root.get("leaveTo").alias("leaveTo"),
                root.get("careof").alias("careof"),
                root.get("contactNumber").alias("contactNumber"),
                root.get("address").alias("address"),
                root.get("joinningDate").alias("joinningDate"),
                totaldaydiff.alias("extraDay"),
                root.get("remark").alias("remark")
        );

        // Order by ID in descending order
        criteriaQuery.orderBy(cb.desc(root.get("id")));

        // Execute the query and return results as a list of maps
        TypedQuery<Tuple> query = em.createQuery(criteriaQuery);

        List<Tuple> resultList = query.getResultList();

        // Convert Tuple result to List<Map<String, Object>>
        List<Map<String, Object>> resultMap = new ArrayList<>();
        for (Tuple tuple : resultList) {
            Map<String, Object> map = new HashMap<>();
            map.put("leaveMotherId", tuple.get("leaveMotherId"));
            map.put("motherMasterCode", tuple.get("motherMasterCode"));
            map.put("motherName", tuple.get("motherName"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("name", tuple.get("name"));
            map.put("sectionName", tuple.get("sectionName"));
            map.put("leaveFrom", tuple.get("leaveFrom"));
            map.put("leaveTo", tuple.get("leaveTo"));
            map.put("careof", tuple.get("careof"));
            map.put("contactNumber", tuple.get("contactNumber"));
            map.put("address", tuple.get("address"));
            map.put("joinningDate", tuple.get("joinningDate"));
            map.put("extraDay", tuple.get("extraDay"));
            map.put("remark", tuple.get("remark"));
            resultMap.add(map);
        }

        return resultMap;
    }

}
