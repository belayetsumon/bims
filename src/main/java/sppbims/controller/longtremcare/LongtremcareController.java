/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.longtremcare;

import sppbims.model.homevisit.Gender;
import sppbims.model.homevisit.M_Child_info;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.longtermcare.FollowUpType;
import sppbims.repository.follow_up_report.ChildCrisisMeetUpRepository;
import sppbims.repository.homevisit.M_Child_infoRepository;
import sppbims.repository.longtermcare.L_FollowUpRepository;
import sppbims.repository.longtermcare.L_FosteRepository;
import sppbims.repository.longtermcare.L_HigherStudyRepository;
import sppbims.repository.longtermcare.L_JobRepository;
import sppbims.repository.longtermcare.L_MarriageRepository;
import sppbims.repository.reintegration_release.ReleaseChildRepository;
import sppbims.services.longtremcare.L_FollowUpService;
import sppbims.services.longtremcare.L_FosteService;
import sppbims.services.longtremcare.L_HigherStudyService;
import sppbims.services.longtremcare.L_JobService;
import sppbims.services.longtremcare.L_MarriageService;
import sppbims.services.reintegration_release.ReleaseChildService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/longtremcare")
public class LongtremcareController {

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Autowired
    L_FosteRepository l_FosteRepository;

    @Autowired
    L_HigherStudyRepository l_HigherStudyRepository;

    @Autowired
    L_JobRepository l_JobRepository;

    @Autowired
    L_MarriageRepository l_MarriageRepository;

    @Autowired
    L_FollowUpRepository l_FollowUpRepository;

    @Autowired
    ReleaseChildRepository releaseChildRepository;

    @Autowired
    ChildCrisisMeetUpRepository childCrisisMeetUpRepository;

    @Autowired
    L_FollowUpService l_FollowUpService;

    @Autowired
    L_FosteService l_FosteService;

    @Autowired
    L_MarriageService l_MarriageService;

    @Autowired
    L_HigherStudyService l_HigherStudyService;

    @Autowired
    L_JobService l_JobService;

    @Autowired
    ReleaseChildService releaseChildService;

    @RequestMapping("/childindex")
    public String childlist(Model model, M_Child_info m_Child_info) {

        // model.addAttribute("list", m_Child_infoRepository.findAll());
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(176l);
        // id 20200105B0100

        List<M_Child_info> childlist = m_Child_infoRepository.findByMotherMasterCodeAndLfosteIsNullAndReleaseChildIsNull(motherMasterData);
        // List<M_Child_info> childlist = m_Child_infoRepository.findByLfosteIsNullAndReleaseChildIsNull();

        model.addAttribute("list", childlist);

        return "longtermcare/childindex";
    }

    @RequestMapping("/alllongtermcarechild")
    public String alllongtermcarechild(Model model, M_Child_info m_Child_info) {
        // model.addAttribute("list", m_Child_infoRepository.findAll());
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(3l);
        // List<M_Child_info> childlist = m_Child_infoRepository.findByLfosteIsNotNullAndReleaseChildIsNull();
        model.addAttribute("lchildlist", m_Child_infoRepository.findByLfosteIsNotNullAndReleaseChildIsNull());
        // model.addAttribute("list", l_FosteRepository.findAll());
        return "longtermcare/alllongtermcarechild";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {

        model.addAttribute("c_id", id);

        M_Child_info m_Child_info = new M_Child_info();

        m_Child_info.setId(id);

        model.addAttribute("foster", l_FosteRepository.findBychildMasterCode(m_Child_info));

        model.addAttribute("foster_follow_up", l_FollowUpRepository.findByChildMasterCodeAndFollowUpType(m_Child_info, FollowUpType.Foster));

        model.addAttribute("higherstudy", l_HigherStudyRepository.findBychildMasterCode(m_Child_info));

        model.addAttribute("higherstudy_follow_up", l_FollowUpRepository.findByChildMasterCodeAndFollowUpType(m_Child_info, FollowUpType.HigherStudy));

        model.addAttribute("job", l_JobRepository.findBychildMasterCode(m_Child_info));

        model.addAttribute("job_follow_up", l_FollowUpRepository.findByChildMasterCodeAndFollowUpType(m_Child_info, FollowUpType.Job));

        model.addAttribute("marriage", l_MarriageRepository.findBychildMasterCode(m_Child_info));

        model.addAttribute("marriage_follow_up", l_FollowUpRepository.findByChildMasterCodeAndFollowUpType(m_Child_info, FollowUpType.Marrige));

        model.addAttribute("childcrisismeet", childCrisisMeetUpRepository.findBychildMasterCode(m_Child_info));

        //model.addAttribute("release", releaseChildRepository.findByChildMasterCode(m_Child_info));
        return "longtermcare/longtermindex";
    }

    @RequestMapping("/fosterlist")
    public String foster(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("foster", l_FosteService.findl_FosteWithAlias(startDate, endDate, gender));
        return "longtermcare/report/fosterlist";
    }

    @RequestMapping("/marriagelist")
    public String marriage(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("marriage", l_MarriageService.findl_MarriageWithAlias(startDate, endDate, gender));
        return "longtermcare/report/marriagelist";
    }

    @RequestMapping("/higherstudylist")
    public String higherstudy(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("higherstudy", l_HigherStudyService.findl_HigherStudyWithAlias(startDate, endDate, gender));
        return "longtermcare/report/higherstudylist";
    }

    @RequestMapping("/joblist")
    public String job(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("job", l_JobService.findl_JobWithAlias(startDate, endDate, gender));
        return "longtermcare/report/joblist";
    }

    @RequestMapping("/follow_uplist")
    public String follow_up(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("follow_up", l_FollowUpService.findFollowUpsWithAlias(startDate, endDate, gender));
        return "longtermcare/report/follow_uplist";
    }

    @RequestMapping("/releasechild")
    public String release_child(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("list", releaseChildService.getReleaseChildListByMotherLongTremCare(176l, gender, startDate, endDate));
        return "longtermcare/report/releasechild";
    }

    @RequestMapping("/existinglongtremcarechild")
    public String existinglongtremcarechild(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("genderList", Gender.values());

        List<Long> releasedchildList = releaseChildService.longTremCareReleasedChildIdList(176l);

        List<Map<String, Object>> existingchild = l_FosteService.findl_FosteWithAlias(startDate, endDate, gender)
                .stream()
                .filter(child -> !releasedchildList.contains(child.get("childMasterCodeId"))) // Assuming "id" is the key in the map
                .collect(Collectors.toList());

        model.addAttribute("list", existingchild);
        return "longtermcare/report/existinglongtremcarechild";
    }

}
