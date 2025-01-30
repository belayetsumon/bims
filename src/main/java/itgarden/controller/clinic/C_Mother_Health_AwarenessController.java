/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.clinic;

import itgarden.model.clinic.C_Mother_Health_Awareness;
import itgarden.model.clinic.C_SpecialCare;
import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.repository.clinic.C_Mother_Health_AwarenessRepository;
import itgarden.repository.clinic.HealthAwarenessRepository;
import itgarden.services.observation.O_ChildAdmissionService;
import itgarden.services.observation.O_MAddmissionService;
import itgarden.services.reintegration_release.ReleaseMotherService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/mother_health_awareness")
public class C_Mother_Health_AwarenessController {

    @Autowired
    O_MAddmissionService addmissionService;

    @Autowired
    C_Mother_Health_AwarenessRepository c_Mother_Health_AwarenessRepository;

    @Autowired
    ReleaseMotherService releaseMotherService;

    @Autowired
    HealthAwarenessRepository healthAwarenessRepository;

    /// mother admission id excluded from realesed id
    public List<MotherMasterDataDTO> admitedMotherIdListExcludeRealesedMotherId() {

        List<Long> relasedid = releaseMotherService.allReleasedMotherIdList();

        List<MotherMasterDataDTO> motherAdmitedIdList = addmissionService.getMotherMasterDataDTOs()
                .stream().filter(motherMasterDataDTO -> !relasedid.contains(motherMasterDataDTO.getId())).collect(Collectors.toList());
        return motherAdmitedIdList;
    }

    @RequestMapping("/add")
    public String add(Model model, C_Mother_Health_Awareness c_Mother_Health_Awareness) {
        model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());

        model.addAttribute("healthAwarenesslist", healthAwarenessRepository.findAll());

        return "clinic/c_Mother_Health_Awareness";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, C_Mother_Health_Awareness c_Mother_Health_Awareness) {

        model.addAttribute("c_Mother_Health_Awareness", c_Mother_Health_AwarenessRepository.findById(id).orElse(null));
        model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());
           model.addAttribute("healthAwarenesslist", healthAwarenessRepository.findAll());
        return "clinic/c_Mother_Health_Awareness";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid C_Mother_Health_Awareness c_Mother_Health_Awareness, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());
               model.addAttribute("healthAwarenesslist", healthAwarenessRepository.findAll());
            return "clinic/c_Mother_Health_Awareness";
        }
        c_Mother_Health_AwarenessRepository.save(c_Mother_Health_Awareness);
        return "redirect:/mother_health_awareness/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, C_Mother_Health_Awareness c_Mother_Health_Awareness, RedirectAttributes redirectAttrs) {

        c_Mother_Health_AwarenessRepository.deleteById(id);
        return "redirect:/mother_health_awareness/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("list", c_Mother_Health_AwarenessRepository.findAll());
        return "clinic/mother_health_awareness_list";
    }

}
