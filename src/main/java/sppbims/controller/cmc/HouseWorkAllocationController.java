/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.controller.cmc;

import sppbims.model.homevisit.Gender;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.rehabilitations.HouseName;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.observation.O_MAddmissionRepository;
import sppbims.repository.rehabilitations.HouseNameRepository;
import sppbims.repository.rehabilitations.R_C_HouseAllocationsRepository;
import sppbims.repository.rehabilitations.R_M_HousAllocationRepository;
import sppbims.repository.rehabilitations.R_M_WorkAllocationRepository;
import sppbims.services.cmc.R_C_HouseAllocationsService;
import sppbims.services.cmc.R_FoodService;
import sppbims.services.cmc.R_M_HousAllocationService;
import sppbims.services.cmc.R_M_WorkAllocationService;
import sppbims.services.observation.O_MAddmissionService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Md Belayet Hossin
 */
@Controller
@RequestMapping("/houseworkallocation")
public class HouseWorkAllocationController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;

    @Autowired
    R_C_HouseAllocationsRepository r_C_HouseAllocationsRepository;

    @Autowired
    R_M_HousAllocationRepository r_M_HousAllocationRepository;

    @Autowired
    R_M_WorkAllocationRepository r_M_WorkAllocationRepository;

    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;
    @Autowired
    O_MAddmissionService o_MAddmissionService;

    @Autowired
    R_M_HousAllocationService r_M_HousAllocationService;

    @Autowired
    R_M_WorkAllocationService r_M_WorkAllocationService;
    @Autowired
    R_C_HouseAllocationsService r_C_HouseAllocationsService;

    @Autowired
    HouseNameRepository houseNameRepository;

    @Autowired
    R_FoodService r_FoodService;

    @Value("${repo_url}")
    public String repo_url;

    public static List<Map<String, Object>> filterUncommonMothers(
            List<Map<String, Object>> admitedMotherList, List<Long> houseMotherList) {

        // Convert houseMotherList to a Set for efficient lookups
        Set<Long> houseMotherSet = new HashSet<>(houseMotherList);

        // Initialize the result list for uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers = new ArrayList<>();

        // Iterate over admitedMotherList and add only uncommon mothers
        for (Map<String, Object> motherMap : admitedMotherList) {
            Long motherId = (Long) motherMap.get("motherMasterCodeId"); // Ensure this matches the field name in your map

            // Check if motherId is not in houseMotherSet
            if (motherId != null && !houseMotherSet.contains(motherId)) {
                uncommonAdmittedMothers.add(motherMap);
            }
        }

        return uncommonAdmittedMothers;
    }

    public static List<Map<String, Object>> filterCommonMothers(
            List<Map<String, Object>> admitedMotherList, List<Long> houseMotherList) {

        // Convert houseMotherList to a Set for efficient lookups
        Set<Long> houseMotherSet = new HashSet<>(houseMotherList);

        // Initialize the result list for common admitted mothers
        List<Map<String, Object>> commonAdmittedMothers = new ArrayList<>();

        // Iterate over admitedMotherList and add only common mothers
        for (Map<String, Object> motherMap : admitedMotherList) {
            Long motherId = (Long) motherMap.get("motherMasterCodeId"); // Ensure this matches the field name in your map

            // Check if motherId is in houseMotherSet
            if (motherId != null && houseMotherSet.contains(motherId)) {
                commonAdmittedMothers.add(motherMap);
            }
        }

        return commonAdmittedMothers;
    }

    @RequestMapping("/mothersearch")
    public String mothersearch(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());

        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
        List<Long> houseMotherList = r_M_HousAllocationService.motherHouseAllicationIdList();

        // Get the uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers = filterUncommonMothers(admitedMotherList, houseMotherList);

        model.addAttribute("list", uncommonAdmittedMothers);

        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/allocations/mothersearch";
    }

    @RequestMapping("/houseallocationlist")
    public String houseAllocationList(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
        List<Long> houseMotherList = r_M_HousAllocationService.motherHouseAllicationIdList();

        List<Map<String, Object>> filterCommonMothers = filterCommonMothers(admitedMotherList, houseMotherList);

        model.addAttribute("list", filterCommonMothers);

        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/allocations/houseallocationlist";
    }

    @RequestMapping("/motherlistforworkallocation")
    public String motherlistforworkallocation(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());

        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
        List<Long> workMotherList = r_M_WorkAllocationService.motherWorkAllicationIdList();

        // Get the uncommon admitted mothers
        List<Map<String, Object>> uncommonAdmittedMothers = filterUncommonMothers(admitedMotherList, workMotherList);

        model.addAttribute("list", uncommonAdmittedMothers);

        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/allocations/motherlistforworkallocation";
    }

    @RequestMapping("/workallocatedlist")
    public String workAllocatedList(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        List<Map<String, Object>> admitedMotherList = o_MAddmissionService.allAdmitedMotherList();
        List<Long> workMotherList = r_M_WorkAllocationService.motherWorkAllicationIdList();

        List<Map<String, Object>> filterCommonMothers = filterCommonMothers(admitedMotherList, workMotherList);

        model.addAttribute("list", filterCommonMothers);

        model.addAttribute("repo_url", repo_url);

        return "rehabilitations/allocations/workallocatedlist";
    }

    @RequestMapping("/report")
    public String report(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("list", o_MAddmissionService.allAdmitedMotherList());
        model.addAttribute("repo_url", repo_url);
        return "cmc/report/index";
    }

    @RequestMapping("/motherhouseallocationlist")
    public String motherhouseallocationlist(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("list", o_MAddmissionService.allAdmitedMotherList());
        model.addAttribute("repo_url", repo_url);
        return "rehabilitations/allocations/mother_house_allocation_list";
    }

    @RequestMapping("/childrenhouseallocationlist")
    public String childrenhouseallocationlist(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("list", o_MAddmissionService.allAdmitedMotherList());
        model.addAttribute("repo_url", repo_url);
        return "rehabilitations/allocations/children_house_allocation_list";
    }

    @RequestMapping("/motherworkallocationlist")
    public String motherworkallocationlist(Model model) {
        // model.addAttribute("list", o_MAddmissionRepository.findAll());
        model.addAttribute("list", o_MAddmissionService.allAdmitedMotherList());
        model.addAttribute("repo_url", repo_url);
        return "rehabilitations/allocations/mother_work_allocation_list";
    }

    @RequestMapping("/index/{id}")

    public String index(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);
        // model.addAttribute("list", r_M_HousAllocationRepository.findBymotherMasterCode(motherMasterData));

        model.addAttribute("list", r_M_HousAllocationService.all_Mother_House_Allocation_by_id(id));

        // model.addAttribute("list2", r_C_HouseAllocationsRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("list2", r_C_HouseAllocationsService.all_childHouse_Allocation_by_mother_id(id));

        return "rehabilitations/allocations/index";
    }

    @RequestMapping("/workindex/{id}")

    public String workIndex(Model model, @PathVariable Long id) {
        model.addAttribute("m_id", id);
        MotherMasterData motherMasterData = new MotherMasterData();
        motherMasterData.setId(id);

        //  model.addAttribute("list3", r_M_WorkAllocationRepository.findBymotherMasterCode(motherMasterData));
        model.addAttribute("list3", r_M_WorkAllocationService.all_Mother_Work_Location_by_id(id));
        return "rehabilitations/allocations/work_allocation_index";
    }

    @RequestMapping("/currentmotherhouselocation")
    public String currentMotherHouseLocation(Model model,
            @RequestParam(name = "houseName", required = false) HouseName houseName
    ) {
        model.addAttribute("houseName", houseNameRepository.findAll());
        model.addAttribute("list", r_M_HousAllocationService.currentMotherHouseLocation(houseName));
        return "rehabilitations/allocations/current_mother_location_list";
    }

    @RequestMapping("/currentchildhouselocation")
    public String currentchildhouselocation(Model model,
            @RequestParam(name = "houseName", required = false) HouseName houseName,
            @RequestParam(name = "gender", required = false) Gender gender
    ) {
        model.addAttribute("houseName", houseNameRepository.findAll());
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("list", r_C_HouseAllocationsService.currentChildHouseLocation(houseName, gender));
        return "rehabilitations/allocations/current_child_location_list";
    }

    @RequestMapping("/mothercurrentworklocation")
    public String mothercurrentworklocation(Model model
    ) {
        model.addAttribute("list", r_M_WorkAllocationService.currentMotherWorkLocation());
        return "rehabilitations/allocations/current_mother_work_location_list";
    }

    @RequestMapping("/food_report")
    public String foodReport(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {

        List<Map<String, Object>> allFoodData = r_FoodService.get_All_FoodData_Report_By_filter(startDate, endDate);
        model.addAttribute("list", allFoodData);

        int totalPresent = allFoodData.stream()
                .mapToInt(data -> (Integer) data.get("totalPresent")) // Extracting 'present' field as Integer
                .sum();

        model.addAttribute("totalpresent", totalPresent);
        return "rehabilitations/food/food_by_date_report";
    }

}
