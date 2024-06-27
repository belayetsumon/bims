/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Income_Generating;
import itgarden.repository.homevisit.Income_GeneratingRepository;
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
@RequestMapping("/income_generating")
public class Income_GeneratingController {
    
    @Autowired
    Income_GeneratingRepository income_GeneratingRepository;
    
    @RequestMapping("/index")
    public String index(Model model, Income_Generating income_Generating) {
        model.addAttribute("list", income_GeneratingRepository.findAll());
        model.addAttribute("table_name", "Income Generating");        
        
        return "homevisit/lookup/income_generating";
    }
    
    @RequestMapping("/save")
    
    public String save(Model model, @Valid Income_Generating income_Generating, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", income_GeneratingRepository.findAll());
            model.addAttribute("table_name", "Income Generating");            
            return "homevisit/lookup/income_generating";
        }
        
        income_GeneratingRepository.save(income_Generating);
        
        return "redirect:/income_generating/index";
    }    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Income_Generating income_Generating, Model model) {
        model.addAttribute("income_Generating", income_GeneratingRepository.findById(id));
        model.addAttribute("list", income_GeneratingRepository.findAll());
        model.addAttribute("table_name", "Income Generating");        
        return "/homevisit/lookup/income_generating";
    }
    
    @GetMapping(value = "/delete/{id}")    
    public String delete(@PathVariable Long id, Income_Generating income_Generating) {        
        
        income_GeneratingRepository.deleteById(id);
        return "redirect:/income_generating/index";
    }    
    
}
