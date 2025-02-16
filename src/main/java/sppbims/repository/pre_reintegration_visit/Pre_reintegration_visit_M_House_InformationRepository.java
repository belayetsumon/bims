/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.pre_reintegration_visit;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.pre_reintegration_visit.M_House_Information_ReintegrationVisit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface Pre_reintegration_visit_M_House_InformationRepository extends JpaRepository<M_House_Information_ReintegrationVisit, Long> {

    List<M_House_Information_ReintegrationVisit> findBymotherMasterCode(MotherMasterData motherMasterData);
}
