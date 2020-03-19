/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.Gender;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.homevisit.EthinicIdentityRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.homevisit.ReligionRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller

@RequestMapping("/childrendetails")
public class ChildrenDetailsController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @Autowired
    ReligionRepository religionRepository;

    @Autowired
    EthinicIdentityRepository ethinicIdentityRepository;

    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    EducationTypeRepository educationTypeRepository;

    @RequestMapping("/mothersearch")
    public String motherSearch(Model model) {
        model.addAttribute("list", motherMasterDataRepository.findAll());
        return "homevisit/childrendetails/mothersearch";
    }

    @RequestMapping("/index/{m_id}")

    public String index(Model model, @PathVariable Long m_id, M_Child_info m_Child_info) {
        
        model.addAttribute("mInfo", m_id);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        model.addAttribute("list", m_Child_infoRepository.findBymotherMasterCode(motherMasterData));
        
        return "homevisit/motherdetails/childindex";
    }

    @RequestMapping("/create/{m_id}")
    public String add(Model model, @PathVariable Long m_id, M_Child_info m_Child_info) {

        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData = motherMasterDataRepository.findOne(m_id);

//       int totalChild = m_Child_infoRepository.findBymotherMasterCode(motherMasterData).size();
//
//        motherMasterData = motherMasterDataRepository.findOne(m_id);
//        int eligiblechildren = motherMasterData.getNumberOfEligibleChildren();
//        System.out.println(" Total child   "+totalChild +"Eligible child"+eligiblechildren);
        /**
         * Mother Default value**
         */
        m_Child_info.setMotherMasterCode(motherMasterData);
        m_Child_info.setMotherName(motherMasterData.motherName);
        m_Child_info.setFathersName(motherMasterData.husbandsName);
        m_Child_info.setPrimeFamilyMemberName(motherMasterData.primeFamilyMemberName);

        /**
         * Child Id create**
         */
        String mcode = motherMasterData.motherMasterCode;
        int totalchild = m_Child_infoRepository.findBymotherMasterCode(motherMasterData).size();
        System.out.println(" total child" + totalchild);
        StringBuilder c_id = new StringBuilder("");
        c_id.append(mcode.substring(0, mcode.length() - 1));
        c_id.append(totalchild + 1);
        m_Child_info.setChildMasterCode(String.valueOf(c_id));

        model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("religion", religionRepository.findAll());

        model.addAttribute("gender", Gender.values());
        model.addAttribute("work", Yes_No.values());
        
         model.addAttribute("eligibility", Eligibility.values());

        return "homevisit/motherdetails/createchild";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, @Valid M_Child_info m_Child_info, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);

        int totalChild = m_Child_infoRepository.findBymotherMasterCode(motherMasterData).size();

        motherMasterData = motherMasterDataRepository.findOne(m_id);
        int eligiblechildren = motherMasterData.getNumberOfEligibleChildren();

        if (totalChild >= eligiblechildren&& m_Child_info.getId()==null) {
            redirectAttrs.addFlashAttribute("error", "Already All Eligible Childen Inserted");
            return "redirect:/childrendetails/index/{m_id}";
        } else {
            if (bindingResult.hasErrors()) {

                motherMasterData = motherMasterDataRepository.findOne(m_id);

                /**
                 * Mother Default value**
                 */
                m_Child_info.setMotherMasterCode(motherMasterData);
                m_Child_info.setMotherName(motherMasterData.motherName);
                m_Child_info.setFathersName(motherMasterData.fathersName);
                m_Child_info.setPrimeFamilyMemberName(motherMasterData.primeFamilyMemberName);

                /**
                 * Child Id create**
                 */
                String mcode = motherMasterData.motherMasterCode;
                int totalchild = m_Child_infoRepository.findBymotherMasterCode(motherMasterData).size();
                System.out.println(" total child" + totalchild);
                StringBuilder c_id = new StringBuilder("");
                c_id.append(mcode.substring(0, mcode.length() - 1));
                c_id.append(totalchild + 1);
                m_Child_info.setChildMasterCode(String.valueOf(c_id));

                model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

                model.addAttribute("educationLevel", educationLevelRepository.findAll());

                model.addAttribute("educationType", educationTypeRepository.findAll());

                model.addAttribute("religion", religionRepository.findAll());

                model.addAttribute("gender", Gender.values());
                model.addAttribute("work", Yes_No.values());
                
                 model.addAttribute("eligibility", Eligibility.values());

                return "homevisit/motherdetails/createchild";

            }
            m_Child_infoRepository.save(m_Child_info);
            return "redirect:/childrendetails/index/{m_id}";
        }
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, M_Child_info m_Child_info) {
        model.addAttribute("m_Child_info", m_Child_infoRepository.findOne(id));

        model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("religion", religionRepository.findAll());

        model.addAttribute("gender", Gender.values());
        model.addAttribute("work", Yes_No.values());
        
         model.addAttribute("eligibility", Eligibility.values());

        return "homevisit/motherdetails/createchild";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model, M_Child_info m_Child_info, RedirectAttributes redirectAttrs) {

        m_Child_info = m_Child_infoRepository.findOne(id);

        redirectAttrs.addAttribute("m_id", m_Child_info.motherMasterCode.getId());

        m_Child_infoRepository.delete(id);

        return "redirect:/childrendetails/index/{m_id}";
    }

}
