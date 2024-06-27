/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.Gender;
import itgarden.model.homevisit.M_Family_information;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.M_Family_informationRepository;
import itgarden.repository.homevisit.OccupationRepository;
import itgarden.repository.homevisit.RelationsRepository;
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

@RequestMapping("/m_family_information")
public class M_Family_informationController {

    @Autowired
    M_Family_informationRepository m_Family_informationRepository;

    @Autowired
    OccupationRepository occupationRepository;

    @Autowired
    RelationsRepository relationsRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Family_information m_Family_information) {
        model.addAttribute("list", m_Family_informationRepository.findAll());

        return "homevisit/lookup/m_family_information";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Family_information m_Family_information) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        m_Family_information.setMotherMasterCode(motherMasterData);

        model.addAttribute("form_title", "Mother Family Information  Add");

        model.addAttribute("sex", Gender.values());

        model.addAttribute("occopation", occupationRepository.findAll());

        model.addAttribute("relation", relationsRepository.findAll());

        return "homevisit/motherdetails/m_family_information";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_Family_information m_Family_information, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            m_Family_information.setMotherMasterCode(motherMasterData);

            model.addAttribute("form_title", "Mother Family Information  Edit/Update");

            model.addAttribute("sex", Gender.values());

            model.addAttribute("occopation", occupationRepository.findAll());

            model.addAttribute("relation", relationsRepository.findAll());

            return "homevisit/motherdetails/m_family_information";
        }

        m_Family_informationRepository.save(m_Family_information);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Family_information m_Family_information, Model model) {
        model.addAttribute("m_Family_information", m_Family_informationRepository.findById(id).orElse(null));
        model.addAttribute("form_title", "Mother Family Information  Edit");

        model.addAttribute("sex", Gender.values());

        model.addAttribute("occopation", occupationRepository.findAll());

        model.addAttribute("relation", relationsRepository.findAll());

        return "homevisit/motherdetails/m_family_information";

    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Family_information m_Family_information,RedirectAttributes redirectAttrs) {
        
        m_Family_information = m_Family_informationRepository.findById(id).orElse(null);
        
        redirectAttrs.addAttribute("m_id", m_Family_information.motherMasterCode.getId());
        
        m_Family_informationRepository.deleteById(id);
        
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
