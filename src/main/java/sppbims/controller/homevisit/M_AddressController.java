/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.homevisit;

import sppbims.model.homevisit.Address_Type;
import sppbims.model.homevisit.District;
import sppbims.model.homevisit.M_Address;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.M_AddressRepository;
import sppbims.repository.homevisit.ThanaRepository;
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
@RequestMapping("/m_address")
public class M_AddressController {

    @Autowired
    M_AddressRepository m_AddressRepository;
    @Autowired
    ThanaRepository thanaRepository;

    @RequestMapping("/index")
    public String index(Model model, M_Address m_Address) {
        model.addAttribute("list", m_AddressRepository.findAll());
        return "homevisit/lookup/m_address";
    }

    @RequestMapping("/create/{m_id}")
    public String add(@PathVariable Long m_id, M_Address m_Address, Model model) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        m_Address.setMotherMasterCode(motherMasterData);
        model.addAttribute("form_title", "Add");
        model.addAttribute("addressType", Address_Type.values());
        model.addAttribute("district", District.values());
        model.addAttribute("thana", thanaRepository.findAll());
        return "homevisit/motherdetails/m_address";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Address m_Address, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            m_Address.setMotherMasterCode(motherMasterData);

            model.addAttribute("list", m_AddressRepository.findAll());

            model.addAttribute("form_title", "Add");

            model.addAttribute("addressType", Address_Type.values());

            model.addAttribute("district", District.values());

            model.addAttribute("thana", thanaRepository.findAll());

            return "homevisit/motherdetails/m_address";

        }

        m_AddressRepository.save(m_Address);

        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, M_Address m_Address, Model model) {
        model.addAttribute("m_Address", m_AddressRepository.findById(id).orElse(null));
        model.addAttribute("form_title", "Add");
        model.addAttribute("addressType", Address_Type.values());

        model.addAttribute("district", District.values());

        model.addAttribute("thana", thanaRepository.findAll());

        return "homevisit/motherdetails/edit_m_address";
    }

    @RequestMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id, @Valid M_Address m_Address, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("list", m_AddressRepository.findAll());

            model.addAttribute("form_title", "Add");

            model.addAttribute("addressType", Address_Type.values());

            model.addAttribute("district", District.values());

            model.addAttribute("thana", thanaRepository.findAll());

            return "homevisit/motherdetails/edit_m_address/{id}";

        }

        m_AddressRepository.save(m_Address);
        Long m_id = m_Address.motherMasterCode.getId();
        id = m_id;
        return "redirect:/motherdetails/motherdetails/{id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {

        M_Address m_Address = m_AddressRepository.findById(id).orElse(null);

        redirectAttrs.addAttribute("m_id", m_Address.motherMasterCode.getId());

        m_AddressRepository.deleteById(id);
        return "redirect:/motherdetails/motherdetails/{m_id}";
    }

}
