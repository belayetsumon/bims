/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.impactmeasurement;

import itgarden.model.impactmeasurement.ContinueNotContinueEnum;
import itgarden.model.impactmeasurement.ImpactMeasurementIndicator;
import itgarden.model.impactmeasurement.ImpactMeasurementYesNo;
import itgarden.model.impactmeasurement.MidTermImpactMeasurement;
import itgarden.model.impactmeasurement.PresentIncomeSource;
import itgarden.repository.homevisit.House_TypeRepository;
import itgarden.repository.homevisit.Ownershif_typeRepository;
import itgarden.repository.impactmeasurement.MidTermImpactMeasurementRepository;
import itgarden.services.impactmeasurement.MidTermImpactMeasurementService;
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
@RequestMapping("/midtermimpactmeasurement")
public class MidTermImpactMeasurementController {

    @Autowired
    MidTermImpactMeasurementRepository midTermImpactMeasurementRepository;

    @Autowired
    MidTermImpactMeasurementService midTermImpactMeasurementService;

    @Autowired
    Ownershif_typeRepository ownershif_typeRepository;

    @Autowired
    House_TypeRepository house_TypeRepository;

    @RequestMapping("/add")
    public String index(Model model, MidTermImpactMeasurement midTermImpactMeasurement) {

        model.addAttribute("releaseMotherid", midTermImpactMeasurementService.newListReleaseMotherIdforMidTermImpactMeasurement());
        model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
        model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());
        model.addAttribute("presentIncomeSource", PresentIncomeSource.values());
        model.addAttribute("ownershiftype", house_TypeRepository.findAll());
        model.addAttribute("housetype", ownershif_typeRepository.findAll());
        model.addAttribute("continueNotContinueEnum", ContinueNotContinueEnum.values());

        return "impactmeasurement/midtermimpactmeasurement";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("attribute", "value");
        model.addAttribute("list", midTermImpactMeasurementRepository.findAll());
        return "impactmeasurement/midtermimpactmeasurementlist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, MidTermImpactMeasurement midTermImpactMeasurement, Model model) {
        model.addAttribute("releaseMotherid", midTermImpactMeasurementService.newListReleaseMotherIdforMidTermImpactMeasurement());

        model.addAttribute("midTermImpactMeasurement", midTermImpactMeasurementRepository.findById(id).orElse(null));

        model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());

        model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());

        return "impactmeasurement/midtermimpactmeasurement";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid MidTermImpactMeasurement midTermImpactMeasurement, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("releaseMotherid", midTermImpactMeasurementService.newListReleaseMotherIdforMidTermImpactMeasurement());
            model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
            model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());

            return "impactmeasurement/midtermimpactmeasurement";
        }
        midTermImpactMeasurementRepository.save(midTermImpactMeasurement);

        return "redirect:/midtermimpactmeasurement/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, MidTermImpactMeasurement midTermImpactMeasurement, RedirectAttributes redirectAttrs) {
        midTermImpactMeasurement = midTermImpactMeasurementRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        midTermImpactMeasurementRepository.deleteById(id);
        return "redirect:/midtermimpactmeasurement/list";
    }

}
