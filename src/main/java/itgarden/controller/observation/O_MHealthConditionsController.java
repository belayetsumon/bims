/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.observation.O_Induction;
import itgarden.model.observation.O_MHealthConditions;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MHealthConditionsRepository;
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
@RequestMapping("/mhealthconditions")
public class O_MHealthConditionsController {

    @Autowired
    O_MHealthConditionsRepository o_MHealthConditionsRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, O_MHealthConditions o_MHealthConditions) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);

        o_MHealthConditions.setMotherMasterCode(motherMasterData);
        O_Induction induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
        o_MHealthConditions.setInduction(induction);

        model.addAttribute("form_title", "Mother Health Conditions Add");
        model.addAttribute("bloodPressure", Yes_No.values());
        model.addAttribute("eyeProblem", Yes_No.values());
        model.addAttribute("earProblem", Yes_No.values());
        model.addAttribute("gynologicalProblem", Yes_No.values());
        model.addAttribute("tt", Yes_No.values());
        model.addAttribute("heart_disease", Yes_No.values());
        model.addAttribute("diabetes", Yes_No.values());
        model.addAttribute("bonyFracture", Yes_No.values());
        model.addAttribute("neurologicalDisease", Yes_No.values());
        model.addAttribute("resporatoryProblem", Yes_No.values());
        model.addAttribute("uti", Yes_No.values());
        return "homevisit/observation/healthcheckup/mhealthcondition";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, O_MHealthConditions o_MHealthConditions) {
        model.addAttribute("o_MHealthConditions", o_MHealthConditionsRepository.findOne(id));
        model.addAttribute("form_title", "Mother Health Conditions Edit");
        model.addAttribute("bloodPressure", Yes_No.values());
        model.addAttribute("eyeProblem", Yes_No.values());
        model.addAttribute("earProblem", Yes_No.values());
        model.addAttribute("gynologicalProblem", Yes_No.values());
        model.addAttribute("tt", Yes_No.values());
        model.addAttribute("heart_disease", Yes_No.values());
        model.addAttribute("diabetes", Yes_No.values());
        model.addAttribute("bonyFracture", Yes_No.values());
        model.addAttribute("neurologicalDisease", Yes_No.values());
        model.addAttribute("resporatoryProblem", Yes_No.values());
        model.addAttribute("uti", Yes_No.values());
        return "homevisit/observation/healthcheckup/mhealthcondition";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid O_MHealthConditions o_MHealthConditions, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            o_MHealthConditions.setMotherMasterCode(motherMasterData);

            o_MHealthConditions.setMotherMasterCode(motherMasterData);
            O_Induction induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
            o_MHealthConditions.setInduction(induction);

            model.addAttribute("form_title", "Mother Health Conditions Save/Update");
            model.addAttribute("bloodPressure", Yes_No.values());
            model.addAttribute("eyeProblem", Yes_No.values());
            model.addAttribute("earProblem", Yes_No.values());
            model.addAttribute("gynologicalProblem", Yes_No.values());
            model.addAttribute("tt", Yes_No.values());
            model.addAttribute("heart_disease", Yes_No.values());
            model.addAttribute("diabetes", Yes_No.values());
            model.addAttribute("bonyFracture", Yes_No.values());
            model.addAttribute("neurologicalDisease", Yes_No.values());
            model.addAttribute("resporatoryProblem", Yes_No.values());
            model.addAttribute("uti", Yes_No.values());
            return "homevisit/observation/healthcheckup/mhealthcondition";
        }

        o_MHealthConditionsRepository.save(o_MHealthConditions);
        return "redirect:/healthcheckup//index/{m_id}";
    }

    @RequestMapping("/delete/{id}")

    public String delete(Model model, @PathVariable Long id, O_MHealthConditions o_MHealthConditions, RedirectAttributes redirectAttrs) {
        o_MHealthConditions = o_MHealthConditionsRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", o_MHealthConditions.motherMasterCode.getId());
        o_MHealthConditionsRepository.delete(id);
        return "redirect:/healthcheckup/index/{m_id}";
    }

}
