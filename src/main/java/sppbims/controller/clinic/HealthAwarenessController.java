/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.clinic;

import sppbims.model.clinic.HealthAwareness;
import sppbims.repository.clinic.HealthAwarenessRepository;
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
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/healthawareness")
public class HealthAwarenessController {

    @Autowired
    HealthAwarenessRepository healthAwarenessRepository;

    @RequestMapping("/index")
    public String index(Model model, HealthAwareness healthAwareness) {
        model.addAttribute("list", healthAwarenessRepository.findAll());
        model.addAttribute("table_name", " Health Awareness");
        return "clinic/healthawareness";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid HealthAwareness healthAwareness, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", healthAwarenessRepository.findAll());
            model.addAttribute("table_name", " Health Awareness");
            return "clinic/healthawareness";
        }
        healthAwarenessRepository.save(healthAwareness);

        return "redirect:/healthawareness/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, HealthAwareness healthAwareness, Model model) {
        model.addAttribute("healthAwareness", healthAwarenessRepository.findById(id));
        model.addAttribute("list", healthAwarenessRepository.findAll());
        model.addAttribute("table_name", " Health Awareness");
        return "clinic/healthawareness";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, HealthAwareness healthAwareness) {

        healthAwarenessRepository.deleteById(id);
        return "redirect:/healthawareness/index";
    }

}
