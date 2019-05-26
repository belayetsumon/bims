/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MHealthConditionsRepository;
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
@RequestMapping("/inductionoutcome")
public class InductionOutcomeController {

    @Autowired
    O_InductionRepository o_InductionRepository;

    @Autowired
    O_MHealthConditionsRepository o_MHealthConditionsRepository;

    @Autowired
    O_Professional_Obserbations_MotherRepository o_Professional_Obserbations_MotherRepository;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @RequestMapping("/mothersearch")
    public String mothersearch(Model model) {

       // model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));

        model.addAttribute("o_Induction", o_InductionRepository.findAll());

        return "homevisit/observation/inductionoutcome/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String motherbyid(Model model, @PathVariable Long id) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_Induction", o_InductionRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_MHConditions", o_MHealthConditionsRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_ProfessionalObserbationsMother", o_Professional_Obserbations_MotherRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/inductionoutcome/inductionoutcomeindex";
    }

}
