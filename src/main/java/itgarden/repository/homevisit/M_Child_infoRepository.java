/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_Child_infoRepository extends JpaRepository<M_Child_info, Long> {
    

    List<M_Child_info> findBymotherMasterCode(MotherMasterData motherMasterData);

//   List<M_Child_info> findDistinctByMotherMasterCodeOrderByIdDesc();
    List<M_Child_info> findByChildAdmissionIsNotNullAndEligibilityStudentIsNullAndRegularAdmissionClassIsNullAndReleaseChildIsNull();

    List<M_Child_info> findByMotherMasterCodeAndLfosteIsNullAndReleaseChildIsNull(MotherMasterData motherMasterData);
    
    List<M_Child_info> findByLfosteIsNotNullAndReleaseChildIsNull();
    
    List<M_Child_info> findByHigherStudyIsNotNullAndReleaseChildIsNull();
    
    List<M_Child_info> findByJobIsNotNullAndReleaseChildIsNull();
    
    List<M_Child_info> findByMarriageIsNotNullAndReleaseChildIsNull();
    
    // total Child now spp
    
    List<M_Child_info> findByChildAdmissionIsNotNullAndReleaseChildIsNull();
    
    
    List<M_Child_info> findByFollowUpChildrenIsNotNull();
    
    
}
