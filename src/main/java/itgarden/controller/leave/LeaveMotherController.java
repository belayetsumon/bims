package itgarden.controller.leave;

import itgarden.model.homevisit.DTO.MotherMasterDataDTO;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.leave.LeaveChild;
import itgarden.model.leave.LeaveMother;
import itgarden.model.leave.LeaveTypeEnum;
import itgarden.repository.leave.LeaveChildRepository;
import itgarden.repository.leave.LeaveMotherRepository;
import itgarden.services.leave.LeaveChildService;
import itgarden.services.leave.LeaveMotherService;
import itgarden.services.observation.O_MAddmissionService;
import itgarden.services.reintegration_release.ReleaseMotherService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/leavemother")
public class LeaveMotherController {
    
    @Autowired
    LeaveMotherRepository leaveMotherRepository;
    
    @Autowired
    O_MAddmissionService addmissionService;
    
    @Autowired
    ReleaseMotherService releaseMotherService;
    @Autowired
    LeaveMotherService leaveMotherService;
    
    @Autowired
    LeaveChildService leaveChildService;
    
    @Autowired
    LeaveChildRepository leaveChildRepository;

    /// mother admission id excluded from realesed id
    public List<MotherMasterDataDTO> admitedMotherIdListExcludeRealesedMotherId() {
        
        List<Long> relasedid = releaseMotherService.allReleasedMotherIdList();
        
        List<MotherMasterDataDTO> motherAdmitedIdList = addmissionService.getMotherMasterDataDTOs()
                .stream().filter(motherMasterDataDTO -> !relasedid.contains(motherMasterDataDTO.getId())).collect(Collectors.toList());
        return motherAdmitedIdList;
    }
    
    @RequestMapping("/add")
    public String index(Model model, LeaveMother leaveMother) {
        
        model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());
        
        return "leave/add_mother_leave";
    }
    
    @RequestMapping("/list")
    public String list(Model model,
            @RequestParam(name = "leaveTypeEnum", required = false) LeaveTypeEnum leaveTypeEnum
    ) {
        model.addAttribute("leavetype", LeaveTypeEnum.values());
        model.addAttribute("list", leaveMotherService.leaveMothersList(
                leaveTypeEnum
        ));
        
        return "leave/leavelist";
    }
    
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LeaveMother leaveMother, Model model) {
        
        model.addAttribute("leaveMother", leaveMotherRepository.findById(id).orElse(null));
        model.addAttribute("motherId", admitedMotherIdListExcludeRealesedMotherId());
        return "leave/add_mother_leave";
    }
    
    @RequestMapping("/save")
    public String save(Model model, @Valid LeaveMother leaveMother, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("motherId", addmissionService.getMotherMasterDataDTOs());
            return "leave/add_mother_leave";
        }
        leaveMotherRepository.save(leaveMother);
        
       // long lastmotherLeaveId = leaveMotherRepository.findTopByOrderByIdDesc();
        
       // leaveChildService.saveChild(leaveMother.getMotherMasterCode().getId(), lastmotherLeaveId);
        
        
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
