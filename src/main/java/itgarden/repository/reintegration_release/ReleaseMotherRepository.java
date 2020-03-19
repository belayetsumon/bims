/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.reintegration_release;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.reintegration_release.ReleaseMother;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ReleaseMotherRepository extends JpaRepository<ReleaseMother, Long> {
    

    List<ReleaseMother> findBymotherMasterCode(MotherMasterData motherMasterData);
    

    ReleaseMother findByMotherMasterCode(MotherMasterData motherMasderCode);
    

    List<ReleaseMother> findByCreatedBetween(LocalDate fromdate, LocalDate todate);
    

}
