/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;


import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.repository.rehabilitations.R_C_HouseAllocationsRepository;
import itgarden.repository.rehabilitations.R_M_HousAllocationRepository;
import itgarden.repository.rehabilitations.R_M_WorkAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/houseworkallocation")
public class HouseWorkAllocationController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_C_HouseAllocationsRepository r_C_HouseAllocationsRepository;

    @Autowired
    R_M_HousAllocationRepository r_M_HousAllocationRepository;

    @Autowired
    R_M_WorkAllocationRepository r_M_WorkAllocationRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Value("${repo_url}")
    public String repo_url;

    @RequestMapping("/mothersearch")
    public String mothersearch(Model model) {
        model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/allocations/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("list", r_M_HousAllocationRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("list2", r_C_HouseAllocationsRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("list3", r_M_WorkAllocationRepository.findBymotherMasterCode(motherMasterData));

        return "rehabilitations/allocations/index";
    }

}
