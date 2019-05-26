/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.longtremcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.FollowUpType;
import itgarden.model.longtermcare.L_FollowUp;
import itgarden.model.longtermcare.L_Job;
import itgarden.repository.longtermcare.L_FollowUpRepository;
import itgarden.repository.longtermcare.L_JobRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/ljob")
public class L_JobController {

    @Autowired
    L_JobRepository l_JobRepository;

    @Autowired
    L_FollowUpRepository l_FollowUpRepository;

    @RequestMapping("/create/{mid}")
    public String add(Model model, @PathVariable Long mid, L_Job l_Job) {

        model.addAttribute("form_title", "Job");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(mid);
        l_Job.setChildMasterCode(m_Child_info);

        return "longtermcare/job";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, L_Job l_Job) {
        model.addAttribute("form_title", "Job");
        model.addAttribute("l_Job", l_JobRepository.findOne(id));
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        l_Job.setChildMasterCode(m_Child_info);
        return "longtermcare/job";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid L_Job l_Job, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Save/Update");
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(mid);
            l_Job.setChildMasterCode(m_Child_info);
            return "longtermcare/job";
        }

        l_JobRepository.save(l_Job);

        return "redirect:/longtremcare/index/{mid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, L_Job l_Job, RedirectAttributes redirectAttrs) {

        l_Job = l_JobRepository.findOne(id);
        redirectAttrs.addAttribute("mid", l_Job.childMasterCode.getId());
        l_JobRepository.delete(id);
        return "redirect:/longtremcare/index/{mid}";
    }

    @RequestMapping("/jobfollowupcreate/{mid}")
    public String fosterfollowupcreate(Model model, @PathVariable Long mid, L_FollowUp l_FollowUp) {
        model.addAttribute("form_title", "Job Follow Up Add");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(mid);
        l_FollowUp.setChildMasterCode(m_Child_info);
        l_FollowUp.setFollowUpType(FollowUpType.Job);
        return "longtermcare/jobfollowup";

    }

    @RequestMapping("/jobfollowupedit/{id}")
    public String fosterfollowupedit(Model model, @PathVariable Long id, L_FollowUp l_FollowUp) {
        model.addAttribute("l_FollowUp", l_FollowUpRepository.findOne(id));
        model.addAttribute("form_title", "JobFollow Up Edit");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        l_FollowUp.setChildMasterCode(m_Child_info);
        l_FollowUp.setFollowUpType(FollowUpType.Job);
        return "longtermcare/jobfollowup";
    }

    @RequestMapping("/jobfollowupsave/{mid}")
    public String fosterfollowupsave(Model model, @PathVariable Long mid, @Valid L_FollowUp l_FollowUp, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Job Follow Up save/update");
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(mid);
            l_FollowUp.setChildMasterCode(m_Child_info);
            l_FollowUp.setFollowUpType(FollowUpType.Job);
            return "longtermcare/jobfollowup";
        }
        l_FollowUpRepository.save(l_FollowUp);
        return "redirect:/longtremcare/index/{mid}";

    }

    @RequestMapping("/jobfllowupdelete/{id}")
    public String fosterfollowupdelete(Model model, @PathVariable Long id, L_FollowUp l_FollowUp, RedirectAttributes redirectAttrs) {
        l_FollowUp = l_FollowUpRepository.findOne(id);
        redirectAttrs.addAttribute("mid", l_FollowUp.childMasterCode.getId());
        l_FollowUpRepository.delete(id);
        return "redirect:/longtremcare/index/{mid}";
    }

}
