/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.clinic;

import sppbims.model.clinic.C_Admission;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.clinic.C_AdmissionRepository;
import sppbims.services.clinic.C_AdmissionService;
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
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/cadmission")
public class C_AdmissionController {

    @Autowired
    C_AdmissionRepository c_AdmissionRepository;

    @Autowired
    C_AdmissionService c_AdmissionService;

    @RequestMapping("/index")
    public String list(Model model) {
        model.addAttribute("c_Admission", c_AdmissionService.findAdmissionDetails());
        return "clinic/admissionlindex";
    }

    @RequestMapping("/create/{id}")
    public String add(Model model, @PathVariable Long id, C_Admission c_Admission) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("form_title", "Mother Admission");
        c_Admission.setMotherMasterCode(motherMasterData);
        return "clinic/admissionadd";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, C_Admission c_Admission) {
        model.addAttribute("c_Admission", c_AdmissionRepository.findById(id).orElse(null));
        return "clinic/admissionadd";
    }

    @RequestMapping("/save/{id}")
    public String save(Model model, @PathVariable Long id, @Valid C_Admission c_Admission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(id);

            model.addAttribute("form_title", "Mother Admission save");

            c_Admission.setMotherMasterCode(motherMasterData);

            return "clinic/admissionadd";
        }
        c_AdmissionRepository.save(c_Admission);

        return "redirect:/cadmission/index";

    }

    @GetMapping(value = "/delete/{id}")

    public String delete(@PathVariable Long id, C_Admission c_Admission, RedirectAttributes redirectAttrs) {

        c_Admission = c_AdmissionRepository.findById(id).orElse(null);

        redirectAttrs.addAttribute("id", c_Admission.motherMasterCode.getId());

        c_AdmissionRepository.deleteById(id);

        return "redirect:/cadmission/index";
    }

}
