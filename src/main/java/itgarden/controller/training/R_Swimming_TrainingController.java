/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.training;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.observation.O_ChildAdmission;
import itgarden.model.rehabilitations.GraduateStatus;
import itgarden.model.rehabilitations.R_Swimming;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.rehabilitations.R_IGA_TrainingRepository;
import itgarden.repository.rehabilitations.R_SwimmingRepository;
import itgarden.repository.rehabilitations.TrainingNameRepository;
import itgarden.services.observation.O_ChildAdmissionService;
import itgarden.services.reintegration_release.ReleaseChildService;
import itgarden.services.training.R_SwimmingService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/swimming")
public class R_Swimming_TrainingController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_IGA_TrainingRepository r_IGA_TrainingRepository;

    @Autowired
    TrainingNameRepository trainingNameRepository;

    @Autowired
    R_SwimmingRepository r_SwimmingRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Autowired
    O_ChildAdmissionService o_ChildAdmissionService;

    @Autowired
    R_SwimmingService r_SwimmingService;

    @Autowired
    ReleaseChildService releaseChildService;

    @RequestMapping("/childlist")
    public String index(Model model, R_Swimming r_Swimming) {

        List<Long> releasedChildList = releaseChildService.allReleasedChildIdList();

        List<Long> swimmingService = r_SwimmingService.swimmingCompletedChildIdList();
        releasedChildList.addAll(swimmingService);

        List<Map<String, Object>> childAdmissionList = o_ChildAdmissionService.allAdmitedChildReportList()
                .stream()
                .filter(childlist -> {
                    Object admissionIdObj = childlist.get("admissionId");
                    return admissionIdObj != null && !releasedChildList.contains(Long.valueOf(admissionIdObj.toString()));
                })
                .collect(Collectors.toList());

        model.addAttribute("clildlist", childAdmissionList);
        return "training/radmissionindex";
    }

    @RequestMapping("/index")
    public String list(Model model, R_Swimming r_Swimming) {
        model.addAttribute("clildlist", r_SwimmingService.swimmingCompletedList());
        return "training/swimlist";
    }

    @RequestMapping("/create/{cid}")
    public String create(Model model, @PathVariable Long cid, R_Swimming r_Swimming) {
        Optional<M_Child_info> optionalchildMasterCode = m_Child_infoRepository.findById(cid);
        M_Child_info m_Child_info = optionalchildMasterCode.orElse(null);
        r_Swimming.setName(m_Child_info.getName());
        r_Swimming.setChildMasterCode(m_Child_info);
        model.addAttribute("graduateStatusList", GraduateStatus.values());
        return "rehabilitations/training/add_swim";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_Swimming r_Swimming) {
        model.addAttribute("r_Swimming", r_SwimmingRepository.findById(id).orElse(null));
        model.addAttribute("graduateStatusList", GraduateStatus.values());
        return "rehabilitations/training/add_swim";
    }

    @RequestMapping("/save/{cid}")
    public String save(Model model, @PathVariable Long cid, @Valid R_Swimming r_Swimming, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            Optional<M_Child_info> optionalchildMasterCode = m_Child_infoRepository.findById(cid);

            M_Child_info m_Child_info = optionalchildMasterCode.orElse(null);
            r_Swimming.setName(m_Child_info.getName());
            r_Swimming.setChildMasterCode(m_Child_info);

            model.addAttribute("graduateStatusList", GraduateStatus.values());

            return "rehabilitations/training/add_swim";
        }
        r_SwimmingRepository.save(r_Swimming);

        return "redirect:/swimming/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_Swimming r_Swimming, RedirectAttributes redirectAttrs) {

        r_SwimmingRepository.deleteById(id);

        return "redirect:/swimming/index";

    }

}
