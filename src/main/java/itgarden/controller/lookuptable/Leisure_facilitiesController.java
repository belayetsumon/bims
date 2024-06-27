/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Leisure_facilities;
import itgarden.repository.homevisit.Leisure_facilitiesRepository;
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
@RequestMapping("/leisure_facilities")
public class Leisure_facilitiesController {
    
    @Autowired
    Leisure_facilitiesRepository leisure_facilitiesRepository;
    
    @RequestMapping("/index")
    public String index(Model model, Leisure_facilities leisure_facilities) {
        model.addAttribute("list", leisure_facilitiesRepository.findAll());
        model.addAttribute("table_name", "Leisure Facilities");
        
        return "homevisit/lookup/leisure_facilities";
    }
    
    @RequestMapping("/save")
    
    public String save(Model model, @Valid Leisure_facilities leisure_facilities, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", leisure_facilitiesRepository.findAll());
            model.addAttribute("table_name", "Leisure Facilities");
            return "homevisit/lookup/leisure_facilities";
        }
        
        leisure_facilitiesRepository.save(leisure_facilities);
        
        return "redirect:/leisure_facilities/index";
    }    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Leisure_facilities leisure_facilities, Model model) {
        model.addAttribute("leisure_facilities", leisure_facilitiesRepository.findById(id));
        model.addAttribute("list", leisure_facilitiesRepository.findAll());
        model.addAttribute("table_name", "Leisure Facilities");
        return "/homevisit/lookup/leisure_facilities";
    }
    
    @GetMapping(value = "/delete/{id}")    
    public String delete(@PathVariable Long id, Leisure_facilities leisure_facilities) {        
        
        leisure_facilitiesRepository.deleteById(id);
        return "redirect:/leisure_facilities/index";
    }    
    
}
