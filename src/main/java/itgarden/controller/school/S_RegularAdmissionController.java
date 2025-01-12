/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.school;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.school.S_RegularAdmissionClass;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.school.S_RegularAdmissionClassRepository;
import itgarden.services.homevisit.M_Child_infoService;
import itgarden.services.school.S_RegularAdmissionClassService;
import jakarta.validation.Valid;
import java.util.Optional;
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
@RequestMapping("/sregularadmission")
public class S_RegularAdmissionController {

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    EducationTypeRepository educationTypeRepository;

    @Autowired
    S_RegularAdmissionClassRepository s_RegularAdmissionClassRepository;

    @Autowired
    M_Child_infoService m_Child_infoService;

    @Autowired
    S_RegularAdmissionClassService s_RegularAdmissionClassService;

    @RequestMapping("/school_admitted_student_list")
    public String index(Model model) {
        //  model.addAttribute("clildlist", m_Child_infoRepository.findByRegularAdmissionClassIsNotNullAndDiscontinuityIsNull());
        model.addAttribute("clildlist", s_RegularAdmissionClassService.findWithCriteria());
        return "school/radmissionindex";
    }

    @RequestMapping("/pendding_for_regular_admission")
    public String allchild(Model model) {
        //model.addAttribute("clildlist", m_Child_infoRepository.findByChildAdmissionIsNotNullAndEligibilityStudentIsNullAndRegularAdmissionClassIsNullAndReleaseChildIsNull());
        model.addAttribute("clildlist", m_Child_infoService.findNotSchoolAdmitedChild());
        return "school/pendding_for_regular_admission";

    }

    @RequestMapping("/create/{id}")
    public String create(Model model, @PathVariable Long id, S_RegularAdmissionClass s_RegularAdmissionClass) {
        M_Child_info m_Child_info = new M_Child_info();
        m_Child_info.setId(id);
        s_RegularAdmissionClass.setChildMasterCode(m_Child_info);

        model.addAttribute("admissionClass", educationLevelRepository.findAll());

        model.addAttribute("lastAttendedEducationType", educationTypeRepository.findAll());

        return "school/sregularadmission";

    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, S_RegularAdmissionClass s_RegularAdmissionClass) {

        model.addAttribute("s_RegularAdmissionClass", s_RegularAdmissionClassRepository.findById(id).orElse(null));

        model.addAttribute("admissionClass", educationLevelRepository.findAll());

        model.addAttribute("lastAttendedEducationType", educationTypeRepository.findAll());

        return "school/sregularadmission";

    }

    @RequestMapping("/save")
    public String save(Model model, @Valid S_RegularAdmissionClass s_RegularAdmissionClass, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("admissionClass", educationLevelRepository.findAll());

            model.addAttribute("lastAttendedEducationType", educationTypeRepository.findAll());

            return "school/sregularadmission";
        }

        s_RegularAdmissionClassRepository.save(s_RegularAdmissionClass);
        return "redirect:/sregularadmission/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, S_RegularAdmissionClass s_RegularAdmissionClass, RedirectAttributes redirectAttrs) {

        Optional<S_RegularAdmissionClass> optionals_RegularAdmissionClass = s_RegularAdmissionClassRepository.findById(id);
        s_RegularAdmissionClass = optionals_RegularAdmissionClass.orElse(null);
        redirectAttrs.addAttribute("id", s_RegularAdmissionClass.getChildMasterCode().getId());
        s_RegularAdmissionClassRepository.deleteById(id);
        return "redirect:/sregularadmission/index";
    }

}
