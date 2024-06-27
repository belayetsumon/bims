/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_IGA_Training;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.rehabilitations.R_IGA_TrainingRepository;
import itgarden.repository.rehabilitations.TrainingNameRepository;
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
@RequestMapping("/rigatraining")
public class R_IGA_TrainingController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_IGA_TrainingRepository r_IGA_TrainingRepository;

    @Autowired
    TrainingNameRepository trainingNameRepository;

    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_IGA_Training r_IGA_Training) {

        MotherMasterData motherMasterData = new MotherMasterData();

        Optional<MotherMasterData> optionalMotherMasterData = motherMasterDataRepository.findById(mid);

        motherMasterData = optionalMotherMasterData.orElse(null);

        r_IGA_Training.setMotherMasterCode(motherMasterData);

        r_IGA_Training.setName(motherMasterData.getMotherName());

        model.addAttribute("trainingName", trainingNameRepository.findAll());

        return "rehabilitations/training/igatraining";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_IGA_Training r_IGA_Training) {
        model.addAttribute("r_IGA_Training", r_IGA_TrainingRepository.findById(id));

        MotherMasterData motherMasterData = new MotherMasterData();

        Optional<MotherMasterData> optionalMotherMasterData = motherMasterDataRepository.findById(id);

        motherMasterData = optionalMotherMasterData.orElse(null);

        r_IGA_Training.setName(motherMasterData.getMotherName());

        model.addAttribute("trainingName", trainingNameRepository.findAll());

        return "rehabilitations/training/igatraining";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_IGA_Training r_IGA_Training, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();
            Optional<MotherMasterData> optionalMotherMasterData = motherMasterDataRepository.findById(mid);

            motherMasterData = optionalMotherMasterData.orElse(null);
            
            r_IGA_Training.setMotherMasterCode(motherMasterData);

            model.addAttribute("trainingName", trainingNameRepository.findAll());

            return "rehabilitations/training/igatraining";
        }
        r_IGA_TrainingRepository.save(r_IGA_Training);

        return "redirect:/rtraining/index/{mid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_IGA_Training r_IGA_Training, RedirectAttributes redirectAttrs) {
        Optional<R_IGA_Training> optionalR_IGA_Training = r_IGA_TrainingRepository.findById(id);
        
        r_IGA_Training = optionalR_IGA_Training.orElse(null);
        
        redirectAttrs.addAttribute("mid", r_IGA_Training.motherMasterCode.getId());
        
        r_IGA_TrainingRepository.deleteById(id);
        return "redirect:/rtraining/index/{mid}";

    }

}
