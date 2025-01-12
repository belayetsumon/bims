/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.follow_up_report;

import itgarden.model.follow_up_report.FollowUpMother;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.follow_up_report.FollowMotherPerCapitaIncomeRepository;
import itgarden.repository.follow_up_report.FollowUpChildrenRepository;
import itgarden.repository.follow_up_report.FollowUpMotherRepsitory;
import itgarden.repository.follow_up_report.MotherCrisisMeetUpRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
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
 * @author User
 */
@Controller
@RequestMapping("/followupmother")
public class FollowUpMotherController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    FollowMotherPerCapitaIncomeRepository followMotherPerCapitaIncomeRepository;

    @Autowired
    FollowUpChildrenRepository followUpChildrenRepository;

    @Autowired
    FollowUpMotherRepsitory followUpMotherRepsitory;

    @Autowired
    MotherCrisisMeetUpRepository motherCrisisMeetUpRepository;

    @Autowired
    ReleaseMotherService releaseMotherService;

    @GetMapping("/mothersearch")
    public String motherSearch(Model model) {

       // model.addAttribute("list", motherMasterDataRepository.findByReleaseMotherIsNotNullOrderByIdDesc());
        
        model.addAttribute("list", releaseMotherService.getReleaseMotherList());

        return "follow_up_report/mothersearch";
    }

    @RequestMapping("/details/{m_id}")
    public String motherdetails(Model model, @PathVariable Long m_id, FollowUpMother followUpMother) {

        model.addAttribute("m_id", m_id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        model.addAttribute("motherlist", followUpMotherRepsitory.findBymotherMasterCode(motherMasterData));
        model.addAttribute("childrenlist", followUpChildrenRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("income", followMotherPerCapitaIncomeRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("mothercrisismeetup", motherCrisisMeetUpRepository.findBymotherMasterCode(motherMasterData));
        return "follow_up_report/details";
    }

    @RequestMapping("/add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, FollowUpMother followUpMother) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        followUpMother.setMotherMasterCode(motherMasterData);

        return "follow_up_report/followupmother";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, FollowUpMother followUpMother) {
        model.addAttribute("followUpMother", followUpMotherRepsitory.findById(id).orElse(null));
        return "follow_up_report/followupmother";
    }

    @RequestMapping("/save/{m_id}")
    public String add(Model model, @PathVariable Long m_id, @Valid FollowUpMother followUpMother, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("m_id", m_id);

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            followUpMother.setMotherMasterCode(motherMasterData);

            return "follow_up_report/followupmother";
        }
        followUpMotherRepsitory.save(followUpMother);

        return "redirect:/followupmother/details/{m_id}";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model, FollowUpMother followUpMother, RedirectAttributes redirectAttrs) {

        followUpMother = followUpMotherRepsitory.findById(id).orElse(null);

        redirectAttrs.addAttribute("m_id", followUpMother.motherMasterCode.getId());

        followUpMotherRepsitory.deleteById(id);
        return "redirect:/followupmother/details/{m_id}";
    }
}
