/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.Reasons;
import sppbims.repository.homevisit.ReasonsRepository;
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
@RequestMapping("/reasons")
public class ReasonsController {

    @Autowired
    ReasonsRepository reasonsRepository;

    @RequestMapping("/index")
    public String index(Model model, Reasons reasons) {
        model.addAttribute("list", reasonsRepository.findAll());
        model.addAttribute("table_name", " Reasons");

        return "homevisit/lookup/reasons";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Reasons reasons, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", reasonsRepository.findAll());
            model.addAttribute("table_name", " Reasons");
            return "homevisit/lookup/reasons";
        }

        reasonsRepository.save(reasons);

        return "redirect:/reasons/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Reasons reasons, Model model) {
        model.addAttribute("reasons", reasonsRepository.findById(id).orElse(null));
        model.addAttribute("list", reasonsRepository.findAll());
        model.addAttribute("table_name", " Reasons");
        return "/homevisit/lookup/reasons";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Reasons reasons) {
        reasonsRepository.deleteById(id);
        return "redirect:/reasons/index";
    }

}
