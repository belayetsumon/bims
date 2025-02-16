/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.clinic;

import sppbims.model.clinic.C_SpecialCare;
import sppbims.repository.clinic.C_SpecialCareRepository;
import sppbims.services.observation.O_ChildAdmissionService;
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
@RequestMapping("/cspecialcare")
public class C_SpecialCareController {

    @Autowired
    C_SpecialCareRepository c_SpecialCareRepository;

    @Autowired
    O_ChildAdmissionService o_ChildAdmissionService;

    @RequestMapping("/add")
    public String add(Model model, C_SpecialCare c_SpecialCare) {
        model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());
        return "clinic/special_care";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, C_SpecialCare c_SpecialCare) {

        model.addAttribute("c_SpecialCare", c_SpecialCareRepository.findById(id).orElse(null));
        model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());
        return "clinic/special_care";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid C_SpecialCare c_SpecialCare, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());
            return "clinic/special_care";
        }
        c_SpecialCareRepository.save(c_SpecialCare);
        return "redirect:/cspecialcare/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, C_SpecialCare c_SpecialCare, RedirectAttributes redirectAttrs) {

        c_SpecialCareRepository.deleteById(id);
        return "redirect:/cspecialcare/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("list", c_SpecialCareRepository.findAll());
        return "clinic/special_care_list";
    }

}
