/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.lookuptable;
import itgarden.model.homevisit.PhysicalStatus;
import itgarden.repository.homevisit.PhysicalStatusRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/physicalstatus")
public class PhysicalStatusController {

    @Autowired
    PhysicalStatusRepository physicalStatusRepository;

    @RequestMapping("/index")
    public String index(Model model, PhysicalStatus physicalStatus) {
        model.addAttribute("list", physicalStatusRepository.findAll());
        model.addAttribute("table_name", "Physical Status");

        return "homevisit/lookup/physicalstatus";
    }

    @RequestMapping("/save")

    public String save(Model model, @Valid PhysicalStatus physicalStatus, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("list", physicalStatusRepository.findAll());
            model.addAttribute("table_name", "Physical Status");
            return "homevisit/lookup/physicalstatus";
        }

        physicalStatusRepository.save(physicalStatus);

        return "redirect:/physicalstatus/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, PhysicalStatus physicalStatus, Model model) {
        model.addAttribute("physicalStatus", physicalStatusRepository.findOne(id));
        model.addAttribute("list", physicalStatusRepository.findAll());
        model.addAttribute("table_name", "Physical Status");
        return "/homevisit/lookup/physicalstatus";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, PhysicalStatus physicalStatus) {
        physicalStatusRepository.delete(id);
        return "redirect:/physicalstatus/index";
    }

}
