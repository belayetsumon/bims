/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.Decision;
import sppbims.model.homevisit.M_Approval;
import sppbims.model.homevisit.MotherMasterData;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_ApprovalRepository extends JpaRepository<M_Approval, Long> {

    Long countByDecission(Decision decission);

    List<M_Approval> findBymotherMasterCode(MotherMasterData motherMasterData);

    @EntityGraph(value = "approve_mother", type = EntityGraph.EntityGraphType.FETCH)
    List<M_Approval> findAllBydecissionOrderByIdDesc(Decision decission);

    List<M_Approval> findAllProjectedBydecissionOrderByIdDesc(Decision decission);

    List<M_Approval> findByDecissionAndCreatedBetweenOrderByIdDesc(Decision decission, LocalDate fromdate, LocalDate todate);

}
