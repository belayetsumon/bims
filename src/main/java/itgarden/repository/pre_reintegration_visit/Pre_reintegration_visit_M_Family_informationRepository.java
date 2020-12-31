/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.pre_reintegration_visit;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_visit.M_Family_information_ReintegrationVisit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface Pre_reintegration_visit_M_Family_informationRepository extends JpaRepository<M_Family_information_ReintegrationVisit, Long> {
     List<M_Family_information_ReintegrationVisit> findBymotherMasterCode(MotherMasterData motherMasterData  );
    
}
