/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.follow_up_report;

import itgarden.model.follow_up_report.FollowUpChildren;
import itgarden.model.follow_up_report.FollowUpMother;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.repository.follow_up_report.FollowUpChildrenRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
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
@RequestMapping("/followupchildren")
public class FollowUpChildrenController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    FollowUpChildrenRepository followUpChildrenRepository;

    @RequestMapping("/add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, FollowUpChildren followUpChildren) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        followUpChildren.setMotherMasterCode(motherMasterData);

        //model.addAttribute("mother", releaseMotherRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("motherid", motherMasterDataRepository.findById(m_id));
        model.addAttribute("yes_no", Yes_No.values());

        // model.addAttribute("childList", releaseChildRepository.findAll());
        return "follow_up_report/followupchildren";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FollowUpChildren followUpChildren) {
        model.addAttribute("followUpChildren", followUpChildrenRepository.findOne(id));

        MotherMasterData motherMasterData = new MotherMasterData();

        followUpChildren = followUpChildrenRepository.findOne(id);

        //model.addAttribute("mother", releaseMotherRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("motherid", motherMasterDataRepository.findById(followUpChildren.getMotherMasterCode().getId()));
        model.addAttribute("yes_no", Yes_No.values());

        // model.addAttribute("childList", releaseChildRepository.findAll());
        return "follow_up_report/followupchildren";
    }

    @RequestMapping("/save/{m_id}")
    public String add(Model model, @PathVariable Long m_id, @Valid FollowUpChildren followUpChildren, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("m_id", m_id);
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            followUpChildren.setMotherMasterCode(motherMasterData);
            model.addAttribute("motherid", motherMasterDataRepository.findById(m_id));
            model.addAttribute("yes_no", Yes_No.values());
            return "follow_up_report/followupchildren";
        }
        followUpChildrenRepository.save(followUpChildren);
        return "redirect:/followupmother/details/{m_id}";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model, FollowUpChildren followUpChildren, RedirectAttributes redirectAttrs) {
        followUpChildren = followUpChildrenRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", followUpChildren.motherMasterCode.getId());
        followUpChildrenRepository.delete(id);
        return "redirect:/followupmother/details/{m_id}";
    }
}
