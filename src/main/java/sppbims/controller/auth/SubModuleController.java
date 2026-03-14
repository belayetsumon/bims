/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package sppbims.controller.auth;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sppbims.exception.DuplicateResourceException;
import sppbims.model.auth.SubModules;
import sppbims.repository.auth.ModulesRepository;
import sppbims.services.auth.SubModuleService;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/submodules")
public class SubModuleController {

    private final SubModuleService service;
    private final ModulesRepository moduleRepo;

    public SubModuleController(SubModuleService service, ModulesRepository moduleRepo) {
        this.service = service;
        this.moduleRepo = moduleRepo;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.findAll());
        return "auth/sub_module_index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("submodule", new SubModules());
        model.addAttribute("modules", moduleRepo.findAll());
        return "auth/sub_module_form";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@Valid @ModelAttribute("submodule") SubModules sm,
            BindingResult result,
            Model model,
            RedirectAttributes redirect) {

        model.addAttribute("modules", moduleRepo.findAll()); // preload modules always

        if (result.hasErrors()) {
            model.addAttribute("list", service.findAll());
            return "auth/sub_module_form";
        }

        try {
            // If ID is null → Create
            // If ID exists → Update
            if (sm.getId() == null) {
                service.save(sm);
                redirect.addFlashAttribute("success", "SubModule created successfully!");
            } else {
                service.update(sm.getId(), sm);
                redirect.addFlashAttribute("success", "SubModule updated successfully!");
            }

        } catch (DuplicateResourceException ex) {
            model.addAttribute("list", service.findAll());
            result.rejectValue("slug", null, ex.getMessage());
            return "auth/sub_module_form";
        }

        return "redirect:/submodules/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        SubModules sm = service.findById(id);
        model.addAttribute("submodule", sm);
        model.addAttribute("modules", moduleRepo.findAll());
        return "auth/sub_module_form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {

        try {
            service.delete(id);
            redirect.addFlashAttribute("success", "SubModule deleted successfully!");
        } catch (IllegalStateException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/submodules/list";
    }
}
