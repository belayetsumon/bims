/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.literacy;

import itgarden.model.literacy.LiteracyDigitalLiteracy;
import itgarden.model.literacy.ResultEnum;
import itgarden.repository.literacy.LiteracyDigitalLiteracyRepository;
import itgarden.services.literacy.DigitalliteracyService;
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
@RequestMapping("/literacydigitalliteracy")
public class LiteracyDigitalLiteracyController {

    @Autowired
    LiteracyDigitalLiteracyRepository literacyDigitalLiteracyRepository;

    @Autowired
    DigitalliteracyService digitalliteracyService;

    @RequestMapping("/add")
    public String index(Model model, LiteracyDigitalLiteracy literacyDigitalLiteracy) {
        model.addAttribute("motherId", digitalliteracyService.getMotherMasterDataDTOs());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_digitalliteracy";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid LiteracyDigitalLiteracy literacyDigitalLiteracy, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("motherId", digitalliteracyService.getMotherMasterDataDTOs());
            model.addAttribute("status", ResultEnum.values());

            return "leave/add_mother_leave";
        }
        literacyDigitalLiteracyRepository.save(literacyDigitalLiteracy);

        return "redirect:/literacydigitalliteracy/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", literacyDigitalLiteracyRepository.findAll());
        return "literacy/digitalliteracy_list";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LiteracyDigitalLiteracy literacyDigitalLiteracy, Model model) {
        model.addAttribute("literacyDigitalLiteracy", literacyDigitalLiteracyRepository.findById(id).orElse(null));
        model.addAttribute("motherId", digitalliteracyService.getMotherMasterDataDTOs());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_digitalliteracy";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LiteracyDigitalLiteracy literacyDigitalLiteracy, RedirectAttributes redirectAttrs) {
        literacyDigitalLiteracy = literacyDigitalLiteracyRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        literacyDigitalLiteracyRepository.deleteById(id);
        return "redirect:/literacydigitalliteracy/list";
    }

}
