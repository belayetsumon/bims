/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.longtremcare;

import itgarden.model.follow_up_report.ChildCrisisMeetUp;
import itgarden.model.homevisit.M_Child_info;
import itgarden.repository.follow_up_report.ChildCrisisMeetUpRepository;
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
@RequestMapping("/childcrisismeetup")

public class ChildCrisisMeetUpController {

    @Autowired
    ChildCrisisMeetUpRepository childCrisisMeetUpRepository;

    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, ChildCrisisMeetUp childCrisisMeetUp) {

        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(mid);
        childCrisisMeetUp.setChildMasterCode(m_Child_info);

        return "longtermcare/childcrisismeetup";

    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, ChildCrisisMeetUp childCrisisMeetUp) {
        model.addAttribute("childCrisisMeetUp", childCrisisMeetUpRepository.findById(id).orElse(null));
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        childCrisisMeetUp.setChildMasterCode(m_Child_info);
        return "longtermcare/childcrisismeetup";
    }

    @RequestMapping("/save/{mid}")
    public String fosterfollowupsave(Model model, @PathVariable Long mid, @Valid ChildCrisisMeetUp childCrisisMeetUp, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(mid);
            childCrisisMeetUp.setChildMasterCode(m_Child_info);
            return "longtermcare/jobfollowup";
        }
        childCrisisMeetUpRepository.save(childCrisisMeetUp);
        return "redirect:/longtremcare/index/{mid}";

    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, ChildCrisisMeetUp childCrisisMeetUp, RedirectAttributes redirectAttrs) {
        childCrisisMeetUp = childCrisisMeetUpRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("mid", childCrisisMeetUp.childMasterCode.getId());
        childCrisisMeetUpRepository.deleteById(id);
        return "redirect:/longtremcare/index/{mid}";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", childCrisisMeetUpRepository.findAll());
        return "longtermcare/childcrisismeetup_list";

    }

}
