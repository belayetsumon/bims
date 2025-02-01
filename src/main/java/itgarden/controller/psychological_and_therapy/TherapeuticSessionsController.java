/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.psychological_and_therapy;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.repository.rehabilitations.R_OTRepository;
import itgarden.repository.rehabilitations.R_OtChildRepository;
import itgarden.repository.rehabilitations.R_PTRepository;
import itgarden.repository.rehabilitations.R_PT_ChildRepository;
import itgarden.services.observation.O_MAddmissionService;
import itgarden.services.psychology.R_OtMotherService;
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
@RequestMapping("/therapeuticsessions")
public class TherapeuticSessionsController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_OtChildRepository r_OtChildRepository;

    @Autowired
    R_OTRepository r_OTRepository;

    @Autowired
    R_PTRepository r_PTRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;

    @Value("${repo_url}")
    public String repo_url;

    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @Autowired
    R_OtMotherService oTService;

    @Autowired
    R_PT_ChildRepository r_PT_ChildRepository;

    public static List<Map<String, Object>> filterUncommonMothers(
            List<Map<String, Object>> admitedMotherList, List<Long> houseMotherList) {

        // Convert houseMotherList to a Set for efficient lookups
        Set<Long> houseMotherSet = new HashSet<>(houseMotherList);

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

    public static List<Map<String, Object>> filterCommonMothers(
            List<Map<String, Object>> admitedMotherList, List<Long> houseMotherList) {

        // Convert houseMotherList to a Set for efficient lookups
        Set<Long> houseMotherSet = new HashSet<>(houseMotherList);

        // Initialize the result list for common admitted mothers
        List<Map<String, Object>> commonAdmittedMothers = new ArrayList<>();

        // Iterate over admitedMotherList and add only common mothers
        for (Map<String, Object> motherMap : admitedMotherList) {
            Long motherId = (Long) motherMap.get("motherMasterCodeId"); // Ensure this matches the field name in your map

            // Check if motherId is in houseMotherSet
            if (motherId != null && houseMotherSet.contains(motherId)) {
                commonAdmittedMothers.add(motherMap);
            }
        }

        return commonAdmittedMothers;
    }

    @RequestMapping("/pending_mother_list_ot")
    public String mothersearch(Model model) {

        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
        List<Long> houseMotherList = oTService.motherOtCompletedList();

        // Get the uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers = filterUncommonMothers(admitedMotherList, houseMotherList);

        model.addAttribute("list", uncommonAdmittedMothers);

        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/therapeuticsessions/mothersearch";
    }

    @RequestMapping("/ot_completed_mother_list")
    public String ot_completed_mother_list(Model model) {

        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
        List<Long> houseMotherList = oTService.motherOtCompletedList();

        // Get the uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers = filterCommonMothers(admitedMotherList, houseMotherList);

        model.addAttribute("list", uncommonAdmittedMothers);

        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/therapeuticsessions/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String page(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(id);

        model.addAttribute("r_ot", r_OTRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("r_OtChild", r_OtChildRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("r_pt", r_PTRepository.findBymotherMasterCode(motherMasterData));
        
        model.addAttribute("r_cpt", r_PT_ChildRepository.findBymotherMasterCode(motherMasterData));

        return "rehabilitations/therapeuticsessions/index";

    }

}
