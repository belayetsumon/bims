/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.clinic;

import itgarden.model.clinic.C_Child_Health_Awareness;
import itgarden.repository.clinic.C_Child_Health_AwarenessRepository;
import itgarden.repository.clinic.HealthAwarenessRepository;
import itgarden.services.clinic.C_Child_Health_AwarenessService;
import itgarden.services.observation.O_ChildAdmissionService;
import itgarden.services.reintegration_release.ReleaseMotherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/child_health_awareness")
public class C_Child_Health_AwarenessController {

    @Autowired
    O_ChildAdmissionService o_ChildAdmissionService;

    @Autowired
    C_Child_Health_AwarenessRepository c_Child_Health_AwarenessRepository;

    @Autowired
    ReleaseMotherService releaseMotherService;

    @Autowired
    HealthAwarenessRepository healthAwarenessRepository;

    @Autowired
    C_Child_Health_AwarenessService c_Child_Health_AwarenessService;

    @RequestMapping("/add")
    public String add(Model model, C_Child_Health_Awareness c_Child_Health_Awareness) {
        model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());

        model.addAttribute("healthAwarenesslist", healthAwarenessRepository.findAll());

        return "clinic/c_Child_Health_Awareness";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, C_Child_Health_Awareness c_Child_Health_Awareness) {

        model.addAttribute("c_Child_Health_Awareness", c_Child_Health_AwarenessRepository.findById(id).orElse(null));
        model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());
        model.addAttribute("healthAwarenesslist", healthAwarenessRepository.findAll());
        return "clinic/c_Child_Health_Awareness";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid C_Child_Health_Awareness c_Child_Health_Awareness, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());
            model.addAttribute("healthAwarenesslist", healthAwarenessRepository.findAll());
            return "clinic/c_Child_Health_Awareness";
        }
        c_Child_Health_AwarenessRepository.save(c_Child_Health_Awareness);
        return "redirect:/child_health_awareness/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, C_Child_Health_Awareness c_Child_Health_Awareness, RedirectAttributes redirectAttrs) {

        c_Child_Health_AwarenessRepository.deleteById(id);
        return "redirect:/child_health_awareness/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("list", c_Child_Health_AwarenessRepository.findAll());
        return "clinic/child_health_awareness_list";
    }

}
