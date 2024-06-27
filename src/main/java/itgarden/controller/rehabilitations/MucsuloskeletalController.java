/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.rehabilitations.Mucsuloskeletal;
import itgarden.repository.rehabilitations.MucsuloskeletalRepository;
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
  @RequestMapping("/mucsuloskeletal")
public class MucsuloskeletalController {
    
  @Autowired
    MucsuloskeletalRepository  mucsuloskeletalRepository;

    @RequestMapping("/index")
    public String index(Model model, Mucsuloskeletal  mucsuloskeletal) {
        model.addAttribute("list", mucsuloskeletalRepository.findAll());
        model.addAttribute("table_name", "  Mucsuloskeletal");
        return "rehabilitations/lookup/mucsuloskeletal";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Mucsuloskeletal  mucsuloskeletal, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", mucsuloskeletalRepository.findAll());
            model.addAttribute("table_name", "Mucsuloskeletal");
            return "rehabilitations/lookup/mucsuloskeletal";
        }

        mucsuloskeletalRepository.save(mucsuloskeletal);

        return "redirect:/mucsuloskeletal/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Mucsuloskeletal  mucsuloskeletal, Model model) {
        model.addAttribute("mucsuloskeletal", mucsuloskeletalRepository.findById(id));
        model.addAttribute("list", mucsuloskeletalRepository.findAll());
        model.addAttribute("table_name", "Mucsuloskeletal");
        return "rehabilitations/lookup/mucsuloskeletal";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Mucsuloskeletal  mucsuloskeletal) {
        mucsuloskeletalRepository.deleteById(id);
        return "redirect:/mucsuloskeletal/index";
    }

}
