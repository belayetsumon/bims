/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.rehabilitations.SessionType;
import itgarden.repository.rehabilitations.SessionTypeRepository;
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

@RequestMapping("/sessiontype")

public class SessionTypeController {
    
    @Autowired
    SessionTypeRepository SessionTypeRepository;
    
    @RequestMapping("/index")
    public String index(Model model, SessionType  sessionType) {
        
        model.addAttribute("list", SessionTypeRepository.findAll());
        
        model.addAttribute("table_name", "  Session Type");
        
        return "rehabilitations/lookup/sessiontype";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid SessionType  sessionType, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            
            model.addAttribute("list", SessionTypeRepository.findAll());
            
            model.addAttribute("table_name", "Session Type");
            
            return "rehabilitations/lookup/sessiontype";
        }

        SessionTypeRepository.save(sessionType);

        return "redirect:/sessiontype/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, SessionType  sessionType, Model model) {
        model.addAttribute("sessionType", SessionTypeRepository.findOne(id));
        model.addAttribute("list", SessionTypeRepository.findAll());
        model.addAttribute("table_name", "Session Type");
        return "rehabilitations/lookup/sessiontype";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, SessionType  sessionType) {
        SessionTypeRepository.delete(id);
        return "redirect:/sessiontype/index";
    }

}
