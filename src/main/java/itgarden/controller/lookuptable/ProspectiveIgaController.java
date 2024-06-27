/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import itgarden.model.homevisit.ProspectiveIga;
import itgarden.repository.homevisit.ProspectiveIgaRepository;
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
@RequestMapping("/prospectiveiga")
public class ProspectiveIgaController {

    @Autowired
    ProspectiveIgaRepository prospectiveIgaRepository;

    @RequestMapping("/index")
    public String index(Model model, ProspectiveIga prospectiveIga) {
        model.addAttribute("list", prospectiveIgaRepository.findAll());
        model.addAttribute("table_name", "Prospective Iga");
        return "homevisit/lookup/prospectiveiga";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid ProspectiveIga prospectiveIga, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", prospectiveIgaRepository.findAll());
            model.addAttribute("table_name", "Prospective Iga");
            return "homevisit/lookup/prospectiveiga";
        }

        prospectiveIgaRepository.save(prospectiveIga);

        return "redirect:/prospectiveiga/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, ProspectiveIga prospectiveIga, Model model) {
        model.addAttribute("prospectiveIga", prospectiveIgaRepository.findById(id));
        model.addAttribute("list", prospectiveIgaRepository.findAll());
        model.addAttribute("table_name", "Prospective Iga");
        return "/homevisit/lookup/prospectiveiga";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, ProspectiveIga prospectiveIga) {
        prospectiveIgaRepository.deleteById(id);
        return "redirect:/prospectiveiga/index";
    }

}
