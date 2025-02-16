/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.Cooking_Fuel_Type;
import sppbims.repository.homevisit.Cooking_Fuel_TypeRepository;
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
@RequestMapping("/cooking_fuel_type")
public class Cooking_Fuel_TypeController {

    @Autowired
    Cooking_Fuel_TypeRepository cooking_Fuel_TypeRepository;

    @RequestMapping("/index")
    public String index(Model model, Cooking_Fuel_Type cooking_Fuel_Type) {
        model.addAttribute("list", cooking_Fuel_TypeRepository.findAll());
        model.addAttribute("table_name", " Cooking Fuel Typel");

        return "/homevisit/lookup/cooking_fuel_type";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Cooking_Fuel_Type cooking_Fuel_Type, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", cooking_Fuel_TypeRepository.findAll());
            model.addAttribute("table_name", " Cooking Fuel Typel");
            return "/homevisit/lookup/cooking_fuel_type";
        }

        cooking_Fuel_TypeRepository.save(cooking_Fuel_Type);

        return "redirect:/cooking_fuel_type/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Cooking_Fuel_Type cooking_Fuel_Type, Model model) {
        model.addAttribute("cooking_Fuel_Type", cooking_Fuel_TypeRepository.findById(id));
        model.addAttribute("list", cooking_Fuel_TypeRepository.findAll());
        model.addAttribute("table_name", " Cooking Fuel Typel");
        return "/homevisit/lookup/cooking_fuel_type";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Cooking_Fuel_Type cooking_Fuel_Type) {

        cooking_Fuel_TypeRepository.deleteById(id);
        return "redirect:/cooking_fuel_type/index";
    }

}
