/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.homevisit.servicess;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class HomeVisitService {

    @PersistenceContext
    EntityManager em;

//    public List<ApproveMotherListDTO> approveMotherList(){
//    
//       CriteriaBuilder cb = em.getCriteriaBuilder();
//    
//     CriteriaQuery<M_Approval> cq = cb.createQuery(M_Approval.class);
//     Root<M_Approval> rootAp = cq.from(M_Approval.class);
//    
//     cq.select(cb.construct(ApproveMotherListDTO.class,
//             
//             rootAp.get("id").alias("id"),
//             
//             ));
//    }
}
