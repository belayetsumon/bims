/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.LocalContactPersion;
import itgarden.repository.homevisit.LocalContactPersionRepository;
import javax.validation.Valid;
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
@RequestMapping("/localcontactpersion")
public class LocalContactPersionController {

    @Autowired
    LocalContactPersionRepository localContactPersionRepository;

    @RequestMapping("/index")

    public String index(Model model, LocalContactPersion localContactPersion) {
        model.addAttribute("list", localContactPersionRepository.findAll());
        model.addAttribute("table_name", " Local Contact Persion ");

        return "homevisit/lookup/localcontactpersion";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid LocalContactPersion localContactPersion, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", localContactPersionRepository.findAll());
            model.addAttribute("table_name", " Local Contact Persion");
            return "homevisit/lookup/localcontactpersion";
        }

        localContactPersionRepository.save(localContactPersion);

        return "redirect:/localcontactpersion/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LocalContactPersion localContactPersion, Model model) {
        model.addAttribute("localContactPersion", localContactPersionRepository.findOne(id));
        model.addAttribute("list", localContactPersionRepository.findAll());
        model.addAttribute("table_name", " Local Contact Persion");
        return "/homevisit/lookup/localcontactpersion";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LocalContactPersion localContactPersion) {

        localContactPersionRepository.delete(id);
        return "redirect:/localcontactpersion/index";
    }

}
