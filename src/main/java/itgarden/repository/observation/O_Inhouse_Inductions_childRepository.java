/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_Inhouse_Inductions_child;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface O_Inhouse_Inductions_childRepository extends JpaRepository<O_Inhouse_Inductions_child, Long> {
    List<O_Inhouse_Inductions_child> findBymotherMasterCode(MotherMasterData motherMasterData  );
}
