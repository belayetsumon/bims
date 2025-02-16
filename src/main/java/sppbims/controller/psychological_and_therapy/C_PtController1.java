/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.psychological_and_therapy;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Yes_No;
import sppbims.model.rehabilitations.R_OtChild;
import sppbims.model.rehabilitations.R_PT;
import sppbims.model.rehabilitations.R_PT_Child;
import sppbims.repository.homevisit.M_Child_infoRepository;
import sppbims.repository.rehabilitations.DegenerativeDiseasesRepository;
import sppbims.repository.rehabilitations.JointMobilityRepository;
import sppbims.repository.rehabilitations.MucsuloskeletalRepository;
import sppbims.repository.rehabilitations.R_PT_ChildRepository;
import sppbims.repository.rehabilitations.TendernessRepository;
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
@RequestMapping("/cpt")
public class C_PtController1 {

    @Autowired
    JointMobilityRepository jointMobilityRepository;

    @Autowired
    MucsuloskeletalRepository mucsuloskeletalRepository;

    @Autowired
    DegenerativeDiseasesRepository degenerativeDiseasesRepository;

    @Autowired
    TendernessRepository tendernessRepository;

    @Autowired
    R_PT_ChildRepository r_PT_ChildRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_PT_Child r_PT_Child) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(mid);

        r_PT_Child.setMotherMasterCode(motherMasterData);

        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

        model.addAttribute("form_title", "  Mother PT");

        model.addAttribute("jointMobility", jointMobilityRepository.findAll());

        model.addAttribute("mucsuloskeletal", mucsuloskeletalRepository.findAll());

        model.addAttribute("degenerativeDiseases", degenerativeDiseasesRepository.findAll());

        model.addAttribute("tenderness", tendernessRepository.findAll());

        model.addAttribute("pain", Yes_No.values());

        model.addAttribute("PhysicalDisability", Yes_No.values());

        model.addAttribute("previouslyTherapyTaken", Yes_No.values());

        return "rehabilitations/therapeuticsessions/cpt";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_PT_Child r_PT_Child) {

        model.addAttribute("r_PT", r_PT_ChildRepository.findById(id));

        Optional<R_PT_Child> optionalr_OtChild = r_PT_ChildRepository.findById(id);

        r_PT_Child = optionalr_OtChild.orElse(null);

        model.addAttribute("r_PT_Child", r_PT_Child);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(r_PT_Child.getMotherMasterCode().getId());

        r_PT_Child.setMotherMasterCode(motherMasterData);

        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

        model.addAttribute("form_title", "  Mother PT");
        model.addAttribute("tenderness", tendernessRepository.findAll());

        model.addAttribute("pain", Yes_No.values());

        model.addAttribute("jointMobility", jointMobilityRepository.findAll());

        model.addAttribute("mucsuloskeletal", mucsuloskeletalRepository.findAll());

        model.addAttribute("degenerativeDiseases", degenerativeDiseasesRepository.findAll());

        model.addAttribute("PhysicalDisability", Yes_No.values());

        model.addAttribute("previouslyTherapyTaken", Yes_No.values());

        return "rehabilitations/therapeuticsessions/cpt";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_PT_Child r_PT_Child, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(mid);

            r_PT_Child.setMotherMasterCode(motherMasterData);

            model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

            model.addAttribute("form_title", "  Mother PT");

            model.addAttribute("pain", Yes_No.values());
            model.addAttribute("tenderness", tendernessRepository.findAll());

            model.addAttribute("jointMobility", jointMobilityRepository.findAll());

            model.addAttribute("mucsuloskeletal", mucsuloskeletalRepository.findAll());

            model.addAttribute("degenerativeDiseases", degenerativeDiseasesRepository.findAll());

            model.addAttribute("PhysicalDisability", Yes_No.values());

            model.addAttribute("previouslyTherapyTaken", Yes_No.values());

            return "rehabilitations/therapeuticsessions/cpt";
        }
        r_PT_ChildRepository.save(r_PT_Child);
        return "redirect:/therapeuticsessions/index/{mid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_PT_Child r_PT_Child, RedirectAttributes redirectAttrs) {

        Optional<R_PT_Child> optionalr_PT_Child = r_PT_ChildRepository.findById(id);
        r_PT_Child = optionalr_PT_Child.orElse(null);

        redirectAttrs.addAttribute("mid", r_PT_Child.motherMasterCode.getId());

        r_PT_ChildRepository.deleteById(id);

        return "redirect:/therapeuticsessions/index/{mid}";
    }

}
