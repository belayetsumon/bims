/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.clinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itgarden.model.clinic.C_ChildNutritionalStatus;
import itgarden.model.clinic.C_NutritionalStatus;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.repository.clinic.C_ChildNutritionalStatusRepository;
import itgarden.repository.homevisit.M_Child_infoRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.observation.O_ChildAdmissionRepository;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/childsutritionalstatus")
public class C_ChildNutritionalStatusController {

	@Autowired
	MotherMasterDataRepository motherMasterDataRepository;

	@Autowired
	M_Child_infoRepository m_Child_infoRepository;
	
	
	@Autowired
	O_ChildAdmissionRepository o_ChildAdmissionRepository;
	
	@Autowired
	C_ChildNutritionalStatusRepository c_ChildNutritionalStatusRepository;

	@RequestMapping("/childadd/{m_id}")
	public String page(Model model, @PathVariable Long m_id, C_ChildNutritionalStatus c_ChildNutritionalStatus) {

		MotherMasterData motherMasterData = new MotherMasterData();

		motherMasterData = motherMasterDataRepository.findById(m_id);

		c_ChildNutritionalStatus.setMotherMasterCode(motherMasterData);

		model.addAttribute("childlist", o_ChildAdmissionRepository.findBymotherMasterCode(motherMasterData));

		model.addAttribute("attribute", "value");

		return "/clinic/nutritionalstatus/childnutritionadd";
	}
	

	@RequestMapping("/save/{m_id}")
	public String save(Model model, @PathVariable Long m_id, C_ChildNutritionalStatus c_ChildNutritionalStatus,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
		MotherMasterData motherMasterData = new MotherMasterData();

		motherMasterData = motherMasterDataRepository.findById(m_id);

		c_ChildNutritionalStatus.setMotherMasterCode(motherMasterData);

		model.addAttribute("childlist", o_ChildAdmissionRepository.findBymotherMasterCode(motherMasterData));

		return "/clinic/nutritionalstatus/childnutritionadd";
		}

		c_ChildNutritionalStatusRepository.save(c_ChildNutritionalStatus);
		
		return "redirect:/nutritionalstatus/index/{m_id}";
	}
	
	
	@RequestMapping("/childedit/{id}")
	public String edit(Model model, @PathVariable Long id, C_ChildNutritionalStatus c_ChildNutritionalStatus) {
		
		
		c_ChildNutritionalStatus =c_ChildNutritionalStatusRepository.findOne(id);
		
		
		model.addAttribute("c_ChildNutritionalStatus", c_ChildNutritionalStatus);
		

		MotherMasterData motherMasterData = new MotherMasterData();
		

		motherMasterData = motherMasterDataRepository.findById(c_ChildNutritionalStatus.motherMasterCode.getId());
		

		c_ChildNutritionalStatus.setMotherMasterCode(motherMasterData);
		

		model.addAttribute("childlist", o_ChildAdmissionRepository.findBymotherMasterCode(motherMasterData));
		

	return "/clinic/nutritionalstatus/childnutritionadd";
	}
	
	
	@RequestMapping("/delete/{id}")
	public String motherdelete(Model model, @PathVariable Long id, C_ChildNutritionalStatus c_ChildNutritionalStatus,
			RedirectAttributes redirectAttrs) {
		c_ChildNutritionalStatus = c_ChildNutritionalStatusRepository.findOne(id);
		redirectAttrs.addAttribute("m_id", c_ChildNutritionalStatus.motherMasterCode.getId());
		c_ChildNutritionalStatusRepository.delete(id);
		return "redirect:/nutritionalstatus/index/{m_id}";
	}

}
