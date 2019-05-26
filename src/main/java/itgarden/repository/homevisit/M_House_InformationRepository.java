/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit;

import itgarden.model.homevisit.M_House_Information;
import itgarden.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_House_InformationRepository extends JpaRepository<M_House_Information, Long> {
    List<M_House_Information> findBymotherMasterCode(MotherMasterData motherMasterData  );
}
