/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.reintegration_checklist;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.pre_reintegration_checklist.Complete_incomplete;
import itgarden.model.pre_reintegration_checklist.ReintegrationCheckList;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.reintegration_checklist.ReintegrationCheckListRepository;
import jakarta.validation.Valid;
import java.util.Optional;
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
 * @author User
 */
@Controller
@RequestMapping("/reintegrationcheklist")
public class RreintegrationCheckList_Controller {
    
    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;
    
    @Autowired
    ReintegrationCheckListRepository reintegrationCheckListRepository;
    
    @RequestMapping("/motherlist")
    public String motherlist(Model model) {
    //    model.addAttribute("list", motherMasterDataRepository.findByPreReintegrationVisitIsNotNullAndReintegrationCheckListIsNullOrderByIdDesc());
        return "reintegration_checklist/mothersearch";
    }
    
    @RequestMapping("/index")
    public String add(Model model) {
        model.addAttribute("list", reintegrationCheckListRepository.findAll());
        return "reintegration_checklist/index";
    }
    
    @RequestMapping("/details/{m_id}")
    public String details(Model model, @PathVariable Long m_id, ReintegrationCheckList reintegrationCheckList) {
        
        model.addAttribute("list", reintegrationCheckListRepository.getOne(m_id));
        
        return "reintegration_checklist/details";
    }
    
    @RequestMapping("/add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, ReintegrationCheckList reintegrationCheckList) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        
        reintegrationCheckList.setMotherMasterCode(motherMasterData);
        
        model.addAttribute("yes_no", Yes_No.values());
        
        model.addAttribute("complete_incomplete", Complete_incomplete.values());
        
        return "reintegration_checklist/reintegrationcheklist";
    }
    
    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, ReintegrationCheckList reintegrationCheckList, Model model) {
        model.addAttribute("reintegrationCheckList", reintegrationCheckListRepository.findById(id));
        model.addAttribute("yes_no", Yes_No.values());
        model.addAttribute("complete_incomplete", Complete_incomplete.values());
        return "reintegration_checklist/reintegrationcheklist";
    }
    
    @RequestMapping("/save")
    public String save(Model model, @Valid ReintegrationCheckList reintegrationCheckList, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("yes_no", Yes_No.values());
            model.addAttribute("complete_incomplete", Complete_incomplete.values());
            
            return "reintegration_checklist/reintegrationcheklist";
        }
        reintegrationCheckListRepository.save(reintegrationCheckList);
        
        return "redirect:/reintegrationcheklist/index";
    }
    
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, ReintegrationCheckList reintegrationCheckList, RedirectAttributes redirectAttrs) {
        Optional<ReintegrationCheckList> optionalreintegrationCheckList = reintegrationCheckListRepository.findById(id);
        
        ReintegrationCheckList ReintegrationCheckList = optionalreintegrationCheckList.orElse(null);
        
        redirectAttrs.addAttribute("m_id", reintegrationCheckList.getMotherMasterCode().getId());
        
        reintegrationCheckListRepository.deleteById(id);
        
        return "redirect:/reintegrationcheklist/index";
    }
}
