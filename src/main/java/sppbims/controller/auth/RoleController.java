/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.auth;

import sppbims.model.auth.Privilege;
import sppbims.model.auth.Role;
import sppbims.repository.auth.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/index")
    public String index(Model model, Role role) {
        model.addAttribute("list", roleRepository.findAll());
        model.addAttribute("table_name", "Role");
        model.addAttribute("privilege", Privilege.values());
        return "/auth/role";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid Role role, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", roleRepository.findAll());
            model.addAttribute("table_name", "Role");
            model.addAttribute("privilege", Privilege.values());
            return "/auth/role";
        }
        roleRepository.save(role);
        return "redirect:/role/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Role role, Model model) {
        model.addAttribute("role", roleRepository.findById(id).orElse(null));
        model.addAttribute("list", roleRepository.findAll());
        model.addAttribute("privilege", Privilege.values());
        model.addAttribute("table_name", "Role");
        return "/auth/role";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Role role) {
        roleRepository.deleteById(id);
        return "redirect:/role/index";
    }

}
