/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.M_Current_Help;
import sppbims.model.homevisit.M_Family_information;
import sppbims.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_Family_informationRepository extends JpaRepository<M_Family_information, Long> {

    List<M_Family_information> findBymotherMasterCode(MotherMasterData motherMasterData);

}
