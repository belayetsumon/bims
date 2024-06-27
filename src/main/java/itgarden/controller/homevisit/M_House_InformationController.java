/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.M_House_Information;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Value_type;
import itgarden.repository.homevisit.House_TypeRepository;
import itgarden.repository.homevisit.M_House_InformationRepository;
import itgarden.repository.homevisit.Ownershif_typeRepository;
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
@RequestMapping("/m_house_information")
public class M_House_InformationController {

    @Autowired
    M_House_InformationRepository m_House_InformationRepository;

    @Autowired
    Ownershif_typeRepository ownershif_typeRepository;

    @Autowired
    House_TypeRepository house_TypeRepository;

    @RequestMapping("/index")

    public String index(Model model, M_House_Information m_House_Information) {
        model.addAttribute("list", m_House_InformationRepository.findAll());

        return "homevisit/lookup/ethnicIdentity";
    }

    @RequestMapping("/create/{m_id}")
    public String add(@PathVariable Long m_id, Model model, M_House_Information m_House_Information) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        model.addAttribute("form_title", "Mother House Information Add");

        m_House_Information.setMotherMasterCode(motherMasterData);

        model.addAttribute("list", m_House_InformationRepository.findAll());

        model.addAttribute("houseType", house_TypeRepository.findAll());

        model.addAttribute("ownership", ownershif_typeRepository.findAll());

        model.addAttribute("valueTitle", Value_type.values());

        return "homevisit/motherdetails/m_house_information";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_House_Information m_House_Information, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            model.addAttribute("form_title", "Mother House Information Save / Update");

            m_House_Information.setMotherMasterCode(motherMasterData);

            model.addAttribute("houseType", house_TypeRepository.findAll());

            model.addAttribute("ownership", ownershif_typeRepository.findAll());

            model.addAttribute("valueTitle", Value_type.values());

            return "homevisit/motherdetails/m_house_information";
        }

        m_House_InformationRepository.save(m_House_Information);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_House_Information m_House_Information, Model model) {

        model.addAttribute("form_title", "Mother House Information Edit");

        model.addAttribute("m_House_Information", m_House_InformationRepository.findById(id).orElse(null));

        model.addAttribute("houseType", house_TypeRepository.findAll());

        model.addAttribute("ownership", ownershif_typeRepository.findAll());

        model.addAttribute("valueTitle", Value_type.values());

        return "homevisit/motherdetails/m_house_information";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_House_Information m_House_Information, RedirectAttributes redirectAttrs) {

        m_House_Information = m_House_InformationRepository.findById(id).orElse(null);

        redirectAttrs.addAttribute("m_id", m_House_Information.motherMasterCode.getId());

        m_House_InformationRepository.deleteById(id);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
