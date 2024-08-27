/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.homevisit;

import itgarden.model.homevisit.MotherMasterData;
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
public class MotherMasterDataServices {

    @PersistenceContext
    EntityManager em;

    public List<Long> motherMasterDataIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        cq.multiselect(root.get("id"));

//        List<Predicate> predicates = new ArrayList<Predicate>();
//
//      
//        predicates.add(cb.between(rootSm.get("starttime"), thirtyMinutesAgo, currentTime));
//
//        cq.where(predicates.toArray(new Predicate[]{}));
        

        cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id =  t.get(0,Long.class);
            idList.add(id);
        }
        return idList;
    }

}
