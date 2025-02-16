/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.training;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.observation.O_MAddmissionRepository;
import sppbims.repository.rehabilitations.R_IGA_TrainingRepository;
import sppbims.repository.rehabilitations.R_Life_Skill_TrainningRepository;
import sppbims.services.observation.O_MAddmissionService;
import sppbims.services.reintegration_release.ReleaseMotherService;
import sppbims.services.training.R_IGA_TrainingService;
import sppbims.services.training.R_Life_Skill_TrainningService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    @Autowired
    ReleaseMotherService releaseMotherService;

    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @Value("${repo_url}")
    public String repo_url;

    @Autowired
    R_IGA_TrainingService r_IGA_TrainingService;

    @Autowired
    R_Life_Skill_TrainningService r_Life_Skill_TrainningService;

    public static List<Map<String, Object>> filterUncommonMothers(
            List<Map<String, Object>> admitedMotherList, List<Long> releasedMotherList) {

        // Convert houseMotherList to a Set for efficient lookups
        Set<Long> houseMotherSet = new HashSet<>(releasedMotherList);

        // Initialize the result list for uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers = new ArrayList<>();

        // Iterate over admitedMotherList and add only uncommon mothers
        for (Map<String, Object> motherMap : admitedMotherList) {
            Long motherId = (Long) motherMap.get("motherMasterCodeId"); // Ensure this matches the field name in your map

            // Check if motherId is not in houseMotherSet
            if (motherId != null && !houseMotherSet.contains(motherId)) {
                uncommonAdmittedMothers.add(motherMap);
            }
        }

        return uncommonAdmittedMothers;
    }

    @RequestMapping("/mother_list_for_training")
    public String mothersearch(Model model) {

        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
        List<Long> releasedMotherList = releaseMotherService.allReleasedMotherIdList();

        // Get the uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers
                = filterUncommonMothers(admitedMotherList, releasedMotherList);

        model.addAttribute("list", uncommonAdmittedMothers);
        return "training/mother_list_for_training";
    }

    @RequestMapping("/index/{id}")
    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(id);

        // model.addAttribute("r_Life_Skill_Trainning", r_Life_Skill_TrainningRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("r_Life_Skill_Trainning", r_Life_Skill_TrainningService.lifeSkillTrainingsList_BY_Mother(id));

        model.addAttribute("r_IGA_Training", r_IGA_TrainingService.igaSkillTrainings_By_Mother(id));

        return "training/index";
    }

    @RequestMapping("/lifeskilltraininglist")
    public String lifeSkill(Model model) {
//        model.addAttribute("m_id", id);
//        MotherMasterData motherMasterData = new MotherMasterData();
//
//        motherMasterData.setId(id);
        model.addAttribute("r_Life_Skill_Trainning", r_Life_Skill_TrainningService.lifeSkillTrainingsList());
        return "training/lifeskilltraininglist";
    }

    @RequestMapping("/igatraininglist")
    public String iga(Model model) {
//        model.addAttribute("m_id", id);
//        MotherMasterData motherMasterData = new MotherMasterData();
//
//        motherMasterData.setId(id);
        model.addAttribute("r_IGA_Training", r_IGA_TrainingService.igaSkillTrainingsList());
        return "training/igatraininglist";
    }

}
