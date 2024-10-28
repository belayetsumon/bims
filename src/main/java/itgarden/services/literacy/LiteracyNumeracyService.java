/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package itgarden.services.literacy;

import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.literacy.LiteracyNumeracy;
import itgarden.services.observation.O_MAddmissionService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class LiteracyNumeracyService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    O_MAddmissionService addmissionService;

    public List<MotherMasterDataDTO> getMotherMasterDataDTOs() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        
        List<Long> admitedMotherList = addmissionService.addmissionMotherIdList();

        List<Long> literacyNumeracyMotherIdList = literacyNumeracyMotherIdList();

        Root<MotherMasterData> root = cq.from(MotherMasterData.class);

        // Select fields from MotherMasterData and related entities
        cq.multiselect(
                root.get("id"),
                root.get("motherMasterCode"),
                root.get("motherName")
        // Converting Enum to String
        );
        List<Predicate> predicates = new ArrayList<Predicate>();
        // Define the predicates for the conditions

        predicates.add(root.get("id").in(admitedMotherList));

        if (!literacyNumeracyMotherIdList.isEmpty()) {
            predicates.add(cb.not(root.get("id").in(literacyNumeracyMotherIdList)));
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Tuple> query = em.createQuery(cq);

        // Execute the query and map results to DTO
        List<MotherMasterDataDTO> result = query.getResultList().stream().map(tuple -> {
            MotherMasterDataDTO dto = new MotherMasterDataDTO();
            dto.setId(tuple.get(0, Long.class));
            dto.setMotherMasterCode(tuple.get(1, String.class));
            dto.setMotherName(tuple.get(2, String.class));

            // Enum converted to String
            return dto;
        }).toList();

        return result;
    }

    public List<Long> literacyNumeracyMotherIdList() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);

        Root<LiteracyNumeracy> root = cq.from(LiteracyNumeracy.class);

        cq.multiselect(root.get("motherMasterCode").get("id"));

       cq.orderBy(cb.desc(root.get("id")));

        List<Tuple> result = em.createQuery(cq).getResultList();

        List<Long> idList = new ArrayList<Long>();
        for (Tuple t : result) {
            Long id = t.get(0, Long.class);
            idList.add(id);
        }
        return idList;
    }

}
