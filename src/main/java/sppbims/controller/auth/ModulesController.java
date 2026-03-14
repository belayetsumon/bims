/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.auth;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sppbims.exception.DuplicateResourceException;
import sppbims.exception.ResourceNotFoundException;
import sppbims.model.auth.Modules;
import sppbims.services.auth.ModulesServiceImpl;

import sppbims.services.auth.SlugService;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/modules")
public class ModulesController {

    @Autowired
    private ModulesServiceImpl service;
    @Autowired
    SlugService slugService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.findAll());
        return "auth/module_index";
    }

    @GetMapping("/create")
    public String create(Model model, Modules modules) {
        model.addAttribute("modules", modules);
        return "auth/module_form";
    }

    @PostMapping("/save")
    public String save(
            @Valid Modules modules,
            BindingResult result,
            RedirectAttributes redirect,
            Model model) {

        if (result.hasErrors()) {
            return "auth/module_form";
        }

        try {
            // Save and get the persisted entity
            Modules savedModule = service.saveModule(modules);

            // Use savedModule.getId() to determine create/update message
            if (modules.getId() == null) { // Before save, ID was null → create
                redirect.addFlashAttribute("success", "Module created successfully!");
            } else { // Otherwise → update
                redirect.addFlashAttribute("success", "Module updated successfully!");
            }

        } catch (DuplicateResourceException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
            return "redirect:/modules/form"; // Redirect back to form if duplicate
        }

        return "redirect:/modules/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Modules modules = service.findById(id);
        if (modules == null) {
            return "redirect:/modules/list";
        }
        model.addAttribute("modules", modules);
        return "auth/module_form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteModule(id);
            redirectAttributes.addFlashAttribute("success", "Module deleted");
        } catch (IllegalStateException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage()); // "Cannot delete module: it has X submodule(s)."
        } catch (ResourceNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", "Module not found");
        }
        return "redirect:/modules/list";
    }

}
