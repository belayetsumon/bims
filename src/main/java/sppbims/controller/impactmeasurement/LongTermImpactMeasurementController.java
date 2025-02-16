/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.impactmeasurement;

import sppbims.model.impactmeasurement.ContinueNotContinueEnum;
import sppbims.model.impactmeasurement.ImpactMeasurementIndicator;
import sppbims.model.impactmeasurement.ImpactMeasurementYesNo;
import sppbims.model.impactmeasurement.LongTermImpactMeasurement;
import sppbims.model.impactmeasurement.PresentIncomeSource;
import sppbims.repository.homevisit.House_TypeRepository;
import sppbims.repository.homevisit.Ownershif_typeRepository;
import sppbims.repository.impactmeasurement.LongTermImpactMeasurementRepository;
import sppbims.services.impactmeasurement.LongTermImpactMeasurementService;
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
@RequestMapping("/longtermimpactmeasurement")
public class LongTermImpactMeasurementController {

    @Autowired
    LongTermImpactMeasurementRepository longTermImpactMeasurementRepository;

    @Autowired
    LongTermImpactMeasurementService longTermImpactMeasurementService;

    @Autowired
    Ownershif_typeRepository ownershif_typeRepository;

    @Autowired
    House_TypeRepository house_TypeRepository;

    @RequestMapping("/add")
    public String index(Model model, LongTermImpactMeasurement longTermImpactMeasurement) {

        model.addAttribute("releaseMotherid", longTermImpactMeasurementService.newListReleaseMotherIdforLongTermImpactMeasurement());
        model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
        model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());
        model.addAttribute("presentIncomeSource", PresentIncomeSource.values());
        model.addAttribute("ownershiftype", house_TypeRepository.findAll());
        model.addAttribute("housetype", ownershif_typeRepository.findAll());
        model.addAttribute("continueNotContinueEnum", ContinueNotContinueEnum.values());

        return "impactmeasurement/longtermimpactmeasurement";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("attribute", "value");
        model.addAttribute("list", longTermImpactMeasurementRepository.findAll());
        return "impactmeasurement/longtermimpactmeasurementlist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LongTermImpactMeasurement longTermImpactMeasurement, Model model) {
        model.addAttribute("releaseMotherid", longTermImpactMeasurementService.newListReleaseMotherIdforLongTermImpactMeasurement());

        model.addAttribute("longTermImpactMeasurement", longTermImpactMeasurementRepository.findById(id).orElse(null));

        model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
        model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());
        model.addAttribute("presentIncomeSource", PresentIncomeSource.values());
        model.addAttribute("ownershiftype", house_TypeRepository.findAll());
        model.addAttribute("housetype", ownershif_typeRepository.findAll());
        model.addAttribute("continueNotContinueEnum", ContinueNotContinueEnum.values());

        return "impactmeasurement/longtermimpactmeasurement";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid LongTermImpactMeasurement longTermImpactMeasurement, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("releaseMotherid", longTermImpactMeasurementService.newListReleaseMotherIdforLongTermImpactMeasurement());
            model.addAttribute("impactMeasurementYesNo", ImpactMeasurementYesNo.values());
            model.addAttribute("impactMeasurementIndicator", ImpactMeasurementIndicator.values());
            model.addAttribute("presentIncomeSource", PresentIncomeSource.values());
            model.addAttribute("ownershiftype", house_TypeRepository.findAll());
            model.addAttribute("housetype", ownershif_typeRepository.findAll());
            model.addAttribute("continueNotContinueEnum", ContinueNotContinueEnum.values());

            return "impactmeasurement/longtermimpactmeasurement";
        }
        longTermImpactMeasurementRepository.save(longTermImpactMeasurement);

        return "redirect:/longtermimpactmeasurement/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LongTermImpactMeasurement longTermImpactMeasurement, RedirectAttributes redirectAttrs) {
        longTermImpactMeasurement = longTermImpactMeasurementRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        longTermImpactMeasurementRepository.deleteById(id);
        return "redirect:/longtermimpactmeasurement/list";
    }

}
