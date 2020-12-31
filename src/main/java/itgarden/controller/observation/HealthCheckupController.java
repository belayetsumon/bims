/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_CHealthConditionsRepository;
import itgarden.repository.observation.O_MHealthConditionsRepository;
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
@RequestMapping("/healthcheckup")
public class HealthCheckupController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_MHealthConditionsRepository o_MHealthConditionsRepository;

    @Autowired
    O_CHealthConditionsRepository o_CHealthConditionsRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @RequestMapping("/newmother")
    public String newmother(Model model) {
        model.addAttribute("list", motherMasterDataRepository.findByOInductionIsNotNullAndOInductionOmHealthConditionsIsNullOrderByIdDesc());
        return "homevisit/observation/healthcheckup/newmother";
    }

    @RequestMapping("/mothersearch")
    public String motherSearch(Model model) {
        model.addAttribute("list", o_MHealthConditionsRepository.findAll());
        return "homevisit/observation/healthcheckup/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_MHConditions", o_MHealthConditionsRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("o_CHealthConditions", o_CHealthConditionsRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/healthcheckup/index";
    }

}
