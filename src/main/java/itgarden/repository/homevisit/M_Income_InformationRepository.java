/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit;

import itgarden.model.homevisit.M_Income_Information;
import itgarden.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_Income_InformationRepository extends JpaRepository<M_Income_Information, Long> {
        List<M_Income_Information> findBymotherMasterCode(MotherMasterData motherMasterData  );
}
