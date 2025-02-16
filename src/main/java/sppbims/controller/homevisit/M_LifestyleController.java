/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.homevisit;

import sppbims.model.homevisit.M_Lifestyle;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.Cooking_Fuel_TypeRepository;
import sppbims.repository.homevisit.Drinking_Water_SourceRepository;
import sppbims.repository.homevisit.Leisure_facilitiesRepository;
import sppbims.repository.homevisit.Light_Source_typeRepository;
import sppbims.repository.homevisit.M_LifestyleRepository;
import sppbims.repository.homevisit.Personal_HabitsRepository;
import sppbims.repository.homevisit.Toilet_TypeRepository;
import sppbims.repository.homevisit.Water_SourceRepository;
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
@RequestMapping("/m_lifestyle")
public class M_LifestyleController {

    @Autowired
    M_LifestyleRepository m_LifestyleRepository;

    @Autowired
    Water_SourceRepository water_SourceRepository;
    @Autowired
    Drinking_Water_SourceRepository drinking_Water_SourceRepository;

    @Autowired
    Toilet_TypeRepository toilet_TypeRepository;

    @Autowired
    Cooking_Fuel_TypeRepository cooking_Fuel_TypeRepository;

    @Autowired
    Light_Source_typeRepository light_Source_typeRepository;

    @Autowired
    Leisure_facilitiesRepository leisure_facilitiesRepository;
    @Autowired
    Personal_HabitsRepository Personal_HabitsRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Lifestyle m_Lifestyle) {
        model.addAttribute("list", m_LifestyleRepository.findAll());

        return "homevisit/lookup/m_lifestylel";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Lifestyle m_Lifestyle) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        model.addAttribute("form_title", "Mother Life Style Add");
        m_Lifestyle.setMotherMasterCode(motherMasterData);
        model.addAttribute("waterSource", water_SourceRepository.findAll());
        model.addAttribute("drinkingWaterSource", drinking_Water_SourceRepository.findAll());
        model.addAttribute("toilet_type", toilet_TypeRepository.findAll());
        model.addAttribute("cookingFuel", cooking_Fuel_TypeRepository.findAll());
        model.addAttribute("lightSource", light_Source_typeRepository.findAll());
        model.addAttribute("leisurFacilities", leisure_facilitiesRepository.findAll());
        model.addAttribute("personalHabits", Personal_HabitsRepository.findAll());
        return "homevisit/motherdetails/m_lifestylel";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Lifestyle m_Lifestyle, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Mother Life Style Edit/Update");
            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);
            m_Lifestyle.setMotherMasterCode(motherMasterData);
            model.addAttribute("waterSource", water_SourceRepository.findAll());
            model.addAttribute("drinkingWaterSource", drinking_Water_SourceRepository.findAll());
            model.addAttribute("toilet_type", toilet_TypeRepository.findAll());
            model.addAttribute("cookingFuel", cooking_Fuel_TypeRepository.findAll());
            model.addAttribute("lightSource", light_Source_typeRepository.findAll());
            model.addAttribute("leisurFacilities", leisure_facilitiesRepository.findAll());
            model.addAttribute("personalHabits", Personal_HabitsRepository.findAll());
            return "homevisit/motherdetails/m_lifestylel";
        }
        m_LifestyleRepository.save(m_Lifestyle);
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Lifestyle m_Lifestyle, Model model) {
        model.addAttribute("m_Lifestyle", m_LifestyleRepository.findById(id).orElse(null));
        model.addAttribute("form_title", "Mother Life Style Edit");
        model.addAttribute("waterSource", water_SourceRepository.findAll());
        model.addAttribute("drinkingWaterSource", drinking_Water_SourceRepository.findAll());
        model.addAttribute("toilet_type", toilet_TypeRepository.findAll());
        model.addAttribute("cookingFuel", cooking_Fuel_TypeRepository.findAll());
        model.addAttribute("lightSource", light_Source_typeRepository.findAll());
        model.addAttribute("leisurFacilities", leisure_facilitiesRepository.findAll());
        model.addAttribute("personalHabits", Personal_HabitsRepository.findAll());
        return "homevisit/motherdetails/m_lifestylel";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Lifestyle m_Lifestyl, RedirectAttributes redirectAttrs) {
        m_Lifestyl = m_LifestyleRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", m_Lifestyl.motherMasterCode.getId());
        m_LifestyleRepository.deleteById(id);
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
