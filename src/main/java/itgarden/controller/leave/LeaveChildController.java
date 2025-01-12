package itgarden.controller.leave;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.leave.LeaveChild;
import itgarden.model.leave.LeaveTypeEnum;
import itgarden.repository.leave.LeaveChildRepository;
import itgarden.services.leave.LeaveChildService;
import itgarden.services.observation.O_ChildAdmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author libertyerp_local
 */
@Controller
@RequestMapping("/leavechild")
public class LeaveChildController {

    @Autowired
    LeaveChildRepository leaveChildRepository;

    @Autowired
    O_ChildAdmissionService o_ChildAdmissionService;
    
    @Autowired
    LeaveChildService leaveChildService;

    @RequestMapping("/add")
    public String add(Model model, LeaveChild leaveChild) {
        model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());
        return "leave/add_child_leave";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LeaveChild leaveChild, Model model) {

        model.addAttribute("leaveMother", leaveChildRepository.findById(id).orElse(null));

        model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());

        return "leave/add_mother_leave";
    }

//    
    @RequestMapping("/save")
    public String save(Model model, @Valid LeaveChild leaveChild, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("childId", o_ChildAdmissionService.all_Admited_Child_Report_Execlude_Released_ChildList());
            return "leave/add_mother_leave";
        }

        long motherId = o_ChildAdmissionService.getMotherIdByChildId(leaveChild.getId());

        MotherMasterData motherMasterCode = new MotherMasterData();

        motherMasterCode.setId(motherId);

        leaveChild.setMotherMasterCode(motherMasterCode);

        leaveChildRepository.save(leaveChild);

        return "redirect:/leavemother/list";
    }
//    

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LeaveChild leaveChild, RedirectAttributes redirectAttrs) {
        leaveChildRepository.deleteById(id);
        return "redirect:/leavemother/list";
    }
    
    
    
       @RequestMapping("/list")
    public String list(Model model,
            @RequestParam(name = "leaveTypeEnum", required = false) LeaveTypeEnum leaveTypeEnum
    ) {
        model.addAttribute("leavetype", LeaveTypeEnum.values());
        model.addAttribute("list", leaveChildService.leaveMothersList(
                leaveTypeEnum
        ));
        
        return "leave/childleavelist";
    }

}
