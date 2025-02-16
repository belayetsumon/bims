/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.clinic;

import sppbims.model.clinic.C_Release;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.clinic.C_ReleaseRepository;
import sppbims.services.clinic.C_ReferralService;
import sppbims.services.clinic.C_ReleaseService;
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
@RequestMapping("/crelease")
public class C_ReleaseController {

    @Autowired
    C_ReleaseRepository c_ReleaseRepository;

    @Autowired
    C_ReleaseService c_ReleaseService;

    @RequestMapping("/index")

    public String index(Model model) {

        // model.addAttribute("release", c_ReleaseRepository.findAll());
        model.addAttribute("release", c_ReleaseService.findReleaseDetails());

        return "clinic/releaseIndex";
    }

    @RequestMapping("/create/{id}")

    public String add(Model model, @PathVariable Long id, C_Release c_Release) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(id);

        model.addAttribute("form_title", "Mother Release");

        c_Release.setMotherMasterCode(motherMasterData);

        return "clinic/releaseadd";
    }

    @RequestMapping("/edit/{id}")

    public String edit(Model model, @PathVariable Long id, C_Release c_Release) {
        model.addAttribute("c_Release", c_ReleaseRepository.findById(id).orElse(null));
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(id);

        model.addAttribute("form_title", "Mother Release  edit");

        c_Release.setMotherMasterCode(motherMasterData);

        return "clinic/releaseadd";
    }

    @RequestMapping("/save/{id}")

    public String save(Model model, @PathVariable Long id, @Valid C_Release c_Release, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(id);

            model.addAttribute("form_title", "Mother Release  edit");

            c_Release.setMotherMasterCode(motherMasterData);

            return "clinic/releaseadd";

        }
        c_ReleaseRepository.save(c_Release);
        return "redirect:/crelease/index";

    }

    @GetMapping(value = "/delete/{id}")
    public String delete(Model model, @PathVariable Long id, C_Release c_Release, RedirectAttributes redirectAttrs) {

        c_Release = c_ReleaseRepository.findById(id).orElse(null);

        redirectAttrs.addAttribute("id", c_Release.motherMasterCode.getId());

        c_ReleaseRepository.deleteById(id);

        return "redirect:/crelease/index";
    }

}
