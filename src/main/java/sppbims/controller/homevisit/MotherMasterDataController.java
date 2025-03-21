/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.homevisit;

import sppbims.model.homevisit.Eligibility;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Yes_No;
import sppbims.repository.homevisit.EducationLevelRepository;
import sppbims.repository.homevisit.EducationTypeRepository;
import sppbims.repository.homevisit.EthinicIdentityRepository;
import sppbims.repository.homevisit.HusbandsStatusRepository;
import sppbims.repository.homevisit.ImmunizationRepository;
import sppbims.repository.homevisit.MaritalStatusRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.homevisit.OccupationRepository;
import sppbims.repository.homevisit.PhysicalStatusRepository;
import sppbims.repository.homevisit.ReasonsRepository;
import sppbims.repository.homevisit.RelationsRepository;
import sppbims.repository.homevisit.ReligionRepository;
import sppbims.services.homevisit.MotherMasterDataServices;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
@RequestMapping("/mothermasterdata")
public class MotherMasterDataController {

    @Autowired
    ReasonsRepository reasonsRepository;
    @Autowired
    ReligionRepository religionRepository;
    @Autowired
    MaritalStatusRepository maritalStatusRepository;
    @Autowired
    HusbandsStatusRepository husbandsStatusRepository;
    @Autowired
    RelationsRepository relationsRepository;
    @Autowired
    EthinicIdentityRepository ethinicIdentityRepository;
    @Autowired
    EducationLevelRepository educationLevelRepository;
    @Autowired
    EducationTypeRepository educationTypeRepository;
    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;
    @Autowired
    OccupationRepository occupationRepository;
    @Autowired
    PhysicalStatusRepository physicalStatusRepository;
    @Autowired
    ImmunizationRepository immunizationRepository;

    @Autowired
    MotherMasterDataServices motherMasterDataServices;

    @RequestMapping("/index")
    public String index(Model model, MotherMasterData motherMasterData) {

        //model.addAttribute("list", motherMasterDataService.allInsertedMotherList());
        // model.addAttribute("list", motherMasterDataRepository.findAllByOrderByIdDesc());
        model.addAttribute("list", motherMasterDataServices.findAllByOrderByIdDesc());
        return "homevisit/insertmother/index";
    }

    @RequestMapping("/create")

    public String add(MotherMasterData motherMasterData, Model model) {

        model.addAttribute("resons", reasonsRepository.findAll());

        model.addAttribute("religion", religionRepository.findAll());

        model.addAttribute("maritalStatus", maritalStatusRepository.findAll());

        model.addAttribute("husbandsStatus", husbandsStatusRepository.findAll());

        model.addAttribute("relationWithPfm", relationsRepository.findAll());

        model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("occupation", occupationRepository.findAll());

        model.addAttribute("physicalStatus", physicalStatusRepository.findAll());

        model.addAttribute("immunization", immunizationRepository.findAll());

        model.addAttribute("eligibility", Eligibility.values());
        model.addAttribute("yesNo", Yes_No.values());

        /**
         * ************* Mother Id Create
         * *********************************************
         */
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today_string = localDate.format(formatter);

        String BO = "B";

        LocalDate date = LocalDate.now();
        List<MotherMasterData> total_today = motherMasterDataRepository.findAllBycreated(date);

        int total = total_today.size() + 1;
        String oo = "00";

        StringBuilder m_id = new StringBuilder("");

        m_id.append(today_string);
        m_id.append(BO);
        m_id.append(String.format("%02d", total));
        m_id.append(oo);

        motherMasterData.setMotherMasterCode(String.valueOf(m_id));

        /**
         * *********************** End ************************
         */
        return "homevisit/insertmother/create";
    }

    @RequestMapping("/save")
    public String save(Model model, @Valid MotherMasterData motherMasterData, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("resons", reasonsRepository.findAll());

            model.addAttribute("religion", religionRepository.findAll());

            model.addAttribute("maritalStatus", maritalStatusRepository.findAll());

            model.addAttribute("husbandsStatus", husbandsStatusRepository.findAll());

            model.addAttribute("relationWithPfm", relationsRepository.findAll());

            model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

            model.addAttribute("educationLevel", educationLevelRepository.findAll());

            model.addAttribute("educationType", educationTypeRepository.findAll());

            model.addAttribute("occupation", occupationRepository.findAll());

            model.addAttribute("physicalStatus", physicalStatusRepository.findAll());

            model.addAttribute("immunization", immunizationRepository.findAll());
            model.addAttribute("eligibility", Eligibility.values());
            model.addAttribute("yesNo", Yes_No.values());

            /**
             * ************* Mother Id Create
             * *********************************************
             */
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String today_string = localDate.format(formatter);

            String BO = "B";

            LocalDate date = LocalDate.now();
            List<MotherMasterData> total_today = motherMasterDataRepository.findAllBycreated(date);

            int total = total_today.size() + 1;
            String oo = "00";

            StringBuilder m_id = new StringBuilder("");

            m_id.append(today_string);
            m_id.append(BO);
            m_id.append(String.format("%02d", total));
            m_id.append(oo);

            motherMasterData.setMotherMasterCode(String.valueOf(m_id));

            /**
             * *********************** End ************************
             */
            return "homevisit/insertmother/create";
        }

//motherMasterData.getCREATED_BY().setId(1L);
        motherMasterDataRepository.save(motherMasterData);
        return "redirect:/mothermasterdata/index";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, MotherMasterData motherMasterData, Model model) {
        model.addAttribute("motherMasterData", motherMasterDataRepository.findById(id).orElse(null));
        //  model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("resons", reasonsRepository.findAll());
        model.addAttribute("religion", religionRepository.findAll());
        model.addAttribute("maritalStatus", maritalStatusRepository.findAll());
        model.addAttribute("husbandsStatus", husbandsStatusRepository.findAll());

        model.addAttribute("relationWithPfm", relationsRepository.findAll());

        model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("occupation", occupationRepository.findAll());

        model.addAttribute("physicalStatus", physicalStatusRepository.findAll());

        model.addAttribute("immunization", immunizationRepository.findAll());
        model.addAttribute("eligibility", Eligibility.values());
        model.addAttribute("yesNo", Yes_No.values());

        return "homevisit/insertmother/create";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, MotherMasterData motherMasterData) {
        motherMasterDataRepository.deleteById(id);
        return "redirect:/mothermasterdata/index";
    }

    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable Long id, MotherMasterData motherMasterData, Model model) {

        model.addAttribute("motherMasterData", motherMasterDataRepository.findById(id).orElse(null));

        // model.addAttribute("list", motherMasterDataRepository.findAll());
        model.addAttribute("resons", reasonsRepository.findAll());

        model.addAttribute("religion", religionRepository.findAll());

        model.addAttribute("maritalStatus", maritalStatusRepository.findAll());

        model.addAttribute("husbandsStatus", husbandsStatusRepository.findAll());

        model.addAttribute("relationWithPfm", relationsRepository.findAll());

        model.addAttribute("ethnicIdentity", ethinicIdentityRepository.findAll());

        model.addAttribute("educationLevel", educationLevelRepository.findAll());

        model.addAttribute("educationType", educationTypeRepository.findAll());

        model.addAttribute("occupation", occupationRepository.findAll());

        model.addAttribute("physicalStatus", physicalStatusRepository.findAll());

        model.addAttribute("immunization", immunizationRepository.findAll());

        model.addAttribute("eligibility", Eligibility.values());

        return "homevisit/insertmother/view";
    }

}
