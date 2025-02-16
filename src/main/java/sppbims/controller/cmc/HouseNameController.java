/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.cmc;

import sppbims.model.rehabilitations.HouseName;
import sppbims.repository.rehabilitations.HouseNameRepository;
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
@RequestMapping("/housename")
public class HouseNameController {

    @Autowired
    HouseNameRepository houseNameRepository;

    @RequestMapping("/index")
    public String index(Model model, HouseName houseName) {
        model.addAttribute("list", houseNameRepository.findAll());
        model.addAttribute("table_name", "House name");
        return "rehabilitations/lookup/housename";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid HouseName houseName, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", houseNameRepository.findAll());
            model.addAttribute("table_name", "House name");
            return "rehabilitations/lookup/housename";
        }

        houseNameRepository.save(houseName);

        return "redirect:/housename/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, HouseName houseName, Model model) {
        model.addAttribute("houseName", houseNameRepository.findById(id));
        model.addAttribute("list", houseNameRepository.findAll());
        model.addAttribute("table_name", "House Name");
        return "rehabilitations/lookup/housename";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, HouseName houseName) {
        houseNameRepository.deleteById(id);
        return "redirect:/housename/index";
    }

}
