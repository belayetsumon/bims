/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.AvilableIga;
import itgarden.repository.homevisit.AvilableIgaRepository;
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
@RequestMapping("/avilableiga")
public class AvilableIgaController {
    @Autowired
    AvilableIgaRepository avilableIgaRepository;

    @RequestMapping("/index")
    public String index(Model model, AvilableIga avilableIga) {
        
        model.addAttribute("list", avilableIgaRepository.findAll());
        
        model.addAttribute("table_name", " Avilable IGA ");
        
        return "homevisit/lookup/avilableiga";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid AvilableIga avilableIga, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", avilableIgaRepository.findAll());
            model.addAttribute("table_name", " Avilable IGA");
            return "homevisit/lookup/avilableiga";
        }
        avilableIgaRepository.save(avilableIga);
        return "redirect:/avilableiga/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, AvilableIga avilableIga, Model model) {
        model.addAttribute("avilableIga", avilableIgaRepository.findOne(id));
        model.addAttribute("list", avilableIgaRepository.findAll());
        model.addAttribute("table_name", " Avilable IGA");
        return "/homevisit/lookup/avilableiga";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, AvilableIga avilableIga) {
        avilableIgaRepository.delete(id);
        return "redirect:/avilableiga/index";
    }

}
