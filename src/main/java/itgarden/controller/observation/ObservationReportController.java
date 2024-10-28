/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.observation;

import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_CHealthConditionsRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.repository.observation.O_MHealthConditionsRepository;
import itgarden.repository.observation.O_Professional_Obserbations_ChildRepository;
import itgarden.repository.observation.O_Professional_Obserbations_MotherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/observationreport")
public class ObservationReportController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_MHealthConditionsRepository o_MHealthConditionsRepository;

    @Autowired
    O_CHealthConditionsRepository o_CHealthConditionsRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @Autowired
    O_Professional_Obserbations_ChildRepository o_Professional_Obserbations_ChildRepository;

    @Autowired
    O_Professional_Obserbations_MotherRepository o_Professional_Obserbations_MotherRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @RequestMapping("/observation")
    public String observation(Model model) {
        return "homevisit/observation/report/index";
    }

    @RequestMapping("/admissionlist")
    public String admissionlist(Model model) {
        model.addAttribute("o_MAddmission", o_MAddmissionRepository.findAll());
        return "homevisit/report/admissionlist";
    }

    @RequestMapping("/inductionlist")
    public String inductionlist(Model model) {
        model.addAttribute("o_Induction", o_InductionRepository.findAll());
        return "homevisit/report/inductionlist";
    }

    @RequestMapping("/healthcheckupmother")
    public String healthcheckupmother(Model model) {
        model.addAttribute("o_MHConditions", o_MHealthConditionsRepository.findAll());
        return "homevisit/observation/report/motherhealthcheckuplist";
    }

    @RequestMapping("/healthcheckupchild")
    public String healthcheckupchild(Model model) {
        model.addAttribute("o_CHealthConditions", o_CHealthConditionsRepository.findAll());
        return "homevisit/observation/report/childhealthcondition ";
    }

    @RequestMapping("/professionalobservationsmother")
    public String professionalobservationsmother(Model model) {
        model.addAttribute("o_ProfessionalObserbationsMother", o_Professional_Obserbations_MotherRepository.findAll());
        return "homevisit/observation/report/professionalobservationmotherlist";
    }

    @RequestMapping("/professionalobservationschild")
    public String professionalobservationschild(Model model) {
        model.addAttribute("o_ProfessionalObserbationsChild", o_Professional_Obserbations_ChildRepository.findAll());
        return "homevisit/observation/report/professionalobservationchildren";
    }

    @RequestMapping("/psychosocialassessmentneededbeneficiarieslist")
    public String psychosocialassessmentneededbeneficiarieslist(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/therapyneededbeneficiarynumberlist")
    public String therapyneededbeneficiarynumberlist(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/dropoutbeneficiarieslist")
    public String dropoutbeneficiarieslist(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

}
