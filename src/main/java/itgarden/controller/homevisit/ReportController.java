/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.Yes_No;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.homevisit.EthinicIdentityRepository;
import itgarden.repository.homevisit.HusbandsStatusRepository;
import itgarden.repository.homevisit.ImmunizationRepository;
import itgarden.repository.homevisit.M_AddressRepository;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.MaritalStatusRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.homevisit.OccupationRepository;
import itgarden.repository.homevisit.PhysicalStatusRepository;
import itgarden.repository.homevisit.ReasonsRepository;
import itgarden.repository.homevisit.RelationsRepository;
import itgarden.repository.homevisit.ReligionRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import java.util.Date;
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

@RequestMapping("/homevisitreport")

public class ReportController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;
    @Autowired
    ReasonsRepository reasonsRepository;
    @Autowired
    ReligionRepository religionRepository;
    @Autowired
    MaritalStatusRepository maritalStatusRepository;
    @Autowired
    HusbandsStatusRepository husbandsStatusRepository;
    @Autowired
    RelationsRepository relationsRepository;
    @Autowired
    EthinicIdentityRepository ethinicIdentityRepository;
    @Autowired
    EducationLevelRepository educationLevelRepository;
    @Autowired
    EducationTypeRepository educationTypeRepository;
    @Autowired
    OccupationRepository occupationRepository;
    @Autowired
    PhysicalStatusRepository physicalStatusRepository;
    @Autowired
    ImmunizationRepository immunizationRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Autowired
    M_AddressRepository m_AddressRepository;

    @RequestMapping("/index")
    public String index(Model model) {

        return "homevisit/report/index";

    }

    @RequestMapping("/observation")
    public String observation(Model model) {
        return "homevisit/observation/report/index";

    }

    @RequestMapping("/homevisitreportsearch")
    public String homeVisitReportSearch(Model model) {

        model.addAttribute("resons", reasonsRepository.findAll());

        model.addAttribute("religion", religionRepository.findAll());

        model.addAttribute("maritalStatus", maritalStatusRepository.findAll());

        model.addAttribute("husbandsStatus", husbandsStatusRepository.findAll());

        model.addAttribute("relationWithPfm", relationsRepository.findAll());

        model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("occupation", occupationRepository.findAll());

        model.addAttribute("physicalStatus", physicalStatusRepository.findAll());

        model.addAttribute("immunization", immunizationRepository.findAll());

        model.addAttribute("eligibility", Eligibility.values());

        model.addAttribute("list", motherMasterDataRepository.findAll());
        
            model.addAttribute("yesNo", Yes_No.values());

        return "homevisit/report/homevisitreportsearch";

    }

    @RequestMapping("/homevisitreportsearchresult")
    public String homeVisitReportSearchResult(
            Model model,
           @RequestParam(name = "name", required = false) String name,
           @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
           @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate
            ) {
        model.addAttribute("list", motherMasterDataRepository.findAll());
        return "homevisit/report/homevisitreportsearchresult";

    }
    
    
    
    

    @RequestMapping("/eligiblemothermlist")
    public String eligibleMotherList(Model model) {
        model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        return "homevisit/report/eligiblemothermlist";
    }

    @RequestMapping("/noteligiblemotherlist")
    public String notEligibleMotherList(Model model) {
        model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Not_Eligible));
        return "homevisit/report/noteligiblemotherlist";
    }

    @RequestMapping("/approvemotherlist")
    public String approveMotherList(Model model) {
        model.addAttribute("approval", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve));
        return "homevisit/report/approvemotherlist";
    }

    @RequestMapping("/notapprovemotherlist")
    public String notapproveMotherList(Model model) {
        model.addAttribute("approval", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Not_Approve));
        return "homevisit/report/notapprovemotherlist";

    }

    @RequestMapping("/approvewithinstructionsmotherlist")
    public String approveWithInstructionsMotherList(Model model) {
        model.addAttribute("approval", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve_With_Instruction));
        return "homevisit/report/approvwithinstructionemotherlist";

    }

    @RequestMapping("/addresslist")
    public String address(Model model) {
        model.addAttribute("addres", m_AddressRepository.findAll());
        return "homevisit/report/addresslist";

    }

    @RequestMapping("/inductionlist")
    public String inductionlist(Model model) {
        model.addAttribute("o_Induction", o_InductionRepository.findAll());
        return "homevisit/report/inductionlist";

    }

    @RequestMapping("/admissionlist")
    public String admissionlist(Model model) {
        model.addAttribute("o_MAddmission", o_MAddmissionRepository.findAll());
        return "homevisit/report/admissionlist";

    }

}
