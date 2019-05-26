/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.EducationType;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
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
@RequestMapping("/educationtype")
public class EducationTypeController {
    
    @Autowired
    EducationTypeRepository educationTypeRepository;

    @RequestMapping("/index")
    
    public String index(Model model,EducationType educationType ) {
      model.addAttribute("list",educationTypeRepository.findAll() );
        model.addAttribute("table_name"," Education Type" );
       
        return "homevisit/lookup/educationtype";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid EducationType educationType ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",educationTypeRepository.findAll() );
                model.addAttribute("table_name"," Education Type" );
         return "homevisit/lookup/educationtype";
        }
        
        educationTypeRepository.save(educationType);

        return "redirect:/educationtype/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, EducationType educationType, Model model) {
       model.addAttribute("educationType",   educationTypeRepository.findOne(id));
        model.addAttribute("list",  educationTypeRepository.findAll());
          model.addAttribute("table_name"," Education Type" );
         return "/homevisit/lookup/educationtype";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, EducationType educationType) {        
     educationTypeRepository.delete(id);
          return "redirect:/educationtype/index";
    }    
    

}
