/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.Ownershif_type;
import sppbims.repository.homevisit.Ownershif_typeRepository;
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
@RequestMapping("/ownershif_type")
public class Ownershif_typeController {

    @Autowired
    Ownershif_typeRepository ownershif_typeRepository;

    @RequestMapping("/index")
    public String index(Model model, Ownershif_type ownershif_type) {
        model.addAttribute("list", ownershif_typeRepository.findAll());
        model.addAttribute("table_name", " Ownershif Type");

        return "homevisit/lookup/ownershif_type";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Ownershif_type ownershif_type, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", ownershif_typeRepository.findAll());
            model.addAttribute("table_name", " Ownershif Type");
            return "homevisit/lookup/ownershif_type";
        }

        ownershif_typeRepository.save(ownershif_type);

        return "redirect:/ownershif_type/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Ownershif_type ownershif_type, Model model) {
        model.addAttribute("ownershif_type", ownershif_typeRepository.findById(id));
        model.addAttribute("list", ownershif_typeRepository.findAll());
        model.addAttribute("table_name", " Ownershif Type");
        return "/homevisit/lookup/ownershif_type";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Ownershif_type ownershif_type) {
        ownershif_typeRepository.deleteById(id);
        return "redirect:/ownershif_type/index";
    }

}
