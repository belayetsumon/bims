/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.clinic;

import itgarden.model.clinic.C_visit;
import itgarden.model.homevisit.MotherMasterData;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface C_visitRepository extends JpaRepository<C_visit, Long> {
    
    List<C_visit> findBymotherMasterCode(MotherMasterData motherMasterData  );
    
}
