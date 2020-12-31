/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_M_HousAllocation;
import itgarden.repository.rehabilitations.HouseNameRepository;
import itgarden.repository.rehabilitations.R_M_HousAllocationRepository;
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
@RequestMapping("/rmhousallocation")
public class R_M_HousAllocationController {

    @Autowired
    R_M_HousAllocationRepository r_M_HousAllocationRepository;

    @Autowired
    HouseNameRepository houseNameRepository;

    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_M_HousAllocation r_M_HousAllocation) {
        
          MotherMasterData motherMasterData = new MotherMasterData();
          
        motherMasterData.setId(mid);
        
        r_M_HousAllocation.setMotherMasterCode(motherMasterData);
        
        model.addAttribute("form_title", "Mother House Allocation");

        model.addAttribute("houseName", houseNameRepository.findAll());

        return "rehabilitations/allocations/mhouseallocations";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_M_HousAllocation r_M_HousAllocation) {
        model.addAttribute("r_M_HousAllocation", r_M_HousAllocationRepository.findOne(id));
        model.addAttribute("form_title", "Mother House Allocation Edit");
        model.addAttribute("houseName", houseNameRepository.findAll());
       
            return "rehabilitations/allocations/mhouseallocations";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_M_HousAllocation r_M_HousAllocation, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
                  MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(mid);
        r_M_HousAllocation.setMotherMasterCode(motherMasterData);
            model.addAttribute("form_title", "Mother House Allocation save/update");

            model.addAttribute("houseName", houseNameRepository.findAll());

            return "rehabilitations/allocations/mhouseallocations";

        }
        r_M_HousAllocationRepository.save(r_M_HousAllocation);
         return "redirect:/houseworkallocation/index/{mid}";
    }
    
    
    @RequestMapping("/delete/{id}")
     public String delete(Model model, @PathVariable Long id, R_M_HousAllocation r_M_HousAllocation, RedirectAttributes redirectAttrs) {
        r_M_HousAllocation = r_M_HousAllocationRepository.findOne(id);
        redirectAttrs.addAttribute("mid", r_M_HousAllocation.motherMasterCode.getId());
        r_M_HousAllocationRepository.delete(id);
     return "redirect:/houseworkallocation/index/{mid}";
    }

}
