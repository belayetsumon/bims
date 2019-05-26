/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.longtremcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.FollowUpType;
import itgarden.model.longtermcare.L_FollowUp;
import itgarden.model.longtermcare.L_HigherStudy;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.longtermcare.L_FollowUpRepository;
import itgarden.repository.longtermcare.L_HigherStudyRepository;
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
@RequestMapping("/higherstudy")
public class L_HigherStudyController {

    @Autowired
    L_HigherStudyRepository l_HigherStudyRepository;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    EducationTypeRepository educationTypeRepository;

    @Autowired
    L_FollowUpRepository l_FollowUpRepository;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/create/{mid}")
    public String add(Model model, @PathVariable Long mid, L_HigherStudy l_HigherStudy) {

        model.addAttribute("form_title", "Higher Study");

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        M_Child_info m_Child_info = new M_Child_info();

        m_Child_info.setId(mid);

        l_HigherStudy.setChildMasterCode(m_Child_info);

        return "longtermcare/higherstudy";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, L_HigherStudy l_HigherStudy) {

        model.addAttribute("form_title", "Higher Study");

        model.addAttribute("l_HigherStudy", l_HigherStudyRepository.findOne(id));

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        M_Child_info m_Child_info = new M_Child_info();

        m_Child_info.setId(id);

        l_HigherStudy.setChildMasterCode(m_Child_info);

        return "longtermcare/higherstudy";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid L_HigherStudy l_HigherStudy, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Save/Update");
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(mid);
            l_HigherStudy.setChildMasterCode(m_Child_info);
            model.addAttribute("educationType", educationTypeRepository.findAll());
            model.addAttribute("educationLevel", educationLevelRepository.findAll());
            return "longtermcare/higherstudy";
        }

        l_HigherStudyRepository.save(l_HigherStudy);
        return "redirect:/longtremcare/index/{mid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, L_HigherStudy l_HigherStudy, RedirectAttributes redirectAttrs) {

        l_HigherStudy = l_HigherStudyRepository.findOne(id);
        redirectAttrs.addAttribute("mid", l_HigherStudy.childMasterCode.getId());
        l_HigherStudyRepository.delete(id);
        return "redirect:/longtremcare/index/{mid}";
    }

    @RequestMapping("/higherstudyfollowupcreate/{mid}")
    public String fosterfollowupcreate(Model model, @PathVariable Long mid, L_FollowUp l_FollowUp) {
        model.addAttribute("form_title", "Higher Study Follow Up Add");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(mid);
        l_FollowUp.setChildMasterCode(m_Child_info);
        l_FollowUp.setFollowUpType(FollowUpType.HigherStudy);
        return "longtermcare/higherstudyfollowup";

    }

    @RequestMapping("/higherstudyfollowupedit/{id}")
    public String fosterfollowupedit(Model model, @PathVariable Long id, L_FollowUp l_FollowUp) {
        model.addAttribute("l_FollowUp", l_FollowUpRepository.findOne(id));
        model.addAttribute("form_title", "Higher study Follow Up Edit");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        l_FollowUp.setChildMasterCode(m_Child_info);
        l_FollowUp.setFollowUpType(FollowUpType.HigherStudy);
        return "longtermcare/higherstudyfollowup";
    }

    @RequestMapping("/higherstudyfollowupsave/{mid}")
    public String fosterfollowupsave(Model model, @PathVariable Long mid, @Valid L_FollowUp l_FollowUp, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Foster Follow Up save/update");
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(mid);
            l_FollowUp.setChildMasterCode(m_Child_info);
            l_FollowUp.setFollowUpType(FollowUpType.HigherStudy);
            return "longtermcare/higherstudyfollowup";

        }

        l_FollowUpRepository.save(l_FollowUp);
        return "redirect:/longtremcare/index/{mid}";

    }

    @RequestMapping("/higherstudyfllowupdelete/{id}")
    public String fosterfollowupdelete(Model model, @PathVariable Long id, L_FollowUp l_FollowUp, RedirectAttributes redirectAttrs) {
        l_FollowUp = l_FollowUpRepository.findOne(id);
        redirectAttrs.addAttribute("mid", l_FollowUp.childMasterCode.getId());
        l_FollowUpRepository.delete(id);
        return "redirect:/longtremcare/index/{mid}";
    }

}
