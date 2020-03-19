/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.pre_reintegration_visit;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_visit.PreReintegrationVisit;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface Pre_reintegration_visit_Repository  extends JpaRepository<PreReintegrationVisit, Long> {
     PreReintegrationVisit findBymotherMasterCode(MotherMasterData motherMasterData  );
   // List<PreReintegrationVisit> findBymotherMasterCode(MotherMasterData motherMasterData  );
    
    List<PreReintegrationVisit>  findByCreatedBetween(LocalDate fromdate, LocalDate todate);
     
}
