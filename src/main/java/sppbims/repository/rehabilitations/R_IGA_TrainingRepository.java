/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.rehabilitations;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.rehabilitations.R_C_HouseAllocations;
import sppbims.model.rehabilitations.R_IGA_Training;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface R_IGA_TrainingRepository extends JpaRepository<R_IGA_Training, Long> {

    List<R_IGA_Training> findBymotherMasterCode(MotherMasterData motherMasterData);
}
