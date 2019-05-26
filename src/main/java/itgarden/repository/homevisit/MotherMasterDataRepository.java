/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit;

import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface MotherMasterDataRepository extends JpaRepository<MotherMasterData, Long> {

    List<MotherMasterData> findAllBycreated(LocalDate date);

    MotherMasterData findById(Long id);

    List<MotherMasterData> findAllByOrderByIdDesc();

    List<MotherMasterData> findAllByeligibilityOrderByIdDesc(Eligibility eligibility);

    List<MotherMasterData> findAllByeligibilityAndMAddressIsNullOrderByIdDesc(Eligibility eligibility);

    List<MotherMasterData> findAllByMAddressIsNotNullAndMAccessibilityIsNotNullAndMCommunityInformationIsNotNullAndMCurrentHelpIsNotNullAndMFamilynformationIsNotNullAndMHouseInformationIsNotNullAndMIncomeInformationIsNotNullAndMLocalGovtFacilitiesIsNotNullAndMNutritionIsNotNullAndMPropertyIsNotNullAndMApprovalIsNullOrderByIdDesc();

    List<MotherMasterData> findAllByMAddressIsNotNullAndMAccessibilityIsNullOrderByIdDesc();

    //List<MotherMasterData> findAllByMAddressIsNullOrMAccessibilityIsNullOrMCommunityInformationIsNullOrMCurrentHelpIsNullOrMFamilynformationIsNullOrMHouseInformationIsNullOrMIncomeInformationIsNullOrMLocalGovtFacilitiesIsNullOrMNutritionIsNullOrMPropertyIsNullOrderByIdDesc();
    List<MotherMasterData> findAllByMAddressIsNotNullOrderByIdDesc();

    // Mother global Search
    List<MotherMasterData> findByMotherNameContaining(String motherName);

    // All childrean 
    //List<MotherMasterData> findAllByMChildinfoIsNotNullAndMApprovalDecissionOrderByIdDesc(Decision decission);
    
    List<MotherMasterData> findByMChildinfoIsNotNullOrderByIdDesc();

    // new childrean 
    List<MotherMasterData> findAllByMChildinfoIsNullAndNumberOfEligibleChildrenGreaterThanAndMApprovalDecissionOrderByIdDesc(int numberOfEligibleChildren, Decision decission);

    /// Induction
    List<MotherMasterData> findByOInductionIsNullAndMApprovalDecission(Decision decission);
    
    List<MotherMasterData> findByOInductionIsNotNullAndAddmissionIsNull();

    // new mother health conditions check
    List<MotherMasterData> findByOInductionIsNotNullAndOInductionOmHealthConditionsIsNull();

    // new  professional observations
    List<MotherMasterData> findByOInductionIsNotNullAndOInductionOProfessionalObserbationsMotherIsNull();

    ///  New admission
    List<MotherMasterData> findByOInductionIsNotNullAndOInductionOmHealthConditionsIsNotNullAndOInductionOProfessionalObserbationsMotherIsNotNullAndAddmissionIsNullOrderByIdDesc();
   
    
    //Psychology 
    
    List<MotherMasterData> findByAddmissionIsNotNullAndRPsychologyMotherIsNullAndReleaseMotherIsNull();
    
    
    // C_NutritionalStatus
    
    List<MotherMasterData> findByAddmissionIsNotNullAndReleaseMotherIsNull();
    
    
    // Pree re itrigration Visit

    List<MotherMasterData> findByAddmissionIsNotNullAndPreReintegrationVisitIsNull();
    
    List<MotherMasterData> findByPreReintegrationVisitIsNullAndReleaseMotherIsNull();

    // Re Intirigrations check list
    List<MotherMasterData> findByPreReintegrationVisitIsNotNullAndReintegrationCheckListIsNull();

    List<MotherMasterData> findByReintegrationCheckListReintegrationAndReleaseMotherIsNull(Yes_No Reintegration);

    // Realease 
    List<MotherMasterData> findByReleaseMotherIsNotNull();
    
    
    // Follow up
    
     List<MotherMasterData> findByFollowUpMotherIsNotNull();

}
