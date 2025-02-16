/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.reintegration_release;

import sppbims.model.homevisit.Gender;
import sppbims.model.reintegration_release.ReleaseChild;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.reintegration_release.ReleaseChildRepository;
import sppbims.repository.reintegration_release.ReleaseMotherRepository;
import sppbims.services.observation.O_MAddmissionService;
import sppbims.services.pre_reintegration_visit.PreReintegrationVisitService;
import sppbims.services.reintegration_checklist.ReintegrationCheckListService;
import sppbims.services.reintegration_release.ReleaseChildService;
import sppbims.services.reintegration_release.ReleaseMotherService;
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
@RequestMapping("/reintegrationreport")
public class Rreintegration_Report_Controller {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    ReleaseMotherRepository releaseMotherRepository;

    @Autowired
    ReleaseChildRepository releaseChildRepository;

    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @Autowired
    PreReintegrationVisitService preReintegrationVisitService;

    @Autowired
    ReintegrationCheckListService reintegrationCheckListService;

    @Autowired
    ReleaseMotherService releaseMotherService;

    @Autowired
    ReleaseChildService releaseChildService;

    @RequestMapping("/report")
    public String index(Model model) {

        model.addAttribute("attribute", "value");

        return "reintegration/report/reportlist";
    }

    @RequestMapping("/motherreport")
    public String motherreport(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String postVisitstartDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String postVisitendDate
    ) {
        model.addAttribute("list", releaseMotherService.getReleaseMotherListwithsearch(startDate, endDate, postVisitstartDate, postVisitendDate));
        return "reintegration/report/mother_report";
    }

    @RequestMapping("/childreport")
    public String childreport(Model model, ReleaseChild releaseChild,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("list", releaseChildService.getReleaseChildListwithsearch(gender, startDate, endDate));
        return "reintegration/report/child_report";
    }

}
