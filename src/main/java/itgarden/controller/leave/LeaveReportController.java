/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.leave;

import itgarden.services.leave.LeaveChildService;
import itgarden.services.leave.LeaveMotherService;
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
@RequestMapping("/leavereport")
public class LeaveReportController {

    @Autowired
    LeaveMotherService leaveMotherService;

    @Autowired
    LeaveChildService leaveChildService;

    @RequestMapping("/index")
    public String page(Model model) {

        return "leave/report/index";
    }

    @RequestMapping("/authorized_leave_mother")
    public String authorized_leave_mother(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", leaveMotherService.authorizeleaveMothersList(startDate, endDate));
        return "leave/report/authorized_leave_mother";
    }

    @RequestMapping("/unauthorized_leave_mother")
    public String unauthorized_leave_mother(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", leaveMotherService.unauthorizeLeaveMothersList(startDate, endDate));
        return "leave/report/unauthorized_leave_mother";
    }

    @RequestMapping("/long_leave_mother")
    public String long_leave_mother(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", leaveMotherService.longLeaveMothersList(startDate, endDate));
        return "leave/report/long_leave_mother";
    }

    @RequestMapping("/authorized_leave_child")
    public String authorized_leave_child(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", leaveChildService.authorizedleaveChildList(startDate, endDate));
        return "leave/report/authorized_leave_child";
    }

    @RequestMapping("/unauthorized_leave_child")
    public String unauthorized_leave_child(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
     model.addAttribute("list", leaveChildService.unauthorizedleaveChildList(startDate, endDate));
        return "leave/report/unauthorized_leave_child";
    }

    @RequestMapping("/long_leave_child")
    public String long_leave_child(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
         model.addAttribute("list", leaveChildService.longleaveChildList(startDate, endDate));
        return "leave/report/long_leave_child";
    }

}
