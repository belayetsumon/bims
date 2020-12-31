/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_Life_Skill_Trainning;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.rehabilitations.R_Life_Skill_TrainningRepository;
import itgarden.repository.rehabilitations.TrainingNameRepository;
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
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/rlifeskilltrainning")
public class R_Life_Skill_TrainningController {

    @Autowired
    R_Life_Skill_TrainningRepository r_Life_Skill_TrainningRepository;

    @Autowired
    TrainingNameRepository trainingNameRepository;

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @RequestMapping("/create/{mid}")
    public String create(Model model, @PathVariable Long mid, R_Life_Skill_Trainning r_Life_Skill_Trainning) {

        MotherMasterData motherMasterData = new MotherMasterData();
        
        motherMasterData = motherMasterDataRepository.findOne(mid);

        r_Life_Skill_Trainning.setMotherMasterCode(motherMasterData);
        
        r_Life_Skill_Trainning.setName(motherMasterData.getMotherName());
        
        model.addAttribute("trainingName", trainingNameRepository.findAll());

        return "rehabilitations/training/lifeskilltraining";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_Life_Skill_Trainning r_Life_Skill_Trainning) {
        model.addAttribute("r_Life_Skill_Trainning", r_Life_Skill_TrainningRepository.findOne(id));

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData = motherMasterDataRepository.findOne(id);

        r_Life_Skill_Trainning.setName(motherMasterData.getMotherName());
        
        model.addAttribute("trainingName", trainingNameRepository.findAll());

        return "rehabilitations/training/lifeskilltraining";
    }

    @RequestMapping("/save/{mid}")
    public String save(Model model, @PathVariable Long mid, @Valid R_Life_Skill_Trainning r_Life_Skill_Trainning, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData = motherMasterDataRepository.findOne(mid);

            r_Life_Skill_Trainning.setMotherMasterCode(motherMasterData);

            r_Life_Skill_Trainning.setName(motherMasterData.getMotherName());

            model.addAttribute("trainingName", trainingNameRepository.findAll());

            return "rehabilitations/training/lifeskilltraining";
        }
        
        r_Life_Skill_TrainningRepository.save(r_Life_Skill_Trainning);

        return "redirect:/rtraining/index/{mid}";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_Life_Skill_Trainning r_Life_Skill_Trainning, RedirectAttributes redirectAttrs) {
        r_Life_Skill_Trainning = r_Life_Skill_TrainningRepository.findOne(id);
        redirectAttrs.addAttribute("mid", r_Life_Skill_Trainning.motherMasterCode.getId());
        r_Life_Skill_TrainningRepository.delete(id);
        return "redirect:/rtraining/index/{mid}";

    }

}
