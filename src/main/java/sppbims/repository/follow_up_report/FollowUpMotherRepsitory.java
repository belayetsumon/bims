/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.follow_up_report;

import sppbims.model.follow_up_report.FollowUpMother;
import sppbims.model.homevisit.MotherMasterData;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface FollowUpMotherRepsitory extends JpaRepository<FollowUpMother, Long> {

    List<FollowUpMother> findBymotherMasterCode(MotherMasterData motherMasterData);

    List<FollowUpMother> findByCreatedBetween(LocalDate fromdate, LocalDate todate);
}
