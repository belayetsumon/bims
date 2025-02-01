/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.clinic;

import itgarden.repository.clinic.C_AdmissionRepository;
import itgarden.repository.clinic.C_ReferralRepository;
import itgarden.repository.clinic.C_ReleaseRepository;
import itgarden.repository.clinic.C_visitRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.services.clinic.C_AdmissionService;
import itgarden.services.clinic.C_Child_Health_AwarenessService;
import itgarden.services.clinic.C_Mother_Health_AwarenessService;
import itgarden.services.clinic.C_Mother_SpecialCareService;
import itgarden.services.clinic.C_ReferralService;
import itgarden.services.clinic.C_ReleaseService;
import itgarden.services.clinic.C_SpecialCareService;
import itgarden.services.clinic.C_visitService;
import itgarden.services.observation.O_MAddmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    C_visitRepository c_visitRepository;

    @Autowired
    C_AdmissionRepository c_AdmissionRepository;

    @Autowired

    C_ReferralRepository c_ReferralRepository;

    @Autowired
    C_ReleaseRepository c_ReleaseRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @Autowired
    C_visitService c_visitService;

    @Autowired
    C_AdmissionService c_AdmissionService;

    @Autowired
    C_ReferralService c_ReferralService;
    @Autowired
    C_ReleaseService c_ReleaseService;

    @Autowired
    C_Child_Health_AwarenessService c_Child_Health_AwarenessService;

    @Autowired
    C_Mother_Health_AwarenessService c_Mother_Health_AwarenessService;

    @Autowired
    C_SpecialCareService c_SpecialCareService;

    @Autowired
    C_Mother_SpecialCareService c_Mother_SpecialCareService;

    @Value("${repo_url}")
    public String repo_url;

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("list", o_MAddmissionService.existing_Admited_Mother_List_Exclude_released_mother());

        model.addAttribute("repo_url", repo_url);

        return "clinic/admited_mother_list_exclude_released_mother";
    }

    @RequestMapping("/report")
    public String report(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        return "clinic/report/reportlist";
    }

    @RequestMapping("/visitlist")
    public String visitlist(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("cvisit", c_visitService.all_visit_list_By_Date_report(startDate, endDate));
        return "clinic/report/visitlist";
    }

    @RequestMapping("/admissionlist")
    public String admissionlist(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("c_Admission", c_AdmissionService.find_all_Admission_By_Date_report(startDate, endDate));
        return "clinic/report/admissionlist";
    }

    @RequestMapping("/referrallist")
    public String referrallist(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("referral", c_ReferralService.allReferral_by_date_report(startDate, endDate));
        return "clinic/report/referrallist";
    }

    @RequestMapping("/releaselist")
    public String releaselist(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("release", c_ReleaseService.find_Release_list_date_report(startDate, endDate));
        return "clinic/report/releaselist";
    }

    @RequestMapping("/mother_special_care_list")
    public String mother_special_care_list(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", c_Mother_SpecialCareService.getAllMotherSpecialCareData_report(startDate, endDate));
        return "clinic/report/mother_special_care_list";
    }

    @RequestMapping("/child_special_care_list")
    public String child_special_care_list(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", c_SpecialCareService.getAllChildSpecialCareData_report(startDate, endDate));
        return "clinic/report/child_special_care_list";
    }

    @RequestMapping("/mother_health_awareness_list")
    public String mother_health_awareness_list(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", c_Mother_Health_AwarenessService.getAllMotherHealthAwarenessData_report(startDate, endDate));
        return "clinic/report/mother_health_awareness_list";
    }

    @RequestMapping("/child_health_awareness_list")
    public String child_health_awareness_list(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", c_Child_Health_AwarenessService.getAllChildHealthAwarenessData_report(startDate, endDate));
        return "clinic/report/child_health_awareness_list";
    }

}
