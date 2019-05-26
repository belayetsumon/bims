/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Immunization;
import itgarden.repository.homevisit.ImmunizationRepository;
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
  @RequestMapping("/immunization")
public class ImmunizationController {
    
    @Autowired
    ImmunizationRepository immunizationRepository;
    
    @RequestMapping("/index")
     public String index(Model model,Immunization immunization ) {
      model.addAttribute("list",immunizationRepository.findAll() );
         model.addAttribute("table_name", "Immunization");    
        return "homevisit/lookup/immunization";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Immunization immunization ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",immunizationRepository.findAll() );
             model.addAttribute("table_name", "Immunization");
         return "homevisit/lookup/immunization";
        }
        immunizationRepository.save(immunization);
        return "redirect:/immunization/index";
    }  
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Immunization immunization, Model model) {
       model.addAttribute("immunization",   immunizationRepository.findOne(id));
        model.addAttribute("list",  immunizationRepository.findAll());
        model.addAttribute("table_name", "Immunization");
         return "/homevisit/lookup/immunization";
    }

    
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Immunization immunization) {

        immunizationRepository.delete(id);
        return "redirect:/immunization/index";
    }
    

}
