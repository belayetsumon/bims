/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_Professional_Obserbations_ChildRepository;
import itgarden.repository.observation.O_Professional_Obserbations_MotherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/professionalobserbations")
public class O_ProfessionalObserbationsController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_Professional_Obserbations_ChildRepository o_Professional_Obserbations_ChildRepository;

    @Autowired
    O_Professional_Obserbations_MotherRepository o_Professional_Obserbations_MotherRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @RequestMapping("/newmother")
    public String newmother(Model model) {
        model.addAttribute("list", motherMasterDataRepository.findByOinductionIsNotNullAndOprofessionalObserbationsMotherIsNullOrderByIdDesc());
        return "homevisit/observation/fobservations/newmother";
    }
    
    
     @RequestMapping("/mothersearch")
    public String motherSearch(Model model) {
        model.addAttribute("list", o_Professional_Obserbations_MotherRepository.findAll());
        return "homevisit/observation/fobservations/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {

        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_ProfessionalObserbationsMother", o_Professional_Obserbations_MotherRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_ProfessionalObserbationsChild", o_Professional_Obserbations_ChildRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/fobservations/index";

    }

}
