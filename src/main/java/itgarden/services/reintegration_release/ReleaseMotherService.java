/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.reintegration_release;

import itgarden.model.rehabilitations.R_M_HousAllocation;
import itgarden.model.reintegration_release.ReleaseMother;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class ReleaseMotherService {

    @PersistenceContext
    EntityManager em;

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

}
