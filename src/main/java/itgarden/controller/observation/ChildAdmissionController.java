/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_ChildAdmission;
import itgarden.model.observation.O_Induction;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_ChildAdmissionRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
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
@RequestMapping("/childadmission")
public class ChildAdmissionController {

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
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/create/{m_id}")
    public String mAdd(Model model, @PathVariable Long m_id, O_ChildAdmission o_ChildAdmission) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        o_ChildAdmission.setMotherMasterCode(motherMasterData);

        O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);

        o_ChildAdmission.setDateArrival(o_Induction.getStartDate());

        model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

        model.addAttribute("inductiondate", o_Induction.getStartDate());

        return "homevisit/observation/admission/addchild";
    }

    @RequestMapping("/edit/{c_id}")
    public String mEdit(Model model, @PathVariable Long c_id, O_ChildAdmission o_ChildAdmission) {

        model.addAttribute("o_ChildAdmission", o_ChildAdmissionRepository.findById(c_id).orElse(null));

        O_ChildAdmission oo_ChildAdmission = o_ChildAdmissionRepository.findById(c_id).orElse(null);

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(oo_ChildAdmission.getMotherMasterCode().getId());
        o_ChildAdmission.setMotherMasterCode(motherMasterData);

        O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
        o_ChildAdmission.setDateArrival(o_Induction.getStartDate());
        model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("inductiondate", o_Induction.getStartDate());

        return "homevisit/observation/admission/addchild";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, O_ChildAdmission o_ChildAdmission, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            o_ChildAdmission.setMotherMasterCode(motherMasterData);

            O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);

            o_ChildAdmission.setDateArrival(o_Induction.getStartDate());

            model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

            model.addAttribute("inductiondate", o_Induction.getStartDate());

            return "homevisit/observation/admission/addchild";
        }

        o_ChildAdmissionRepository.save(o_ChildAdmission);
        return "redirect:/admission/index/{m_id}";
    }

    @Transactional
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_ChildAdmission o_ChildAdmission, RedirectAttributes redirectAttrs) {
        o_ChildAdmission = o_ChildAdmissionRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", o_ChildAdmission.motherMasterCode.getId());
        o_ChildAdmissionRepository.deleteById(id);
        return "redirect:/admission/index/{m_id}";
    }

}
