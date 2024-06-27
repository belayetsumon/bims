/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.pre_reintegration_visit;

import itgarden.model.homevisit.M_House_Information;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Value_type;
import itgarden.model.pre_reintegration_visit.M_House_Information_ReintegrationVisit;
import itgarden.repository.homevisit.House_TypeRepository;
import itgarden.repository.homevisit.Ownershif_typeRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_House_InformationRepository;
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
@RequestMapping("/pre_reintegration_m_house_information")
public class M_House_Information_Pre_reintegration_visitController {

    @Autowired
    Pre_reintegration_visit_M_House_InformationRepository pre_reintegration_visit_M_House_InformationRepository;

    @Autowired
    Ownershif_typeRepository ownershif_typeRepository;

    @Autowired
    House_TypeRepository house_TypeRepository;

    @RequestMapping("/index")

    public String index(Model model, M_House_Information m_House_Information) {
        model.addAttribute("list", pre_reintegration_visit_M_House_InformationRepository.findAll());

        return "homevisit/lookup/ethnicIdentity";
    }

    @RequestMapping("/create/{m_id}")
    public String add(@PathVariable Long m_id, Model model, M_House_Information_ReintegrationVisit m_House_Information_ReintegrationVisit) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        model.addAttribute("form_title", "Mother House Information Add");

        m_House_Information_ReintegrationVisit.setMotherMasterCode(motherMasterData);

        model.addAttribute("list", pre_reintegration_visit_M_House_InformationRepository.findAll());

        model.addAttribute("houseType", house_TypeRepository.findAll());

        model.addAttribute("ownership", ownershif_typeRepository.findAll());

        model.addAttribute("valueTitle", Value_type.values());

        return "pre_reintegration_visit/m_house_information";
    }

    @RequestMapping("/save/{m_id}")

    public String save(Model model, @PathVariable Long m_id, @Valid M_House_Information_ReintegrationVisit m_House_Information_ReintegrationVisit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            model.addAttribute("form_title", "Mother House Information Save / Update");

            m_House_Information_ReintegrationVisit.setMotherMasterCode(motherMasterData);

            model.addAttribute("houseType", house_TypeRepository.findAll());

            model.addAttribute("ownership", ownershif_typeRepository.findAll());

            model.addAttribute("valueTitle", Value_type.values());

            return "pre_reintegration_visit/m_house_information";
        }

        pre_reintegration_visit_M_House_InformationRepository.save(m_House_Information_ReintegrationVisit);

        return "redirect:/pre_reintegration_visit//details/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_House_Information_ReintegrationVisit m_House_Information_ReintegrationVisit, Model model) {

        model.addAttribute("form_title", "Mother House Information Edit");

        model.addAttribute("m_House_Information_ReintegrationVisit", pre_reintegration_visit_M_House_InformationRepository.findById(id));

        model.addAttribute("houseType", house_TypeRepository.findAll());

        model.addAttribute("ownership", ownershif_typeRepository.findAll());

        model.addAttribute("valueTitle", Value_type.values());

        return "pre_reintegration_visit/m_house_information";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_House_Information_ReintegrationVisit m_House_Information_ReintegrationVisit, RedirectAttributes redirectAttrs) {

        Optional<M_House_Information_ReintegrationVisit> optionalhir = pre_reintegration_visit_M_House_InformationRepository.findById(id);
       m_House_Information_ReintegrationVisit = optionalhir.orElse(null);
        
 

        redirectAttrs.addAttribute("m_id", m_House_Information_ReintegrationVisit.motherMasterCode.getId());

        pre_reintegration_visit_M_House_InformationRepository.deleteById(id);

         return "redirect:/pre_reintegration_visit//details/{m_id}";
    }

}
