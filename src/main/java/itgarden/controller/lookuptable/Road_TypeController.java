/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.Road_Type;
import itgarden.repository.homevisit.Road_TypeRepository;
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
@RequestMapping("/road_type")
public class Road_TypeController {

    @Autowired
    Road_TypeRepository road_TypeRepository;

    @RequestMapping("/index")
    public String index(Model model, Road_Type road_Type) {
        model.addAttribute("list", road_TypeRepository.findAll());
        model.addAttribute("table_name", " Road Type ");

        return "homevisit/lookup/road_type";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Road_Type road_Type, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", road_TypeRepository.findAll());
            model.addAttribute("table_name", " Road Type ");
            return "homevisit/lookup/road_type";
        }

        road_TypeRepository.save(road_Type);

        return "redirect:/road_type/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Road_Type road_Type, Model model) {
        model.addAttribute("road_Type", road_TypeRepository.findById(id));
        model.addAttribute("list", road_TypeRepository.findAll());
        model.addAttribute("table_name", " Road Type ");
        return "/homevisit/lookup/road_type";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Road_Type road_Type) {
        road_TypeRepository.deleteById(id);
        return "redirect:/road_type/index";
    }

}
