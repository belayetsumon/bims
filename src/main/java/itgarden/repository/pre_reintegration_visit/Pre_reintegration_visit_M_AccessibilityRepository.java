/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.pre_reintegration_visit;

import itgarden.model.homevisit.M_Accessibility;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_visit.M_Accessibility_ReintegrationVisit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface Pre_reintegration_visit_M_AccessibilityRepository extends JpaRepository<M_Accessibility_ReintegrationVisit, Long> {
    List<M_Accessibility_ReintegrationVisit> findBymotherMasterCode(MotherMasterData motherMasterData  );
    
}
