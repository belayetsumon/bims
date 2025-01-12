/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.impactmeasurement;

import itgarden.model.reintegration_release.ReleaseMother;
import itgarden.model.reintegration_release.ReleaseMotherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class LongTermImpactMeasurementService {
    @PersistenceContext
    EntityManager em;
    
    
public List<ReleaseMotherDTO> newListReleaseMotherIdforLongTermImpactMeasurement() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<ReleaseMother> rootSm = cq.from(ReleaseMother.class);

        cq.multiselect(
                rootSm.get("id").alias("id"),
                rootSm.get("motherMasterCode").get("motherMasterCode").alias("motherMasterCode"),
                  rootSm.get("motherMasterCode").get("motherName").alias("motherName")
        );

        List<Predicate> predicates = new ArrayList<Predicate>();

        //predicates.add(cb.and(cb.like(rootSm.get("items").get("itemCode"), itemCode)));
        cq.where(predicates.toArray(new Predicate[]{}));

        cq.groupBy(rootSm.get("id"));

        cq.orderBy(cb.desc(rootSm.get("id")));

        TypedQuery<Tuple> result = em.createQuery(cq);

        List<ReleaseMotherDTO> releaseMotherDTOList = new ArrayList<>();

        for (Tuple tuplereleaseMother : result.getResultList()) {

            ReleaseMotherDTO releaseMother = new ReleaseMotherDTO();

            releaseMother.setId(tuplereleaseMother.get("id", Long.class));

            releaseMother.setMotherMasterCode(tuplereleaseMother.get("motherMasterCode", String.class));
            releaseMother.setMotherMasterCode(tuplereleaseMother.get("motherMasterCode", String.class)+" - "+tuplereleaseMother.get("motherName", String.class));

            releaseMotherDTOList.add(releaseMother);
        }

        return releaseMotherDTOList;

    }






}
