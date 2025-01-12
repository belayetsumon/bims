/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.leave;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.leave.LeaveChild;
import itgarden.model.leave.LeaveMother;
import itgarden.model.leave.LeaveTypeEnum;
import itgarden.repository.leave.LeaveChildRepository;
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
import java.time.LocalDate;
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

    public List<Map<String, Object>> leaveMothersList(
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

        Predicate authorizeLeave = cb.lessThan(root.get("leaveTo"), LocalDate.now());

        if (ObjectUtils.isNotEmpty(leaveType)) { // empty check

            if (leaveType == LeaveTypeEnum.Authorize) {
                predicates.add(cb.and(joinningDateNull, authorizeLeave));
            } else if (leaveType == LeaveTypeEnum.Unauthorize) {

                predicates.add(
                        cb.and(
                                cb.isNull(root.get("joinningDate")),
                                cb.greaterThan(root.get("leaveTo"), LocalDate.now()),
                                cb.lessThanOrEqualTo(root.get("leaveTo"), LocalDate.now().plusDays(90))
                        )// end and
                );/// predicates

            } else if (leaveType == LeaveTypeEnum.Long_Leave) {

                predicates.add(cb.and(
                        cb.isNull(root.get("joinningDate")),
                        cb.greaterThan(root.get("leaveTo"), LocalDate.now()),
                        cb.greaterThanOrEqualTo(root.get("leaveTo"), LocalDate.now().plusDays(90))
                ));

            }

        }

        criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));

        // Create the select clause with aliasing
        criteriaQuery.multiselect(
                root.get("id").alias("leaveMotherId"),
                motherMasterJoin.get("motherMasterCode").alias("motherMasterCode"),
                 m_Child_info_join.get("childMasterCode").alias("childMasterCode"),
                root.get("sectionName").alias("sectionName"),
                root.get("leaveFrom").alias("leaveFrom"),
                root.get("leaveTo").alias("leaveTo"),
                root.get("careof").alias("careof"),
                root.get("contactNumber").alias("contactNumber"),
                root.get("address").alias("address"),
                root.get("joinningDate").alias("joinningDate"),
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
             map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("sectionName", tuple.get("sectionName"));
            map.put("leaveFrom", tuple.get("leaveFrom"));
            map.put("leaveTo", tuple.get("leaveTo"));
            map.put("careof", tuple.get("careof"));
            map.put("contactNumber", tuple.get("contactNumber"));
            map.put("address", tuple.get("address"));
            map.put("joinningDate", tuple.get("joinningDate"));
            map.put("remark", tuple.get("remark"));
            resultMap.add(map);
        }

        return resultMap;
    }

}
