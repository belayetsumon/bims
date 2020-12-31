/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller 
@RequestMapping("/lookupdashboards")
public class LookupDashbords {
    
    @GetMapping("/homevisit")
    public String index( Model model) {
        return "/homevisit/lookup/index";
    }
    
    @GetMapping("/administration")
    public String administration( Model model) {
        return "/homevisit/lookup/admin_look_up";
    }
}
