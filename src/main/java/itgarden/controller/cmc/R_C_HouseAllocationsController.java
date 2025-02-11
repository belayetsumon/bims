/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.cmc;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_C_HouseAllocations;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.rehabilitations.HouseNameRepository;
import itgarden.repository.rehabilitations.R_C_HouseAllocationsRepository;
import itgarden.services.cmc.R_C_HouseAllocationsService;
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
@RequestMapping("/chouseallocation")
public class R_C_HouseAllocationsController {

    @Autowired
    R_C_HouseAllocationsRepository r_C_HouseAllocationsRepository;

    @Autowired
    HouseNameRepository houseNameRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Autowired
    R_C_HouseAllocationsService r_C_HouseAllocationsService;

    @RequestMapping("/create/{m_id}")
    public String create(Model model, @PathVariable Long m_id, R_C_HouseAllocations r_C_HouseAllocations) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        r_C_HouseAllocations.setMotherMasterCode(motherMasterData);
        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("form_title", "Child House Allocation");
        model.addAttribute("houseName", houseNameRepository.findAll());
        return "rehabilitations/allocations/chouseallocations";

    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_C_HouseAllocations r_C_HouseAllocations) {
        model.addAttribute("r_C_HouseAllocations", r_C_HouseAllocationsRepository.findById(id).orElse(null));
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("form_title", "Child House Allocation Edit");
        model.addAttribute("houseName", houseNameRepository.findAll());
        return "rehabilitations/allocations/chouseallocations";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid R_C_HouseAllocations r_C_HouseAllocations, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            r_C_HouseAllocations.setMotherMasterCode(motherMasterData);
            model.addAttribute("form_title", " Child House Allocation save/update");
            model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
            model.addAttribute("houseName", houseNameRepository.findAll());
            return "rehabilitations/allocations/chouseallocations";
        }
        r_C_HouseAllocationsRepository.save(r_C_HouseAllocations);
        return "redirect:/houseworkallocation/index/{m_id}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_C_HouseAllocations r_C_HouseAllocations, RedirectAttributes redirectAttrs) {

        Optional<R_C_HouseAllocations> optionalR_C_HouseAllocations = r_C_HouseAllocationsRepository.findById(id);

        r_C_HouseAllocations = optionalR_C_HouseAllocations.orElse(null);

        redirectAttrs.addAttribute("m_id", r_C_HouseAllocations.motherMasterCode.getId());

        r_C_HouseAllocationsRepository.findById(id);
        return "redirect:/houseworkallocation/index/{m_id}";
    }

    @RequestMapping("/all_child_house_allocations_list")
    public String all_mother_house_allocations_list(Model model) {
        model.addAttribute("list", r_C_HouseAllocationsService.all_childHouse_Allocation_List());
        return "rehabilitations/allocations/all_child_house_allocations_list";
    }

}
