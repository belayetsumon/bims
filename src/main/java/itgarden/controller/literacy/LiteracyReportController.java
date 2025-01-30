/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.literacy;

import itgarden.model.homevisit.EducationLevel;
import itgarden.model.literacy.ResultEnum;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.literacy.LiteracyHigherEducationAdmissionRepository;
import itgarden.services.literacy.DigitalliteracyService;
import itgarden.services.literacy.LiteracyHigherEducationAdmissionService;
import itgarden.services.literacy.LiteracyNumeracyService;
import itgarden.services.literacy.LiteracyTalkingScienceService;
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
@RequestMapping("/literacyreport")
public class LiteracyReportController {

    @Autowired
    LiteracyHigherEducationAdmissionRepository literacyHigherEducationAdmissionRepository;

    @Autowired
    LiteracyHigherEducationAdmissionService literacyHigherEducationAdmissionService;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    LiteracyNumeracyService literacyNumeracyService;

    @Autowired
    DigitalliteracyService digitalliteracyService;

    @Autowired
    LiteracyTalkingScienceService literacyTalkingScienceService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "literacy/report/index";
    }

    @RequestMapping("/literacy_numeracy_report")
    public String literacy_numeracy_report(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "admissionLevel", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") EducationLevel admissionLevel,
            @RequestParam(name = "result", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") ResultEnum result
    ) {

        model.addAttribute("educationLavel", educationLevelRepository.findAll());
        model.addAttribute("status", ResultEnum.values());

        model.addAttribute("list", literacyNumeracyService.getAllLiteracyNumeracyDataReport(startDate, endDate, result, admissionLevel));
        return "literacy/report/literacy_numeracy_report";
    }

    @RequestMapping("/higher_education_report")
    public String higher_education_report(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "admissionLevel", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") EducationLevel admissionLevel,
            @RequestParam(name = "result", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") ResultEnum result
    ) {

        model.addAttribute("educationLavel", educationLevelRepository.findAll());

        model.addAttribute("status", ResultEnum.values());
        model.addAttribute("list", literacyHigherEducationAdmissionService.getAllHigherEducationAdmissionDataReport(startDate, endDate, result, admissionLevel));
        return "literacy/report/higher_education_report";
    }

    @RequestMapping("/digital_literacy")
    public String digital_literacy(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "result", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") ResultEnum result
    ) {
        model.addAttribute("status", ResultEnum.values());
        model.addAttribute("list", digitalliteracyService.getAllLiteracyDigitalLiteracyDataReport(startDate, endDate, result));
        return "literacy/report/digital_literacy";
    }

    @RequestMapping("/talking_science")
    public String talking_science(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "result", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") ResultEnum result
    ) {
        model.addAttribute("status", ResultEnum.values());
        model.addAttribute("list", literacyTalkingScienceService.getLiteracyTalkingScienceDataReport(startDate, endDate, result));
        return "literacy/report/talking_science";
    }

}
