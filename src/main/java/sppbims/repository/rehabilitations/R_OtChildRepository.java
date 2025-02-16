/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.rehabilitations;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.rehabilitations.R_OtChild;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface R_OtChildRepository extends JpaRepository<R_OtChild, Long> {

    List<R_OtChild> findBymotherMasterCode(MotherMasterData motherMasterData);
}
