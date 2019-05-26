/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.Eligibility;
import itgarden.repository.auth.UsersRepository;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.longtermcare.L_FosteRepository;
import itgarden.repository.longtermcare.L_HigherStudyRepository;
import itgarden.repository.longtermcare.L_JobRepository;
import itgarden.repository.longtermcare.L_MarriageRepository;
import itgarden.repository.observation.O_ChildAdmissionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.repository.reintegration_release.ReleaseChildRepository;
import itgarden.repository.reintegration_release.ReleaseMotherRepository;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/dashboards")
public class DashboardsController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Autowired
    O_ChildAdmissionRepository o_ChildAdmissionRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @Autowired
    L_MarriageRepository l_MarriageRepository;

    @Autowired
    L_HigherStudyRepository l_HigherStudyRepository;

    @Autowired
    L_JobRepository l_JobRepository;

    @Autowired
    L_FosteRepository l_FosteRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Autowired
    ReleaseMotherRepository releaseMotherRepository;

    @Autowired
    ReleaseChildRepository releaseChildRepository;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepository.findByEmail(auth.getName());

        model.addAttribute("userinfo", users);

        model.addAttribute("totalHomeVisit", motherMasterDataRepository.findAll().size());

        model.addAttribute("eligible", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible).size());

        model.addAttribute("noteligible", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Not_Eligible).size());

        model.addAttribute("approve", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve).size());

        model.addAttribute("notapprove", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Not_Approve).size());

        model.addAttribute("eligiblechild", m_Child_infoRepository.findAll().size());

        model.addAttribute("totalobservationmother", motherMasterDataRepository.findByOInductionIsNotNullAndAddmissionIsNull().size());

        model.addAttribute("totalmotheradmission", o_MAddmissionRepository.findAll().size());

        model.addAttribute("totalmothernow", motherMasterDataRepository.findByAddmissionIsNotNullAndReleaseMotherIsNull().size());

        model.addAttribute("totalchildadmission", o_ChildAdmissionRepository.findAll().size());

        model.addAttribute("totalchildnow", m_Child_infoRepository.findByChildAdmissionIsNotNullAndReleaseChildIsNull().size());

        model.addAttribute("longtermcarechildlist", m_Child_infoRepository.findByLfosteIsNotNullAndReleaseChildIsNull().size());

        model.addAttribute("totalhigherstudy", m_Child_infoRepository.findByHigherStudyIsNotNullAndReleaseChildIsNull().size());

        model.addAttribute("totaljob", m_Child_infoRepository.findByJobIsNotNullAndReleaseChildIsNull().size());

        model.addAttribute("totalmarrage", m_Child_infoRepository.findByMarriageIsNotNullAndReleaseChildIsNull().size());

        model.addAttribute("preReintegrationNow", motherMasterDataRepository.findByPreReintegrationVisitIsNullAndReleaseMotherIsNull().size());

        model.addAttribute("totalreintegrationmother", releaseMotherRepository.findAll().size());

        model.addAttribute("totalreintegrationchild", releaseChildRepository.findAll().size());

        model.addAttribute("totalmotherfollowup", motherMasterDataRepository.findByFollowUpMotherIsNotNull().size());

        model.addAttribute("totalchildfollowup", m_Child_infoRepository.findByFollowUpChildrenIsNotNull().size());

        return "dashboards/index";
    }

    
    @RequestMapping(value = {"/age"}, method = RequestMethod.GET)
    @ResponseBody
    public int age(Model model) {

        LocalDate startDate = LocalDate.of(1984, Month.AUGUST, 10);
        LocalDate endDate = LocalDate.of(2015, Month.JANUARY, 10);

        int age;
        age = Period.between(startDate, endDate).getYears();
        // validate inputs ...
        return age;
    }

}
