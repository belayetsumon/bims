/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.school;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.school.EligibilityStudent;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.school.EligibilityStudentRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/eligibility")
public class EligibilityController {

    @Autowired
    EligibilityStudentRepository eligibilityStudentRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/eligibility")
    public String index(Model model) {

        model.addAttribute("clildlist", eligibilityStudentRepository.findAll());

        return "school/eligibilityindex";
    }

    @RequestMapping("/add/{c_id}")
    public String add(Model model, @PathVariable Long c_id, EligibilityStudent eligibilityStudent) {

        M_Child_info m_Child_info = m_Child_infoRepository.getOne(c_id);

        eligibilityStudent.setChildMasterCode(m_Child_info);

        return "school/addeligibility";
    }

    @RequestMapping("/save/{c_id}")
    public String save(Model model, @PathVariable Long c_id, @Valid EligibilityStudent eligibilityStudent, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            
            M_Child_info m_Child_info = m_Child_infoRepository.getOne(c_id);

            eligibilityStudent.setChildMasterCode(m_Child_info);

            return "school/addeligibility";
        }
        
        eligibilityStudentRepository.save(eligibilityStudent);

        return "redirect:/eligibility/eligibility";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, EligibilityStudent eligibilityStudent, RedirectAttributes redirectAttrs) {
        eligibilityStudent = eligibilityStudentRepository.findOne(id);
        redirectAttrs.addAttribute("id", eligibilityStudent.getChildMasterCode().getId());
        eligibilityStudentRepository.delete(id);
        return "redirect:/eligibility/eligibility";
    }

}
