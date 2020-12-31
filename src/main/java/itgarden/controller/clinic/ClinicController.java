/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.clinic;

import itgarden.model.homevisit.Eligibility;
import itgarden.repository.clinic.C_AdmissionRepository;
import itgarden.repository.clinic.C_ReferralRepository;
import itgarden.repository.clinic.C_ReleaseRepository;
import itgarden.repository.clinic.C_visitRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Value("${repo_url}")
    public String repo_url;

    @RequestMapping("/index")
    public String index(Model model) {

        model.addAttribute("list", o_MAddmissionRepository.findAll());
        
        model.addAttribute("repo_url", repo_url);
        
        return "clinic/mothersearch";
    }

    @RequestMapping("/report")
    public String report(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        return "clinic/report/reportlist";
    }

    @RequestMapping("/visitlist")
    public String visitlist(Model model) {
        model.addAttribute("cvisit", c_visitRepository.findAll());
        return "clinic/report/visitlist";
    }

    @RequestMapping("/admissionlist")
    public String admissionlist(Model model) {
        model.addAttribute("c_Admission", c_AdmissionRepository.findAll());
        return "clinic/report/admissionlist";
    }

    @RequestMapping("/referrallist")
    public String referrallist(Model model) {
        model.addAttribute("referral", c_ReferralRepository.findAll());
        return "clinic/report/referrallist";
    }

    @RequestMapping("/releaselist")
    public String releaselist(Model model) {
        model.addAttribute("release", c_ReleaseRepository.findAll());
        return "clinic/report/releaselist";
    }

}
