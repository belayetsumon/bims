/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Personal_Habits;
import itgarden.repository.homevisit.Personal_HabitsRepository;
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
    @RequestMapping("/personal_habits")
public class Personal_HabitsController {
    
    @Autowired
    Personal_HabitsRepository personal_HabitsRepository;
    
   @RequestMapping("/index")
    public String index(Model model,Personal_Habits personal_Habits ) {
      model.addAttribute("list",personal_HabitsRepository.findAll() );
           model.addAttribute("table_name","Personal Habit" );
       
        return "homevisit/lookup/personal_habits";
    }
    
    
  @RequestMapping("/save")
    
    public String save(Model model, @Valid Personal_Habits personal_Habits ,BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
             model.addAttribute("list",personal_HabitsRepository.findAll() );
                    model.addAttribute("table_name","Personal Habit" );
         return "homevisit/lookup/personal_habits";
        }
        
        personal_HabitsRepository.save(personal_Habits);

        return "redirect:/personal_habits/index";
    }  
    
    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Personal_Habits personal_Habits, Model model) {
       model.addAttribute("personal_Habits",   personal_HabitsRepository.findById(id));
        model.addAttribute("list",  personal_HabitsRepository.findAll());
             model.addAttribute("table_name","Personal Habit" );
         return "/homevisit/lookup/personal_habits";
    }

    
    @GetMapping(value = "/delete/{id}")    
    public String delete (@PathVariable Long id, Personal_Habits personal_Habits) {        
     personal_HabitsRepository.deleteById(id);
          return "redirect:/personal_habits/index";
    }    
    

}