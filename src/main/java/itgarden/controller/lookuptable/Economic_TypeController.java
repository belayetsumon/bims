/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Economic_Type;
import itgarden.repository.homevisit.Economic_TypeRepository;
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
 @RequestMapping("/economic_type")
public class Economic_TypeController {
    
    @Autowired
    Economic_TypeRepository economic_TypeRepository;
    
    
    @RequestMapping("/index")    
    public String index(Model model,Economic_Type economic_Type ) {
      model.addAttribute("list",economic_TypeRepository.findAll() );
       
        return "/homevisit/lookup/economic_type";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid  Economic_Type economic_Type ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",economic_TypeRepository.findAll() );
         return "/homevisit/lookup/economic_type";
        }
        
        economic_TypeRepository.save(economic_Type);

        return "redirect:/economic_type/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Economic_Type economic_Type, Model model) {
       model.addAttribute("economic_Type",   economic_TypeRepository.findOne(id));
        model.addAttribute("list",  economic_TypeRepository.findAll());
         return "/homevisit/lookup/economic_type";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Economic_Type economic_Type) {        

     economic_TypeRepository.delete(id);
          return "redirect:/aid_type/index";
    }    
    

}
