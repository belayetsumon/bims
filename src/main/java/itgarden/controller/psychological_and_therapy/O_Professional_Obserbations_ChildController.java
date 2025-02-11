/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.psychological_and_therapy;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.observation.O_Induction;
import itgarden.model.observation.O_Professional_Obserbations_Child;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_Professional_Obserbations_ChildRepository;
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
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/opobserbationschild")
public class O_Professional_Obserbations_ChildController {
    
    @Autowired
    O_Professional_Obserbations_ChildRepository o_Professional_Obserbations_ChildRepository;
    
    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;
    
    @Autowired
    O_InductionRepository o_InductionRepository;
    
    @Autowired
    M_Child_infoRepository m_Child_infoRepository;
    
    @RequestMapping("/create/{m_id}")
    public String create(Model model, @PathVariable Long m_id, O_Professional_Obserbations_Child o_Professional_Obserbations_Child) {
        
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        o_Professional_Obserbations_Child.setMotherMasterCode(motherMasterData);
        O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
        o_Professional_Obserbations_Child.setInductionStartDate(o_Induction.getStartDate());
        model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("physicalDisability", Yes_No.values());
        model.addAttribute("therapy", Yes_No.values());
        model.addAttribute("adlPerformance", Yes_No.values());
        model.addAttribute("psychocialAssesmentNeeds", Yes_No.values());
        return "homevisit/observation/fobservations/childpobservation";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, O_Professional_Obserbations_Child o_Professional_Obserbations_Child) {
        
        model.addAttribute("o_Professional_Obserbations_Child", o_Professional_Obserbations_ChildRepository.findById(id).orElse(null));
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        
        model.addAttribute("physicalDisability", Yes_No.values());
        
        model.addAttribute("therapy", Yes_No.values());
        
        model.addAttribute("adlPerformance", Yes_No.values());
        
        model.addAttribute("psychocialAssesmentNeeds", Yes_No.values());
        
        return "homevisit/observation/fobservations/childpobservation";
    }
    
    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid O_Professional_Obserbations_Child o_Professional_Obserbations_Child,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            o_Professional_Obserbations_Child.setMotherMasterCode(motherMasterData);
            O_Induction o_Induction = o_InductionRepository.findByMotherMasterCode(motherMasterData);
            o_Professional_Obserbations_Child.setInductionStartDate(o_Induction.getStartDate());
            model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
            model.addAttribute("physicalDisability", Yes_No.values());
            model.addAttribute("therapy", Yes_No.values());
            model.addAttribute("adlPerformance", Yes_No.values());
            model.addAttribute("psychocialAssesmentNeeds", Yes_No.values());
            return "homevisit/observation/fobservations/childpobservation";
        }
        o_Professional_Obserbations_ChildRepository.save(o_Professional_Obserbations_Child);
        return "redirect:/professionalobserbations/index/{m_id}";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_Professional_Obserbations_Child o_Professional_Obserbations_Child, RedirectAttributes redirectAttrs) {
        o_Professional_Obserbations_Child = o_Professional_Obserbations_ChildRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", o_Professional_Obserbations_Child.motherMasterCode.getId());
        o_Professional_Obserbations_ChildRepository.deleteById(id);
        return "redirect:/professionalobserbations/index/{m_id}";
    }
    
}
