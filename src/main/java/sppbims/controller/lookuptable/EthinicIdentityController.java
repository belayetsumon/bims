/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.EthinicIdentity;
import sppbims.repository.homevisit.EthinicIdentityRepository;
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
@RequestMapping("/ethnicIdentity")
public class EthinicIdentityController {

    @Autowired
    EthinicIdentityRepository ethinicIdentityRepository;

    @RequestMapping("/index")

    public String index(Model model, EthinicIdentity ethinicIdentity) {
        model.addAttribute("list", ethinicIdentityRepository.findAll());
        model.addAttribute("table_name", " Ethnic Identity");

        return "homevisit/lookup/ethnicIdentity";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid EthinicIdentity ethinicIdentity, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", ethinicIdentityRepository.findAll());
            model.addAttribute("table_name", " Ethnic Identity");
            return "homevisit/lookup/ethnicIdentity";
        }

        ethinicIdentityRepository.save(ethinicIdentity);

        return "redirect:/ethnicIdentity/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, EthinicIdentity ethinicIdentity, Model model) {
        model.addAttribute("ethinicIdentity", ethinicIdentityRepository.findById(id));
        model.addAttribute("list", ethinicIdentityRepository.findAll());
        model.addAttribute("table_name", " Ethnic Identity");
        return "/homevisit/lookup/ethnicIdentity";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, EthinicIdentity ethinicIdentity) {

        ethinicIdentityRepository.deleteById(id);
        return "redirect:/ethnicIdentity/index";
    }

}
