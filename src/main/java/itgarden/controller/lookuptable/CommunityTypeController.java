/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.CommunityType;
import itgarden.repository.homevisit.CommunityTypeRepository;
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
@RequestMapping("/communitytype")
public class CommunityTypeController {
    
    @Autowired
    CommunityTypeRepository communityTypeRepository;
    
    @RequestMapping("/index")
    
    public String index(Model model, CommunityType CommunityType) {
        model.addAttribute("list", communityTypeRepository.findAll());
        model.addAttribute("table_name", " Community Type ");
        
        return "homevisit/lookup/communitytype";
    }
    
    @RequestMapping("/save")
    
    public String save(Model model, @Valid CommunityType communityType, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", communityTypeRepository.findAll());
            model.addAttribute("table_name", " Community Type");
            return "homevisit/lookup/communitytype";
        }
        
        communityTypeRepository.save(communityType);
        
        return "redirect:/communitytype/index";
    }    
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, CommunityType communityType, Model model) {
        model.addAttribute("communityType", communityTypeRepository.findById(id));
        model.addAttribute("list", communityTypeRepository.findAll());
        model.addAttribute("table_name", " Community Type l");
        return "/homevisit/lookup/communitytype";
    }
    
    @GetMapping(value = "/delete/{id}")    
    public String delete(@PathVariable Long id, CommunityType communityType) {        
        
        communityTypeRepository.deleteById(id);
        return "redirect:/communitytype/index";
    }    
    
}
