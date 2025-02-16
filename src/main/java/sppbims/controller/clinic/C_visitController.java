/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.clinic;

import sppbims.model.clinic.C_visit;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.clinic.C_visitRepository;
import sppbims.services.clinic.C_visitService;
import sppbims.services.observation.O_MAddmissionService;
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
@RequestMapping("/cvisit")
public class C_visitController {

    @Autowired
    C_visitRepository c_visitRepository;

    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @Autowired
    C_visitService c_visitService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("cvisit", c_visitService.all_visit_list());
        return "clinic/c_visit_list";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(id);

        model.addAttribute("m_id", id);

        model.addAttribute("cvisit", c_visitRepository.findBymotherMasterCode(motherMasterData));

        return "clinic/cvisitindex";
    }

    @RequestMapping("/create/{id}")
    public String create(Model model, @PathVariable Long id, C_visit c_visit) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        c_visit.setMotherMasterCode(motherMasterData);

        return "clinic/cvisit";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, C_visit c_visit) {

        model.addAttribute("c_visit", c_visitRepository.findById(id).orElse(null));

        return "clinic/cvisit";
    }

    @RequestMapping("/save/{id}")
    public String save(Model model, @PathVariable Long id, @Valid C_visit c_visit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "clinic/cvisit";
        }
        c_visitRepository.save(c_visit);
        return "redirect:/cvisit/index/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, C_visit c_visit, RedirectAttributes redirectAttrs) {

        c_visit = c_visitRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("id", c_visit.motherMasterCode.getId());

        c_visitRepository.deleteById(id);
        return "redirect:/cvisit/index/{id}";
    }
}
