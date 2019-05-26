/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.observation.O_CHealthConditions;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.observation.O_CHealthConditionsRepository;
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
@RequestMapping("/chealthconditions")
public class O_CHealthConditionsController {

    @Autowired
    O_CHealthConditionsRepository o_CHealthConditionsRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, O_CHealthConditions o_CHealthConditions) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        o_CHealthConditions.setMotherMasterCode(motherMasterData);
        model.addAttribute("childList", m_Child_infoRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("bloodPressure", Yes_No.values());
        model.addAttribute("eyeProblem", Yes_No.values());
        model.addAttribute("earProblem", Yes_No.values());
        model.addAttribute("gynologicalProblem", Yes_No.values());
        model.addAttribute("tt", Yes_No.values());
        model.addAttribute("heart_disease", Yes_No.values());
        model.addAttribute("disability", Yes_No.values());
        model.addAttribute("bonyFracture", Yes_No.values());
        model.addAttribute("neurologicalDisease", Yes_No.values());
        model.addAttribute("resporatoryProblem", Yes_No.values());
        model.addAttribute("uti", Yes_No.values());
        return "homevisit/observation/healthcheckup/chealthcondition";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, O_CHealthConditions o_CHealthConditions) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_CHealthConditions", o_CHealthConditionsRepository.findOne(id));
        model.addAttribute("childList", m_Child_infoRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("bloodPressure", Yes_No.values());
        model.addAttribute("eyeProblem", Yes_No.values());
        model.addAttribute("earProblem", Yes_No.values());
        model.addAttribute("gynologicalProblem", Yes_No.values());
        model.addAttribute("tt", Yes_No.values());
        model.addAttribute("heart_disease", Yes_No.values());
        model.addAttribute("disability", Yes_No.values());
        model.addAttribute("bonyFracture", Yes_No.values());
        model.addAttribute("neurologicalDisease", Yes_No.values());
        model.addAttribute("resporatoryProblem", Yes_No.values());
        model.addAttribute("uti", Yes_No.values());
        return "homevisit/observation/healthcheckup/chealthcondition";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid O_CHealthConditions o_CHealthConditions, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            o_CHealthConditions.setMotherMasterCode(motherMasterData);
            model.addAttribute("childList", m_Child_infoRepository.findBymotherMasterCode(motherMasterData));
            model.addAttribute("bloodPressure", Yes_No.values());
            model.addAttribute("eyeProblem", Yes_No.values());
            model.addAttribute("earProblem", Yes_No.values());
            model.addAttribute("gynologicalProblem", Yes_No.values());
            model.addAttribute("tt", Yes_No.values());
            model.addAttribute("heart_disease", Yes_No.values());
            model.addAttribute("disability", Yes_No.values());
            model.addAttribute("bonyFracture", Yes_No.values());
            model.addAttribute("neurologicalDisease", Yes_No.values());
            model.addAttribute("resporatoryProblem", Yes_No.values());
            model.addAttribute("uti", Yes_No.values());
            return "homevisit/observation/healthcheckup/chealthcondition";
        }
        o_CHealthConditionsRepository.save(o_CHealthConditions);
        return "redirect:/healthcheckup//index/{m_id}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_CHealthConditions o_CHealthConditions, RedirectAttributes redirectAttrs) {
        o_CHealthConditions = o_CHealthConditionsRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", o_CHealthConditions.motherMasterCode.getId());
        o_CHealthConditionsRepository.delete(id);
        return "redirect:/healthcheckup//index/{m_id}";
    }

}
