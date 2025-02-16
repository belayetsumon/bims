/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.follow_up_report;

import sppbims.model.follow_up_report.FollowMotherPerCapitaIncome;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.follow_up_report.FollowMotherPerCapitaIncomeRepository;
import sppbims.repository.homevisit.Income_GeneratingRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
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
 * @author User
 */
@Controller
@RequestMapping("/followmotherpercapitaincome")
public class FollowMotherPerCapitaIncomeController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    FollowMotherPerCapitaIncomeRepository followMotherPerCapitaIncomeRepository;

    @Autowired
    Income_GeneratingRepository income_GeneratingRepository;

    @RequestMapping("/add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, FollowMotherPerCapitaIncome followMotherPerCapitaIncome) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        followMotherPerCapitaIncome.setMotherMasterCode(motherMasterData);

        model.addAttribute("income_activity", income_GeneratingRepository.findAll());

        return "follow_up_report/followmotherpercapitaincome";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FollowMotherPerCapitaIncome followMotherPerCapitaIncome) {

        model.addAttribute("followMotherPerCapitaIncome", followMotherPerCapitaIncomeRepository.findById(id).orElse(null));

        model.addAttribute("income_activity", income_GeneratingRepository.findAll());

        return "follow_up_report/followmotherpercapitaincome";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid FollowMotherPerCapitaIncome followMotherPerCapitaIncome, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            followMotherPerCapitaIncome.setMotherMasterCode(motherMasterData);

            model.addAttribute("income_activity", income_GeneratingRepository.findAll());
            return "follow_up_report/followmotherpercapitaincome";
        }
        followMotherPerCapitaIncomeRepository.save(followMotherPerCapitaIncome);
        return "redirect:/followupmother/details/{m_id}";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model, FollowMotherPerCapitaIncome followMotherPerCapitaIncome, RedirectAttributes redirectAttrs) {

        followMotherPerCapitaIncome = followMotherPerCapitaIncomeRepository.findById(id).orElse(null);

        redirectAttrs.addAttribute("m_id", followMotherPerCapitaIncome.motherMasterCode.getId());

        followMotherPerCapitaIncomeRepository.deleteById(id);
        return "redirect:/followupmother/details/{m_id}";
    }

}
