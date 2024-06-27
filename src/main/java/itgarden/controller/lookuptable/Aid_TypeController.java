/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Aid_Type;
import itgarden.repository.homevisit.Aid_TypeRepository;
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
   @RequestMapping("/aid_type")
public class Aid_TypeController {
    @Autowired
     Aid_TypeRepository aid_TypeRepository;
    
   @RequestMapping("/index")
    
    public String index(Model model,Aid_Type aid_Type ) {
      model.addAttribute("list",aid_TypeRepository.findAll() );
         model.addAttribute("table_name"," Aid Types" );
       
        return "/homevisit/lookup/aid_type";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid  Aid_Type aid_Type ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",aid_TypeRepository.findAll() );
               model.addAttribute("table_name"," Aid Types" );
         return "/homevisit/lookup/aid_type";
        }
        
        aid_TypeRepository.save(aid_Type);

        return "redirect:/aid_type/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Aid_Type aid_Type, Model model) {
       model.addAttribute("aid_Type",   aid_TypeRepository.findById(id));
        model.addAttribute("list",  aid_TypeRepository.findAll());
          model.addAttribute("table_name"," Aid Types" );
         return "/homevisit/lookup/aid_type";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Aid_Type aid_Type) {        

     aid_TypeRepository.deleteById(id);
          return "redirect:/aid_type/index";
    }    
    

}
