/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Relations;
import itgarden.repository.homevisit.RelationsRepository;
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
 @RequestMapping("/relations")
public class RelationsController {
    
    @Autowired
    RelationsRepository relationsRepository;
    
    @RequestMapping("/index")
    public String index(Model model,Relations relations ) {
      model.addAttribute("list",relationsRepository.findAll() );
           model.addAttribute("table_name"," Relations" );
       
        return "homevisit/lookup/relations";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Relations relations ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",relationsRepository.findAll() );
                  model.addAttribute("table_name"," Relations" );
         return "homevisit/lookup/relations";
        }
        
        relationsRepository.save(relations);

        return "redirect:/relations/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Relations relations, Model model) {
       model.addAttribute("relations",   relationsRepository.findById(id));
        model.addAttribute("list",  relationsRepository.findAll());
         model.addAttribute("table_name"," Relations" );
         return "/homevisit/lookup/relations";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Relations relations) {        
     relationsRepository.deleteById(id);
          return "redirect:/relations/index";
    }    
    

}