/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.longtremcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.FollowUpType;
import itgarden.model.longtermcare.L_FollowUp;
import itgarden.model.longtermcare.L_Foste;
import itgarden.repository.longtermcare.L_FollowUpRepository;
import itgarden.repository.longtermcare.L_FosteRepository;
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
@RequestMapping("/foster")
public class L_FosteController {

    @Autowired
    L_FosteRepository l_FosteRepository;

    @Autowired
    L_FollowUpRepository l_FollowUpRepository;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @RequestMapping("/create/{cid}")
    public String add(Model model, @PathVariable Long cid, L_Foste l_Foste) {
        model.addAttribute("form_title", "Foster");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(cid);
        l_Foste.setChildMasterCode(m_Child_info);

        return "longtermcare/foster";

    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, L_Foste l_Foste) {

        model.addAttribute("l_Foste", l_FosteRepository.findOne(id));
        model.addAttribute("form_title", "Foster");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        l_Foste.setChildMasterCode(m_Child_info);
        return "longtermcare/foster";
    }

    @RequestMapping("/save/{cid}")
    public String save(Model model, @PathVariable Long cid, @Valid L_Foste l_Foste, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "Foster");

            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(cid);
            l_Foste.setChildMasterCode(m_Child_info);

            return "longtermcare/foster";
        }
        l_FosteRepository.save(l_Foste);
        return "redirect:/longtremcare/index/{cid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, L_Foste l_Foste, RedirectAttributes redirectAttrs) {

        l_Foste = l_FosteRepository.findOne(id);

        redirectAttrs.addAttribute("cid", l_Foste.childMasterCode.getId());

        l_FosteRepository.delete(id);

        return "redirect:/longtremcare/index/{cid}";
    }

    @RequestMapping("/fosterfollowupcreate/{cid}")
    public String fosterfollowupcreate(Model model, @PathVariable Long cid, L_FollowUp l_FollowUp) {
        model.addAttribute("form_title", "Foster Follow Up Add");
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(cid);
        l_FollowUp.setChildMasterCode(m_Child_info);
        l_FollowUp.setFollowUpType(FollowUpType.Foster);

        return "longtermcare/fosterfollowup";

    }

    @RequestMapping("/fosterfollowupedit/{id}")
    public String fosterfollowupedit(Model model, @PathVariable Long id, L_FollowUp l_FollowUp) {

        model.addAttribute("l_FollowUp", l_FollowUpRepository.findOne(id));

        model.addAttribute("form_title", "Foster Follow Up Edit");

        M_Child_info m_Child_info = new M_Child_info();

        m_Child_info.setId(id);

        l_FollowUp.setChildMasterCode(m_Child_info);

        l_FollowUp.setFollowUpType(FollowUpType.Foster);

        return "longtermcare/fosterfollowup";

    }

    @RequestMapping("/fosterfollowupsave/{cid}")
    public String fosterfollowupsave(Model model, @PathVariable Long cid, @Valid L_FollowUp l_FollowUp, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("form_title", "Foster Follow Up save/update");

            M_Child_info m_Child_info = new M_Child_info();

            m_Child_info.setId(cid);

            l_FollowUp.setChildMasterCode(m_Child_info);

            l_FollowUp.setFollowUpType(FollowUpType.Foster);

            return "longtermcare/fosterfollowup";

        }

        l_FollowUpRepository.save(l_FollowUp);
        return "redirect:/longtremcare/index/{cid}";

    }
    
    @RequestMapping("/fosterfllowupdelete/{id}")
    public String fosterfollowupdelete(Model model, @PathVariable Long id, L_FollowUp l_FollowUp, RedirectAttributes redirectAttrs) {

        l_FollowUp = l_FollowUpRepository.findOne(id);

        redirectAttrs.addAttribute("cid", l_FollowUp.childMasterCode.getId());

        l_FollowUpRepository.delete(id);

        return "redirect:/longtremcare/index/{cid}";
    }
    
}
