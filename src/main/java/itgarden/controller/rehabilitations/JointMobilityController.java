/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.rehabilitations.JointMobility;
import itgarden.model.rehabilitations.Tenderness;
import itgarden.repository.rehabilitations.JointMobilityRepository;
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
@RequestMapping("/jointmobility")
public class JointMobilityController {

    @Autowired
    JointMobilityRepository JointMobilityRepository;

    @RequestMapping("/index")
    public String index(Model model, JointMobility jointMobility) {
        model.addAttribute("list", JointMobilityRepository.findAll());
        model.addAttribute("table_name", "  Joint Mobility ");
        return "rehabilitations/lookup/jointmobility";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid JointMobility jointMobility, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", JointMobilityRepository.findAll());
            model.addAttribute("table_name", "Joint Mobility");
            return "rehabilitations/lookup/jointmobility";
        }

        JointMobilityRepository.save(jointMobility);

        return "redirect:/jointmobility/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, JointMobility jointMobility, Model model) {
        model.addAttribute("jointMobility", JointMobilityRepository.findOne(id));
        model.addAttribute("list", JointMobilityRepository.findAll());
        model.addAttribute("table_name", "Joint Mobility");
        return "rehabilitations/lookup/jointmobility";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, JointMobility jointMobility) {
        JointMobilityRepository.delete(id);
        return "redirect:/jointmobility/index";
    }

}
