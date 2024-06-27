/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.VisitsOfficersName;
import itgarden.repository.homevisit.VisitsOfficersNameRepository;
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
 @RequestMapping("/visitsofficersname")
public class VisitsOfficersNameController {
    
    @Autowired
    VisitsOfficersNameRepository visitsOfficersNameRepository;
    
    
    @RequestMapping("/index")
    public String index(Model model,VisitsOfficersName visitsOfficersName ) {
      model.addAttribute("list",visitsOfficersNameRepository.findAll() );
       
        return "homevisit/lookup/visitsOfficersName";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid VisitsOfficersName visitsOfficersName ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",visitsOfficersNameRepository.findAll() );
         return "homevisit/lookup/visitsOfficersName";
        }
        
        visitsOfficersNameRepository.save(visitsOfficersName);

        return "redirect:/visitsOfficersName/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, VisitsOfficersName visitsOfficersName, Model model) {
       model.addAttribute("visitsOfficersName",   visitsOfficersNameRepository.findById(id));
        model.addAttribute("list",  visitsOfficersNameRepository.findAll());
         return "/homevisit/lookup/visitsOfficersName";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, VisitsOfficersName visitsOfficersName) {        
     visitsOfficersNameRepository.deleteById(id);
          return "redirect:/visitsOfficersName/index";
    }    
    

}

