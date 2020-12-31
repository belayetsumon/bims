/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.rehabilitations.R_C_HouseAllocationsRepository;
import itgarden.repository.rehabilitations.R_IGA_TrainingRepository;
import itgarden.repository.rehabilitations.R_Life_Skill_TrainningRepository;
import itgarden.repository.rehabilitations.R_M_HousAllocationRepository;
import itgarden.repository.rehabilitations.R_M_WorkAllocationRepository;
import itgarden.repository.rehabilitations.R_OTRepository;
import itgarden.repository.rehabilitations.R_PTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/rehabilitations")
public class RehabilitationsController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_C_HouseAllocationsRepository r_C_HouseAllocationsRepository;

    @Autowired
    R_M_HousAllocationRepository r_M_HousAllocationRepository;

    @Autowired
    R_M_WorkAllocationRepository r_M_WorkAllocationRepository;

    @Autowired
    R_OTRepository r_OTRepository;

    @Autowired
    R_PTRepository r_PTRepository;

    @Autowired
    R_Life_Skill_TrainningRepository r_Life_Skill_TrainningRepository;

    @Autowired
    R_IGA_TrainingRepository r_IGA_TrainingRepository;

    @RequestMapping("/report")
    public String report(Model model) {
        model.addAttribute("attribute", "value");
        return "rehabilitations/report/reportlist";
    }
    
    @RequestMapping("/lookup")
    public String lookup(Model model) {
        model.addAttribute("attribute", "value");
        return "rehabilitations/lookup/lookup";
    }

    @RequestMapping("/motherhouseallocations")
    public String motherhouseallocations(Model model) {
        model.addAttribute("list", r_M_HousAllocationRepository.findAll());
        return "rehabilitations/report/motherhouseallocations";
    }

    @RequestMapping("/childhouseallocation")
    public String childhouseallocation(Model model) {
        model.addAttribute("list2", r_C_HouseAllocationsRepository.findAll());
        return "rehabilitations/report/childhouseallocation";
    }

    @RequestMapping("/motherworkallocations")
    public String motherworkallocations(Model model) {
        model.addAttribute("list3", r_M_WorkAllocationRepository.findAll());
        return "rehabilitations/report/motherworkallocations";
    }

    @RequestMapping("/lifeskilltraining")
    public String lifeskilltraining(Model model) {
        model.addAttribute("r_Life_Skill_Trainning", r_Life_Skill_TrainningRepository.findAll());
        return "rehabilitations/report/lifeskilltraining";
    }

    @RequestMapping("/igaraining")
    public String igaraining(Model model) {
        model.addAttribute("r_IGA_Training", r_IGA_TrainingRepository.findAll());
        return "rehabilitations/report/igaraining";
    }

    @RequestMapping("/ot")
    public String ot(Model model) {
        model.addAttribute("r_ot", r_OTRepository.findAll());
        return "rehabilitations/report/ot";
    }

    @RequestMapping("/pt")
    public String pt(Model model) {
        model.addAttribute("r_pt", r_PTRepository.findAll());
        return "rehabilitations/report/pt";
    }

}
