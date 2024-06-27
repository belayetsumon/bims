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
import itgarden.repository.follow_up_report.FollowUpMotherRepsitory;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.longtermcare.L_FosteRepository;
import itgarden.repository.longtermcare.L_HigherStudyRepository;
import itgarden.repository.longtermcare.L_JobRepository;
import itgarden.repository.longtermcare.L_MarriageRepository;
import itgarden.repository.observation.O_ChildAdmissionRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.repository.reintegration_release.ReleaseChildRepository;
import itgarden.repository.reintegration_release.ReleaseMotherRepository;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    O_InductionRepository o_InductionRepository;

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

    @Autowired
    FollowUpMotherRepsitory followUpMotherRepsitory;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepository.findByEmail(auth.getName());

        model.addAttribute("userinfo", users);

        model.addAttribute("totalHomeVisit", motherMasterDataRepository.count());

        model.addAttribute("eligible", motherMasterDataRepository.countByeligibility(Eligibility.Eligible));

        model.addAttribute("noteligible", motherMasterDataRepository.countByeligibility(Eligibility.Not_Eligible));

        model.addAttribute("approve", m_ApprovalRepository.countByDecission(Decision.Approve));

        model.addAttribute("notapprove", m_ApprovalRepository.countByDecission(Decision.Not_Approve));

        model.addAttribute("eligiblechild", m_Child_infoRepository.count());

        model.addAttribute("totalobservationmother", motherMasterDataRepository.countByoinductionIsNotNullAndAddmissionIsNullOrderByIdDesc());

        model.addAttribute("totalmotheradmission", o_MAddmissionRepository.count());

        model.addAttribute("totalmothernow", motherMasterDataRepository.countByAddmissionIsNotNullAndReleaseMotherIsNullOrderByIdDesc());

        model.addAttribute("totalchildadmission", o_ChildAdmissionRepository.count());

        model.addAttribute("totalchildnow", m_Child_infoRepository.countByChildAdmissionIsNotNullAndReleaseChildIsNull());

        model.addAttribute("longtermcarechildlist", m_Child_infoRepository.countByLfosteIsNotNullAndReleaseChildIsNull());

        model.addAttribute("totalhigherstudy", m_Child_infoRepository.countByHigherStudyIsNotNullAndReleaseChildIsNull());

        model.addAttribute("totaljob", m_Child_infoRepository.countByJobIsNotNullAndReleaseChildIsNull());

        model.addAttribute("totalmarrage", m_Child_infoRepository.countByMarriageIsNotNullAndReleaseChildIsNull());

        model.addAttribute("preReintegrationNow", motherMasterDataRepository.countByPreReintegrationVisitIsNullAndReleaseMotherIsNullOrderByIdDesc());

        model.addAttribute("totalreintegrationmother", releaseMotherRepository.count());

        model.addAttribute("totalreintegrationchild", releaseChildRepository.count());

        model.addAttribute("totalmotherfollowup", motherMasterDataRepository.countByFollowUpMotherIsNotNullOrderByIdDesc());

        model.addAttribute("totalchildfollowup", m_Child_infoRepository.countByFollowUpChildrenIsNotNull());

        return "dashboards/index";
    }

    @RequestMapping(value = {"/report"}, method = RequestMethod.GET)
    public String customdashboards(Model model,
            @RequestParam("fdate") String fdate,
            @RequestParam("tdate") String tdate) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersRepository.findByEmail(auth.getName());

        model.addAttribute("userinfo", users);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fromdate = LocalDate.parse(fdate, formatter);

        LocalDate todate = LocalDate.parse(tdate, formatter);

        model.addAttribute("ffdate", fdate);
        model.addAttribute("ttdate", tdate);

        //  model.addAttribute("totalHomeVisit", motherMasterDataRepository.findByCreatedBetween(fromdate, todate).size());
        //  model.addAttribute("eligible", motherMasterDataRepository.findByEligibilityAndCreatedBetween(Eligibility.Eligible, fromdate, todate).size());
        //  model.addAttribute("noteligible", motherMasterDataRepository.findByEligibilityAndCreatedBetween(Eligibility.Not_Eligible, fromdate, todate).size());
        model.addAttribute("approve", m_ApprovalRepository.findByDecissionAndCreatedBetweenOrderByIdDesc(Decision.Approve, fromdate, todate).size());

        model.addAttribute("notapprove", m_ApprovalRepository.findByDecissionAndCreatedBetweenOrderByIdDesc(Decision.Not_Approve, fromdate, todate).size());

        model.addAttribute("eligiblechild", m_Child_infoRepository.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totalobservationmother", o_InductionRepository.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totalmotheradmission", o_MAddmissionRepository.findByCreatedBetween(fromdate, todate).size());

        //  model.addAttribute("totalmothernow", motherMasterDataRepository.findByAddmissionIsNotNullAndReleaseMotherIsNullOrderByIdDesc().size());
        model.addAttribute("totalchildadmission", o_ChildAdmissionRepository.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totalchildnow", m_Child_infoRepository.countByChildAdmissionIsNotNullAndReleaseChildIsNull());

        model.addAttribute("longtermcarechildlist", m_Child_infoRepository.countByLfosteIsNotNullAndReleaseChildIsNull());

        model.addAttribute("totalhigherstudy", l_HigherStudyRepository.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totaljob", l_JobRepository.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totalmarrage", l_MarriageRepository.findByCreatedBetween(fromdate, todate).size());

        // model.addAttribute("preReintegrationNow", motherMasterDataRepository.findByPreReintegrationVisitIsNullAndReleaseMotherIsNullOrderByIdDesc().size());
        model.addAttribute("totalreintegrationmother", releaseMotherRepository.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totalreintegrationchild", releaseChildRepository.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totalmotherfollowup", followUpMotherRepsitory.findByCreatedBetween(fromdate, todate).size());

        model.addAttribute("totalchildfollowup", m_Child_infoRepository.countByFollowUpChildrenIsNotNull());

        return "dashboards/customdashboards";
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
