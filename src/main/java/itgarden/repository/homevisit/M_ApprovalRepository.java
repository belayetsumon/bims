/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit;

import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.M_Approval;
import itgarden.model.homevisit.MotherMasterData;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_ApprovalRepository extends JpaRepository<M_Approval, Long> {

    List<M_Approval> findBymotherMasterCode(MotherMasterData motherMasterData);

    List<M_Approval> findAllBydecissionOrderByIdDesc(Decision decission);

    List<M_Approval> findByDecissionAndCreatedBetweenOrderByIdDesc(Decision decission, LocalDate fromdate, LocalDate todate);

}
