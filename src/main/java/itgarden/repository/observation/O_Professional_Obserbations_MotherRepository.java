/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_Professional_Obserbations_Child;
import itgarden.model.observation.O_Professional_Obserbations_Mother;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface O_Professional_Obserbations_MotherRepository extends JpaRepository<O_Professional_Obserbations_Mother, Long> {
      List<O_Professional_Obserbations_Mother> findBymotherMasterCode(MotherMasterData motherMasterData);
      
}
