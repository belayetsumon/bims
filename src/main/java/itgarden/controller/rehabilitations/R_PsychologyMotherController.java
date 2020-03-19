/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.rehabilitations;

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
import itgarden.model.rehabilitations.R_M_WorkAllocation;
import itgarden.model.rehabilitations.R_PsychologyMother;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.rehabilitations.R_PsychologyMotherRepository;

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

	@RequestMapping("/mothersearch")
	public String mothersearch(Model model) {
		model.addAttribute("list",
				motherMasterDataRepository.findByAddmissionIsNotNullAndRPsychologyMotherIsNullAndReleaseMotherIsNullOrderByIdDesc());
		return "/rehabilitations/psychology/mothersearch";
	}

	@RequestMapping("/allmother")
	public String allmother(Model model) {
		model.addAttribute("rpsychology", r_PsychologyMotherRepository.findAll());
		return "/rehabilitations/psychology/allmother";
	}

	@RequestMapping("/addmother/{m_id}")
	public String addmother(Model model, @PathVariable Long m_id, R_PsychologyMother r_PsychologyMother) {
		MotherMasterData motherMasterData= new MotherMasterData();
		
		motherMasterData.setId(m_id);
		
		r_PsychologyMother.setMotherMasterCode(motherMasterData);

		model.addAttribute("YesNo", Yes_No.values());

		model.addAttribute("copingstrategy", CopingStrategyEnum.values());
		model.addAttribute("familyrelationship", Family_Relationship_Enum.values());
		model.addAttribute("iq", IqEnum.values());
		model.addAttribute("presencetrauma", Presence_traumaEnum.values());
                 model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

		return "/rehabilitations/psychology/addmother";
	}

	@RequestMapping("/save/{m_id}")
	public String save(Model model,@PathVariable Long m_id, R_PsychologyMother r_PsychologyMother, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			
			MotherMasterData motherMasterData= new MotherMasterData();
			motherMasterData.setId(m_id);
			r_PsychologyMother.setMotherMasterCode(motherMasterData);

			model.addAttribute("YesNo", Yes_No.values());
			model.addAttribute("copingstrategy", CopingStrategyEnum.values());
			model.addAttribute("familyrelationship", Family_Relationship_Enum.values());
			model.addAttribute("iq", IqEnum.values());
			model.addAttribute("presencetrauma", Presence_traumaEnum.values());
                         model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

			return "/rehabilitations/psychology/addmother";
		}
		
		r_PsychologyMotherRepository.save(r_PsychologyMother);
		
		return "redirect:/psychologymother/allmother";
	}
	
	
	@RequestMapping("/edit/{id}")
	public String edit(Model model, @PathVariable Long id, R_PsychologyMother r_PsychologyMother) {
		
		r_PsychologyMother = r_PsychologyMotherRepository.findOne(id);
		
		model.addAttribute("r_PsychologyMother", r_PsychologyMother);

		MotherMasterData motherMasterData= new MotherMasterData();
		
		motherMasterData.setId(r_PsychologyMother.motherMasterCode.getId());
		
		r_PsychologyMother.setMotherMasterCode(motherMasterData);

		model.addAttribute("YesNo", Yes_No.values());

		model.addAttribute("copingstrategy", CopingStrategyEnum.values());
		model.addAttribute("familyrelationship", Family_Relationship_Enum.values());
		model.addAttribute("iq", IqEnum.values());
		model.addAttribute("presencetrauma", Presence_traumaEnum.values());
                 model.addAttribute("emotionStatusenum", EmotionStatusEnum.values());

		return "/rehabilitations/psychology/addmother";
	}
	
	
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, R_PsychologyMother r_PsychologyMother, RedirectAttributes redirectAttrs) {
    	r_PsychologyMother = r_PsychologyMotherRepository.findOne(id);
        redirectAttrs.addAttribute("mid", r_PsychologyMother.motherMasterCode.getId());
        r_PsychologyMotherRepository.delete(id);
        return "redirect:/psychologymother/allmother";
    }
	

}
