/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.homevisit;

import itgarden.model.homevisit.Address_Type;
import itgarden.model.homevisit.District;
import itgarden.model.homevisit.M_Property;
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
public class M_PropertyService {

    @PersistenceContext
    EntityManager em;

    public List<Tuple> motherproperty(District district, Address_Type addressType) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Property> root = cq.from(M_Property.class);
        // Define multiselect with aliases     
        cq.multiselect(
                root.get("id").alias("property"),
                root.get("addressType").alias("addressType"),
                root.get("motherMasterCode").get("id").alias("motherMasterCodeId"),
                root.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                root.get("motherMasterCode").get("motherName").alias("motherName"),
                root.get("motherMasterCode").get("mobileNumber").alias("mobileNumber"),
                root.get("co").alias("careOf"),
                root.get("vill").alias("village"),
                root.get("po").alias("postOffice"),
                root.get("district").alias("district"),
                root.get("thana").get("name").alias("thana")
        );

        List<Predicate> predicates = new ArrayList<>();

        // Add conditions only if parameters are not null
        if (ObjectUtils.isNotEmpty(district)) {
            predicates.add(cb.equal(root.get("district"), district));
        }
        if (ObjectUtils.isNotEmpty(addressType)) {
            predicates.add(cb.equal(root.get("addressType"), addressType));
        }

        // Apply 'OR' condition if both predicates are present
        if (!predicates.isEmpty()) {
            cq.where(cb.or(predicates.toArray(new Predicate[0])));
        }
        cq.orderBy(cb.desc(root.get("id")));
        return em.createQuery(cq).getResultList();
    }

}
