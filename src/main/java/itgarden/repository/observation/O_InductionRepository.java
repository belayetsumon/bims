/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_Induction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface O_InductionRepository extends JpaRepository<O_Induction, Long> {
     List<O_Induction> findBymotherMasterCode(MotherMasterData motherMasterData  );
     O_Induction findByMotherMasterCode(MotherMasterData motherMasterData);
}
