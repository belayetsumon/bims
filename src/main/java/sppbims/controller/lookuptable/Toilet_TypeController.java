/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.Toilet_Type;
import sppbims.repository.homevisit.Toilet_TypeRepository;
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
@RequestMapping("/toilet_type")
public class Toilet_TypeController {

    @Autowired
    Toilet_TypeRepository toilet_TypeRepository;

    @RequestMapping("/index")
    public String index(Model model, Toilet_Type toilet_Type) {
        model.addAttribute("list", toilet_TypeRepository.findAll());
        model.addAttribute("table_name", " Toilet Type ");

        return "homevisit/lookup/toilet_type";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Toilet_Type toilet_Type, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", toilet_TypeRepository.findAll());
            model.addAttribute("table_name", " Toilet Type ");
            return "homevisit/lookup/toilet_type";
        }

        toilet_TypeRepository.save(toilet_Type);

        return "redirect:/toilet_type/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Toilet_Type toilet_Type, Model model) {
        model.addAttribute("toilet_Type", toilet_TypeRepository.findById(id));
        model.addAttribute("list", toilet_TypeRepository.findAll());
        model.addAttribute("table_name", " Toilet Type ");
        return "/homevisit/lookup/toilet_type";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Toilet_Type toilet_Type) {
        toilet_TypeRepository.deleteById(id);
        return "redirect:/toilet_type/index";
    }

}
