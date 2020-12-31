/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.reintegration_checklist;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_checklist.ReintegrationCheckList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ReintegrationCheckListRepository extends JpaRepository<ReintegrationCheckList, Long> {
    List<ReintegrationCheckList> findBymotherMasterCode(MotherMasterData motherMasterData  );
}
