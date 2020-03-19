/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.pre_reintegration_visit;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_visit.PreReintegrationVisit;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_AccessibilityRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_AddressRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_Community_InformationRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_Family_informationRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_House_InformationRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_LifestyleRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_Repository;
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
 * @author User
 */
@Controller
@RequestMapping("/pre_reintegration_visit")
public class Pre_reintegration_visit_Controller {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    Pre_reintegration_visit_Repository pre_reintegration_visit_Repository;

    @Autowired
    Pre_reintegration_visit_M_AccessibilityRepository pre_reintegration_visit_M_AccessibilityRepository;

    @Autowired
    Pre_reintegration_visit_M_AddressRepository pre_reintegration_visit_M_AddressRepository;

    @Autowired
    Pre_reintegration_visit_M_House_InformationRepository pre_reintegration_visit_M_House_InformationRepository;

    @Autowired
    Pre_reintegration_visit_M_Community_InformationRepository pre_reintegration_visit_M_Community_InformationRepository;

    @Autowired
    Pre_reintegration_visit_M_LifestyleRepository pre_reintegration_visit_M_LifestyleRepository;

    @Autowired
    Pre_reintegration_visit_M_Family_informationRepository pre_reintegration_visit_M_Family_informationRepository;

    @RequestMapping("/motherlist")
    public String motherlist(Model model) {
     model.addAttribute("list", motherMasterDataRepository.findByAddmissionIsNotNullAndPreReintegrationVisitIsNullOrderByIdDesc());
        return "pre_reintegration_visit/mothersearch";
    }

    @RequestMapping("/index")
    public String add(Model model) {
        model.addAttribute("list", pre_reintegration_visit_Repository.findAll());
        return "pre_reintegration_visit/index";
    }

    @RequestMapping("/details/{m_id}")
    public String details(Model model, @PathVariable Long m_id, PreReintegrationVisit preReintegrationVisit) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        model.addAttribute("preReintegrationVisit", pre_reintegration_visit_Repository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("accessibility", pre_reintegration_visit_M_AccessibilityRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("m_address", pre_reintegration_visit_M_AddressRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("house", pre_reintegration_visit_M_House_InformationRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("community", pre_reintegration_visit_M_Community_InformationRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("lifestyle", pre_reintegration_visit_M_LifestyleRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("family", pre_reintegration_visit_M_Family_informationRepository.findBymotherMasterCode(motherMasterData));
        return "pre_reintegration_visit/details";
    }

    @RequestMapping("/add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, PreReintegrationVisit preReintegrationVisit) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        preReintegrationVisit.setMotherMasterCode(motherMasterData);
        return "pre_reintegration_visit/pre_reintegration_visit";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, PreReintegrationVisit preReintegrationVisit, Model model) {
        model.addAttribute("preReintegrationVisit", pre_reintegration_visit_Repository.findOne(id));
        return "pre_reintegration_visit/pre_reintegration_visit";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid PreReintegrationVisit preReintegrationVisit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "pre_reintegration_visit/pre_reintegration_visit";
        }
        pre_reintegration_visit_Repository.save(preReintegrationVisit);

        return "redirect:/pre_reintegration_visit/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, PreReintegrationVisit preReintegrationVisit, RedirectAttributes redirectAttrs) {
        preReintegrationVisit = pre_reintegration_visit_Repository.findOne(id);
        redirectAttrs.addAttribute("m_id", preReintegrationVisit.motherMasterCode.getId());
        pre_reintegration_visit_Repository.delete(id);
        return "redirect:/pre_reintegration_visit/index";
    }

}
