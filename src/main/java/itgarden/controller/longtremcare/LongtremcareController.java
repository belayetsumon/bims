/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.longtremcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.longtermcare.FollowUpType;
import itgarden.repository.follow_up_report.ChildCrisisMeetUpRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.longtermcare.L_FollowUpRepository;
import itgarden.repository.longtermcare.L_FosteRepository;
import itgarden.repository.longtermcare.L_HigherStudyRepository;
import itgarden.repository.longtermcare.L_JobRepository;
import itgarden.repository.longtermcare.L_MarriageRepository;
import itgarden.repository.reintegration_release.ReleaseChildRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    ChildCrisisMeetUpRepository  childCrisisMeetUpRepository;
    
    
    
    @RequestMapping("/childindex")
    public String childlist(Model model, M_Child_info m_Child_info) {

        // model.addAttribute("list", m_Child_infoRepository.findAll());
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(176l);
        // id 20200105B0100

        List<M_Child_info> childlist = m_Child_infoRepository.findByMotherMasterCodeAndLfosteIsNullAndReleaseChildIsNull(motherMasterData);

        model.addAttribute("list", childlist);

        return "longtermcare/childindex";
    }

    @RequestMapping("/alllongtermcarechild")
    public String alllongtermcarechild(Model model, M_Child_info m_Child_info) {
        // model.addAttribute("list", m_Child_infoRepository.findAll());
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(3l);
        //List<M_Child_info> childlist = m_Child_infoRepository.findByLfosteIsNotNullAndReleaseChildIsNull();
        model.addAttribute("lchildlist",  m_Child_infoRepository.findByLfosteIsNotNullAndReleaseChildIsNull());
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
    public String foster(Model model) {
        model.addAttribute("foster", l_FosteRepository.findAll());
        return "longtermcare/report/fosterlist";
    }

    @RequestMapping("/marriagelist")
    public String marriage(Model model) {
        model.addAttribute("marriage", l_MarriageRepository.findAll());
        return "longtermcare/report/marriagelist";
    }

    @RequestMapping("/higherstudylist")
    public String higherstudy(Model model) {
        model.addAttribute("higherstudy", l_HigherStudyRepository.findAll());
        return "longtermcare/report/higherstudylist";
    }

    @RequestMapping("/joblist")
    public String job(Model model) {
        model.addAttribute("job", l_JobRepository.findAll());
        return "longtermcare/report/joblist";
    }

    @RequestMapping("/follow_uplist")
    public String follow_up(Model model) {
        model.addAttribute("follow_up", l_FollowUpRepository.findAll());
        return "longtermcare/report/follow_uplist";
    }

}
