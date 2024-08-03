/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.impactmeasurement;

import itgarden.model.impactmeasurement.ImpactMeasurementIndicator;
import itgarden.model.impactmeasurement.ImpactMeasurementYesNo;
import itgarden.model.impactmeasurement.ShortTermImpactMeasurement;
import itgarden.repository.impactmeasurement.ShortTermImpactMeasurementRepository;
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
@RequestMapping("/shorttermtmpactmeasurement")
public class ShortTermImpactMeasurementController {

    @Autowired
    ShortTermImpactMeasurementRepository shortTermImpactMeasurementRepository;

    @RequestMapping("/add")
    public String index(Model model, ShortTermImpactMeasurement shortTermImpactMeasurement) {
        model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
        
        model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());
        
        return "impactmeasurement/shorttermtmpactmeasurement";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("attribute", "value");
        return "impactmeasurement/shorttermtmpactmeasurementlist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, ShortTermImpactMeasurement shortTermImpactMeasurement, Model model) {
        model.addAttribute("preReintegrationVisit", shortTermImpactMeasurementRepository.findById(id).orElse(null));

        model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
        
        model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());

        return "impactmeasurement/shorttermtmpactmeasurement";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid ShortTermImpactMeasurement shortTermImpactMeasurement, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
            model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());

            return "impactmeasurement/shorttermtmpactmeasurement";
        }
        shortTermImpactMeasurementRepository.save(shortTermImpactMeasurement);

        return "redirect:/shorttermtmpactmeasurement/index";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, ShortTermImpactMeasurement shortTermImpactMeasurement, RedirectAttributes redirectAttrs) {
        shortTermImpactMeasurement = shortTermImpactMeasurementRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        shortTermImpactMeasurementRepository.deleteById(id);
        return "redirect:/shorttermtmpactmeasurement/index";
    }
}
