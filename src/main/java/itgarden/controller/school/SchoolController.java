/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.school;

import itgarden.repository.school.DiscontinuityRepository;
import itgarden.repository.school.S_RegularAdmissionClassRepository;
import itgarden.services.school.DiscontinuityService;
import itgarden.services.school.S_RegularAdmissionClassService;
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
@RequestMapping("school")
public class SchoolController {

    @Autowired
    S_RegularAdmissionClassRepository s_RegularAdmissionClassRepository;

    @Autowired
    DiscontinuityRepository discontinuityRepository;

    @Autowired
    S_RegularAdmissionClassService s_RegularAdmissionClassService;

    @Autowired
    DiscontinuityService discontinuityService;

    @RequestMapping("/report")
    public String report(Model model) {
        return "school/report/reportlist";
    }

    @RequestMapping("/admitedstudent")
    public String admitedstudent(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {

        model.addAttribute("clildlist", s_RegularAdmissionClassService.alladmitedchildList(startDate, endDate));
        return "school/report/admitedstudentlist";
    }

    @RequestMapping("/discontinulist")
    public String discontinulist(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("clildlist", discontinuityService.allDiscontinuitiesListReport(startDate, endDate));
        return "school/report/discontinulist";
    }

}
