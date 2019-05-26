/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Occupation;
import itgarden.repository.homevisit.OccupationRepository;
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
 @RequestMapping("/occupation")
public class OccupationController {
    
    @Autowired
    OccupationRepository occupationRepository;
    
    
   @RequestMapping("/index")
    public String index(Model model,Occupation occupation ) {
      model.addAttribute("list",occupationRepository.findAll() );
       model.addAttribute("table_name","Occupations" );
       
        return "homevisit/lookup/occupation";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Occupation occupation ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",occupationRepository.findAll() );
              model.addAttribute("table_name","Occupations" );
         return "homevisit/lookup/occupation";
        }
        
        occupationRepository.save(occupation);

        return "redirect:/occupation/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Occupation occupation, Model model) {
       model.addAttribute("occupation",   occupationRepository.findOne(id));
        model.addAttribute("list",  occupationRepository.findAll());
         model.addAttribute("table_name","Occupations" );
         return "/homevisit/lookup/occupation";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Occupation occupation) {        
     occupationRepository.delete(id);
          return "redirect:/occupation/index";
    }    
    

}