/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.school;

import sppbims.model.homevisit.M_Child_info;
import sppbims.model.observation.O_ChildAdmission;
import sppbims.model.school.EligibilityStudent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class EligibilityStudentService {

    @PersistenceContext
    EntityManager em;

    public List<Long> notEligibleChildIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<EligibilityStudent> root = cq.from(EligibilityStudent.class);

        cq.multiselect(root.get("childMasterCode").get("id"));

        cq.orderBy(cb.desc(root.get("childMasterCode").get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get(0, Long.class);
            idList.add(id);
        }
        return idList;
    }

    public List<Map<String, Object>> findEligibilityStudentData() {
        // Obtain CriteriaBuilder and CriteriaQuery
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        // Root of the query (representing the EligibilityStudent entity)
        Root<EligibilityStudent> root = cq.from(EligibilityStudent.class);

        // Join with M_Child_info (One-to-One relationship)
        Join<EligibilityStudent, M_Child_info> childMasterCodeJoin = root.join("childMasterCode", JoinType.LEFT);

        // Multi-select fields with aliases (use alias names)
        // Get the current date
        Expression<java.sql.Date> currentDate = cb.currentDate();

        Path<Date> birthDate = childMasterCodeJoin.get("dateOfBirth");
        // Calculate the difference in years (age)

        // Calculate the difference in years (age)
        Expression<Integer> age = cb.diff(
                cb.function("year", Integer.class, currentDate),
                cb.function("year", Integer.class, birthDate)
        );

        cq.multiselect(
                root.get("id").alias("id"),
                childMasterCodeJoin.get("childMasterCode").alias("childMasterCode"),
                childMasterCodeJoin.get("motherName").alias("motherName"),
                childMasterCodeJoin.get("name").alias("name"),
                childMasterCodeJoin.get("dateOfBirth").alias("dateOfBirth"),
                age.alias("age"),
                root.get("remark").alias("remark")
        );

        cq.orderBy(cb.desc(root.get("id")));

        // Execute the query and get the result as a List of Tuples
        List<Tuple> result = em.createQuery(cq).getResultList();

        // Convert Tuple list to List<Map<String, Object>>
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Tuple tuple : result) {
            Map<String, Object> map = new HashMap<>();

            // Extract data from Tuple using the alias names
            map.put("id", tuple.get("id"));
            map.put("childMasterCode", tuple.get("childMasterCode"));
            map.put("motherName", tuple.get("motherName"));
            map.put("name", tuple.get("name"));
            map.put("dateOfBirth", tuple.get("dateOfBirth"));
            map.put("age", tuple.get("age"));
            map.put("remark", tuple.get("remark"));

            resultList.add(map);
        }

        return resultList;
    }

}
