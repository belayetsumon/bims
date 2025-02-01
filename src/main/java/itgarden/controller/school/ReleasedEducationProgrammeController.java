/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package itgarden.controller.school;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/releasededucationprogramme")
public class ReleasedEducationProgrammeController {
    
    @RequestMapping("/releasedfromedu")
    public String indrx(Model model) {
        model.addAttribute("attribute", "value");
      return "school/releasedfromedu";
    }
    
    
    @RequestMapping("/releasedfromedulist")
    public String list(Model model) {
        model.addAttribute("attribute", "value");
      return "school/releasedchildlist";
    }
    
}
