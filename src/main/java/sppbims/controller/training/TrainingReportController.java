/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.training;

import sppbims.model.rehabilitations.GraduateStatus;
import sppbims.model.rehabilitations.HouseName;
import sppbims.model.rehabilitations.TrainingName;
import sppbims.repository.rehabilitations.TrainingNameRepository;
import sppbims.services.training.R_IGA_TrainingService;
import sppbims.services.training.R_Life_Skill_TrainningService;
import sppbims.services.training.R_SwimmingService;
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
@RequestMapping("/trainingreport")
public class TrainingReportController {

    @Autowired
    R_IGA_TrainingService r_IGA_TrainingService;

    @Autowired
    R_Life_Skill_TrainningService r_Life_Skill_TrainningService;

    @Autowired
    TrainingNameRepository trainingNameRepository;

    @Autowired
    R_SwimmingService r_SwimmingService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", "value");
        return "training/training_report_index";
    }

    @RequestMapping("/iga_training_report_by_skill")
    public String igaTrainingBySkill(Model model,
            @RequestParam(name = "trainingName", required = false) TrainingName trainingName,
            @RequestParam(name = "motherMasterCode", required = false) String motherMasterCode,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("trainingName", trainingNameRepository.findAll());
        model.addAttribute("list", r_IGA_TrainingService.iga_training_report_by_skill(motherMasterCode, trainingName, startDate, endDate));
        return "training/iga_training_report_by_skill";
    }

    @RequestMapping("/iga_training_report_by_mother")
    public String iga_training_report_by_mother(Model model,
            @RequestParam(name = "trainingName", required = false) TrainingName trainingName,
            @RequestParam(name = "motherMasterCode", required = false) String motherMasterCode,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("trainingName", trainingNameRepository.findAll());
        model.addAttribute("list", r_IGA_TrainingService.iga_training_report_by_mother(motherMasterCode, trainingName, startDate, endDate));

        return "training/iga_training_report_by_mother";
    }

    @RequestMapping("/livelihood_training_report_by_skill")
    public String livelihood_training_report_by_skill(Model model,
            @RequestParam(name = "trainingName", required = false) TrainingName trainingName,
            @RequestParam(name = "motherMasterCode", required = false) String motherMasterCode,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("trainingName", trainingNameRepository.findAll());
        model.addAttribute("list", r_Life_Skill_TrainningService.livelihood_training_report_by_skill(motherMasterCode, trainingName, startDate, endDate));
        return "training/livelihood_training_report_by_skill";
    }

    @RequestMapping("/livelihood_training_report_by_mother")
    public String livelihood_training_report_by_mother(Model model,
            @RequestParam(name = "trainingName", required = false) TrainingName trainingName,
            @RequestParam(name = "motherMasterCode", required = false) String motherMasterCode,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("trainingName", trainingNameRepository.findAll());
        model.addAttribute("list", r_Life_Skill_TrainningService.livelihood_training_report_by_mother(motherMasterCode, trainingName, startDate, endDate));
        return "training/livelihood_training_report_by_mother";
    }

    @RequestMapping("/swimming_report")
    public String swimming_report(Model model,
            @RequestParam(name = "graduateStatus", required = false) GraduateStatus graduateStatus,
            @RequestParam(name = "childMasterCode", required = false) String childMasterCode,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("graduatestatus", GraduateStatus.values());
        model.addAttribute("list", r_SwimmingService.swimmingCompletedReportList(
                childMasterCode, graduateStatus, startDate, endDate
        ));
        return "training/swimming_report";
    }

}
