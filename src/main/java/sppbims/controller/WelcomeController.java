/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
public class WelcomeController {

    @RequestMapping(value = {"/"})
    public String index(Model model) {
        //return "welcome";
         return "redirect:/dashboards/index";
    }

}
