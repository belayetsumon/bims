/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.repository.rehabilitations.R_IGA_TrainingRepository;
import itgarden.repository.rehabilitations.R_Life_Skill_TrainningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/rtraining")
public class RtrainingController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_Life_Skill_TrainningRepository r_Life_Skill_TrainningRepository;

    @Autowired
    R_IGA_TrainingRepository r_IGA_TrainingRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Value("${repo_url}")
    public String repo_url;

    @RequestMapping("/mothersearch")
    public String mothersearch(Model model) {
        model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("repo_url", repo_url);
        return "rehabilitations/training/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(id);

        model.addAttribute("r_Life_Skill_Trainning", r_Life_Skill_TrainningRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("r_IGA_Training", r_IGA_TrainingRepository.findBymotherMasterCode(motherMasterData));

        return "rehabilitations/training/index";
    }

}
