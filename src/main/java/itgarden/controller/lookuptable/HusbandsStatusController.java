/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.HusbandsStatus;
import itgarden.repository.homevisit.HusbandsStatusRepository;
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
@RequestMapping("/husbandsstatus")
public class HusbandsStatusController {
    
        @Autowired
    HusbandsStatusRepository husbandsStatusRepository;
        
    @RequestMapping("/index")
     public String index(Model model,HusbandsStatus husbandsStatus ) {
      model.addAttribute("list",husbandsStatusRepository.findAll() );
       model.addAttribute("table_name"," Husbands Status" );
       
        return "homevisit/lookup/husbandsstatus";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid HusbandsStatus husbandsStatus ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",husbandsStatusRepository.findAll() );
                 model.addAttribute("table_name"," Husbands Status" );
         return "homevisit/lookup/husbandsstatus";
        }
        
        husbandsStatusRepository.save(husbandsStatus);

        return "redirect:/husbandsstatus/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, HusbandsStatus husbandsStatus, Model model) {
       model.addAttribute("husbandsStatus",   husbandsStatusRepository.findOne(id));
        model.addAttribute("list",  husbandsStatusRepository.findAll());
            model.addAttribute("table_name"," Husbands Status" );
         return "/homevisit/lookup/husbandsstatus";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, HusbandsStatus husbandsStatus) {        

     husbandsStatusRepository.delete(id);
          return "redirect:/husbandsstatus/index";
    }    
    

}
