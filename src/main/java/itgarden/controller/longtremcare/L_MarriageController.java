/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.longtremcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.FollowUpType;
import itgarden.model.longtermcare.L_FollowUp;
import itgarden.model.longtermcare.L_Marriage;
import itgarden.repository.longtermcare.L_FollowUpRepository;
import itgarden.repository.longtermcare.L_MarriageRepository;
import jakarta.validation.Valid;
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
@RequestMapping("/marriage")
public class L_MarriageController {

    @Autowired
    L_MarriageRepository l_MarriageRepository;

    @Autowired
    L_FollowUpRepository l_FollowUpRepository;

    @RequestMapping("/create/{mid}")
    public String add(Model model, @PathVariable Long mid, L_Marriage l_Marriage) {

        model.addAttribute("form_title", "Marriage");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(mid);
        l_Marriage.setChildMasterCode(m_Child_info);
        return "longtermcare/marriage";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, L_Marriage l_Marriage) {
        model.addAttribute("form_title", "Marriage");
        model.addAttribute("l_Marriage", l_MarriageRepository.findById(id).orElse(null));
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        l_Marriage.setChildMasterCode(m_Child_info);

        return "longtermcare/marriage";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid L_Marriage l_Marriage, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Save/Update");
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(mid);
            l_Marriage.setChildMasterCode(m_Child_info);
            return "longtermcare/marriage";
        }

        l_MarriageRepository.save(l_Marriage);

        return "redirect:/longtremcare/index/{mid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, L_Marriage l_Marriage, RedirectAttributes redirectAttrs) {

        l_Marriage = l_MarriageRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("mid", l_Marriage.childMasterCode.getId());
        l_MarriageRepository.deleteById(id);
        return "redirect:/longtremcare/index/{mid}";
    }

    @RequestMapping("/marriagefollowupcreate/{mid}")
    public String fosterfollowupcreate(Model model, @PathVariable Long mid, L_FollowUp l_FollowUp) {
        model.addAttribute("form_title", "Marriage Follow Up Add");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(mid);
        l_FollowUp.setChildMasterCode(m_Child_info);
        l_FollowUp.setFollowUpType(FollowUpType.Marrige);
        return "longtermcare/marriagefollowup";

    }

    @RequestMapping("/marriagefollowupedit/{id}")
    public String fosterfollowupedit(Model model, @PathVariable Long id, L_FollowUp l_FollowUp) {
        model.addAttribute("l_FollowUp", l_FollowUpRepository.findById(id).orElse(null));
        model.addAttribute("form_title", "Marriage Follow Up Edit");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        l_FollowUp.setChildMasterCode(m_Child_info);
        l_FollowUp.setFollowUpType(FollowUpType.Marrige);
        return "longtermcare/marriagefollowup";
    }

    @RequestMapping("/marriagefollowupsave/{mid}")
    public String fosterfollowupsave(Model model, @PathVariable Long mid, @Valid L_FollowUp l_FollowUp, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Marriage Follow Up save/update");
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(mid);
            l_FollowUp.setChildMasterCode(m_Child_info);
            l_FollowUp.setFollowUpType(FollowUpType.Marrige);
            return "longtermcare/marriagefollowup";
        }
        l_FollowUpRepository.save(l_FollowUp);
        return "redirect:/longtremcare/index/{mid}";

    }

    @RequestMapping("/marriagefllowupdelete/{id}")
    public String fosterfollowupdelete(Model model, @PathVariable Long id, L_FollowUp l_FollowUp, RedirectAttributes redirectAttrs) {
        l_FollowUp = l_FollowUpRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("mid", l_FollowUp.childMasterCode.getId());
        l_FollowUpRepository.deleteById(id);
        return "redirect:/longtremcare/index/{mid}";
    }

}
