/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.pre_reintegration_visit;

import itgarden.model.pre_reintegration_visit.M_Accessibility_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.M_Community_Information_ReintegrationVisit;
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
public class M_Community_Information_ReintegrationVisitService {
    @PersistenceContext
    EntityManager em;

    public List<Long> all_M_Community_Information_Reintegration_Visit_Mother_Id_List() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<M_Community_Information_ReintegrationVisit> root = cq.from(M_Community_Information_ReintegrationVisit.class);

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
