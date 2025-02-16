/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.M_Local_Govt_Facilities;
import sppbims.model.homevisit.M_Nutrition;
import sppbims.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_NutritionRepository extends JpaRepository<M_Nutrition, Long> {

    List< M_Nutrition> findBymotherMasterCode(MotherMasterData motherMasterData);
}
