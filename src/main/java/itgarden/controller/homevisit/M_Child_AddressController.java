/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.Address_Type;
import itgarden.model.homevisit.District;
import itgarden.model.homevisit.M_Child_Address;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.repository.homevisit.M_Child_AddressRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.ThanaRepository;
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
@RequestMapping("/childaddress")
public class M_Child_AddressController {

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Autowired
    M_Child_AddressRepository m_Child_AddressRepository;

    @Autowired
    ThanaRepository thanaRepository;

    @RequestMapping("/index/{c_id}")

    public String index(Model model, @PathVariable Long c_id) {

        model.addAttribute("child_info", m_Child_infoRepository.findOne(c_id));
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(c_id);
        model.addAttribute("c_addres", m_Child_AddressRepository.findBychildMasterCode(m_Child_info));
        return "homevisit/motherdetails/childAddressIndex";
    }

    @RequestMapping("/create/{c_id}")
    public String add(@PathVariable Long c_id, M_Child_Address m_Child_Address, Model model) {
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(c_id);
        m_Child_Address.setChildMasterCode(m_Child_info);
        m_Child_info = m_Child_infoRepository.findOne(c_id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData = m_Child_info.getMotherMasterCode();
        m_Child_Address.setMotherMasterCode(motherMasterData);

        model.addAttribute("form_title", "Add");
        model.addAttribute("same", Yes_No.values());
        model.addAttribute("addressType", Address_Type.values());
        model.addAttribute("district", District.values());
        model.addAttribute("thana", thanaRepository.findAll());
        return "homevisit/motherdetails/m_c_address";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, M_Child_Address m_Child_Address, Model model) {
        model.addAttribute("m_Child_Address", m_Child_AddressRepository.findOne(id));
        model.addAttribute("form_title", "Add");
        model.addAttribute("same", Yes_No.values());
        model.addAttribute("addressType", Address_Type.values());
        model.addAttribute("district", District.values());
        model.addAttribute("thana", thanaRepository.findAll());
        return "homevisit/motherdetails/m_c_address";
    }

    @RequestMapping("/save/{c_id}")
    public String save(@PathVariable Long c_id, @Valid M_Child_Address m_Child_Address, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            M_Child_info m_Child_info = new M_Child_info();
            m_Child_info.setId(c_id);
            m_Child_Address.setChildMasterCode(m_Child_info);
            m_Child_info = m_Child_infoRepository.findOne(c_id);
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData = m_Child_info.getMotherMasterCode();
            m_Child_Address.setMotherMasterCode(motherMasterData);
            model.addAttribute("same", Yes_No.values());
            model.addAttribute("form_title", "Add");
            model.addAttribute("addressType", Address_Type.values());
            model.addAttribute("district", District.values());
            model.addAttribute("thana", thanaRepository.findAll());
            return "homevisit/motherdetails/m_c_address";

        }
        m_Child_AddressRepository.save(m_Child_Address);
        return "redirect:/childaddress/index/{c_id}";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, M_Child_Address m_Child_Address, RedirectAttributes redirectAttrs) {

        m_Child_Address = m_Child_AddressRepository.findOne(id);
        redirectAttrs.addAttribute("c_id", m_Child_Address.getChildMasterCode());
        m_Child_AddressRepository.delete(id);
        return "redirect:/childaddress//index/{c_id}";
    }
}
