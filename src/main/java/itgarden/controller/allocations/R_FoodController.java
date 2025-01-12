/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.allocations;

import itgarden.model.rehabilitations.FoodByHouse;
import itgarden.model.rehabilitations.R_Food;
import itgarden.repository.rehabilitations.R_FoodRepository;
import itgarden.services.cmc.R_FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/food")
public class R_FoodController {

    @Autowired
    R_FoodRepository r_FoodRepository;
    
    @Autowired
    R_FoodService r_FoodService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", r_FoodService.getFoodData());
        return "rehabilitations/food/food_index";
    }

    @RequestMapping("/add")
    public String addfood(Model model, R_Food r_Food) {
        model.addAttribute("foodByHouses", FoodByHouse.values());
        return "rehabilitations/food/food_add";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_Food r_Food) {
        model.addAttribute("r_Food", r_FoodRepository.findById(id));
        model.addAttribute("foodByHouses", FoodByHouse.values());

        return "rehabilitations/food/food_add";
    }

    @RequestMapping("/save")
    public String save(Model model,  @Valid R_Food r_Food, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("foodByHouses", FoodByHouse.values());
            return "rehabilitations/food/food_add";
        }
        r_FoodRepository.save(r_Food);
        return "redirect:/food/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_Food r_Food, RedirectAttributes redirectAttrs) {

        r_FoodRepository.deleteById(id);
        return "redirect:/food/index";

    }

}
