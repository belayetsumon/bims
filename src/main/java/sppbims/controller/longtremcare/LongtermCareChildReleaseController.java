/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.longtremcare;

import sppbims.model.homevisit.M_Child_info;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.reintegration_release.ReleaseChild;
import sppbims.repository.homevisit.M_Child_infoRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.reintegration_release.ReleaseChildRepository;
import sppbims.repository.reintegration_release.ReleaseMotherRepository;
import jakarta.validation.Valid;
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
@RequestMapping("/longtermcarechildrelase")
public class LongtermCareChildReleaseController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    ReleaseMotherRepository releaseMotherRepository;

    @Autowired
    ReleaseChildRepository releaseChildRepository;

    @Autowired
    M_Child_infoRepository m_Child_infoRepository;

//    @RequestMapping("/motherlist")
//    public String motherlist(Model model) {
//        model.addAttribute("list", motherMasterDataRepository.findByReintegrationCheckListReintegrationAndReleaseMotherIsNull(Yes_No.Yes));
//        return "reintegration/mothersearch";
//    }
//
//    @RequestMapping("/index")
//    public String add(Model model) {
//        model.addAttribute("list", releaseMotherRepository.findAll());
//        return "reintegration/index";
//    }
//
//    @RequestMapping("/details/{m_id}")
//    public String details(Model model, @PathVariable Long m_id, ReleaseMother releaseMother) {
//        MotherMasterData motherMasterData = new MotherMasterData();
//        motherMasterData.setId(m_id);
//        return "pre_reintegration_visit/details";
//    }
//
//    @RequestMapping("/mother_add/{m_id}")
//    public String add(Model model, @PathVariable Long m_id, ReleaseMother releaseMother) {
//        MotherMasterData motherMasterData = new MotherMasterData();
//        motherMasterData.setId(m_id);
//        releaseMother.setMotherMasterCode(motherMasterData);
//
//        return "reintegration/reintegration_mother";
//    }
//
//    @GetMapping(value = "/mother_edit/{id}")
//    public String edit(@PathVariable Long id, ReleaseMother releaseMother, Model model) {
//        model.addAttribute("releaseMother", releaseMotherRepository.findOne(id));
//
//        return "reintegration/reintegration_mother";
//    }
//
//    @RequestMapping("/mother_save")
//    public String save(Model model, @Valid ReleaseMother releaseMother, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "reintegration/reintegration_mother";
//        }
//        releaseMotherRepository.save(releaseMother);
//        return "redirect:/reintegration/index";
//    }
//
//    @GetMapping(value = "/mother_delete/{id}")
//    public String delete(@PathVariable Long id, ReleaseMother releaseMother, RedirectAttributes redirectAttrs) {
//        releaseMother = releaseMotherRepository.findOne(id);
//        redirectAttrs.addAttribute("m_id", releaseMother.getMotherMasterCode().getId());
//        releaseMotherRepository.delete(id);
//        return "redirect:/reintegration/index";
//    }
    //// Chaild relase
    @RequestMapping("/child_entry/{c_id}")
    public String child_entry(Model model, @PathVariable Long c_id, ReleaseChild releaseChild) {

        M_Child_info m_Child_info = new M_Child_info();

        m_Child_info = m_Child_infoRepository.getOne(c_id);

        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(m_Child_info.getMotherMasterCode().getId());

        releaseChild.setMotherMasterCode(motherMasterData);

        releaseChild.setChildMasterCode(m_Child_info);

        model.addAttribute("motherid", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

        //model.addAttribute("childList", releaseChildRepository.findAll());
        return "longtermcare/reintegration_child";
    }

    @RequestMapping("/child_save/{c_id}")
    public String childSave(Model model, @PathVariable Long c_id, @Valid ReleaseChild releaseChild, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            M_Child_info m_Child_info = new M_Child_info();

            m_Child_info = m_Child_infoRepository.getOne(c_id);

            MotherMasterData motherMasterData = new MotherMasterData();

            motherMasterData.setId(m_Child_info.getMotherMasterCode().getId());

            releaseChild.setMotherMasterCode(motherMasterData);
            releaseChild.setId(c_id);

            model.addAttribute("motherid", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));
            //model.addAttribute("childList", releaseChildRepository.findAll());
            return "longtermcare/reintegration_child";
        }
        releaseChildRepository.save(releaseChild);
        return "redirect:/reintegration/allreintegrationchild";
    }

    @RequestMapping("/child_edit/{id}")
    public String child_edit(Model model, @PathVariable Long id, ReleaseChild releaseChild) {

        releaseChild = releaseChildRepository.getOne(id);

        model.addAttribute("releaseChild", releaseChild);
        MotherMasterData motherMasterData = new MotherMasterData();

        motherMasterData.setId(releaseChild.getMotherMasterCode().getId());
        releaseChild.setMotherMasterCode(motherMasterData);

        model.addAttribute("motherid", m_Child_infoRepository.findByMotherMasterCode(motherMasterData));

        model.addAttribute("childList", releaseChildRepository.findAll());

        return "reintegration/motherlist_child_entry";
    }

    @GetMapping(value = "/child_delete/{id}")
    public String delete(@PathVariable Long id, ReleaseChild releaseChild, RedirectAttributes redirectAttrs) {
        releaseChild = releaseChildRepository.findById(id).orElse(null);
        redirectAttrs.addAttribute("m_id", releaseChild.getMotherMasterCode().getId());
        releaseChildRepository.deleteById(id);
        return "redirect:/longtremcare/longtermindex/{c_id}";
    }

}
