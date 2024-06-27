/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.observation.O_Inhouse_Inductions_Mother;
import itgarden.repository.observation.O_Inhouse_Inductions_MotherRepository;
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
@RequestMapping("/inhouseinductionmother")
public class InHouseInductionController {

    @Autowired
    O_Inhouse_Inductions_MotherRepository o_Inhouse_Inductions_MotherRepository;

    @RequestMapping("/create/{id}")
    public String add(Model mode, @PathVariable Long id, O_Inhouse_Inductions_Mother o_Inhouse_Inductions_Mother) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        o_Inhouse_Inductions_Mother.setMotherMasterCode(motherMasterData);
        return "homevisit/observation/induction/inhouseinductionmother";
    }

    @RequestMapping("/save/{id}")
    public String save(Model model, @PathVariable Long id, O_Inhouse_Inductions_Mother o_Inhouse_Inductions_Mother, @Valid BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(id);
            o_Inhouse_Inductions_Mother.setMotherMasterCode(motherMasterData);
            return "homevisit/observation/induction/inhouseinductionmother";
        }
        o_Inhouse_Inductions_MotherRepository.save(o_Inhouse_Inductions_Mother);
        return "redirect:/induction/index/{id}";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model ,@PathVariable Long id, O_Inhouse_Inductions_Mother o_Inhouse_Inductions_Mother) {
        model.addAttribute("o_Inhouse_Inductions_Mother", o_Inhouse_Inductions_MotherRepository.findById(id).orElse(null));
        return "homevisit/observation/induction/inhouseinductionmother";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_Inhouse_Inductions_Mother o_Inhouse_Inductions_Mother, RedirectAttributes redirectAttrs) {
        
        o_Inhouse_Inductions_Mother = o_Inhouse_Inductions_MotherRepository.findById(id).orElse(null);
        
        redirectAttrs.addAttribute("id", o_Inhouse_Inductions_Mother.motherMasterCode.getId());
        
        o_Inhouse_Inductions_MotherRepository.deleteById(id);
        
         return "redirect:/induction//index/{id}";
    }

}
