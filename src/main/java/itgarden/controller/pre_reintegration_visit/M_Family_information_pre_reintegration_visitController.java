/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.pre_reintegration_visit;

import itgarden.model.homevisit.Gender;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_visit.M_Family_information_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.M_Lifestyle_ReintegrationVisit;
import itgarden.repository.homevisit.OccupationRepository;
import itgarden.repository.homevisit.RelationsRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_Family_informationRepository;
import jakarta.validation.Valid;
import java.util.Optional;
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

@RequestMapping("/pre_reintegration_m_family_information")
public class M_Family_information_pre_reintegration_visitController {

    @Autowired
    Pre_reintegration_visit_M_Family_informationRepository pre_reintegration_visit_M_Family_informationRepository;

    @Autowired
    OccupationRepository occupationRepository;

    @Autowired
    RelationsRepository relationsRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Lifestyle_ReintegrationVisit m_Lifestyle_ReintegrationVisit) {
        model.addAttribute("list", pre_reintegration_visit_M_Family_informationRepository.findAll());

        return "homevisit/lookup/m_family_information";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Family_information_ReintegrationVisit m_Family_information_ReintegrationVisit) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        m_Family_information_ReintegrationVisit.setMotherMasterCode(motherMasterData);

        model.addAttribute("form_title", "Mother Family Information  Add");

        model.addAttribute("sex", Gender.values());

        model.addAttribute("occopation", occupationRepository.findAll());

        model.addAttribute("relation", relationsRepository.findAll());

        return "pre_reintegration_visit/m_family_information";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_Family_information_ReintegrationVisit m_Family_information_ReintegrationVisit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            m_Family_information_ReintegrationVisit.setMotherMasterCode(motherMasterData);

            model.addAttribute("form_title", "Mother Family Information  Edit/Update");

            model.addAttribute("sex", Gender.values());

            model.addAttribute("occopation", occupationRepository.findAll());

            model.addAttribute("relation", relationsRepository.findAll());

            return "pre_reintegration_visit/m_family_information";
        }

        pre_reintegration_visit_M_Family_informationRepository.save(m_Family_information_ReintegrationVisit);

        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Family_information_ReintegrationVisit m_Family_information_ReintegrationVisit, Model model) {
        
        model.addAttribute("m_Family_information_ReintegrationVisit", pre_reintegration_visit_M_Family_informationRepository.findById(id));
        model.addAttribute("form_title", "Mother Family Information  Edit");

        model.addAttribute("sex", Gender.values());

        model.addAttribute("occopation", occupationRepository.findAll());

        model.addAttribute("relation", relationsRepository.findAll());

        return "pre_reintegration_visit/m_family_information";

    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Family_information_ReintegrationVisit m_Family_information_ReintegrationVisit, RedirectAttributes redirectAttrs) {

        Optional<M_Family_information_ReintegrationVisit> optionalm_Family_information_ReintegrationVisit = pre_reintegration_visit_M_Family_informationRepository.findById(id);
       
        m_Family_information_ReintegrationVisit = optionalm_Family_information_ReintegrationVisit.orElse(null);

        redirectAttrs.addAttribute("m_id", m_Family_information_ReintegrationVisit.motherMasterCode.getId());

        pre_reintegration_visit_M_Family_informationRepository.deleteById(id);

        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

}
