/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.M_Lifestyle;
import sppbims.model.homevisit.M_Local_Govt_Facilities;
import sppbims.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_Local_Govt_FacilitiesRepository extends JpaRepository<M_Local_Govt_Facilities, Long> {

    List< M_Local_Govt_Facilities> findBymotherMasterCode(MotherMasterData motherMasterData);
}
