/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.House_Type;
import sppbims.repository.homevisit.House_TypeRepository;
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
@RequestMapping("/house_type")
public class House_TypeController {

    @Autowired
    House_TypeRepository house_TypeRepository;

    @RequestMapping("/index")
    public String index(Model model, House_Type house_Type) {
        model.addAttribute("list", house_TypeRepository.findAll());
        model.addAttribute("table_name", " House Type");

        return "homevisit/lookup/house_type";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid House_Type house_Type, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", house_TypeRepository.findAll());
            model.addAttribute("table_name", " House Type");
            return "homevisit/lookup/house_type";
        }

        house_TypeRepository.save(house_Type);

        return "redirect:/house_type/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, House_Type house_Type, Model model) {
        model.addAttribute("house_Type", house_TypeRepository.findById(id));
        model.addAttribute("list", house_TypeRepository.findAll());
        model.addAttribute("table_name", " House Type");
        return "/homevisit/lookup/house_type";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, House_Type house_Type) {

        house_TypeRepository.deleteById(id);
        return "redirect:/house_type/index";
    }

}
