/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.search;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @RequestMapping("/searchmother")
    public String searchMother(@RequestParam("motherName") String motherName, Model model) {

//        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.EXACT).withIgnoreCase();
//
//        Example<MotherMasterData> mname = Example.of(new MotherMasterData(motherName), matcher);
        model.addAttribute("motheronfo", motherMasterDataRepository.findByMotherNameContaining(motherName));

//        model.addAttribute("mothername", motherMasterDataRepository.findAll(mname));
        return "search/index";
    }

}
