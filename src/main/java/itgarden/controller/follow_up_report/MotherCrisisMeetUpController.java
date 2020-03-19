/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.follow_up_report;

import itgarden.model.follow_up_report.FollowUpChildren;
import itgarden.model.follow_up_report.MotherCrisisMeetUp;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.follow_up_report.MotherCrisisMeetUpRepository;
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
 * @author User
 */
@Controller
@RequestMapping("/mothercrisismeetup")
public class MotherCrisisMeetUpController {

    @Autowired
    MotherCrisisMeetUpRepository motherCrisisMeetUpRepository;

    @RequestMapping("/add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, MotherCrisisMeetUp motherCrisisMeetUp) {
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        motherCrisisMeetUp.setMotherMasterCode(motherMasterData);

        return "follow_up_report/mothercrisismeetup";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid MotherCrisisMeetUp motherCrisisMeetUp, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            motherCrisisMeetUp.setMotherMasterCode(motherMasterData);

            return "follow_up_report/mothercrisismeetup";
        }

        motherCrisisMeetUpRepository.save(motherCrisisMeetUp);

        return "redirect:/followupmother/details/{m_id}";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, MotherCrisisMeetUp motherCrisisMeetUp) {
        model.addAttribute("motherCrisisMeetUp", motherCrisisMeetUpRepository.findOne(id));

        // model.addAttribute("childList", releaseChildRepository.findAll());
        return "follow_up_report/mothercrisismeetup";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model, MotherCrisisMeetUp motherCrisisMeetUp, RedirectAttributes redirectAttrs) {
        motherCrisisMeetUp = motherCrisisMeetUpRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", motherCrisisMeetUp.motherMasterCode.getId());
        motherCrisisMeetUpRepository.delete(id);
        return "redirect:/followupmother/details/{m_id}";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", motherCrisisMeetUpRepository.findAll());
        return "follow_up_report/list";
    }

}
