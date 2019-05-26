/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.M_Income_Information;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.Income_GeneratingRepository;
import itgarden.repository.homevisit.M_Income_InformationRepository;
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
@RequestMapping("/m_income_information")
public class M_Income_InformationController {

    @Autowired
    M_Income_InformationRepository m_Income_InformationRepository;

    @Autowired
    Income_GeneratingRepository income_GeneratingRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Income_Information m_Income_Information) {
        model.addAttribute("list", m_Income_InformationRepository.findAll());

        return "homevisit/lookup/m_income_information";
    }

    @RequestMapping("/create/{m_id}")
    public String create(@PathVariable Long m_id, Model model, M_Income_Information m_Income_Information) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Income_Information.setMotherMasterCode(motherMasterData);
        model.addAttribute("form_title", "Per Capita Income");

        model.addAttribute("income_activity", income_GeneratingRepository.findAll());
        return "homevisit/motherdetails/m_income_information";

    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Income_Information m_Income_Information, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Income_Information.setMotherMasterCode(motherMasterData);
            
            model.addAttribute("form_title", "Per Capita Income");
            model.addAttribute("income_activity", income_GeneratingRepository.findAll());
            return "homevisit/motherdetails/m_income_information";
        }
        m_Income_InformationRepository.save(m_Income_Information);
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Income_Information m_Income_Information, Model model) {

        model.addAttribute("m_Income_Information", m_Income_InformationRepository.findOne(id));

        model.addAttribute("income_activity", income_GeneratingRepository.findAll());

        model.addAttribute("form_title", "Per Capita Income");

        return "/homevisit/motherdetails/m_income_information";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Income_Information m_Income_Information, RedirectAttributes redirectAttrs) {

        m_Income_Information = m_Income_InformationRepository.findOne(id);

        redirectAttrs.addAttribute("m_id", m_Income_Information.motherMasterCode.getId());

        m_Income_InformationRepository.delete(id);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
