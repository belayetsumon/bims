/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.rehabilitations.Diagonosis;
import itgarden.repository.rehabilitations.DiagonosisRepository;
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
     @RequestMapping("/diagonosis")
public class DiagonosisController {
    
    @Autowired
    DiagonosisRepository diagonosisRepository;
    
     @RequestMapping("/index")
    public String index(Model model, Diagonosis  diagonosis) {
        model.addAttribute("list", diagonosisRepository.findAll());
        model.addAttribute("table_name", "  Therapeutic   Diagnosis  ");
        return "rehabilitations/lookup/diagonosis";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Diagonosis  diagonosis, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", diagonosisRepository.findAll());
            model.addAttribute("table_name", "Therapeutic   Diagnosis ");
             return "rehabilitations/lookup/diagonosis";
        }

        diagonosisRepository.save(diagonosis);

        return "redirect:/diagonosis/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Diagonosis diagonosis, Model model) {
        model.addAttribute("diagonosis", diagonosisRepository.findById(id));
        model.addAttribute("list", diagonosisRepository.findAll());
             model.addAttribute("table_name", "Therapeutic   Diagnosis ");
         return "rehabilitations/lookup/diagonosis";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Diagonosis  diagonosis) {
        diagonosisRepository.deleteById(id);
        return "redirect:/diagonosis/index";
    }

}

