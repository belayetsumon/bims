/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.homevisit;

import itgarden.model.homevisit.House_Type;
import itgarden.model.homevisit.M_House_Information;
import itgarden.model.homevisit.Ownershif_type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class M_House_InformationService {

    @PersistenceContext
    EntityManager em;

    public List<Tuple> motherHouseownership(
            Ownershif_type ownership,
            House_Type houseType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_House_Information> root = cq.from(M_House_Information.class);
        // Define multiselect with aliases     
        cq.multiselect(
                root.get("id").alias("id"),
                root.get("houseType").get("name").alias("houseType"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("motherMasterCode").get("mobileNumber").alias("mobileNumber"),
                root.get("ownership").get("name").alias("ownership"),
                root.get("valueTitle").alias("valueTitle"),
                root.get("value").alias("value"),
                root.get("remarks").alias("remarks")
        );

        List<Predicate> predicates = new ArrayList<>();

        // Add conditions only if parameters are not null
        if (ObjectUtils.isNotEmpty(ownership)) {
            predicates.add(cb.equal(root.get("ownership"), ownership));
        }
        if (ObjectUtils.isNotEmpty(houseType)) {
            predicates.add(cb.equal(root.get("houseType"), houseType));
        }

        // Apply 'OR' condition if both predicates are present
        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }
        cq.orderBy(cb.desc(root.get("id")));
        return em.createQuery(cq).getResultList();
    }

}
