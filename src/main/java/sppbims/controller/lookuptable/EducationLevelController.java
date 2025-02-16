/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.EducationLevel;
import sppbims.repository.homevisit.EducationLevelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/educationlavel")
public class EducationLevelController {

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @RequestMapping("/index")

    public String index(Model model, EducationLevel educationLevel) {
        model.addAttribute("list", educationLevelRepository.findAll());
        model.addAttribute("table_name", " Education Level");

        return "homevisit/lookup/educationlavel";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid EducationLevel educationLevel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", educationLevelRepository.findAll());
            model.addAttribute("table_name", " Education Level");
            return "homevisit/lookup/educationlavel";
        }

        educationLevelRepository.save(educationLevel);

        return "redirect:/educationlavel/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, EducationLevel educationLevel, Model model) {
        model.addAttribute("ethinicIdentity", educationLevelRepository.findById(id));
        model.addAttribute("list", educationLevelRepository.findAll());
        model.addAttribute("table_name", " Education Level");
        return "/homevisit/lookup/educationlavel";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, EducationLevel educationLevel) {

        educationLevelRepository.deleteById(id);
        return "redirect:/educationlavel/index";
    }

}
