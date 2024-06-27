/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.Daily_Meal;
import itgarden.model.homevisit.M_Nutrition;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Nutritions_trems;
import itgarden.repository.homevisit.M_NutritionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/m_nutrition")
public class M_NutritionController {

    @Autowired
    M_NutritionRepository m_NutritionRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Nutrition m_Nutrition) {

        model.addAttribute("list", m_NutritionRepository.findAll());

        return "homevisit/lookup/ethnicIdentity";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Nutrition m_Nutrition) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        m_Nutrition.setMotherMasterCode(motherMasterData);

        model.addAttribute("form_title", "Mother Nutrition Add");

        model.addAttribute("dailyMeal", Daily_Meal.values());

        model.addAttribute("nutritionsTrems", Nutritions_trems.values());

        return "homevisit/motherdetails/m_nutrition";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_Nutrition m_Nutrition, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            m_Nutrition.setMotherMasterCode(motherMasterData);

            model.addAttribute("form_title", "Mother Nutrition Edit/Update");

            model.addAttribute("dailyMeal", Daily_Meal.values());

            model.addAttribute("nutritionsTrems", Nutritions_trems.values());

            return "homevisit/motherdetails/m_nutrition";
        }

        m_NutritionRepository.save(m_Nutrition);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Nutrition m_Nutrition, Model model) {
        model.addAttribute("m_Nutrition", m_NutritionRepository.findById(id).orElse(null));
      
        model.addAttribute("form_title", "Mother Nutrition Edit");

        model.addAttribute("dailyMeal", Daily_Meal.values());

        model.addAttribute("nutritionsTrems", Nutritions_trems.values());

        return "homevisit/motherdetails/m_nutrition";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Nutrition m_Nutrition,RedirectAttributes redirectAttrs) {
        m_Nutrition = m_NutritionRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", m_Nutrition.motherMasterCode.getId());
        m_NutritionRepository.deleteById(id);
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
