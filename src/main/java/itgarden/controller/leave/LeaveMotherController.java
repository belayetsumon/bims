package itgarden.controller.leave;

import itgarden.model.leave.LeaveMother;
import itgarden.repository.leave.LeaveMotherRepository;
import itgarden.services.observation.O_MAddmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/leavemother")
public class LeaveMotherController {

    @Autowired
    LeaveMotherRepository leaveMotherRepository;

    @Autowired
    O_MAddmissionService addmissionService;

    @RequestMapping("/add")
    public String index(Model model, LeaveMother leaveMother) {
        
       model.addAttribute("motherId", addmissionService.getMotherMasterDataDTOs());

        return "leave/add_mother_leave";
    }

    @RequestMapping("/list")
    public String list(Model model) {

        model.addAttribute("list", leaveMotherRepository.findAll());
        return "leave/leavelist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LeaveMother leaveMother, Model model) {

        model.addAttribute("leaveMother", leaveMotherRepository.findById(id).orElse(null));
        return "leave/add_mother_leave";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid LeaveMother leaveMother, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "leave/add_mother_leave";
        }
        leaveMotherRepository.save(leaveMother);

        return "redirect:/leavemother/list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LeaveMother leaveMother, RedirectAttributes redirectAttrs) {
        leaveMother = leaveMotherRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        leaveMotherRepository.deleteById(id);
        return "redirect:/leavemother/list";
    }

}
