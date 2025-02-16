/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.psychological_and_therapy;

import sppbims.model.homevisit.Yes_No;
import sppbims.repository.observation.O_Professional_Obserbations_ChildRepository;
import sppbims.repository.observation.O_Professional_Obserbations_MotherRepository;
import sppbims.services.psychology.O_Professional_Obserbations_Child_Service;
import sppbims.services.psychology.ProfessionalObserbationsService;
import sppbims.services.psychology.R_OtChildService;
import sppbims.services.psychology.R_OtMotherService;
import sppbims.services.psychology.R_PTService;
import sppbims.services.psychology.R_PsychologyChildService;
import sppbims.services.psychology.R_PsychologyMotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/sychological_and_therapy_report")
public class Psychological_and_therapyController {

    @Autowired
    O_Professional_Obserbations_ChildRepository o_Professional_Obserbations_ChildRepository;

    @Autowired
    O_Professional_Obserbations_MotherRepository o_Professional_Obserbations_MotherRepository;

    @Autowired
    ProfessionalObserbationsService professionalObserbationsService;

    @Autowired
    O_Professional_Obserbations_Child_Service o_Professional_Obserbations_Child_Service;

    @Autowired
    R_PsychologyMotherService r_PsychologyMotherService;
    @Autowired
    R_PsychologyChildService r_PsychologyChildService;

    @Autowired
    R_PTService r_PTService;

    @Autowired
    R_OtMotherService r_OtMotherService;
    @Autowired
    R_OtChildService r_OtChildService;

    @RequestMapping("/index")
    public String observation(Model model) {
        return "psychological_and_therapy/report/index";
    }

    @RequestMapping("/professionalobservationsmother")
    public String professionalobservationsmother(Model model) {
        model.addAttribute("o_ProfessionalObserbationsMother", o_Professional_Obserbations_MotherRepository.findAll());
        return "psychological_and_therapy/report/professional_observation_mother_list_report";
    }

    @RequestMapping("/professionalObserbationsCompletedMotherListReport")
    public String motherSearch(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "physicalDisability", required = false) Yes_No physicalDisability,
            @RequestParam(name = "therapy", required = false) Yes_No therapy,
            @RequestParam(name = "adlPerformance", required = false) Yes_No adlPerformance,
            @RequestParam(name = "psychocialAssesmentNeeds", required = false) Yes_No psychocialAssesmentNeeds
    ) {
        model.addAttribute("yesno", Yes_No.values());
        model.addAttribute("list", professionalObserbationsService.professional_Obserbations_Completed_Mother_List_Report(startDate, endDate, physicalDisability, therapy, adlPerformance, psychocialAssesmentNeeds));
        return "psychological_and_therapy/report/professional_observation_mother_list_report";
    }

    @RequestMapping("/professionalobservationschild")
    public String professionalobservationschild(Model model) {
        model.addAttribute("o_ProfessionalObserbationsChild", o_Professional_Obserbations_ChildRepository.findAll());
        return "psychological_and_therapy/report/professional_observation_children_list_report";
    }

    @RequestMapping("/professionalObserbationsCompletedChildListReport")
    public String professionalObserbationsCompletedChildListReport(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "physicalDisability", required = false) Yes_No physicalDisability,
            @RequestParam(name = "therapy", required = false) Yes_No therapy,
            @RequestParam(name = "adlPerformance", required = false) Yes_No adlPerformance,
            @RequestParam(name = "psychocialAssesmentNeeds", required = false) Yes_No psychocialAssesmentNeeds
    ) {
        model.addAttribute("yesno", Yes_No.values());
        model.addAttribute("list", o_Professional_Obserbations_Child_Service.professional_Obserbations_Completed_Child_List_Report(startDate, endDate, physicalDisability, therapy, adlPerformance, psychocialAssesmentNeeds));
        return "psychological_and_therapy/report/professional_observation_children_list_report";
    }

    @RequestMapping("/mother_occupational_therapy_session_report")
    public String mother_occupational_therapy_session_report(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", r_OtMotherService.motherOtCompletedListReport(startDate, endDate));
        return "psychological_and_therapy/report/mother_occupational_therapy_session_report";
    }

    @RequestMapping("/child_occupational_therapy_session_report")
    public String child_occupational_therapy_session_report(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {

        model.addAttribute("list", r_OtChildService.childOtCompletedListReport(startDate, endDate));
        return "psychological_and_therapy/report/child_occupational_therapy_session_report";
    }

    @RequestMapping("/mother_physical_therapy_session_report")
    public String mother_physical_therapy_session_report(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", r_PTService.ptcomplete_mother_list(startDate, endDate));
        return "psychological_and_therapy/report/mother_physical_therapy_session_report";
    }

    @RequestMapping("/mother_psychology_session_report")
    public String mother_psychology_session_report(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {

        model.addAttribute("list", r_PsychologyMotherService.psychologyCompletedMotherListReport(startDate, endDate));
        return "psychological_and_therapy/report/mother_psychology_session_report";
    }

    @RequestMapping("/child_psychology_session_report")
    public String child_psychology_session_report(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", r_PsychologyChildService.psychologyCompletedChildListReport(startDate, endDate));
        return "psychological_and_therapy/report/child_psychology_session_report";
    }

}
