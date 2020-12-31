/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Transport_Type;
import itgarden.repository.homevisit.Transport_TypeRepository;
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
    @RequestMapping("/transport_type")
public class Transport_TypeController {
    
    @Autowired
    Transport_TypeRepository  transport_TypeRepository;
    @RequestMapping("/index")
    public String index(Model model,Transport_Type transport_Type ) {
      model.addAttribute("list",transport_TypeRepository.findAll() );
      return "homevisit/lookup/transport_type";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Transport_Type transport_Type ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",transport_TypeRepository.findAll() );
         return "homevisit/lookup/transport_type";
        }
       transport_TypeRepository.save(transport_Type);

        return "redirect:/transport_type/index";
    }  
   
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Transport_Type transport_Type, Model model) {
       model.addAttribute("transport_Type",   transport_TypeRepository.findOne(id));
        model.addAttribute("list",  transport_TypeRepository.findAll());
         return "/homevisit/lookup/transport_type";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Transport_Type transport_Type) {        
     transport_TypeRepository.delete(id);
          return "redirect:/transport_type/index";
    }    
    

}
