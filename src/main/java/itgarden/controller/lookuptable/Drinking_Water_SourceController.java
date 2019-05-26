/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Drinking_Water_Source;
import itgarden.repository.homevisit.Drinking_Water_SourceRepository;
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

    @RequestMapping("/drinking_water_source")
public class Drinking_Water_SourceController {
    @Autowired
    Drinking_Water_SourceRepository drinking_Water_SourceRepository;
    
    @RequestMapping("/index")    
    public String index(Model model,Drinking_Water_Source drinking_Water_Source ) {
      model.addAttribute("list",drinking_Water_SourceRepository.findAll() );
          model.addAttribute("table_name", " Drinking Water Source ");
       
        return "/homevisit/lookup/drinking_water_source";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid  Drinking_Water_Source drinking_Water_Source ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",drinking_Water_SourceRepository.findAll() );
               model.addAttribute("table_name", " Drinking Water Source ");
         return "/homevisit/lookup/drinking_water_source";
        }
        
        drinking_Water_SourceRepository.save(drinking_Water_Source);

        return "redirect:/drinking_water_source/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Drinking_Water_Source drinking_Water_Source, Model model) {
       model.addAttribute("drinking_Water_Source",   drinking_Water_SourceRepository.findOne(id));
        model.addAttribute("list",  drinking_Water_SourceRepository.findAll());
          model.addAttribute("table_name", " Drinking Water Source ");
         return "/homevisit/lookup/drinking_water_source";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Drinking_Water_Source drinking_Water_Source) {        

     drinking_Water_SourceRepository.delete(id);
          return "redirect:/aid_type/index";
    }    
    

}
