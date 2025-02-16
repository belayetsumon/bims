/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.pre_reintegration_visit;

import sppbims.model.homevisit.M_Lifestyle;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.pre_reintegration_visit.M_Lifestyle_ReintegrationVisit;
import sppbims.repository.homevisit.Cooking_Fuel_TypeRepository;
import sppbims.repository.homevisit.Drinking_Water_SourceRepository;
import sppbims.repository.homevisit.Leisure_facilitiesRepository;
import sppbims.repository.homevisit.Light_Source_typeRepository;
import sppbims.repository.homevisit.Personal_HabitsRepository;
import sppbims.repository.homevisit.Toilet_TypeRepository;
import sppbims.repository.homevisit.Water_SourceRepository;
import sppbims.repository.pre_reintegration_visit.Pre_reintegration_visit_M_LifestyleRepository;
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
@RequestMapping("/pre_reintegration_m_lifestyle")
public class M_Lifestyle_pre_reintegration_visitController {

    @Autowired
    Pre_reintegration_visit_M_LifestyleRepository pre_reintegration_visit_M_LifestyleRepository;

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
        model.addAttribute("list", pre_reintegration_visit_M_LifestyleRepository.findAll());

        return "homevisit/lookup/m_lifestylel";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Lifestyle_ReintegrationVisit m_Lifestyle_ReintegrationVisit) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        model.addAttribute("form_title", "Mother Life Style Add");
        m_Lifestyle_ReintegrationVisit.setMotherMasterCode(motherMasterData);
        model.addAttribute("waterSource", water_SourceRepository.findAll());
        model.addAttribute("drinkingWaterSource", drinking_Water_SourceRepository.findAll());
        model.addAttribute("toilet_type", toilet_TypeRepository.findAll());
        model.addAttribute("cookingFuel", cooking_Fuel_TypeRepository.findAll());
        model.addAttribute("lightSource", light_Source_typeRepository.findAll());
        model.addAttribute("leisurFacilities", leisure_facilitiesRepository.findAll());
        model.addAttribute("personalHabits", Personal_HabitsRepository.findAll());
        return "pre_reintegration_visit/m_lifestylel";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Lifestyle_ReintegrationVisit m_Lifestyle_ReintegrationVisit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Mother Life Style Edit/Update");
            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);
            m_Lifestyle_ReintegrationVisit.setMotherMasterCode(motherMasterData);
            model.addAttribute("waterSource", water_SourceRepository.findAll());
            model.addAttribute("drinkingWaterSource", drinking_Water_SourceRepository.findAll());
            model.addAttribute("toilet_type", toilet_TypeRepository.findAll());
            model.addAttribute("cookingFuel", cooking_Fuel_TypeRepository.findAll());
            model.addAttribute("lightSource", light_Source_typeRepository.findAll());
            model.addAttribute("leisurFacilities", leisure_facilitiesRepository.findAll());
            model.addAttribute("personalHabits", Personal_HabitsRepository.findAll());
            return "pre_reintegration_visit/m_lifestylel";
        }
        pre_reintegration_visit_M_LifestyleRepository.save(m_Lifestyle_ReintegrationVisit);
        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, M_Lifestyle_ReintegrationVisit m_Lifestyle_ReintegrationVisit, Model model) {
        model.addAttribute("m_Lifestyle_ReintegrationVisit", pre_reintegration_visit_M_LifestyleRepository.findById(id).orElse(null));
        model.addAttribute("form_title", "Mother Life Style Edit");
        model.addAttribute("waterSource", water_SourceRepository.findAll());
        model.addAttribute("drinkingWaterSource", drinking_Water_SourceRepository.findAll());
        model.addAttribute("toilet_type", toilet_TypeRepository.findAll());
        model.addAttribute("cookingFuel", cooking_Fuel_TypeRepository.findAll());
        model.addAttribute("lightSource", light_Source_typeRepository.findAll());
        model.addAttribute("leisurFacilities", leisure_facilitiesRepository.findAll());
        model.addAttribute("personalHabits", Personal_HabitsRepository.findAll());
        return "pre_reintegration_visit/m_lifestylel";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, M_Lifestyle_ReintegrationVisit m_Lifestyle_ReintegrationVisit, RedirectAttributes redirectAttrs) {
        m_Lifestyle_ReintegrationVisit = pre_reintegration_visit_M_LifestyleRepository.findById(id).orElse(null);
        if (m_Lifestyle_ReintegrationVisit != null) {
            redirectAttrs.addAttribute("m_id", m_Lifestyle_ReintegrationVisit.getMotherMasterCode().getId());
            pre_reintegration_visit_M_LifestyleRepository.deleteById(id);
        }
        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

}
