/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.M_Property;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.M_PropertyRepository;
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
@RequestMapping("/m_property")
public class M_PropertyController {

    @Autowired
    M_PropertyRepository m_PropertyRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Property m_Property) {
        model.addAttribute("list", m_PropertyRepository.findAll());

        return "homevisit/lookup/m_property";
    }

    @RequestMapping("/create/{m_id}")

    public String create(@PathVariable Long m_id, Model model, M_Property m_Property) {
        model.addAttribute("form_title", "Mother Property Add");
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Property.setMotherMasterCode(motherMasterData);
        return "homevisit/motherdetails/m_property";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_Property m_Property, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Mother Property Save/Update");
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Property.setMotherMasterCode(motherMasterData);
            return "homevisit/motherdetails/m_property";
        }

        m_PropertyRepository.save(m_Property);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Property m_Property, Model model) {
        model.addAttribute("form_title", "Mother Property Edit");
        model.addAttribute("m_Property", m_PropertyRepository.findOne(id));
        model.addAttribute("list", m_PropertyRepository.findAll());
        return "/homevisit/motherdetails/m_property";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Property m_Property, RedirectAttributes redirectAttrs) {
        m_Property = m_PropertyRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", m_Property.motherMasterCode.getId());
        m_PropertyRepository.delete(id);
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
