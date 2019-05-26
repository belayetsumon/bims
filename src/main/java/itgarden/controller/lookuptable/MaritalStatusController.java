/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.MaritalStatus;
import itgarden.repository.homevisit.MaritalStatusRepository;
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
   @RequestMapping("/maritalstatus")
public class MaritalStatusController {
    
    @Autowired
    MaritalStatusRepository maritalStatusRepository;
    
    @RequestMapping("/index")
    public String index(Model model,MaritalStatus maritalStatus ) {
      model.addAttribute("list",maritalStatusRepository.findAll() );
      model.addAttribute("table_name"," Marital Status" );
       
        return "homevisit/lookup/maritalstatus";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid MaritalStatus maritalStatus ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",maritalStatusRepository.findAll() );
             model.addAttribute("table_name"," Marital Status" );
         return "homevisit/lookup/maritalstatus";
        }
        
        maritalStatusRepository.save(maritalStatus);

        return "redirect:/maritalstatus/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, MaritalStatus maritalStatus, Model model) {
       model.addAttribute("maritalStatus",   maritalStatusRepository.findOne(id));
        model.addAttribute("list",  maritalStatusRepository.findAll());
        model.addAttribute("table_name"," Marital Status" );
         return "/homevisit/lookup/maritalstatus";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, MaritalStatus maritalStatus) {        
     maritalStatusRepository.delete(id);
          return "redirect:/maritalstatus/index";
    }    
    

}
