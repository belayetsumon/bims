/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_Induction;
import itgarden.model.observation.O_MAddmission;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_ChildAdmissionRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.services.StorageProperties;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/admission")
public class AdmissionController {

    @Autowired
    StorageProperties properties;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @Autowired
    O_ChildAdmissionRepository o_ChildAdmissionRepository;

    @RequestMapping("/motherlist")
    public String page(Model model) {
        
        model.addAttribute("list", o_MAddmissionRepository.findAll());
        return "homevisit/observation/admission/mothersearch";
    }
    
    @RequestMapping("/newadmission")
    public String newadmission(Model model) {
        // model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        model.addAttribute("list",  motherMasterDataRepository.findByOInductionIsNotNullAndOInductionOmHealthConditionsIsNotNullAndOInductionOProfessionalObserbationsMotherIsNotNullAndAddmissionIsNullOrderByIdDesc());
        return "homevisit/observation/admission/newadmission";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("minfo", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_MAddmission", o_MAddmissionRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_ChildAddmission", o_ChildAdmissionRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/admission/index";
    }

    @RequestMapping("/create/{id}")
    public String mAdd(Model model, @PathVariable Long id, O_MAddmission o_MAddmission) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        o_MAddmission.setMotherMasterCode(motherMasterData);
        O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
        o_MAddmission.setDateArrival(o_Induction.getStartDate());
        model.addAttribute("inductiondate", o_Induction.getStartDate());
        return "homevisit/observation/admission/addmotherimg";
    }

    @RequestMapping("/edit/{id}")
    public String mEdit(Model model, @PathVariable Long id, O_MAddmission o_MAddmission) {
        model.addAttribute("o_MAddmission", o_MAddmissionRepository.findOne(id));
        return "homevisit/observation/admission/addmotherimg";
    }

    @RequestMapping("/save/{id}")

    public String mSave(Model model, @PathVariable Long id, @Valid O_MAddmission o_MAddmission,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(id);
            o_MAddmission.setMotherMasterCode(motherMasterData);
            return "homevisit/observation/admission/addmotherimg";
        }

        model.addAttribute("message", "You successfully uploaded");

        model.addAttribute("o_MAddmission", motherMasterDataRepository.findOne(id));
        o_MAddmissionRepository.save(o_MAddmission);

        redirectAttributes.addFlashAttribute("message", "Sucess");

        return "redirect:/admission/index/{id}";

    }

    @Transactional
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_MAddmission o_MAddmission, RedirectAttributes redirectAttrs) {
        o_MAddmission = o_MAddmissionRepository.findOne(id);
        redirectAttrs.addAttribute("id", o_MAddmission.motherMasterCode.getId());
        o_MAddmissionRepository.delete(id);
        return "redirect:/admission/index/{id}";
    }
}
