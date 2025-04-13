/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.observation;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.observation.O_Induction;
import sppbims.model.observation.O_MAddmission;
import sppbims.repository.homevisit.M_ApprovalRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.observation.O_ChildAdmissionRepository;
import sppbims.repository.observation.O_InductionRepository;
import sppbims.repository.observation.O_MAddmissionRepository;
import sppbims.services.StorageProperties;
import sppbims.services.observation.O_MAddmissionService;
import jakarta.validation.Valid;
import java.util.Optional;
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

    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @RequestMapping("/motherlist")
    public String index(Model model) {

        //model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("list", o_MAddmissionService.allAdmitedMotherList());
        return "homevisit/observation/admission/mothersearch";
    }

    @RequestMapping("/newadmission")
    public String newadmission(Model model) {
        // model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        model.addAttribute("list", motherMasterDataRepository.findByOinductionIsNotNullAndOinductionOmHealthConditionsIsNotNullAndAddmissionIsNullOrderByIdDesc());
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

    @RequestMapping("/pdf/{id}")
    public String pdf(Model model, @PathVariable Long id) {
        model.addAttribute("minfo", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_MAddmission", o_MAddmissionRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_ChildAddmission", o_ChildAdmissionRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/admission/index_pdf";
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

        model.addAttribute("o_MAddmission", o_MAddmissionRepository.findById(id).orElse(null));

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

        model.addAttribute("o_MAddmission", motherMasterDataRepository.findById(id));
        o_MAddmissionRepository.save(o_MAddmission);

        redirectAttributes.addFlashAttribute("message", "Sucess");

        return "redirect:/admission/index/{id}";

    }

    @Transactional
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_MAddmission o_MAddmission, RedirectAttributes redirectAttrs) {

        Optional<O_MAddmission> optionalo_MAddmission = o_MAddmissionRepository.findById(id);

        o_MAddmission = optionalo_MAddmission.orElse(null);

        redirectAttrs.addAttribute("id", o_MAddmission.motherMasterCode.getId());
        o_MAddmissionRepository.deleteById(id);
        return "redirect:/admission/index/{id}";
    }
}
