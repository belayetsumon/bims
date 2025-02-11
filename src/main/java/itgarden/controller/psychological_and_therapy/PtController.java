/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.psychological_and_therapy;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.rehabilitations.R_PT;
import itgarden.repository.rehabilitations.DegenerativeDiseasesRepository;
import itgarden.repository.rehabilitations.JointMobilityRepository;
import itgarden.repository.rehabilitations.MucsuloskeletalRepository;
import itgarden.repository.rehabilitations.R_PTRepository;
import itgarden.repository.rehabilitations.TendernessRepository;
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
@RequestMapping("/pt")
public class PtController {
    
    @Autowired
    R_PTRepository r_PTRepository;
    
    @Autowired
    JointMobilityRepository jointMobilityRepository;
    
    @Autowired
    MucsuloskeletalRepository mucsuloskeletalRepository;
    
    @Autowired
    DegenerativeDiseasesRepository degenerativeDiseasesRepository;
    
    @Autowired
    TendernessRepository tendernessRepository;
    
    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_PT r_PT) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(mid);
        r_PT.setMotherMasterCode(motherMasterData);
        
        model.addAttribute("form_title", "  Mother PT");
        
        model.addAttribute("jointMobility", jointMobilityRepository.findAll());
        
        model.addAttribute("mucsuloskeletal", mucsuloskeletalRepository.findAll());
        
        model.addAttribute("degenerativeDiseases", degenerativeDiseasesRepository.findAll());
        
        model.addAttribute("tenderness", tendernessRepository.findAll());
        
        model.addAttribute("pain", Yes_No.values());
        
        model.addAttribute("PhysicalDisability", Yes_No.values());
        
        model.addAttribute("previouslyTherapyTaken", Yes_No.values());
        
        return "rehabilitations/therapeuticsessions/mpt";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_PT r_PT) {
        
        model.addAttribute("r_PT", r_PTRepository.findById(id).orElse(null));
        
        model.addAttribute("form_title", "  Mother PT");
        model.addAttribute("tenderness", tendernessRepository.findAll());
        
        model.addAttribute("pain", Yes_No.values());
        
        model.addAttribute("jointMobility", jointMobilityRepository.findAll());
        
        model.addAttribute("mucsuloskeletal", mucsuloskeletalRepository.findAll());
        
        model.addAttribute("degenerativeDiseases", degenerativeDiseasesRepository.findAll());
        
        model.addAttribute("PhysicalDisability", Yes_No.values());
        
        model.addAttribute("previouslyTherapyTaken", Yes_No.values());
        
        return "rehabilitations/therapeuticsessions/mpt";
    }
    
    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_PT r_PT, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(mid);
            r_PT.setMotherMasterCode(motherMasterData);
            
            model.addAttribute("form_title", "  Mother PT");
            
            model.addAttribute("pain", Yes_No.values());
            model.addAttribute("tenderness", tendernessRepository.findAll());
            
            model.addAttribute("jointMobility", jointMobilityRepository.findAll());
            
            model.addAttribute("mucsuloskeletal", mucsuloskeletalRepository.findAll());
            
            model.addAttribute("degenerativeDiseases", degenerativeDiseasesRepository.findAll());
            
            model.addAttribute("PhysicalDisability", Yes_No.values());
            
            model.addAttribute("previouslyTherapyTaken", Yes_No.values());
            
            return "rehabilitations/therapeuticsessions/mpt";
        }
        r_PTRepository.save(r_PT);
        return "redirect:/therapeuticsessions/index/{mid}";
    }
    
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_PT r_PT, RedirectAttributes redirectAttrs) {
        
        Optional<R_PT> optionalR_PT = r_PTRepository.findById(id);
        r_PT = optionalR_PT.orElse(null);
        
        redirectAttrs.addAttribute("mid", r_PT.motherMasterCode.getId());
        
        r_PTRepository.deleteById(id);
        
        return "redirect:/therapeuticsessions/index/{mid}";
    }
    
}
