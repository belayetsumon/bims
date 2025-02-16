/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.lookuptable;

import sppbims.model.homevisit.Work;
import sppbims.repository.homevisit.WorkRepository;
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
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkRepository workRepository;

    @RequestMapping("/index")
    public String index(Model model, Work work) {
        model.addAttribute("list", workRepository.findAll());
        model.addAttribute("table_name", " Work ");

        return "homevisit/lookup/work";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Work work, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", workRepository.findAll());
            model.addAttribute("table_name", " Work");
            return "homevisit/lookup/work";
        }

        workRepository.save(work);

        return "redirect:/work/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Work work, Model model) {
        model.addAttribute("work", workRepository.findById(id));
        model.addAttribute("list", workRepository.findAll());
        model.addAttribute("table_name", " Work");
        return "/homevisit/lookup/work";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Work work) {

        workRepository.deleteById(id);
        return "redirect:/work/index";
    }

}
