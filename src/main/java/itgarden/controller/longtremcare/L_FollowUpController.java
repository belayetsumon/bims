/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.longtremcare;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
  @RequestMapping("/lfollowup")
public class L_FollowUpController {
    
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }
    
}
