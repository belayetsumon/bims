/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.observation;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.observation.O_Professional_Obserbations_Child;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface O_Professional_Obserbations_ChildRepository extends JpaRepository<O_Professional_Obserbations_Child, Long> {

    List<O_Professional_Obserbations_Child> findBymotherMasterCode(MotherMasterData motherMasterData);
}
