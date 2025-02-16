/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.follow_up_report;

import sppbims.model.follow_up_report.FollowUpChildren;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Yes_No;
import sppbims.repository.follow_up_report.FollowUpChildrenRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.services.FollowUp.FollowUpChildrenService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    FollowUpChildrenService followUpChildrenService;

    @RequestMapping("/add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, FollowUpChildren followUpChildren) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        followUpChildren.setMotherMasterCode(motherMasterData);

        //model.addAttribute("mother", releaseMotherRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("motherid", motherMasterDataRepository.findById(m_id).orElse(null));
        model.addAttribute("yes_no", Yes_No.values());

        // model.addAttribute("childList", releaseChildRepository.findAll());
        return "follow_up_report/followupchildren";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FollowUpChildren followUpChildren) {
        Optional<FollowUpChildren> followUpChildrenopt = followUpChildrenRepository.findById(id);

        followUpChildren = followUpChildrenopt.orElse(null);

        model.addAttribute("followUpChildren", followUpChildren);

        //model.addAttribute("mother", releaseMotherRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("motherid", motherMasterDataRepository.findById(followUpChildren.getMotherMasterCode().getId()).orElse(null));
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
        followUpChildren = followUpChildrenRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", followUpChildren.motherMasterCode.getId());
        followUpChildrenRepository.deleteById(id);
        return "redirect:/followupmother/details/{m_id}";
    }

    @RequestMapping(value = "/list")
    public String list(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", followUpChildrenService.find_All_Child_follow_up(startDate, endDate));
        return "follow_up_report/child_follow_up_list";
    }
}
