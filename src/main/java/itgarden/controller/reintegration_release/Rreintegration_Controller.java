/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.reintegration_release;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import itgarden.model.reintegration_release.ReleaseChild;
import itgarden.model.reintegration_release.ReleaseMother;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.reintegration_release.ReleaseChildRepository;
import itgarden.repository.reintegration_release.ReleaseMotherRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/reintegration")
public class Rreintegration_Controller {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    ReleaseMotherRepository releaseMotherRepository;

    @Autowired
    ReleaseChildRepository releaseChildRepository;

    @RequestMapping("/motherlist")
    public String motherlist(Model model) {
        model.addAttribute("list", motherMasterDataRepository.findByReintegrationCheckListReintegrationAndReleaseMotherIsNullOrderByIdDesc(Yes_No.Yes));
        return "reintegration/mothersearch";
    }

    @RequestMapping("/index")
    public String add(Model model) {
        model.addAttribute("list", releaseMotherRepository.findAll());
        return "reintegration/index";
    }

    @RequestMapping("/details/{m_id}")
    public String details(Model model, @PathVariable Long m_id, ReleaseMother releaseMother) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        return "pre_reintegration_visit/details";
    }

    @RequestMapping("/mother_add/{m_id}")
    public String add(Model model, @PathVariable Long m_id, ReleaseMother releaseMother) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        releaseMother.setMotherMasterCode(motherMasterData);

        return "reintegration/reintegration_mother";
    }

    @GetMapping(value = "/mother_edit/{id}")
    public String edit(@PathVariable Long id, ReleaseMother releaseMother, Model model) {
        model.addAttribute("releaseMother", releaseMotherRepository.findOne(id));

        return "reintegration/reintegration_mother";
    }

    @RequestMapping("/mother_save")
    public String save(Model model, @Valid ReleaseMother releaseMother, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "reintegration/reintegration_mother";
        }
        releaseMotherRepository.save(releaseMother);
        return "redirect:/reintegration/index";
    }

    @GetMapping(value = "/mother_delete/{id}")
    public String delete(@PathVariable Long id, ReleaseMother releaseMother, RedirectAttributes redirectAttrs) {
        releaseMother = releaseMotherRepository.getOne(id);
        redirectAttrs.addAttribute("m_id", releaseMother.getMotherMasterCode().getId());
        releaseMotherRepository.delete(id);
        return "redirect:/reintegration/index";
    }

    //// Chaild relase
    
    
    
        @RequestMapping("/allreintegrationchild")
    public String AllchildIndex(Model model, ReleaseChild releaseChild) {
             model.addAttribute("list", releaseChildRepository.findAll());
        return "reintegration/allreintegrationchild";
    }
    
    
    @RequestMapping("/childindex/{m_id}")
    public String childIndex(Model model, @PathVariable Long m_id, ReleaseChild releaseChild) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);

        model.addAttribute("m_id", m_id);

        model.addAttribute("list", releaseChildRepository.findBymotherMasterCode(motherMasterData));
        return "reintegration/index_child";
    }

    @RequestMapping("/child_entry/{m_id}")
    public String child_entry(Model model, @PathVariable Long m_id, ReleaseChild releaseChild) {
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(m_id);
        releaseChild.setMotherMasterCode(motherMasterData);
        model.addAttribute("mother", releaseMotherRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("motherid", motherMasterDataRepository.findById(m_id));
        model.addAttribute("childList", releaseChildRepository.findAll());

        return "reintegration/motherlist_child_entry";
    }

    @RequestMapping("/child_save/{m_id}")
    public String childSave(Model model, @PathVariable Long m_id, @Valid ReleaseChild releaseChild, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            MotherMasterData motherMasterData = new MotherMasterData();
            motherMasterData.setId(m_id);
            releaseChild.setMotherMasterCode(motherMasterData);
            model.addAttribute("mother", releaseMotherRepository.findByMotherMasterCode(motherMasterData));
            model.addAttribute("motherid", motherMasterDataRepository.findById(m_id));
            model.addAttribute("childList", releaseChildRepository.findAll());

            return "reintegration/motherlist_child_entry";
        }
        releaseChildRepository.save(releaseChild);
        return "redirect:/reintegration/childindex/{m_id}";
    }

    @RequestMapping("/child_edit/{c_id}")
    public String child_edit(Model model, @PathVariable Long c_id, ReleaseChild releaseChild) {

        model.addAttribute("releaseChild", releaseChildRepository.getOne(c_id));
        MotherMasterData motherMasterData = new MotherMasterData();
        releaseChild = releaseChildRepository.getOne(c_id);
        motherMasterData.setId(releaseChild.getMotherMasterCode().getId());
        releaseChild.setMotherMasterCode(motherMasterData);
        model.addAttribute("mother", releaseMotherRepository.findByMotherMasterCode(motherMasterData));
        model.addAttribute("motherid", motherMasterDataRepository.findById(releaseChild.getMotherMasterCode().getId()));
        model.addAttribute("childList", releaseChildRepository.findAll());

        return "reintegration/motherlist_child_entry";
    }

    
    @GetMapping(value = "/child_delete/{id}")
    public String delete(@PathVariable Long id, ReleaseChild releaseChild, RedirectAttributes redirectAttrs) {
        releaseChild = releaseChildRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", releaseChild.getMotherMasterCode().getId());
        releaseChildRepository.delete(id);
        return "redirect:/reintegration/childindex/{m_id}";
    }
    
    @GetMapping(value = "/child_delete2/{id}")
    public String delete2(@PathVariable Long id, ReleaseChild releaseChild, RedirectAttributes redirectAttrs) {
        releaseChild = releaseChildRepository.findOne(id);
        redirectAttrs.addAttribute("m_id", releaseChild.getMotherMasterCode().getId());
        releaseChildRepository.delete(id);
        return "redirect:/reintegration/allreintegrationchild";
    }
    
}
