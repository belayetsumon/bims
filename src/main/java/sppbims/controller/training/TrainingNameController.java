/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.training;

import sppbims.model.rehabilitations.TrainingName;
import sppbims.repository.rehabilitations.TrainingNameRepository;
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
@RequestMapping("/trainingname")
public class TrainingNameController {

    @Autowired
    TrainingNameRepository trainingNameRepository;

    @RequestMapping("/index")
    public String index(Model model, TrainingName trainingName) {
        model.addAttribute("list", trainingNameRepository.findAll());
        model.addAttribute("table_name", "  Training Name ");
        return "rehabilitations/lookup/trainingname";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid TrainingName trainingName, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", trainingNameRepository.findAll());
            model.addAttribute("table_name", "Training Name");
            return "rehabilitations/lookup/trainingname";
        }

        trainingNameRepository.save(trainingName);

        return "redirect:/trainingname/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, TrainingName trainingName, Model model) {
        model.addAttribute("trainingName", trainingNameRepository.findById(id));
        model.addAttribute("list", trainingNameRepository.findAll());
        model.addAttribute("table_name", "Diagonosis");
        return "rehabilitations/lookup/trainingname";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, TrainingName trainingName) {
        trainingNameRepository.deleteById(id);
        return "redirect:/trainingname/index";
    }

}
