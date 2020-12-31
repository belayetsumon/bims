/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.school;

import itgarden.repository.school.DiscontinuityRepository;
import itgarden.repository.school.S_RegularAdmissionClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @RequestMapping("/report")
    public String report(Model model) {
        return "school/report/reportlist";
    }

    @RequestMapping("/admitedstudent")
    public String admitedstudent(Model model) {
        model.addAttribute("clildlist", s_RegularAdmissionClassRepository.findAll());
        return "school/report/admitedstudentlist";
    }

    @RequestMapping("/discontinulist")
    public String discontinulist(Model model) {
        model.addAttribute("clildlist", discontinuityRepository.findAll());
        return "school/report/discontinulist";
    }

}
