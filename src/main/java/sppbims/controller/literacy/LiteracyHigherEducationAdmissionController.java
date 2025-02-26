package sppbims.controller.literacy;

import sppbims.model.literacy.LiteracyHigherEducationAdmission;
import sppbims.model.literacy.ResultEnum;
import sppbims.repository.homevisit.EducationLevelRepository;
import sppbims.repository.literacy.LiteracyHigherEducationAdmissionRepository;
import sppbims.services.literacy.LiteracyHigherEducationAdmissionService;
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
@RequestMapping("/literacyhighereducationadmission")
public class LiteracyHigherEducationAdmissionController {

    @Autowired
    LiteracyHigherEducationAdmissionRepository literacyHigherEducationAdmissionRepository;

    @Autowired
    LiteracyHigherEducationAdmissionService literacyHigherEducationAdmissionService;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @RequestMapping("/add")
    public String index(Model model, LiteracyHigherEducationAdmission literacyHigherEducationAdmission) {
        model.addAttribute("educationLavel", educationLevelRepository.findAll());
        model.addAttribute("motherId", literacyHigherEducationAdmissionService.getMotherMasterDataList());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_literacyhighereducation";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid LiteracyHigherEducationAdmission literacyHigherEducationAdmission, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("educationLavel", educationLevelRepository.findAll());
            model.addAttribute("motherId", literacyHigherEducationAdmissionService.getMotherMasterDataList());
            model.addAttribute("status", ResultEnum.values());

            return "literacy/add_literacyhighereducation";
        }
        literacyHigherEducationAdmissionRepository.save(literacyHigherEducationAdmission);

        return "redirect:/literacyhighereducationadmission/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", literacyHigherEducationAdmissionService.getAllHigherEducationAdmissionData());
        return "literacy/literacyhighereducation_list";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, LiteracyHigherEducationAdmission literacyHigherEducationAdmission, Model model) {
        model.addAttribute("educationLavel", educationLevelRepository.findAll());
        model.addAttribute("literacyHigherEducationAdmission", literacyHigherEducationAdmissionRepository.findById(id).orElse(null));
        model.addAttribute("motherId", literacyHigherEducationAdmissionService.getMotherMasterDataList());
        model.addAttribute("status", ResultEnum.values());
        return "literacy/add_literacyhighereducation";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, LiteracyHigherEducationAdmission literacyHigherEducationAdmission, RedirectAttributes redirectAttrs) {
        literacyHigherEducationAdmission = literacyHigherEducationAdmissionRepository.findById(id).orElse(null);
//        redirectAttrs.addAttribute("m_id", shortTermImpactMeasurement.motherMasterCode.getId());
        literacyHigherEducationAdmissionRepository.deleteById(id);
        return "redirect:/literacyhighereducationadmission/list";
    }

}
