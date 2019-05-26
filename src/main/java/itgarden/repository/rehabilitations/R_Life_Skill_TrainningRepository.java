/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_IGA_Training;
import itgarden.model.rehabilitations.R_Life_Skill_Trainning;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface R_Life_Skill_TrainningRepository extends JpaRepository<R_Life_Skill_Trainning, Long> {
     List<R_Life_Skill_Trainning> findBymotherMasterCode(MotherMasterData motherMasterData);
}
