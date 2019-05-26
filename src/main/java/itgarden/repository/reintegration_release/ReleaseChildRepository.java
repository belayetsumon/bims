/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.reintegration_release;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.reintegration_release.ReleaseChild;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ReleaseChildRepository extends JpaRepository<ReleaseChild, Long> {

    List<ReleaseChild> findBymotherMasterCode(MotherMasterData motherMasterData);
    
    ReleaseChildRepository findByChildMasterCode(M_Child_info m_Child_info);
}
