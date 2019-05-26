/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.controller.homevisit;

import itgarden.model.homevisit.Thana;
import itgarden.repository.homevisit.ThanaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Md Belayet Hossin
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ThanaRepository thanaRepository;

    @RequestMapping(name = "/thana", method = RequestMethod.GET)
    public List<Thana> thana(Model model) {
        return thanaRepository.findAll();
    }

}
