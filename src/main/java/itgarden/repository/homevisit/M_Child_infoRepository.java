/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import java.time.LocalDate;
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

    // long tream Care 
    List<M_Child_info> findByLfosteIsNullAndReleaseChildIsNull();

    List<M_Child_info> findByLfosteIsNotNullAndReleaseChildIsNull();

    Long countByLfosteIsNotNullAndReleaseChildIsNull();

    Long countByHigherStudyIsNotNullAndReleaseChildIsNull();

    Long countByJobIsNotNullAndReleaseChildIsNull();

    Long countByMarriageIsNotNullAndReleaseChildIsNull();

    // total Child now spp
    Long countByChildAdmissionIsNotNullAndReleaseChildIsNull();

    // new discontinue 
    List<M_Child_info> findByRegularAdmissionClassIsNotNullAndDiscontinuityIsNull();

    Long countByFollowUpChildrenIsNotNull();

    List<M_Child_info> findByCreatedBetween(LocalDate fromdate, LocalDate todate);

}
