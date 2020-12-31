/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.M_Current_Help;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.Aid_TypeRepository;
import itgarden.repository.homevisit.M_Current_HelpRepository;
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
@RequestMapping("/m_current_help")
public class M_Current_HelpController {

    @Autowired
    M_Current_HelpRepository m_Current_HelpRepository;

    @Autowired
    Aid_TypeRepository aid_TypeRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Current_Help m_Current_Help) {
        model.addAttribute("list", m_Current_HelpRepository.findAll());

        return "homevisit/lookup/m_current_help";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Current_Help m_Current_Help) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Current_Help.setMotherMasterCode(motherMasterData);

        model.addAttribute("form_title", "Mother current support or help add");
        model.addAttribute("aidType", aid_TypeRepository.findAll());
        return "homevisit/motherdetails/m_current_help";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Current_Help m_Current_Help, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Mother Current Aid/ Help Save/Update");
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Current_Help.setMotherMasterCode(motherMasterData);
            model.addAttribute("aidType", aid_TypeRepository.findAll());
            return "homevisit/motherdetails/m_current_help";
        }

        m_Current_HelpRepository.save(m_Current_Help);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable Long id, M_Current_Help m_Current_Help) {
        model.addAttribute("m_Current_Help", m_Current_HelpRepository.findOne(id));
        model.addAttribute("aidType", aid_TypeRepository.findAll());
           model.addAttribute("form_title", "Mother Current Aid/ Help edit");
        return "/homevisit/motherdetails/m_current_help";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Current_Help m_Current_Help, RedirectAttributes redirectAttrs) {

        m_Current_Help = m_Current_HelpRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", m_Current_Help.motherMasterCode.getId());
        
        m_Current_HelpRepository.delete(id);
        
         redirectAttrs.addFlashAttribute("delete_message"," Current help data delete successfully."+ m_Current_Help.getId());
         
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
