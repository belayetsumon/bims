/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.pre_reintegration_visit;

import itgarden.model.homevisit.Address_Type;
import itgarden.model.homevisit.District;
import itgarden.model.homevisit.M_Address;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.pre_reintegration_visit.M_Address_ReintegrationVisit;
import itgarden.repository.homevisit.ThanaRepository;
import itgarden.repository.pre_reintegration_visit.Pre_reintegration_visit_M_AddressRepository;
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
@RequestMapping("/pre_reintegration_m_address")
public class M_Address_Pre_reintegration_visitController {

    @Autowired
    Pre_reintegration_visit_M_AddressRepository pre_reintegration_visit_M_AddressRepository;
    @Autowired
    ThanaRepository thanaRepository;

    @RequestMapping("/index")
    public String index(Model model, M_Address m_Address) {
        model.addAttribute("list", pre_reintegration_visit_M_AddressRepository.findAll());
        return "homevisit/lookup/m_address";
    }

    @RequestMapping("/create/{m_id}")
    public String add(@PathVariable Long m_id, M_Address_ReintegrationVisit m_Address_ReintegrationVisit, Model model) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Address_ReintegrationVisit.setMotherMasterCode(motherMasterData);
        model.addAttribute("form_title", "Add");
        model.addAttribute("addressType", Address_Type.values());
        model.addAttribute("district", District.values());
        model.addAttribute("thana", thanaRepository.findAll());
        return "pre_reintegration_visit/m_address";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Address_ReintegrationVisit m_Address_ReintegrationVisit, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Address_ReintegrationVisit.setMotherMasterCode(motherMasterData);

            model.addAttribute("list", pre_reintegration_visit_M_AddressRepository.findAll());

            model.addAttribute("form_title", "Add");

            model.addAttribute("addressType", Address_Type.values());

            model.addAttribute("district", District.values());

            model.addAttribute("thana", thanaRepository.findAll());

            return "pre_reintegration_visit/m_address";

        }

        pre_reintegration_visit_M_AddressRepository.save(m_Address_ReintegrationVisit);

        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Address_ReintegrationVisit m_Address_ReintegrationVisit, Model model) {
        model.addAttribute("m_Address_ReintegrationVisit", pre_reintegration_visit_M_AddressRepository.findOne(id));
        model.addAttribute("form_title", "Add");
        model.addAttribute("addressType", Address_Type.values());

        model.addAttribute("district", District.values());

        model.addAttribute("thana", thanaRepository.findAll());

        return "pre_reintegration_visit/m_address";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model, M_Address_ReintegrationVisit m_Address_ReintegrationVisit ,RedirectAttributes redirectAttrs) {

        m_Address_ReintegrationVisit = pre_reintegration_visit_M_AddressRepository.findOne(id);

        redirectAttrs.addAttribute("m_id", m_Address_ReintegrationVisit.motherMasterCode.getId());

        pre_reintegration_visit_M_AddressRepository.delete(id);
        return "redirect:/pre_reintegration_visit/details/{m_id}";
    }

}
