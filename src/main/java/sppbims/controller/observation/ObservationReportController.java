/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.observation;

import sppbims.model.homevisit.Gender;
import sppbims.model.homevisit.Yes_No;
import sppbims.repository.homevisit.M_ApprovalRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.observation.O_CHealthConditionsRepository;
import sppbims.repository.observation.O_InductionRepository;
import sppbims.repository.observation.O_MAddmissionRepository;
import sppbims.repository.observation.O_MHealthConditionsRepository;
import sppbims.services.observation.O_CHealthConditionsService;
import sppbims.services.observation.O_ChildAdmissionService;
import sppbims.services.observation.O_InductionService;
import sppbims.services.observation.O_MAddmissionService;
import sppbims.services.observation.O_MHealthConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    O_MAddmissionRepository o_MAddmissionRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @Autowired
    O_ChildAdmissionService o_ChildAdmissionService;

    @Autowired
    O_MHealthConditionsService o_MHealthConditionsService;

    @Autowired
    O_CHealthConditionsService o_CHealthConditionsService;

    @Autowired
    O_InductionService o_InductionService;

    @RequestMapping("/observation")
    public String observation(Model model) {
        return "homevisit/observation/report/index";
    }

    @RequestMapping("/admissionlist")
    public String admissionlist(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        // model.addAttribute("o_MAddmission", o_MAddmissionRepository.findAll());    
        model.addAttribute("list", o_MAddmissionService.allAdmitedMotherPeriodicReportList(startDate, endDate));

        return "homevisit/report/admissionlist";
    }

    @RequestMapping("/childadmissionlist")
    public String childadmissionlist(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        // model.addAttribute("o_MAddmission", o_MAddmissionRepository.findAll()); 
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("list", o_ChildAdmissionService.allAdmitedChildPeriodicReportList(
                startDate,
                endDate,
                gender
        ));

        return "homevisit/observation/report/child_admissionlist";
    }

    @RequestMapping("/inductionlist")
    public String inductionlist(Model model) {

        model.addAttribute("o_Induction", o_InductionService.getAllInductions());
        //   model.addAttribute("o_Induction", o_InductionRepository.findAll());
        return "homevisit/observation/report/inductionlist";
    }

    @RequestMapping("/healthcheckupmother")
    public String healthcheckupmother(Model model,
            @RequestParam(name = "bloodPressure", required = false) Yes_No bloodPressure,
            @RequestParam(name = "eyeProblem", required = false) Yes_No eyeProblem,
            @RequestParam(name = "earProblem", required = false) Yes_No earProblem,
            @RequestParam(name = "gynologicalProblem", required = false) Yes_No gynologicalProblem,
            @RequestParam(name = "tt", required = false) Yes_No tt,
            @RequestParam(name = "heart_disease", required = false) Yes_No heart_disease,
            @RequestParam(name = "diabetes", required = false) Yes_No diabetes,
            @RequestParam(name = "bonyFracture", required = false) Yes_No bonyFracture,
            @RequestParam(name = "neurologicalDisease", required = false) Yes_No neurologicalDisease,
            @RequestParam(name = "resporatoryProblem", required = false) Yes_No resporatoryProblem,
            @RequestParam(name = "uti", required = false) Yes_No uti
    ) {
        // model.addAttribute("o_MHConditions", o_MHealthConditionsRepository.findAll());

        model.addAttribute("o_MHConditions", o_MHealthConditionsService.motherHelthConditionsReport(
                bloodPressure,
                eyeProblem,
                earProblem,
                gynologicalProblem,
                tt,
                heart_disease,
                diabetes,
                bonyFracture,
                neurologicalDisease,
                resporatoryProblem,
                uti
        ));
        model.addAttribute("yesno", Yes_No.values());
        return "homevisit/observation/report/motherhealthcheckuplist";
    }

    @RequestMapping("/healthcheckupchild")
    public String healthcheckupchild(Model model,
            @RequestParam(name = "bloodPressure", required = false) Yes_No bloodPressure,
            @RequestParam(name = "eyeProblem", required = false) Yes_No eyeProblem,
            @RequestParam(name = "earProblem", required = false) Yes_No earProblem,
            @RequestParam(name = "gynologicalProblem", required = false) Yes_No gynologicalProblem,
            @RequestParam(name = "tt", required = false) Yes_No tt,
            @RequestParam(name = "heart_disease", required = false) Yes_No heart_disease,
            @RequestParam(name = "diabetes", required = false) Yes_No diabetes,
            @RequestParam(name = "bonyFracture", required = false) Yes_No bonyFracture,
            @RequestParam(name = "neurologicalDisease", required = false) Yes_No neurologicalDisease,
            @RequestParam(name = "resporatoryProblem", required = false) Yes_No resporatoryProblem,
            @RequestParam(name = "uti", required = false) Yes_No uti
    ) {
        model.addAttribute("yesno", Yes_No.values());
        model.addAttribute("o_CHealthConditions", o_CHealthConditionsService.childHelthConditionsReport(bloodPressure, eyeProblem, earProblem, tt, heart_disease, diabetes, bonyFracture, neurologicalDisease, resporatoryProblem, uti));
        return "homevisit/observation/report/childhealthcondition ";
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
