/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.cmc;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.rehabilitations.R_M_WorkAllocation;
import sppbims.repository.rehabilitations.R_M_WorkAllocationRepository;
import sppbims.services.cmc.R_M_WorkAllocationService;
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
@RequestMapping("/rmworkallocation")
public class R_M_WorkAllocationController {

    @Autowired
    R_M_WorkAllocationRepository r_M_WorkAllocationRepository;

    @Autowired
    R_M_WorkAllocationService r_M_WorkAllocationService;

    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_M_WorkAllocation r_M_WorkAllocation) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(mid);
        r_M_WorkAllocation.setMotherMasterCode(motherMasterData);
        model.addAttribute("form_title", " Rehab Mother Work Allocation");
        return "rehabilitations/allocations/mworkallocations";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_M_WorkAllocation r_M_WorkAllocation) {
        model.addAttribute("r_M_WorkAllocation", r_M_WorkAllocationRepository.findById(id).orElse(null));
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        r_M_WorkAllocation.setMotherMasterCode(motherMasterData);
        model.addAttribute("form_title", " Rehab Mother Work Allocation Edit");
        return "rehabilitations/allocations/mworkallocations";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_M_WorkAllocation r_M_WorkAllocation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(mid);
            r_M_WorkAllocation.setMotherMasterCode(motherMasterData);
            model.addAttribute("form_title", " Rehab Mother Work Allocationsave/update");
            return "rehabilitations/allocations/mworkallocations";
        }
        r_M_WorkAllocationRepository.save(r_M_WorkAllocation);
        return "redirect:/houseworkallocation/workindex/{mid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_M_WorkAllocation r_M_WorkAllocation, RedirectAttributes redirectAttrs) {

        Optional<R_M_WorkAllocation> optionalr_M_WorkAllocation = r_M_WorkAllocationRepository.findById(id);

        r_M_WorkAllocation = optionalr_M_WorkAllocation.orElse(null);

        redirectAttrs.addAttribute("mid", r_M_WorkAllocation.motherMasterCode.getId());

        r_M_WorkAllocationRepository.deleteById(id);

        return "redirect:/houseworkallocation/workindex/{mid}";
    }

    @RequestMapping("/all_work_allocations_list")
    public String all_mother_house_allocations_list(Model model) {
        model.addAttribute("list", r_M_WorkAllocationService.all_Mother_Work_Location());

        return "rehabilitations/allocations/all_work_allocations_list";
    }
}
