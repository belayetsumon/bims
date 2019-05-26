/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.rehabilitations.DegenerativeDiseases;
import itgarden.repository.rehabilitations.DegenerativeDiseasesRepository;
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
@RequestMapping("/degenerative")
public class DegenerativeDiseasesController {

    @Autowired
    DegenerativeDiseasesRepository degenerativeDiseasesRepository;

    @RequestMapping("/index")
    public String index(Model model, DegenerativeDiseases degenerativeDiseases) {
        model.addAttribute("list", degenerativeDiseasesRepository.findAll());
        model.addAttribute("table_name", " Degenerative Diseases ");
        return "rehabilitations/lookup/degenerative";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid DegenerativeDiseases degenerativeDiseases, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", degenerativeDiseasesRepository.findAll());
            model.addAttribute("table_name", "Degenerative Diseases");
            return "rehabilitations/lookup/degenerative";
        }

        degenerativeDiseasesRepository.save(degenerativeDiseases);

        return "redirect:/degenerative/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, DegenerativeDiseases degenerativeDiseases, Model model) {
        model.addAttribute("degenerativeDiseases", degenerativeDiseasesRepository.findOne(id));
          model.addAttribute("list", degenerativeDiseasesRepository.findAll());
        model.addAttribute("table_name", "Degenerative Diseases");
        return "rehabilitations/lookup/degenerative";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, DegenerativeDiseases degenerativeDiseases) {
        degenerativeDiseasesRepository.delete(id);
        return "redirect:/degenerative/index";
    }

}
