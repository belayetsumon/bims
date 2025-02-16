/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.psychological_and_therapy;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.M_ApprovalRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.observation.O_Professional_Obserbations_ChildRepository;
import sppbims.repository.observation.O_Professional_Obserbations_MotherRepository;
import sppbims.services.homevisit.MotherMasterDataServices;
import sppbims.services.psychology.ProfessionalObserbationsService;
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

    @Autowired
    MotherMasterDataServices motherMasterDataServices;

    @Autowired
    ProfessionalObserbationsService professionalObserbationsService;

    @RequestMapping("/pendind_mother_for_professional_obserbations")
    public String newmother(Model model) {
        model.addAttribute("list", motherMasterDataServices.pending_Mother_List_For_Professional_Observations());
        // model.addAttribute("list", motherMasterDataRepository.findByOinductionIsNotNullAndOprofessionalObserbationsMotherIsNullOrderByIdDesc());
        return "homevisit/observation/fobservations/newmother";
    }

    @RequestMapping("/professionalObserbationsCompletedMotherList")
    public String motherSearch(Model model) {
        model.addAttribute("list", professionalObserbationsService.professional_Obserbations_Completed_Mother_List());
        return "psychological_and_therapy/professionalObserbationsCompletedMotherList";
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
