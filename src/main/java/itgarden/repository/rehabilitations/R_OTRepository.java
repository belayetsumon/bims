/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_OT;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface R_OTRepository extends JpaRepository<R_OT, Long> {
   List<R_OT> findBymotherMasterCode(MotherMasterData motherMasterData  ); 
}
