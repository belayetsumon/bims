/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.auth;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.Yes_No;
import itgarden.repository.auth.RoleRepository;
import itgarden.repository.auth.UsersRepository;
import itgarden.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", usersRepository.findAll());
        return "auth/index";
    }

    @RequestMapping("/registration")
    public String registration(Model model, Users users) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("status", Yes_No.values());
        return "auth/registration";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, Users users, @PathVariable Long id) {
        model.addAttribute("users", usersRepository.findById(id).orElse(null));
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("status", Yes_No.values());
        return "auth/registration";
    }

    
    @RequestMapping("/save")
    //@Transactional
    public String save(Model model, @RequestParam(value = "password2", required = false) String password2, @Valid Users users, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("status", Yes_No.values());
            return "auth/registration";
        }

        // users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        try {

            if (users.getPassword().isEmpty() && password2 != null && users.getId() != null) {

                users.setPassword(password2);
            } else {

                users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
            }

            usersRepository.save(users);
            return "redirect:/users/index";

        } catch (Exception e) {
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("status", Yes_No.values());
            redirectAttributes.addFlashAttribute("message", e);
            model.addAttribute("message", e);
            return "auth/registration";
        }
    }
    
    

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        usersRepository.deleteById(id);
        return "redirect:/users/index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("attribute", "value");
        return "auth/login";
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//
//        model.addAttribute("logout", "You have been successfully logout");
//        redirectAttributes.addFlashAttribute("logout", "You have been successfully logout");
//        return "redirect:/users/login";
//    }
    
    
    @RequestMapping("/logout")
    public String logout(Model model,HttpServletRequest request, HttpServletResponse response) {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
         return "redirect:/users/login";
    }
    

}
