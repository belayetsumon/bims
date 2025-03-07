/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.observation;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.observation.O_Inhouse_Inductions_child;
import sppbims.repository.homevisit.M_Child_infoRepository;
import sppbims.repository.observation.O_Inhouse_Inductions_childRepository;
import jakarta.validation.Valid;
import java.util.Optional;
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
@RequestMapping("/inhouseinductionchild")
public class InhouseInductionChildController {

    @Autowired
    O_Inhouse_Inductions_childRepository o_Inhouse_Inductions_childRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, O_Inhouse_Inductions_child o_Inhouse_Inductions_child) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        o_Inhouse_Inductions_child.setMotherMasterCode(motherMasterData);
        model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        return "homevisit/observation/induction/inhouseinductionchild";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid O_Inhouse_Inductions_child o_Inhouse_Inductions_child, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            o_Inhouse_Inductions_child.setMotherMasterCode(motherMasterData);
            model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
            return "homevisit/observation/induction/inhouseinductionchild";
        }
        o_Inhouse_Inductions_childRepository.save(o_Inhouse_Inductions_child);
        return "redirect:/induction/index/{m_id}";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, O_Inhouse_Inductions_child o_Inhouse_Inductions_child) {

        Optional<O_Inhouse_Inductions_child> o_Inhouse_Inductions_childopt = o_Inhouse_Inductions_childRepository.findById(id);

        o_Inhouse_Inductions_child = o_Inhouse_Inductions_childopt.orElse(null);

        model.addAttribute("o_Inhouse_Inductions_child", o_Inhouse_Inductions_child);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(o_Inhouse_Inductions_child.getMotherMasterCode().getId());

        model.addAttribute("childList", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        return "homevisit/observation/induction/inhouseinductionchild";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_Inhouse_Inductions_child o_Inhouse_Inductions_child, RedirectAttributes redirectAttrs) {
        o_Inhouse_Inductions_child = o_Inhouse_Inductions_childRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", o_Inhouse_Inductions_child.motherMasterCode.getId());
        o_Inhouse_Inductions_childRepository.deleteById(id);
        return "redirect:/induction/index/{m_id}";
    }

}
