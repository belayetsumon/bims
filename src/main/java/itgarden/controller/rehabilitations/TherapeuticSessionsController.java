/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.repository.rehabilitations.R_OTRepository;
import itgarden.repository.rehabilitations.R_OtChildRepository;
import itgarden.repository.rehabilitations.R_PTRepository;
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
@RequestMapping("/therapeuticsessions")
public class TherapeuticSessionsController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_OtChildRepository r_OtChildRepository;

    @Autowired
    R_OTRepository r_OTRepository;

    @Autowired
    R_PTRepository r_PTRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Value("${repo_url}")
    public String repo_url;

    @RequestMapping("/mothersearch")
    public String mothersearch(Model model) {

        model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/therapeuticsessions/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String page(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);

        model.addAttribute("r_ot", r_OTRepository.findBymotherMasterCode(motherMasterData));
        
        model.addAttribute("r_OtChild", r_OtChildRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("r_pt", r_PTRepository.findBymotherMasterCode(motherMasterData));

        return "rehabilitations/therapeuticsessions/index";

    }

}
