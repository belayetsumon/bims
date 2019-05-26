/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.clinic;

import itgarden.model.clinic.C_Referral;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.clinic.C_ReferralRepository;
import javax.validation.Valid;
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
@RequestMapping("/referral")
public class C_ReferralController {

    @Autowired

    C_ReferralRepository c_ReferralRepository;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("referral", c_ReferralRepository.findAll());
        return "clinic/referralindex";
    }

    @RequestMapping("/create/{id}")
    public String add(Model model, @PathVariable Long id, C_Referral c_Referral) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("form_title", "Mother Referral");
        c_Referral.setMotherMasterCode(motherMasterData);

        return "clinic/referraladd";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, C_Referral c_Referral) {
        model.addAttribute("c_Referral", c_ReferralRepository.findOne(id));
        model.addAttribute("form_title", "Mother Referral edit");
        return "clinic/referraladd";
    }

    @RequestMapping("/save/{id}")
    public String save(Model model, @PathVariable Long id, @Valid C_Referral c_Referral, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(id);
            model.addAttribute("form_title", "Mother Referral");
            c_Referral.setMotherMasterCode(motherMasterData);
            return "clinic/referraladd";
        }
        c_ReferralRepository.save(c_Referral);
        return "redirect:/referral/index";
    }

    
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, C_Referral c_Referral, RedirectAttributes redirectAttrs) {
        c_Referral = c_ReferralRepository.findOne(id);
        redirectAttrs.addAttribute("id", c_Referral.motherMasterCode.getId());
        c_ReferralRepository.delete(id);
        return "redirect:/referral/index";
        }
    
}
