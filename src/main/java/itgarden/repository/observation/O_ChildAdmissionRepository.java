/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_CHealthConditions;
import itgarden.model.observation.O_ChildAdmission;
import itgarden.model.observation.O_MAddmission;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface O_ChildAdmissionRepository extends JpaRepository<O_ChildAdmission, Long> {
    List<O_ChildAdmission> findBymotherMasterCode(MotherMasterData motherMasterData  );
    
    List<O_ChildAdmission> findByCreatedBetween(LocalDate fromdate, LocalDate todate);
}
