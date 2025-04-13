/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.homevisit;

import sppbims.model.homevisit.Decision;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.EducationLevelRepository;
import sppbims.repository.homevisit.EducationTypeRepository;
import sppbims.repository.homevisit.EthinicIdentityRepository;
import sppbims.repository.homevisit.HusbandsStatusRepository;
import sppbims.repository.homevisit.ImmunizationRepository;
import sppbims.repository.homevisit.M_AccessibilityRepository;
import sppbims.repository.homevisit.M_AddressRepository;
import sppbims.repository.homevisit.M_ApprovalRepository;
import sppbims.repository.homevisit.M_Child_infoRepository;
import sppbims.repository.homevisit.M_Community_InformationRepository;
import sppbims.repository.homevisit.M_Current_HelpRepository;
import sppbims.repository.homevisit.M_Family_informationRepository;
import sppbims.repository.homevisit.M_House_InformationRepository;
import sppbims.repository.homevisit.M_Income_InformationRepository;
import sppbims.repository.homevisit.M_LifestyleRepository;
import sppbims.repository.homevisit.M_Local_Govt_FacilitiesRepository;
import sppbims.repository.homevisit.M_NutritionRepository;
import sppbims.repository.homevisit.M_PropertyRepository;
import sppbims.repository.homevisit.MaritalStatusRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.homevisit.OccupationRepository;
import sppbims.repository.homevisit.PhysicalStatusRepository;
import sppbims.repository.homevisit.ReasonsRepository;
import sppbims.repository.homevisit.RelationsRepository;
import sppbims.repository.homevisit.ReligionRepository;
import sppbims.repository.homevisit.ThanaRepository;
import sppbims.services.homevisit.M_ApprovalServices;
import sppbims.services.homevisit.MotherMasterDataServices;
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

    @Autowired
    MotherMasterDataServices motherMasterDataServices;

    @Autowired
    M_ApprovalServices approvalService;

    @RequestMapping("/mothersearch")
    public String motherSearch(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());

        // model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityAndMaddressIsNullOrderByIdDesc(Eligibility.Eligible));
        // model.addAttribute("list", motherMasterDataServices.findAllByEligibilityAndMaddressIsNullOrderByIdDesc(Eligibility.Eligible));
        model.addAttribute("list", motherMasterDataServices.allmothersDetailsEmptyList());

        return "homevisit/motherdetails/mothersearch";
    }

    @RequestMapping("/allmotherdetails")
    public String allmotherdetails(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        // model.addAttribute("list", motherMasterDataRepository.findAllByOrderByIdDesc());
        // model.addAttribute("list", motherMasterDataRepository.findAllByMaddressIsNotNullOrderByIdDesc());

        model.addAttribute("list", motherMasterDataServices.motherDetailscompleteList());

        return "homevisit/motherdetails/allmotherdetails";
    }

    @RequestMapping("/incompletemotherdetails")
    public String incompletemotherdetails(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        //      model.addAttribute("list", motherMasterDataRepository.findAllByMaddressIsNotNullAndMaccessibilityIsNullOrderByIdDesc());

        model.addAttribute("list", motherMasterDataServices.motherDetailsIncompleteList());
        return "homevisit/motherdetails/incompletemotherdetails";
    }

    @RequestMapping("/completemotherdetails")
    public String completemotherdetails(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("list", motherMasterDataRepository.findAllByMaddressIsNotNullAndMaccessibilityIsNotNullAndMcommunityInformationIsNotNullAndMcurrentHelpIsNotNullAndMfamilynformationIsNotNullAndMhouseInformationIsNotNullAndMincomeInformationIsNotNullAndMlocalGovtFacilitiesIsNotNullAndMnutritionIsNotNullAndMpropertyIsNotNullAndMapprovalIsNullOrderByIdDesc());

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
        model.addAttribute("m_info", motherMasterDataRepository.findById(mother_id).orElse(null));
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

    @RequestMapping("/pdf/{mother_id}")
    public String single_mother_pdf(Model model, @PathVariable Long mother_id) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(mother_id);
        model.addAttribute("m_info", motherMasterDataRepository.findById(mother_id).orElse(null));
        model.addAttribute("accessibility", m_AccessibilityRepository.findByMotherMasterCode_Id(mother_id));
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
        model.addAttribute("property", m_PropertyRepository.findByMotherMasterCode_Id(mother_id));
        return "homevisit/motherdetails/motherdetails_print_view";
    }

    @RequestMapping("/newchildren")
    public String newchildren(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        //   model.addAttribute("list", motherMasterDataRepository.findAllByMchildinfoIsNullAndNumberOfEligibleChildrenGreaterThanAndMapprovalDecissionOrderByIdDesc(0, Decision.Approve));

        model.addAttribute("list", motherMasterDataServices.findAllByMchildinfoIsNullAndNumberOfEligibleChildrenGreaterThanAndMapprovalDecissionOrderByIdDesc());
        return "homevisit/motherdetails/newchildren";
    }

    @RequestMapping("/msearchchildinfo")
    public String msearchchildinfo(Model model) {

        // model.addAttribute("list", motherMasterDataRepository.findByMchildinfoIsNotNullOrderByIdDesc());
        model.addAttribute("list", motherMasterDataServices.findByMchildinfoIsNotNullOrderByIdDesc());

        return "homevisit/motherdetails/msearchforchildinfo";
    }

    @RequestMapping("/motherapproval")
    public String motherApproval(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        // model.addAttribute("list", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve));
        model.addAttribute("list", approvalService.allApprovalMother(Decision.Approve));
        return "homevisit/motherdetails/approval";
    }

    @RequestMapping("/mother_not_approval")
    public String mother_not_approval(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAll());
        // model.addAttribute("list", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve));
        model.addAttribute("list", approvalService.allApprovalMother(Decision.Not_Approve));
        return "homevisit/motherdetails/not_approval";
    }
}
