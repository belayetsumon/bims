/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.M_Accessibility;
import sppbims.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_AccessibilityRepository extends JpaRepository<M_Accessibility, Long> {

    List<M_Accessibility> findBymotherMasterCode(MotherMasterData motherMasterData);

    M_Accessibility findByMotherMasterCode_Id(Long data);

}
