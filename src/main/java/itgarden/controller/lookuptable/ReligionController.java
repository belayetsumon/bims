/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Religion;
import itgarden.repository.homevisit.ReligionRepository;
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
  @RequestMapping("/religion")
public class ReligionController {
    @Autowired
    ReligionRepository religionRepository;
    
  @RequestMapping("/index")
    public String index(Model model,Religion religion ) {
      model.addAttribute("list",religionRepository.findAll() );
      model.addAttribute("table_name"," Religion" );
       
        return "homevisit/lookup/religion";
    }
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Religion religion ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",religionRepository.findAll() );
             model.addAttribute("table_name"," Religion" );
         return "homevisit/lookup/religion";
        }
        
        religionRepository.save(religion);

        return "redirect:/religion/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Religion religion, Model model) {
       model.addAttribute("religion",   religionRepository.findById(id));
        model.addAttribute("list",  religionRepository.findAll());
        model.addAttribute("table_name"," Religion" );
         return "/homevisit/lookup/religion";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Religion religion) {        
    religionRepository.deleteById(id);
          return "redirect:/religion/index";
    }    
    

}