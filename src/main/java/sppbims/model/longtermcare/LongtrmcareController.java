/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.longtermcare;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/longtrmcare")
public class LongtrmcareController {

    @RequestMapping("/report")
    public String report(Model model) {
        return "longtermcare/report/reportlist";
    }

}
