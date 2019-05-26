/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.rehabilitations.Tenderness;
import itgarden.repository.rehabilitations.TendernessRepository;
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
@RequestMapping("/tenderness")
public class TendernessController {

    @Autowired
    TendernessRepository tendernessRepository;

    @RequestMapping("/index")
    public String index(Model model, Tenderness tenderness) {
        model.addAttribute("list", tendernessRepository.findAll());
        model.addAttribute("table_name", "  Tenderness ");
        return "rehabilitations/lookup/tenderness";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid Tenderness tenderness, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", tendernessRepository.findAll());
            model.addAttribute("table_name", "Tenderness");
            return "rehabilitations/lookup/tenderness";
        }

        tendernessRepository.save(tenderness);

        return "redirect:/tenderness/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Tenderness tenderness, Model model) {
        model.addAttribute("tenderness", tendernessRepository.findOne(id));
        model.addAttribute("list", tendernessRepository.findAll());
        model.addAttribute("table_name", "Diagonosis");
        return "rehabilitations/lookup/tenderness";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Tenderness tenderness) {
        tendernessRepository.delete(id);
        return "redirect:/tenderness/index";
    }

}
