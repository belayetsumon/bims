/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Business;
import itgarden.repository.homevisit.BusinessRepository;
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
 @RequestMapping("/business")
public class BusinessController {
    
     @Autowired
   BusinessRepository  businessRepository;

    @RequestMapping("/index")
    
    public String index(Model model,Business  business ) {
      model.addAttribute("list",businessRepository.findAll() );
      model.addAttribute("table_name"," Business " );
       
        return "homevisit/lookup/business";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Business  business ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",businessRepository.findAll() );
               model.addAttribute("table_name"," Business" );
         return "homevisit/lookup/business";
        }
        
        businessRepository.save(business);

        return "redirect:/business/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Business business, Model model) {
       model.addAttribute("business",   businessRepository.findById(id));
        model.addAttribute("list",  businessRepository.findAll());
         model.addAttribute("table_name"," Community Type l" );
         return "/homevisit/lookup/business";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Business business) {        

     businessRepository.deleteById(id);
          return "redirect:/business/index";
    }    
    

}