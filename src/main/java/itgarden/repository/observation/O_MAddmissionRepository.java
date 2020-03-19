/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_MAddmission;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface O_MAddmissionRepository extends JpaRepository<O_MAddmission, Long> {

    List<O_MAddmission> findBymotherMasterCode(MotherMasterData motherMasterData);

    O_MAddmission findByMotherMasterCode(MotherMasterData motherMasterData);

    List<O_MAddmission> findBymotherImageIsNullOrderByIdDesc();

    List<O_MAddmission> findByCreated(MotherMasterData motherMasterData);

    List<O_MAddmission> findByCreatedBetween(LocalDate fromdate, LocalDate todate);

}
