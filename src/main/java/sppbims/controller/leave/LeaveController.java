/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.leave;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller

@RequestMapping("/leave")
public class LeaveController {

    @RequestMapping("/index")
    public String motherSearch(Model model) {
        return "leave/index";
    }

}
