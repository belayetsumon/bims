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

    List<MotherMasterData> findByHomeVisitDateBetween(LocalDate fromdate, LocalDate todate);

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
    List<MotherMasterData> findByOInductionIsNullAndMApprovalDecissionOrderByIdDesc(Decision decission);

    List<MotherMasterData> findByOInductionIsNotNullAndAddmissionIsNullOrderByIdDesc();

    // new mother health conditions check
    //  List<MotherMasterData> findByOInductionIsNotNullAndOInductionOmHealthConditionsIsNullOrderByIdDesc();
    List<MotherMasterData> findByOInductionIsNotNullAndOInductionOmHealthConditionsIsNullOrderByIdDesc();

    // new  professional observations
    List<MotherMasterData> findByOInductionIsNotNullAndOInductionOProfessionalObserbationsMotherIsNullOrderByIdDesc();

    ///  New admission
    List<MotherMasterData> findByOInductionIsNotNullAndOInductionOmHealthConditionsIsNotNullAndOInductionOProfessionalObserbationsMotherIsNotNullAndAddmissionIsNullOrderByIdDesc();

    //Psychology 
    List<MotherMasterData> findByAddmissionIsNotNullAndRPsychologyMotherIsNullAndReleaseMotherIsNullOrderByIdDesc();

    // C_NutritionalStatus
    List<MotherMasterData> findByAddmissionIsNotNullAndReleaseMotherIsNullOrderByIdDesc();

    // Pree re itrigration Visit
    List<MotherMasterData> findByAddmissionIsNotNullAndPreReintegrationVisitIsNullOrderByIdDesc();

    List<MotherMasterData> findByPreReintegrationVisitIsNullAndReleaseMotherIsNullOrderByIdDesc();

    // Re Intirigrations check list
    List<MotherMasterData> findByPreReintegrationVisitIsNotNullAndReintegrationCheckListIsNullOrderByIdDesc();

    List<MotherMasterData> findByReintegrationCheckListReintegrationAndReleaseMotherIsNullOrderByIdDesc(Yes_No Reintegration);

    // Realease 
    List<MotherMasterData> findByReleaseMotherIsNotNullOrderByIdDesc();

    // Follow up
    List<MotherMasterData> findByFollowUpMotherIsNotNullOrderByIdDesc();

    // Custom report
    List<MotherMasterData> findByCreatedBetween(LocalDate fromdate, LocalDate todate);

    List<MotherMasterData> findByEligibilityAndCreatedBetween(Eligibility eligibility, LocalDate fromdate, LocalDate todate);

}
