/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.clinic;

import itgarden.model.clinic.C_Mother_Health_Awareness;
import itgarden.model.clinic.C_Mother_SpecialCare;
import itgarden.model.clinic.C_SpecialCare;
import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.repository.clinic.C_Mother_SpecialCareRepository;
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
@RequestMapping("/cmotherspecialcare")
public class C_Mother_SpecialCareController {

    @Autowired
    C_Mother_SpecialCareRepository c_Mother_SpecialCareRepository;

    @Autowired
    ReleaseMotherService releaseMotherService;

    @Autowired
    O_MAddmissionService addmissionService;

    public List<MotherMasterDataDTO> admitedMotherIdListExcludeRealesedMotherId() {

        List<Long> relasedid = releaseMotherService.allReleasedMotherIdList();

        List<MotherMasterDataDTO> motherAdmitedIdList = addmissionService.getMotherMasterDataDTOs()
                .stream().filter(motherMasterDataDTO -> !relasedid.contains(motherMasterDataDTO.getId())).collect(Collectors.toList());
        return motherAdmitedIdList;
    }

    @RequestMapping("/add")
    public String add(Model model, C_Mother_SpecialCare c_Mother_SpecialCare) {
        model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());
        return "clinic/mother_special_care";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, C_Mother_SpecialCare c_Mother_SpecialCare) {

        model.addAttribute("c_Mother_SpecialCare", c_Mother_SpecialCareRepository.findById(id).orElse(null));
       model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());
        return "clinic/mother_special_care";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid C_Mother_SpecialCare c_Mother_SpecialCare, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
           model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());
            return "clinic/mother_special_care";
        }
        c_Mother_SpecialCareRepository.save(c_Mother_SpecialCare);
        return "redirect:/cmotherspecialcare/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, C_Mother_SpecialCare c_Mother_SpecialCare, RedirectAttributes redirectAttrs) {

        c_Mother_SpecialCareRepository.deleteById(id);
        return "redirect:/cmotherspecialcare/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model) {
        model.addAttribute("list", c_Mother_SpecialCareRepository.findAll());
        return "clinic/mother_special_care_list";
    }

}
