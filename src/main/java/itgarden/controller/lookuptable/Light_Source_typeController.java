/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Light_Source_type;
import itgarden.repository.homevisit.Light_Source_typeRepository;
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
    @RequestMapping("/light_source_type")
public class Light_Source_typeController {
    
    @Autowired
    Light_Source_typeRepository light_Source_typeRepository;
    
    
    
    @RequestMapping("/index")
     public String index(Model model,Light_Source_type light_Source_type ) {
      model.addAttribute("list",light_Source_typeRepository.findAll() );
            model.addAttribute("table_name","Light Source type" );
       
        return "homevisit/lookup/light_source_type";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Light_Source_type light_Source_type ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",light_Source_typeRepository.findAll() );
               model.addAttribute("table_name","Light Source type" );
         return "homevisit/lookup/light_source_type";
        }
        
        light_Source_typeRepository.save(light_Source_type);

        return "redirect:/light_source_type/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Light_Source_type light_Source_type, Model model) {
       model.addAttribute("light_Source_type",   light_Source_typeRepository.findOne(id));
        model.addAttribute("list",  light_Source_typeRepository.findAll());
          model.addAttribute("table_name","Light Source type" );
         return "/homevisit/lookup/light_source_type";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Light_Source_type light_source_type) {        

     light_Source_typeRepository.delete(id);
          return "redirect:/light_source_type/index";
    }    
    

}
