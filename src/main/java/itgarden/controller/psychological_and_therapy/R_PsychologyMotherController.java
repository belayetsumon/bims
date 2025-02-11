/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.psychological_and_therapy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.rehabilitations.CopingStrategyEnum;
import itgarden.model.rehabilitations.EmotionStatusEnum;
import itgarden.model.rehabilitations.Family_Relationship_Enum;
import itgarden.model.rehabilitations.IqEnum;
import itgarden.model.rehabilitations.Presence_traumaEnum;
import itgarden.model.rehabilitations.R_PsychologyMother;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.rehabilitations.R_PsychologyMotherRepository;
import itgarden.services.observation.O_MAddmissionService;
import itgarden.services.psychology.R_PsychologyMotherService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/psychologymother")
public class R_PsychologyMotherController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_PsychologyMotherRepository r_PsychologyMotherRepository;

    @Autowired
    O_MAddmissionService o_MAddmissionService;
   
    @Autowired
    R_PsychologyMotherService psychologyMotherService;

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
            
            List<Map<String, Object>> admitedMotherList, List<Long> psychologyMother) {

        // Convert houseMotherList to a Set for efficient lookups
        Set<Long> psychologyMotherset = new HashSet<>(psychologyMother);

        // Initialize the result list for common admitted mothers
        List<Map<String, Object>> commonAdmittedMothers = new ArrayList<>();

        // Iterate over admitedMotherList and add only common mothers
        for (Map<String, Object> motherMap : admitedMotherList) {
            Long motherId = (Long) motherMap.get("motherMasterCodeId"); // Ensure this matches the field name in your map

            // Check if motherId is in houseMotherSet
            if (motherId != null && psychologyMotherset.contains(motherId)) {
                commonAdmittedMothers.add(motherMap);
            }
        }

        return commonAdmittedMothers;
    }

    @RequestMapping("/mothersearch")
    public String mothersearch(Model model) {

        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
       
        List<Long> otList = psychologyMotherService.motherOtCompletedList();
        
        // Get the uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers = filterUncommonMothers(admitedMotherList,otList);
        model.addAttribute("list", uncommonAdmittedMothers);
       // model.addAttribute("list", motherMasterDataRepository.findByAddmissionIsNotNullAndRpsychologyMotherIsNullAndReleaseMotherIsNullOrderByIdDesc());
        return "/psychological_and_therapy/pending_mother_for_psychology";
    }

    @RequestMapping("/allmother")
    public String allmother(Model model) {
        
        model.addAttribute("list", psychologyMotherService.psychologyCompletedMotherList());
        
        return "/psychological_and_therapy/psychology_session_complted_mother_list";
    }

    @RequestMapping("/addmother/{m_id}")
    public String addmother(Model model, @PathVariable Long m_id, R_PsychologyMother r_PsychologyMother) {
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        r_PsychologyMother.setMotherMasterCode(motherMasterData);
        model.addAttribute("YesNo", Yes_No.values());
        model.addAttribute("copingstrategy", CopingStrategyEnum.values());
        model.addAttribute("familyrelationship", Family_Relationship_Enum.values());
        model.addAttribute("iq", IqEnum.values());
        model.addAttribute("presencetrauma", Presence_traumaEnum.values());
        model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());
        return "/psychological_and_therapy/psychology_session_addmother";
    }

    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, R_PsychologyMother r_PsychologyMother, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            r_PsychologyMother.setMotherMasterCode(motherMasterData);

            model.addAttribute("YesNo", Yes_No.values());
            model.addAttribute("copingstrategy", CopingStrategyEnum.values());
            model.addAttribute("familyrelationship", Family_Relationship_Enum.values());
            model.addAttribute("iq", IqEnum.values());
            model.addAttribute("presencetrauma", Presence_traumaEnum.values());
            model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

            return "/psychological_and_therapy/psychology_session_addmother";
        }

        r_PsychologyMotherRepository.save(r_PsychologyMother);

        return "redirect:/psychologymother/allmother";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_PsychologyMother r_PsychologyMother) {
        Optional<R_PsychologyMother> optionalr_PsychologyMother = r_PsychologyMotherRepository.findById(id);

        r_PsychologyMother = optionalr_PsychologyMother.orElse(null);

        model.addAttribute("r_PsychologyMother", r_PsychologyMother);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(r_PsychologyMother.motherMasterCode.getId());

        r_PsychologyMother.setMotherMasterCode(motherMasterData);

        model.addAttribute("YesNo", Yes_No.values());

        model.addAttribute("copingstrategy", CopingStrategyEnum.values());
        model.addAttribute("familyrelationship", Family_Relationship_Enum.values());
        model.addAttribute("iq", IqEnum.values());
        model.addAttribute("presencetrauma", Presence_traumaEnum.values());
        model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

       return "/psychological_and_therapy/psychology_session_addmother";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_PsychologyMother r_PsychologyMother, RedirectAttributes redirectAttrs) {
        Optional<R_PsychologyMother> optionalr_PsychologyMother = r_PsychologyMotherRepository.findById(id);

        r_PsychologyMother = optionalr_PsychologyMother.orElse(null);

        redirectAttrs.addAttribute("mid", r_PsychologyMother.motherMasterCode.getId());

        r_PsychologyMotherRepository.deleteById(id);

        return "redirect:/psychologymother/allmother";
    }

}
