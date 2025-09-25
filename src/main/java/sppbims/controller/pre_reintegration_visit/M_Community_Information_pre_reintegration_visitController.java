/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.pre_reintegration_visit;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.pre_reintegration_visit.M_Community_Information_ReintegrationVisit;
import sppbims.repository.homevisit.AvilableIgaRepository;
import sppbims.repository.homevisit.Economic_TypeRepository;
import sppbims.repository.homevisit.ProspectiveIgaRepository;
import sppbims.repository.pre_reintegration_visit.Pre_reintegration_visit_M_Community_InformationRepository;
import jakarta.validation.Valid;
import java.util.Optional;
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
@RequestMapping("/pre_reintegration_m_community_information")
public class M_Community_Information_pre_reintegration_visitController {

    @Autowired
    Pre_reintegration_visit_M_Community_InformationRepository pre_reintegration_visit_M_Community_InformationRepository;

    @Autowired
    AvilableIgaRepository avilableIgaRepository;

    @Autowired
    ProspectiveIgaRepository prospectiveIgaRepository;

    @Autowired
    Economic_TypeRepository economic_TypeRepository;

    @RequestMapping("/index")

    public String index(Model model, M_Community_Information_ReintegrationVisit m_Community_Information_ReintegrationVisit) {
        model.addAttribute("list", pre_reintegration_visit_M_Community_InformationRepository.findAll());
        return "pre_reintegration_visit/m_community_information";
    }

    @RequestMapping("/create/{m_id}")

    public String add(Model model, @PathVariable Long m_id, M_Community_Information_ReintegrationVisit m_Community_Information_ReintegrationVisit) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Community_Information_ReintegrationVisit.setMotherMasterCode(motherMasterData);
        model.addAttribute("form_title", "Mother Community Information Add");
        model.addAttribute("economyType", economic_TypeRepository.findAll());
        model.addAttribute("avilableIga", avilableIgaRepository.findAll());
        model.addAttribute("prospectiveIga", prospectiveIgaRepository.findAll());
        return "pre_reintegration_visit/m_community_information";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Community_Information_ReintegrationVisit m_Community_Information_ReintegrationVisit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Community_Information_ReintegrationVisit.setMotherMasterCode(motherMasterData);
            model.addAttribute("form_title", "Mother Community Information Add");
            model.addAttribute("economyType", economic_TypeRepository.findAll());
            model.addAttribute("avilableIga", avilableIgaRepository.findAll());
            model.addAttribute("prospectiveIga", prospectiveIgaRepository.findAll());
            return "pre_reintegration_visit/m_community_information";
        }

        pre_reintegration_visit_M_Community_InformationRepository.save(m_Community_Information_ReintegrationVisit);

        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Community_Information_ReintegrationVisit m_Community_Information_ReintegrationVisit, Model model) {

        model.addAttribute("m_Community_Information_ReintegrationVisit", pre_reintegration_visit_M_Community_InformationRepository.findById(id).orElse(null));

        model.addAttribute("form_title", "Mother Community Information Add");
        model.addAttribute("economyType", economic_TypeRepository.findAll());
        model.addAttribute("avilableIga", avilableIgaRepository.findAll());
        model.addAttribute("prospectiveIga", prospectiveIgaRepository.findAll());
        return "pre_reintegration_visit/m_community_information";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Community_Information_ReintegrationVisit m_Community_Information_ReintegrationVisit, RedirectAttributes redirectAttrs) {

        Optional<M_Community_Information_ReintegrationVisit> optional_Community_Information_ReintegrationVisit = pre_reintegration_visit_M_Community_InformationRepository.findById(id);

        m_Community_Information_ReintegrationVisit = optional_Community_Information_ReintegrationVisit.orElse(null);

        redirectAttrs.addAttribute("m_id", m_Community_Information_ReintegrationVisit.motherMasterCode.getId());

        pre_reintegration_visit_M_Community_InformationRepository.deleteById(id);
        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

}
