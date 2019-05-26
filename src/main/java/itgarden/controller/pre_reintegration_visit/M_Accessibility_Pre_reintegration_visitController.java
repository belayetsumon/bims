/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.pre_reintegration_visit;

import itgarden.model.homevisit.M_Accessibility;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.pre_reintegration_visit.M_Accessibility_ReintegrationVisit;
import itgarden.repository.homevisit.Road_TypeRepository;
import itgarden.repository.homevisit.Transport_TypeRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_AccessibilityRepository;
import javax.validation.Valid;
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
@RequestMapping("/pre_reintegration_m_accessibility")
public class M_Accessibility_Pre_reintegration_visitController {

    @Autowired
    Pre_reintegration_visit_M_AccessibilityRepository pre_reintegration_visit_M_AccessibilityRepository;
    @Autowired
    Road_TypeRepository road_TypeRepository;
    @Autowired
    Transport_TypeRepository transport_TypeRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Accessibility m_Accessibility) {
        model.addAttribute("list", pre_reintegration_visit_M_AccessibilityRepository.findAll());
        return "homevisit/lookup/m_accessibility";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Accessibility_ReintegrationVisit m_Accessibility_ReintegrationVisit) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        model.addAttribute("form_title", "Mother Accessibility Add");
        m_Accessibility_ReintegrationVisit.setMotherMasterCode(motherMasterData);
        model.addAttribute("road", road_TypeRepository.findAll());
        model.addAttribute("transport", transport_TypeRepository.findAll());
        model.addAttribute("primary_school", Yes_No.values());
        model.addAttribute("secondary_school", Yes_No.values());
        model.addAttribute("healthCare", Yes_No.values());
        model.addAttribute("marketPlace", Yes_No.values());
        model.addAttribute("bank", Yes_No.values());
        model.addAttribute("ngo", Yes_No.values());
        model.addAttribute("welfareInstitutions", Yes_No.values());
        model.addAttribute("technicalTrainingInstitute", Yes_No.values());
        return "pre_reintegration_visit/m_accessibility";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Accessibility_ReintegrationVisit m_Accessibility_ReintegrationVisit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Accessibility_ReintegrationVisit.setMotherMasterCode(motherMasterData);

            model.addAttribute("form_title", "Mother Accessibility Edit/Update");
            model.addAttribute("road", road_TypeRepository.findAll());
            model.addAttribute("transport", transport_TypeRepository.findAll());
            model.addAttribute("primary_school", Yes_No.values());
            model.addAttribute("secondary_school", Yes_No.values());
            model.addAttribute("healthCare", Yes_No.values());
            model.addAttribute("marketPlace", Yes_No.values());
            model.addAttribute("bank", Yes_No.values());
            model.addAttribute("ngo", Yes_No.values());
            model.addAttribute("welfareInstitutions", Yes_No.values());
            model.addAttribute("technicalTrainingInstitute", Yes_No.values());
            return "pre_reintegration_visit/m_accessibility";
        }

        pre_reintegration_visit_M_AccessibilityRepository.save(m_Accessibility_ReintegrationVisit);
        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable Long id, M_Accessibility_ReintegrationVisit m_Accessibility_ReintegrationVisit) {
        model.addAttribute("m_Accessibility_ReintegrationVisit", pre_reintegration_visit_M_AccessibilityRepository.findOne(id));
        model.addAttribute("road", road_TypeRepository.findAll());
        model.addAttribute("transport", transport_TypeRepository.findAll());
        model.addAttribute("primary_school", Yes_No.values());
        model.addAttribute("secondary_school", Yes_No.values());
        model.addAttribute("healthCare", Yes_No.values());
        model.addAttribute("marketPlace", Yes_No.values());
        model.addAttribute("bank", Yes_No.values());
        model.addAttribute("ngo", Yes_No.values());
        model.addAttribute("welfareInstitutions", Yes_No.values());
        model.addAttribute("technicalTrainingInstitute", Yes_No.values());
        return "pre_reintegration_visit/m_accessibility";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Accessibility_ReintegrationVisit m_Accessibility_ReintegrationVisit, RedirectAttributes redirectAttrs) {

        m_Accessibility_ReintegrationVisit = pre_reintegration_visit_M_AccessibilityRepository.findOne(id);

        redirectAttrs.addAttribute("m_id", m_Accessibility_ReintegrationVisit.motherMasterCode.getId());

        pre_reintegration_visit_M_AccessibilityRepository.delete(id);
        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

}
