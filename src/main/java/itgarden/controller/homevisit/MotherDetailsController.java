/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.homevisit.EthinicIdentityRepository;
import itgarden.repository.homevisit.HusbandsStatusRepository;
import itgarden.repository.homevisit.ImmunizationRepository;
import itgarden.repository.homevisit.M_AccessibilityRepository;
import itgarden.repository.homevisit.M_AddressRepository;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.M_Community_InformationRepository;
import itgarden.repository.homevisit.M_Current_HelpRepository;
import itgarden.repository.homevisit.M_Family_informationRepository;
import itgarden.repository.homevisit.M_House_InformationRepository;
import itgarden.repository.homevisit.M_Income_InformationRepository;
import itgarden.repository.homevisit.M_LifestyleRepository;
import itgarden.repository.homevisit.M_Local_Govt_FacilitiesRepository;
import itgarden.repository.homevisit.M_NutritionRepository;
import itgarden.repository.homevisit.M_PropertyRepository;
import itgarden.repository.homevisit.MaritalStatusRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.homevisit.OccupationRepository;
import itgarden.repository.homevisit.PhysicalStatusRepository;
import itgarden.repository.homevisit.ReasonsRepository;
import itgarden.repository.homevisit.RelationsRepository;
import itgarden.repository.homevisit.ReligionRepository;
import itgarden.repository.homevisit.ThanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/motherdetails")
public class MotherDetailsController {

    @Autowired
    ReasonsRepository reasonsRepository;

    @Autowired
    ReligionRepository religionRepository;

    @Autowired
    MaritalStatusRepository maritalStatusRepository;

    @Autowired
    HusbandsStatusRepository husbandsStatusRepository;

    @Autowired
    RelationsRepository relationsRepository;

    @Autowired
    EthinicIdentityRepository ethinicIdentityRepository;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    EducationTypeRepository educationTypeRepository;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    OccupationRepository occupationRepository;

    @Autowired
    PhysicalStatusRepository physicalStatusRepository;

    @Autowired
    ImmunizationRepository immunizationRepository;

    @Autowired
    M_AccessibilityRepository m_AccessibilityRepository;

    @Autowired
    M_AddressRepository m_AddressRepository;

    @Autowired
    ThanaRepository thanaRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @Autowired
    M_Community_InformationRepository m_Community_InformationRepository;

    @Autowired
    M_Current_HelpRepository m_Current_HelpRepository;

    @Autowired
    M_Family_informationRepository m_Family_informationRepository;

    @Autowired
    M_House_InformationRepository m_House_InformationRepository;

    @Autowired
    M_Income_InformationRepository m_Income_InformationRepository;

    @Autowired
    M_LifestyleRepository m_LifestyleRepository;

    @Autowired
    M_Local_Govt_FacilitiesRepository m_Local_Govt_FacilitiesRepository;

    @Autowired
    M_NutritionRepository m_NutritionRepository;

    @Autowired
    M_PropertyRepository m_PropertyRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/mothersearch")
    public String motherSearch(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityAndMAddressIsNullOrderByIdDesc(Eligibility.Eligible));

        return "homevisit/motherdetails/mothersearch";
    }

    @RequestMapping("/allmotherdetails")
    public String allmotherdetails(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("list", motherMasterDataRepository.findAllByMAddressIsNotNullOrderByIdDesc());

        return "homevisit/motherdetails/allmotherdetails";
    }

    @RequestMapping("/incompletemotherdetails")
    public String incompletemotherdetails(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("list", motherMasterDataRepository.findAllByMAddressIsNotNullAndMAccessibilityIsNullOrderByIdDesc());

        return "homevisit/motherdetails/incompletemotherdetails";
    }

    @RequestMapping("/completemotherdetails")
    public String completemotherdetails(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("list", motherMasterDataRepository.findAllByMAddressIsNotNullAndMAccessibilityIsNotNullAndMCommunityInformationIsNotNullAndMCurrentHelpIsNotNullAndMFamilynformationIsNotNullAndMHouseInformationIsNotNullAndMIncomeInformationIsNotNullAndMLocalGovtFacilitiesIsNotNullAndMNutritionIsNotNullAndMPropertyIsNotNullAndMApprovalIsNullOrderByIdDesc());

        return "homevisit/motherdetails/completemotherdetails";
    }

    @RequestMapping("/searchbyid/{mother_master_code}")
    public String search_by_id(@PathVariable Long id, Model model) {
        model.addAttribute("m_info", motherMasterDataRepository.findById(id));
        return "homevisit/motherdetails/motherdetails";
    }

    @RequestMapping("/motherdetails/{mother_id}")
    public String single_mother(Model model, @PathVariable Long mother_id) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(mother_id);
        model.addAttribute("m_info", motherMasterDataRepository.findOne(mother_id));
        model.addAttribute("accessibility", m_AccessibilityRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("addres", m_AddressRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("approval", m_ApprovalRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("community", m_Community_InformationRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("current", m_Current_HelpRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("family", m_Family_informationRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("house", m_House_InformationRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("income", m_Income_InformationRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("lifestyle", m_LifestyleRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("localgovt", m_Local_Govt_FacilitiesRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("nutrition", m_NutritionRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("property", m_PropertyRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/motherdetails/motherdetails";
    }

    @RequestMapping("/newchildren")
    public String newchildren(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("list", motherMasterDataRepository.findAllByMChildinfoIsNullAndNumberOfEligibleChildrenGreaterThanAndMApprovalDecissionOrderByIdDesc(0, Decision.Approve));
        return "homevisit/motherdetails/newchildren";
    }

    @RequestMapping("/msearchchildinfo")
    public String msearchchildinfo(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
       model.addAttribute("list", motherMasterDataRepository.findByMChildinfoIsNotNullOrderByIdDesc());
       // model.addAttribute("childlist", m_Child_infoRepository.findDistinctMotherMasterCodeOrderByIdDesc());
        return "homevisit/motherdetails/msearchforchildinfo";
    }

    @RequestMapping("/motherapproval")
    public String motherApproval(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("list", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve));
        return "homevisit/motherdetails/approval";
    }
}