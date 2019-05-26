/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.M_Community_Information;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.AvilableIgaRepository;
import itgarden.repository.homevisit.Economic_TypeRepository;
import itgarden.repository.homevisit.M_Community_InformationRepository;
import itgarden.repository.homevisit.ProspectiveIgaRepository;
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
@RequestMapping("/m_community_information")
public class M_Community_InformationController {

    @Autowired
    M_Community_InformationRepository m_Community_InformationRepository;

    @Autowired
    AvilableIgaRepository avilableIgaRepository;

    @Autowired
    ProspectiveIgaRepository prospectiveIgaRepository;

    @Autowired
    Economic_TypeRepository economic_TypeRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Community_Information m_Community_Information) {
        model.addAttribute("list", m_Community_InformationRepository.findAll());

        return "homevisit/lookup/m_community_information";
    }

    @RequestMapping("/create/{m_id}")

    public String add(Model model, @PathVariable Long m_id, M_Community_Information m_Community_Information) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Community_Information.setMotherMasterCode(motherMasterData);

        model.addAttribute("form_title", "Mother Community Information Add");

        model.addAttribute("economyType", economic_TypeRepository.findAll());

        model.addAttribute("avilableIga", avilableIgaRepository.findAll());

        model.addAttribute("prospectiveIga", prospectiveIgaRepository.findAll());

        return "homevisit/motherdetails/m_community_information";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Community_Information m_Community_Information, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Community_Information.setMotherMasterCode(motherMasterData);

            model.addAttribute("form_title", "Mother Community Information Add");

            model.addAttribute("economyType", economic_TypeRepository.findAll());

            model.addAttribute("avilableIga", avilableIgaRepository.findAll());

            model.addAttribute("prospectiveIga", prospectiveIgaRepository.findAll());
            return "homevisit/motherdetails/m_community_information";
        }

        m_Community_InformationRepository.save(m_Community_Information);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Community_Information m_Community_Information, Model model) {
        model.addAttribute("m_Community_Information", m_Community_InformationRepository.findOne(id));
        model.addAttribute("form_title", "Mother Community Information Add");
        model.addAttribute("economyType", economic_TypeRepository.findAll());
        model.addAttribute("avilableIga", avilableIgaRepository.findAll());
        model.addAttribute("prospectiveIga", prospectiveIgaRepository.findAll());
        return "homevisit/motherdetails/m_community_information";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Community_Information m_Community_Information, RedirectAttributes redirectAttrs) {

        m_Community_Information = m_Community_InformationRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", m_Community_Information.motherMasterCode.getId());
        m_Community_InformationRepository.delete(id);
      return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
