/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.psychological_and_therapy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Yes_No;
import sppbims.model.rehabilitations.CopingStrategyEnum;
import sppbims.model.rehabilitations.EmotionStatusEnum;
import sppbims.model.rehabilitations.Family_Relationship_Enum;
import sppbims.model.rehabilitations.IqEnum;
import sppbims.model.rehabilitations.Presence_traumaEnum;
import sppbims.model.rehabilitations.R_PsychologyChild;
import sppbims.repository.homevisit.M_Child_infoRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.rehabilitations.R_PsychologyChildRepository;
import sppbims.repository.rehabilitations.R_PsychologyMotherRepository;
import java.util.Optional;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/psychologychild")
public class R_PsychologyChildController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_PsychologyMotherRepository r_PsychologyMotherRepository;

    @Autowired
    R_PsychologyChildRepository r_PsychologyChildRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

    @RequestMapping("/childlist/{m_id}")
    public String mothersearch(Model model, @PathVariable Long m_id, R_PsychologyChild r_PsychologyChild) {

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_id);

        r_PsychologyChild.setMotherMasterCode(motherMasterData);

        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

        model.addAttribute("pchildlist", r_PsychologyChildRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("YesNo", Yes_No.values());

        model.addAttribute("copingstrategy", CopingStrategyEnum.values());

        model.addAttribute("familyrelationship", Family_Relationship_Enum.values());

        model.addAttribute("iq", IqEnum.values());

        model.addAttribute("presencetrauma", Presence_traumaEnum.values());

        model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

        return "/psychological_and_therapy/psychology_Session_addchild";
    }

//    @RequestMapping("/addmother/{m_id}")
//    public String addmother(Model model, @PathVariable Long m_id, R_PsychologyMother r_PsychologyMother) {
//        MotherMasterData motherMasterData = new MotherMasterData();
//
//        motherMasterData.setId(m_id);
//
//        r_PsychologyMother.setMotherMasterCode(motherMasterData);
//
//        model.addAttribute("YesNo", Yes_No.values());
//
//        model.addAttribute("copingstrategy", CopingStrategyEnum.values());
//        model.addAttribute("familyrelationship", Family_Relationship_Enum.values());
//        model.addAttribute("iq", IqEnum.values());
//        model.addAttribute("presencetrauma", Presence_traumaEnum.values());
//
//        return "/rehabilitations/psychology/addmother";
//    }
    @RequestMapping("/save/{m_id}")
    public String save(Model model, @PathVariable Long m_id, R_PsychologyChild r_PsychologyChild, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_id);

            r_PsychologyChild.setMotherMasterCode(motherMasterData);

            model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

            model.addAttribute("pchildlist", r_PsychologyChildRepository.findBymotherMasterCode(motherMasterData));

            model.addAttribute("YesNo", Yes_No.values());

            model.addAttribute("copingstrategy", CopingStrategyEnum.values());

            model.addAttribute("familyrelationship", Family_Relationship_Enum.values());

            model.addAttribute("iq", IqEnum.values());

            model.addAttribute("presencetrauma", Presence_traumaEnum.values());
            model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

            return "/psychological_and_therapy/psychology_Session_addchild";
        }

        r_PsychologyChildRepository.save(r_PsychologyChild);

        return "redirect:/psychologychild/childlist/{m_id}";

    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, R_PsychologyChild r_PsychologyChild) {

        Optional<R_PsychologyChild> optionalR_PsychologyChild = r_PsychologyChildRepository.findById(id);

        r_PsychologyChild = optionalR_PsychologyChild.orElse(null);

        model.addAttribute("r_PsychologyChild", r_PsychologyChild);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(r_PsychologyChild.motherMasterCode.getId());

        r_PsychologyChild.setMotherMasterCode(motherMasterData);

        model.addAttribute("childlist", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

        model.addAttribute("pchildlist", r_PsychologyChildRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("YesNo", Yes_No.values());

        model.addAttribute("copingstrategy", CopingStrategyEnum.values());

        model.addAttribute("familyrelationship", Family_Relationship_Enum.values());

        model.addAttribute("iq", IqEnum.values());

        model.addAttribute("presencetrauma", Presence_traumaEnum.values());
        model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

        return "/psychological_and_therapy/psychology_Session_addchild";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_PsychologyChild r_PsychologyChild, RedirectAttributes redirectAttrs) {

        Optional<R_PsychologyChild> optionalR_PsychologyChild = r_PsychologyChildRepository.findById(id);

        r_PsychologyChild = optionalR_PsychologyChild.orElse(null);

        redirectAttrs.addAttribute("m_id", r_PsychologyChild.motherMasterCode.getId());

        r_PsychologyChildRepository.deleteById(id);

        return "redirect:/psychologychild/childlist/{m_id}";
    }

}
