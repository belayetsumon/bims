/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.observation;

import sppbims.model.homevisit.Decision;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.observation.O_Induction;
import sppbims.repository.homevisit.M_ApprovalRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.observation.O_InductionRepository;
import sppbims.repository.observation.O_Inhouse_Inductions_MotherRepository;
import sppbims.repository.observation.O_Inhouse_Inductions_childRepository;
import sppbims.services.homevisit.MotherMasterDataServices;
import sppbims.services.observation.M_ApprovalService;
import jakarta.validation.Valid;
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
@RequestMapping("/induction")
public class InductionController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    O_InductionRepository o_InductionRepository;

    @Autowired
    O_Inhouse_Inductions_childRepository o_Inhouse_Inductions_childRepository;

    @Autowired
    O_Inhouse_Inductions_MotherRepository o_Inhouse_Inductions_MotherRepository;

    @Autowired
    M_ApprovalRepository m_ApprovalRepository;

    @Autowired
    MotherMasterDataServices motherMasterDataServices;

    @Autowired
    M_ApprovalService m_ApprovalService;

    @RequestMapping("/newmother")
    public String newmother(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        //model.addAttribute("list", motherMasterDataRepository.findByOinductionIsNullAndMapprovalDecissionOrderByIdDesc(Decision.Approve));
        model.addAttribute("list", motherMasterDataServices.findMotherDataWithApprovalAndInductionNull());
        return "homevisit/observation/induction/newmother";
    }

    @RequestMapping("/motherlist")
    public String motherlist(Model model) {
        //model.addAttribute("list", motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        // model.addAttribute("list", m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve));
        model.addAttribute("list", m_ApprovalService.findAllBydecissionOrderByIdDesc(Decision.Approve));
        return "homevisit/observation/induction/mothersearch";
    }

    @RequestMapping("/index/{id}")
    public String motherbyid(Model model, @PathVariable Long id, O_Induction o_Induction) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_Induction", o_InductionRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_Inhouse_Inductions_Mother", o_Inhouse_Inductions_MotherRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_Inhouse_Inductions_child", o_Inhouse_Inductions_childRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/induction/inductionIndex";
    }

    @RequestMapping("/pdf/{id}")
    public String pdf(Model model, @PathVariable Long id, O_Induction o_Induction) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        model.addAttribute("o_Induction", o_InductionRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_Inhouse_Inductions_Mother", o_Inhouse_Inductions_MotherRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("o_Inhouse_Inductions_child", o_Inhouse_Inductions_childRepository.findBymotherMasterCode(motherMasterData));
        return "homevisit/observation/induction/induction_print_view";
    }

    @RequestMapping("/create/{id}")
    public String create(Model model, @PathVariable Long id, O_Induction o_Induction) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        o_Induction.setMotherMasterCode(motherMasterData);
        return "homevisit/observation/induction/inductionCreate";
    }

    @RequestMapping("/save/{id}")
    public String save(Model model, @PathVariable Long id, @Valid O_Induction o_Induction, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("m_id", id);
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(id);
            o_Induction.setMotherMasterCode(motherMasterData);
            return "homevisit/observation/induction/inductionCreate";
        }
        o_InductionRepository.save(o_Induction);
        return "redirect:/induction/index/{id}";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, O_Induction o_Induction) {
        model.addAttribute("m_id", id);
        model.addAttribute("o_Induction", o_InductionRepository.findById(id).orElse(null));
        return "homevisit/observation/induction/inductionCreate";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id, O_Induction o_Induction, RedirectAttributes redirectAttrs) {
        o_Induction = o_InductionRepository.findById(id).orElse(null);

        redirectAttrs.addAttribute("id", o_Induction.motherMasterCode.getId());

        o_InductionRepository.deleteById(id);

        return "redirect:/induction/index/{id}";
    }
}
