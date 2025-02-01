/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.clinic;

import itgarden.model.clinic.C_NutritionalStatus;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.clinic.C_ChildNutritionalStatusRepository;
import itgarden.repository.clinic.C_NutritionalStatusRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/nutritionalstatus")
public class C_NutritionalStatusController {

	@Autowired
	MotherMasterDataRepository motherMasterDataRepository;

	@Autowired
	C_NutritionalStatusRepository c_NutritionalStatusRepository;
	
	@Autowired
	C_ChildNutritionalStatusRepository c_ChildNutritionalStatusRepository;

	@RequestMapping("/mothersearch")
	public String mothersearch(Model model) {
		model.addAttribute("list", motherMasterDataRepository.findByAddmissionIsNotNullAndReleaseMotherIsNullOrderByIdDesc());
		return "/clinic/nutritionalstatus/mothersearch";
	}

	@RequestMapping("/index/{m_id}")
	public String index(Model model, @PathVariable Long m_id) {
		model.addAttribute("m_id", m_id);
		MotherMasterData motherMasterData = new MotherMasterData();
		motherMasterData.setId(m_id);
		model.addAttribute("m_nutritionalStatus",
				c_NutritionalStatusRepository.findBymotherMasterCode(motherMasterData));
		
		model.addAttribute("c_childnutritionalStatus",
				c_ChildNutritionalStatusRepository.findBymotherMasterCode(motherMasterData));

		return "/clinic/nutritionalstatus/index";
	}

	@RequestMapping("/motheradd/{m_id}")
	public String motheradd(Model model, @PathVariable Long m_id, C_NutritionalStatus c_NutritionalStatus) {

	

		MotherMasterData motherMasterData = new MotherMasterData();

		motherMasterData = motherMasterDataRepository.findById(m_id).orElse(null);

		c_NutritionalStatus.setMotherMasterCode(motherMasterData);

		c_NutritionalStatus.setName(motherMasterData.motherName);

		c_NutritionalStatus.setAdmissionDate(motherMasterData.addmission.dateAdmission);

		c_NutritionalStatus.setDob(motherMasterData.dateOfBirth);

		model.addAttribute("attribute", "value");
		return "/clinic/nutritionalstatus/mothernutritionadd";
	}

	@RequestMapping("/mothersave/{m_id}")
	public String mothersave(Model model, @PathVariable Long m_id, C_NutritionalStatus c_NutritionalStatus,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			MotherMasterData motherMasterData = new MotherMasterData();

			motherMasterData = motherMasterDataRepository.findById(m_id).orElse(null);

			c_NutritionalStatus.setMotherMasterCode(motherMasterData);

			c_NutritionalStatus.setName(motherMasterData.motherName);

			c_NutritionalStatus.setAdmissionDate(motherMasterData.addmission.dateAdmission);

			c_NutritionalStatus.setDob(motherMasterData.dateOfBirth);

			return "/clinic/nutritionalstatus/mothernutritionadd";
		}

		c_NutritionalStatusRepository.save(c_NutritionalStatus);
		return "redirect:/nutritionalstatus/index/{m_id}";
	}

	@RequestMapping("/motheredit/{id}")
	public String motheredit(Model model, @PathVariable Long id, C_NutritionalStatus c_NutritionalStatus) {

		c_NutritionalStatus = c_NutritionalStatusRepository.findById(id).orElse(null);

		model.addAttribute("c_NutritionalStatus", c_NutritionalStatus);

		MotherMasterData motherMasterData = new MotherMasterData();
		motherMasterData = motherMasterDataRepository.findById(c_NutritionalStatus.motherMasterCode.getId()).orElse(null);

		c_NutritionalStatus.setMotherMasterCode(motherMasterData);

		c_NutritionalStatus.setName(motherMasterData.motherName);

		c_NutritionalStatus.setAdmissionDate(motherMasterData.addmission.dateAdmission);

		c_NutritionalStatus.setDob(motherMasterData.dateOfBirth);
		return "/clinic/nutritionalstatus/mothernutritionadd";
	}

	@RequestMapping("/motheredelete/{id}")
	public String motherdelete(Model model, @PathVariable Long id, C_NutritionalStatus c_NutritionalStatus,
			RedirectAttributes redirectAttrs) {
		c_NutritionalStatus = c_NutritionalStatusRepository.findById(id).orElse(null);
		redirectAttrs.addAttribute("m_id", c_NutritionalStatus.motherMasterCode.getId());
		c_NutritionalStatusRepository.deleteById(id);
		return "redirect:/nutritionalstatus/index/{m_id}";
	}

}
