/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.literacy;

import sppbims.model.literacy.LiteracyTalkingScience;
import sppbims.model.literacy.ResultEnum;
import sppbims.repository.literacy.LiteracyTalkingScienceRepository;
import sppbims.services.literacy.LiteracyTalkingScienceService;
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
@RequestMapping("/literacytalkingscience")
public class LiteracyalkingScienceController {

    @Autowired
    LiteracyTalkingScienceRepository literacyTalkingScienceRepository;

    @Autowired
    LiteracyTalkingScienceService literacyTalkingScienceService;

    @RequestMapping("/add")
    public String index(Model model, LiteracyTalkingScience literacyTalkingScience) {
        model.addAttribute("motherId", literacyTalkingScienceService.getMotherMasterDataDTOs());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_literacytalkingscience";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid LiteracyTalkingScience literacyTalkingScience, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("motherId", literacyTalkingScienceService.getMotherMasterDataDTOs());
            model.addAttribute("status", ResultEnum.values());

            return "literacy/add_literacytalkingscience";
        }
        literacyTalkingScienceRepository.save(literacyTalkingScience);

        return "redirect:/literacytalkingscience/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", literacyTalkingScienceService.getLiteracyTalkingScienceData());
        return "literacy/literacytalkingscience_list";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LiteracyTalkingScience literacyTalkingScience, Model model) {
        model.addAttribute("literacyTalkingScience", literacyTalkingScienceRepository.findById(id).orElse(null));
        model.addAttribute("motherId", literacyTalkingScienceService.getMotherMasterDataDTOs());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_literacytalkingscience";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LiteracyTalkingScience literacyTalkingScience, RedirectAttributes redirectAttrs) {
        literacyTalkingScience = literacyTalkingScienceRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        literacyTalkingScienceRepository.deleteById(id);
        return "redirect:/literacytalkingscience/list";
    }

}
