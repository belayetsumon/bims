/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.literacy;

import itgarden.model.literacy.LiteracyNumeracy;
import itgarden.model.literacy.ResultEnum;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.literacy.LiteracyNumeracyRepository;
import itgarden.services.literacy.LiteracyNumeracyService;
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
@RequestMapping("/literacynumeracy")
public class LiteracyNumeracyController {

    @Autowired
    LiteracyNumeracyRepository literacyNumeracyRepository;

    @Autowired
    LiteracyNumeracyService literacyNumeracyService;

    @Autowired
    EducationLevelRepository educationLevelRepository;



    @RequestMapping("/add")
    public String index(Model model, LiteracyNumeracy literacyNumeracy) {
        model.addAttribute("educationLavel", educationLevelRepository.findAll());
        model.addAttribute("motherId", literacyNumeracyService.getMotherList());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_literacynumeracy";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid LiteracyNumeracy literacyNumeracy, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("educationLavel", educationLevelRepository.findAll());
            model.addAttribute("motherId", literacyNumeracyService.getMotherList());
            model.addAttribute("status", ResultEnum.values());

            return "literacy/add_literacynumeracy";
        }
        literacyNumeracyRepository.save(literacyNumeracy);

        return "redirect:/literacynumeracy/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", literacyNumeracyService.getAllLiteracyNumeracyData());
        return "literacy/literacynumeracy_list";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LiteracyNumeracy literacyNumeracy, Model model) {
        model.addAttribute("educationLavel", educationLevelRepository.findAll());
        model.addAttribute("literacyNumeracy", literacyNumeracyRepository.findById(id).orElse(null));
        model.addAttribute("motherId", literacyNumeracyService.getMotherList());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_literacynumeracy";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LiteracyNumeracy literacyNumeracy, RedirectAttributes redirectAttrs) {
        literacyNumeracy = literacyNumeracyRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        literacyNumeracyRepository.deleteById(id);
        return "redirect:/literacynumeracy/list";
    }

}
