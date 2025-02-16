/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.M_Current_Help;
import sppbims.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_Current_HelpRepository extends JpaRepository<M_Current_Help, Long> {

    List<M_Current_Help> findBymotherMasterCode(MotherMasterData motherMasterData);

}
