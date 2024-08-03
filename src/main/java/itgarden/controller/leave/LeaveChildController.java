package itgarden.controller.leave;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/leavechild")
public class LeaveChildController {
    
    @RequestMapping("/url")
    public String index(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }
    
}
