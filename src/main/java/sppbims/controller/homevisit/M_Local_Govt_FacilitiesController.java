/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.homevisit;

import sppbims.model.homevisit.M_Local_Govt_Facilities;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Yes_No;
import sppbims.repository.homevisit.LocalContactPersionRepository;
import sppbims.repository.homevisit.M_Local_Govt_FacilitiesRepository;
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
@RequestMapping("/m_local_govt_facilities")
public class M_Local_Govt_FacilitiesController {

    @Autowired
    M_Local_Govt_FacilitiesRepository m_Local_Govt_FacilitiesRepository;

    @Autowired
    LocalContactPersionRepository localContactPersionRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Local_Govt_Facilities m_Local_Govt_Facilities) {
        model.addAttribute("list", m_Local_Govt_FacilitiesRepository.findAll());

        return "homevisit/lookup/m_local_govt_facilities";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Local_Govt_Facilities m_Local_Govt_Facilities) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        m_Local_Govt_Facilities.setMotherMasterCode(motherMasterData);

        model.addAttribute("form_title", "Mother Local Govt Facilities Add ");
        model.addAttribute("localPersons", localContactPersionRepository.findAll());
        model.addAttribute("votingParticipation", Yes_No.values());

        return "homevisit/motherdetails/m_local_govt_facilities";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_Local_Govt_Facilities m_Local_Govt_Facilities, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            m_Local_Govt_Facilities.setMotherMasterCode(motherMasterData);

            model.addAttribute("form_title", "Mother Local Govt Facilities Edit/Update");
            model.addAttribute("localPersons", localContactPersionRepository.findAll());
            model.addAttribute("votingParticipation", Yes_No.values());

            return "homevisit/motherdetails/m_local_govt_facilities";
        }

        m_Local_Govt_FacilitiesRepository.save(m_Local_Govt_Facilities);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Local_Govt_Facilities m_Local_Govt_Facilities, Model model) {
        model.addAttribute("m_Local_Govt_Facilities", m_Local_Govt_FacilitiesRepository.findById(id).orElse(null));

        model.addAttribute("form_title", "Mother Local Govt Facilities Edit");
        model.addAttribute("localPersons", localContactPersionRepository.findAll());
        model.addAttribute("votingParticipation", Yes_No.values());

        return "homevisit/motherdetails/m_local_govt_facilities";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Local_Govt_Facilities m_Local_Govt_Facilities, RedirectAttributes redirectAttrs) {
        m_Local_Govt_Facilities = m_Local_Govt_FacilitiesRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", m_Local_Govt_Facilities.motherMasterCode.getId());
        m_Local_Govt_FacilitiesRepository.deleteById(id);
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
