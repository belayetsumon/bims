/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.observation;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.M_ApprovalRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.observation.O_CHealthConditionsRepository;
import sppbims.repository.observation.O_MHealthConditionsRepository;
import sppbims.services.homevisit.MotherMasterDataServices;
import sppbims.services.observation.O_MHealthConditionsService;
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

    @Autowired
    MotherMasterDataServices motherMasterDataServices;

    @Autowired
    O_MHealthConditionsService o_MHealthConditionsService;

    @RequestMapping("/newmother")
    public String newmother(Model model) {
        //   model.addAttribute("list", motherMasterDataRepository.findByOinductionIsNotNullAndOinductionOmHealthConditionsIsNullOrderByIdDesc());

        model.addAttribute("list", motherMasterDataServices.pending_Mother_for_healthcheckup());

        return "homevisit/observation/healthcheckup/newmother";
    }

    @RequestMapping("/mothersearch")
    public String motherSearch(Model model) {
        // model.addAttribute("list", o_MHealthConditionsRepository.findAll());

        model.addAttribute("list", o_MHealthConditionsService.findAllmHealthConditions());

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
