/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.psychological_and_therapy;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_OtChild;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.rehabilitations.DiagonosisRepository;
import itgarden.repository.rehabilitations.R_OTRepository;
import itgarden.repository.rehabilitations.R_OtChildRepository;
import itgarden.repository.rehabilitations.SessionTypeRepository;
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
@RequestMapping("/otchild")
public class OtChildController {
    
    @Autowired
    R_OTRepository r_OTRepository;
    
    @Autowired
    R_OtChildRepository r_OtChildRepository;
    
    @Autowired
    SessionTypeRepository sessionTypeRepository;
    
    @Autowired
    DiagonosisRepository diagonosisRepository;
    
    @Autowired
    M_Child_infoRepository m_Child_infoRepository;
    
    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;
    
    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_OtChild r_OtChild) {
        
        MotherMasterData motherMasterData = new MotherMasterData();
        
        motherMasterData.setId(mid);
        
        r_OtChild.setMotherMasterCode(motherMasterData);
        
        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        
        model.addAttribute("form_title", "  ");
        
        model.addAttribute("sessionType", sessionTypeRepository.findAll());
        
        model.addAttribute("diagonosis", diagonosisRepository.findAll());
        
        return "rehabilitations/therapeuticsessions/cot";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_OtChild r_OtChild) {
        
        Optional<R_OtChild> optionalr_OtChild = r_OtChildRepository.findById(id);
        r_OtChild = optionalr_OtChild.orElse(null);
        
        model.addAttribute("r_OtChild", r_OtChild);
        
        MotherMasterData motherMasterData = new MotherMasterData();
        
        motherMasterData.setId(r_OtChild.getMotherMasterCode().getId());
        
        r_OtChild.setMotherMasterCode(motherMasterData);
        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
        
        model.addAttribute("sessionType", sessionTypeRepository.findAll());
        
        model.addAttribute("diagonosis", diagonosisRepository.findAll());
        
        return "rehabilitations/therapeuticsessions/cot";
    }
    
    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_OtChild r_OtChild, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("form_title", " ");
            MotherMasterData motherMasterData = new MotherMasterData();
            
            motherMasterData.setId(mid);
            
            r_OtChild.setMotherMasterCode(motherMasterData);
            model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
            model.addAttribute("sessionType", sessionTypeRepository.findAll());
            
            model.addAttribute("diagonosis", diagonosisRepository.findAll());
            
            return "rehabilitations/therapeuticsessions/cot";
        }
        
        r_OtChildRepository.save(r_OtChild);
        
        return "redirect:/therapeuticsessions/index/{mid}";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_OtChild r_OtChild, RedirectAttributes redirectAttrs) {
        
        Optional<R_OtChild> optionalr_OtChild = r_OtChildRepository.findById(id);
        
        redirectAttrs.addAttribute("mid", r_OtChild.motherMasterCode.getId());
        
        r_OtChildRepository.deleteById(id);
        
        return "redirect:/therapeuticsessions/index/{mid}";
    }
    
}
