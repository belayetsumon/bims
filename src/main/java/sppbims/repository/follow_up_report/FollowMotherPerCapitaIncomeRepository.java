/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.follow_up_report;

import sppbims.model.follow_up_report.FollowMotherPerCapitaIncome;
import sppbims.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface FollowMotherPerCapitaIncomeRepository extends JpaRepository<FollowMotherPerCapitaIncome, Long> {

    List<FollowMotherPerCapitaIncome> findBymotherMasterCode(MotherMasterData motherMasterData);
}
