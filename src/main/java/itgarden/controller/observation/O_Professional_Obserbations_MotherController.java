/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.observation.O_Induction;
import itgarden.model.observation.O_Professional_Obserbations_Mother;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_Professional_Obserbations_MotherRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/mprofessionalobserbations")
public class O_Professional_Obserbations_MotherController {

    @Autowired
    O_Professional_Obserbations_MotherRepository o_Professional_Obserbations_MotherRepository;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @RequestMapping("/create/{id}")
    public String create(Model model, @PathVariable Long id, O_Professional_Obserbations_Mother o_Professional_Obserbations_Mother) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        o_Professional_Obserbations_Mother.setMotherMasterCode(motherMasterData);
        O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
        o_Professional_Obserbations_Mother.setInduction(o_Induction);
        o_Professional_Obserbations_Mother.setInductionStartDate(o_Induction.getStartDate());

        model.addAttribute("physicalDisability", Yes_No.values());
        model.addAttribute("therapy", Yes_No.values());
        model.addAttribute("adlPerformance", Yes_No.values());
        model.addAttribute("psychocialAssesmentNeeds", Yes_No.values());

        return "homevisit/observation/fobservations/motherpobservations";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, O_Professional_Obserbations_Mother o_Professional_Obserbations_Mother) {

        model.addAttribute("o_Professional_Obserbations_Mother", o_Professional_Obserbations_MotherRepository.findOne(id));
        model.addAttribute("physicalDisability", Yes_No.values());
        model.addAttribute("therapy", Yes_No.values());
        model.addAttribute("adlPerformance", Yes_No.values());
        model.addAttribute("psychocialAssesmentNeeds", Yes_No.values());
        return "homevisit/observation/fobservations/motherpobservations";
    }

    @RequestMapping("/save/{id}")
    public String save(Model model, @PathVariable Long id, @Valid O_Professional_Obserbations_Mother o_Professional_Obserbations_Mother,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(id);
            o_Professional_Obserbations_Mother.setMotherMasterCode(motherMasterData);
            O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
            o_Professional_Obserbations_Mother.setInduction(o_Induction);
            o_Professional_Obserbations_Mother.setInductionStartDate(o_Induction.getStartDate());
            model.addAttribute("physicalDisability", Yes_No.values());
            model.addAttribute("therapy", Yes_No.values());
            model.addAttribute("adlPerformance", Yes_No.values());
            model.addAttribute("psychocialAssesmentNeeds", Yes_No.values());

            return "homevisit/observation/fobservations/motherpobservations";
        }
        o_Professional_Obserbations_MotherRepository.save(o_Professional_Obserbations_Mother);
        return "redirect:/professionalobserbations//index/{id}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_Professional_Obserbations_Mother o_Professional_Obserbations_Mother, RedirectAttributes redirectAttrs) {
        o_Professional_Obserbations_Mother = o_Professional_Obserbations_MotherRepository.findOne(id);
        redirectAttrs.addAttribute("id", o_Professional_Obserbations_Mother.motherMasterCode.getId());
        o_Professional_Obserbations_MotherRepository.delete(id);
        return "redirect:/professionalobserbations/index/{id}";
    }

}
