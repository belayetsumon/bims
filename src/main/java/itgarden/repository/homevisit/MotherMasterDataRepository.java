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
import itgarden.repository.homevisit.projection.AllMotherInterface;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Md Belayet Hossin
 */
public interface MotherMasterDataRepository extends JpaRepository<MotherMasterData, Long> {

    long count();

    List<MotherMasterData> findAllBycreated(LocalDate date);

    List<MotherMasterData> findByHomeVisitDateBetween(LocalDate fromdate, LocalDate todate);

    List<MotherMasterData> findAllByOrderByIdDesc();
    //List<AllMotherInterface> findAllByOrderByIdDesc();

    Long countByeligibility(Eligibility eligibility);

    List<AllMotherInterface> findAllByeligibilityOrderByIdDesc(Eligibility eligibility);// criteria api created

    // List<AllMotherInterface> findAllByeligibilityAndMAddressIsNullOrderByIdDesc(Eligibility eligibility);
    List<MotherMasterData> findAllByeligibilityAndMaddressIsNullOrderByIdDesc(Eligibility eligibility);

    // incomplete mother list
    
    List<AllMotherInterface> findAllByMaddressIsNotNullAndMaccessibilityIsNotNullAndMcommunityInformationIsNotNullAndMcurrentHelpIsNotNullAndMfamilynformationIsNotNullAndMhouseInformationIsNotNullAndMincomeInformationIsNotNullAndMlocalGovtFacilitiesIsNotNullAndMnutritionIsNotNullAndMpropertyIsNotNullAndMapprovalIsNullOrderByIdDesc();

    List<AllMotherInterface> findAllByMaddressIsNotNullAndMaccessibilityIsNullOrderByIdDesc();

    //List<MotherMasterData> findAllByMAddressIsNullOrMAccessibilityIsNullOrMCommunityInformationIsNullOrMCurrentHelpIsNullOrMFamilynformationIsNullOrMHouseInformationIsNullOrMIncomeInformationIsNullOrMLocalGovtFacilitiesIsNullOrMNutritionIsNullOrMPropertyIsNullOrderByIdDesc();
    List<AllMotherInterface> findAllByMaddressIsNotNullOrderByIdDesc();

    // Mother global Search
    List<MotherMasterData> findByMotherNameContaining(String motherName);

    // All childrean 
    //List<MotherMasterData> findAllByMChildinfoIsNotNullAndMApprovalDecissionOrderByIdDesc(Decision decission);
    List<MotherMasterData> findByMchildinfoIsNotNullOrderByIdDesc(); // criteria api created

    // new childrean 
    List<MotherMasterData> findAllByMchildinfoIsNullAndNumberOfEligibleChildrenGreaterThanAndMapprovalDecissionOrderByIdDesc(int numberOfEligibleChildren, Decision decission);

    /// Induction
    List<MotherMasterData> findByOinductionIsNullAndMapprovalDecissionOrderByIdDesc(Decision decission);// criteria api created

    Long countByoinductionIsNotNullAndAddmissionIsNullOrderByIdDesc();

    // new mother health conditions check
    List<MotherMasterData> findByOinductionIsNotNullAndOinductionOmHealthConditionsIsNullOrderByIdDesc();// criteria api created

    //List<MotherMasterData> findByoInductionIsNotNullAndOInductionOmHealthConditionsIsNullOrderByIdDesc();
   
    // new  professional observations

     List<MotherMasterData> findByOinductionIsNotNullAndOprofessionalObserbationsMotherIsNullOrderByIdDesc();

    ///  New admission
    List<MotherMasterData> findByOinductionIsNotNullAndOinductionOmHealthConditionsIsNotNullAndAddmissionIsNullOrderByIdDesc();

    //Psychology 
    List<MotherMasterData> findByAddmissionIsNotNullAndRpsychologyMotherIsNullAndReleaseMotherIsNullOrderByIdDesc();
//
//    // C_NutritionalStatus

    Long countByAddmissionIsNotNullAndReleaseMotherIsNullOrderByIdDesc();

    List<MotherMasterData> findByAddmissionIsNotNullAndReleaseMotherIsNullOrderByIdDesc();
//
//    // Pree re itrigration Visit

    List<MotherMasterData> findByAddmissionIsNotNullAndPreReintegrationVisitIsNullOrderByIdDesc();
//

    Long countByPreReintegrationVisitIsNullAndReleaseMotherIsNullOrderByIdDesc();
//
//    // Re Intirigrations check list

    List<MotherMasterData> findByPreReintegrationVisitIsNotNullAndReintegrationCheckListIsNullOrderByIdDesc();
//

    List<MotherMasterData> findByReintegrationCheckListReintegrationAndReleaseMotherIsNullOrderByIdDesc(Yes_No Reintegration);
//
//    // Realease 

    List<MotherMasterData> findByReleaseMotherIsNotNullOrderByIdDesc();
//
//    // Follow up

    Long countByFollowUpMotherIsNotNullOrderByIdDesc();
//
//    // Custom report
//    List<MotherMasterData> findByCreatedBetween(LocalDate fromdate, LocalDate todate);
//
//    List<MotherMasterData> findByEligibilityAndCreatedBetween(Eligibility eligibility, LocalDate fromdate, LocalDate todate);

}
