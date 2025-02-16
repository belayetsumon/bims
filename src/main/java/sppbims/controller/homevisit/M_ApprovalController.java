/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.homevisit;

import sppbims.model.homevisit.Decision;
import sppbims.model.homevisit.M_Approval;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.EducationLevelRepository;
import sppbims.repository.homevisit.EducationTypeRepository;
import sppbims.repository.homevisit.EthinicIdentityRepository;
import sppbims.repository.homevisit.HusbandsStatusRepository;
import sppbims.repository.homevisit.ImmunizationRepository;
import sppbims.repository.homevisit.M_AccessibilityRepository;
import sppbims.repository.homevisit.M_AddressRepository;
import sppbims.repository.homevisit.M_ApprovalRepository;
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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/m_approval")
public class M_ApprovalController {

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;
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

    @RequestMapping("/index")

    public String index(Model model, M_Approval m_Approval) {
        model.addAttribute("list", m_ApprovalRepository.findAll());
        return "homevisit/lookup/m_approval";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Approval m_Approval) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Approval.setMotherMasterCode(motherMasterData);

        model.addAttribute("decission", Decision.values());

        return "homevisit/motherdetails/m_approval";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_Approval m_Approval, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            m_Approval.setMotherMasterCode(motherMasterData);

            model.addAttribute("decission", Decision.values());

            return "homevisit/motherdetails/m_approval";
        }

        m_ApprovalRepository.save(m_Approval);

        return "redirect:/m_approval/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")

    public String edit(@PathVariable Long id, M_Approval m_Approval, Model model) {

        model.addAttribute("m_Approval", m_ApprovalRepository.findById(id).orElse(null));

        model.addAttribute("decission", Decision.values());

        return "homevisit/motherdetails/m_approval";
    }

    @GetMapping(value = "/delete/{id}")

    public String delete(@PathVariable Long id, M_Approval m_Approval, RedirectAttributes redirectAttrs) {

        m_Approval = m_ApprovalRepository.findById(id).orElse(null);

        redirectAttrs.addAttribute("m_id", m_Approval.motherMasterCode.getId());

        m_ApprovalRepository.deleteById(id);

        return "redirect:/m_approval/motherdetails/{m_id}";
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

        return "homevisit/motherdetails/motherdetailsapproval";

    }

}
