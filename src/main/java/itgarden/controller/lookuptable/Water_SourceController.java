/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Water_Source;
import itgarden.repository.homevisit.Water_SourceRepository;
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
 @RequestMapping("/water_source")
public class Water_SourceController {
    
    @Autowired
    Water_SourceRepository water_SourceRepository;
    
    @RequestMapping("/index")
    public String index(Model model,Water_Source water_Source ) {
      model.addAttribute("list",water_SourceRepository.findAll() );
        model.addAttribute("table_name", " Water Source ");
       
        return "homevisit/lookup/water_source";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Water_Source water_Source ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",water_SourceRepository.findAll() );
               model.addAttribute("table_name", " Water Source ");
         return "homevisit/lookup/water_source";
        }
        
        water_SourceRepository.save(water_Source);

        return "redirect:/water_source/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Water_Source water_Source, Model model) {
       model.addAttribute("water_Source",   water_SourceRepository.findOne(id));
        model.addAttribute("list",  water_SourceRepository.findAll());
          model.addAttribute("table_name", " Water Source ");
         return "/homevisit/lookup/water_source";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Water_Source water_Source) {        
     water_SourceRepository.delete(id);
          return "redirect:/water_source/index";
    }    
    

}
