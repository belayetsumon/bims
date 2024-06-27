/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_OT;
import itgarden.repository.rehabilitations.DiagonosisRepository;
import itgarden.repository.rehabilitations.R_OTRepository;
import itgarden.repository.rehabilitations.SessionTypeRepository;
import jakarta.validation.Valid;
import java.util.Optional;
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
@RequestMapping("/ot")
public class OtController {

    @Autowired
    R_OTRepository r_OTRepository;

    @Autowired
    SessionTypeRepository sessionTypeRepository;

    @Autowired
    DiagonosisRepository diagonosisRepository;

    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_OT r_OT) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(mid);
        r_OT.setMotherMasterCode(motherMasterData);
        model.addAttribute("form_title", "  Mother OT");

        model.addAttribute("sessionType", sessionTypeRepository.findAll());

        model.addAttribute("diagonosis", diagonosisRepository.findAll());

        return "rehabilitations/therapeuticsessions/mot";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_OT r_OT) {

        model.addAttribute("r_OT", r_OTRepository.findById(id));

        model.addAttribute("form_title", "  Mother OT Edit");

        model.addAttribute("sessionType", sessionTypeRepository.findAll());

        model.addAttribute("diagonosis", diagonosisRepository.findAll());

        return "rehabilitations/therapeuticsessions/mot";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_OT r_OT, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", "  Mother OT Save/Update");
            model.addAttribute("sessionType", sessionTypeRepository.findAll());
            model.addAttribute("diagonosis", diagonosisRepository.findAll());
            return "rehabilitations/therapeuticsessions/mot";
        }
        r_OTRepository.save(r_OT);
        return "redirect:/therapeuticsessions/index/{mid}";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id,  R_OT r_OT,  RedirectAttributes redirectAttrs ) {
       Optional<R_OT> optionalr_OT = r_OTRepository.findById(id);
        r_OT = optionalr_OT.orElse(null);
        redirectAttrs.addAttribute("mid", r_OT.motherMasterCode.getId());
        r_OTRepository.deleteById(id);
        return "redirect:/therapeuticsessions/index/{mid}";
    }
    

}
