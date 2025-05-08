package sppbims.controller.homevisit;

import sppbims.model.homevisit.Address_Type;
import sppbims.model.homevisit.Decision;
import sppbims.model.homevisit.District;
import sppbims.model.homevisit.EducationLevel;
import sppbims.model.homevisit.EducationType;
import sppbims.model.homevisit.Eligibility;
import sppbims.model.homevisit.EthinicIdentity;
import sppbims.model.homevisit.Gender;
import sppbims.model.homevisit.House_Type;
import sppbims.model.homevisit.HusbandsStatus;
import sppbims.model.homevisit.MaritalStatus;
import sppbims.model.homevisit.Occupation;
import sppbims.model.homevisit.Ownershif_type;
import sppbims.model.homevisit.Reasons;
import sppbims.model.homevisit.Relations;
import sppbims.model.homevisit.Religion;
import sppbims.model.homevisit.Yes_No;
import sppbims.repository.homevisit.EducationLevelRepository;
import sppbims.repository.homevisit.EducationTypeRepository;
import sppbims.repository.homevisit.EthinicIdentityRepository;
import sppbims.repository.homevisit.House_TypeRepository;
import sppbims.repository.homevisit.HusbandsStatusRepository;
import sppbims.repository.homevisit.ImmunizationRepository;
import sppbims.repository.homevisit.M_AddressRepository;
import sppbims.repository.homevisit.M_ApprovalRepository;
import sppbims.repository.homevisit.MaritalStatusRepository;
import sppbims.repository.homevisit.MotherMasterDataRepository;
import sppbims.repository.homevisit.OccupationRepository;
import sppbims.repository.homevisit.Ownershif_typeRepository;
import sppbims.repository.homevisit.PhysicalStatusRepository;
import sppbims.repository.homevisit.ReasonsRepository;
import sppbims.repository.homevisit.RelationsRepository;
import sppbims.repository.homevisit.ReligionRepository;
import sppbims.repository.observation.O_InductionRepository;
import sppbims.repository.observation.O_MAddmissionRepository;
import sppbims.services.homevisit.M_AddressService;
import sppbims.services.homevisit.M_ApprovalServices;
import sppbims.services.homevisit.M_Child_infoService;
import sppbims.services.homevisit.M_Current_HelpService;
import sppbims.services.homevisit.M_House_InformationService;
import sppbims.services.homevisit.M_Income_InformationService;
import sppbims.services.homevisit.M_PropertyService;
import sppbims.services.homevisit.MotherMasterDataReportService;
import jakarta.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sppbims.services.homevisit.M_AccessibilityService;
import sppbims.services.homevisit.M_Community_InformationService;
import sppbims.services.homevisit.M_Family_informationService;
import sppbims.services.homevisit.M_LifestyleService;
import sppbims.services.homevisit.M_Local_Govt_FacilitiesService;
import sppbims.services.homevisit.M_NutritionService;

@Controller
@RequestMapping({"/homevisitreport"})
public class ReportController {

    @Autowired
    MotherMasterDataRepository motherMasterDataRepository;
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
    OccupationRepository occupationRepository;
    @Autowired
    PhysicalStatusRepository physicalStatusRepository;
    @Autowired
    ImmunizationRepository immunizationRepository;
    @Autowired
    M_ApprovalRepository m_ApprovalRepository;
    @Autowired
    O_InductionRepository o_InductionRepository;
    @Autowired
    O_MAddmissionRepository o_MAddmissionRepository;
    @Autowired
    M_AddressRepository m_AddressRepository;
    @Autowired
    MotherMasterDataReportService motherMasterDataReportService;
    @Autowired
    M_AddressService m_AddressService;
    @Autowired
    M_Child_infoService m_Child_infoService;
    @Autowired
    M_Current_HelpService m_Current_HelpService;
    @Autowired
    M_House_InformationService m_House_InformationService;
    @Autowired
    M_Income_InformationService m_Income_InformationService;
    @Autowired
    M_PropertyService m_PropertyService;
    @Autowired
    House_TypeRepository house_TypeRepository;
    @Autowired
    Ownershif_typeRepository ownershif_typeRepository;

    @Autowired
    M_ApprovalServices approvalService;

    @Autowired
    M_AccessibilityService m_AccessibilityService;

    @Autowired
    M_Community_InformationService m_Community_InformationService;

    @Autowired
    M_LifestyleService m_LifestyleService;

    @Autowired
    M_Family_informationService m_Family_informationService;

    @Autowired
    M_NutritionService m_NutritionService;

    @Autowired
    M_Local_Govt_FacilitiesService m_Local_Govt_FacilitiesService;

    @RequestMapping({"/index"})
    public String index(Model model) {
        return "homevisit/report/index";
    }

    @RequestMapping({"/homevisitreportsearch"})
    public String homeVisitReportSearch(Model model) {
        model.addAttribute("resons", this.reasonsRepository.findAll());
        model.addAttribute("religion", this.religionRepository.findAll());
        model.addAttribute("maritalStatus", this.maritalStatusRepository.findAll());
        model.addAttribute("husbandsStatus", this.husbandsStatusRepository.findAll());
        model.addAttribute("relationWithPfm", this.relationsRepository.findAll());
        model.addAttribute("ethnicIdentity", this.ethinicIdentityRepository.findAll());
        model.addAttribute("educationLevel", this.educationLevelRepository.findAll());
        model.addAttribute("educationType", this.educationTypeRepository.findAll());
        model.addAttribute("occupation", this.occupationRepository.findAll());
        model.addAttribute("physicalStatus", this.physicalStatusRepository.findAll());
        model.addAttribute("immunization", this.immunizationRepository.findAll());
        model.addAttribute("eligibility", Eligibility.values());
        model.addAttribute("yesNo", Yes_No.values());
        return "homevisit/report/homevisitreportsearch";
    }

    @RequestMapping({"/homevisitreportsearchresult"})
    public String homeVisitReportSearchResult(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(required = false) Reasons resons,
            @RequestParam(required = false) String motherMasterCode,
            @RequestParam(required = false) String motherName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String dateOfBirth,
            @RequestParam(required = false) String mobileNumber,
            @RequestParam(required = false) Religion religion,
            @RequestParam(required = false) MaritalStatus maritalStatus,
            @RequestParam(required = false) HusbandsStatus husbandsStatus,
            @RequestParam(required = false) Relations relationWithPfm,
            @RequestParam(required = false) EthinicIdentity ethnicIdentity,
            @RequestParam(required = false) EducationLevel educationLevel,
            @RequestParam(required = false) EducationType educationType,
            @RequestParam(required = false) Occupation occupation,
            @RequestParam(required = false) String numberOfSons,
            @RequestParam(required = false) String numberOfDaughters,
            @RequestParam(required = false) String numberOfEligibleChildren,
            @RequestParam(required = false) Yes_No socialviolence,
            @RequestParam(required = false) Yes_No childrenFacedSocialViolence,
            @RequestParam(required = false) Yes_No sexualAbuse,
            @RequestParam(required = false) Yes_No childrenSexualAbuse,
            @RequestParam(required = false) Yes_No earlyMarriage,
            @RequestParam(required = false) Yes_No pregnancyAfterBeingRaped,
            @RequestParam(required = false) Yes_No facedDowryAbuse,
            @RequestParam(required = false) Eligibility eligibility, Model model) {
        model.addAttribute("list", this.motherMasterDataReportService.matherMasterdataSearchResult(startDate, endDate, resons, motherMasterCode, motherName, dateOfBirth, mobileNumber, religion, maritalStatus, husbandsStatus, relationWithPfm, ethnicIdentity, educationLevel, educationType, occupation, numberOfSons, numberOfDaughters, numberOfEligibleChildren, socialviolence, childrenFacedSocialViolence, sexualAbuse, childrenSexualAbuse, earlyMarriage, pregnancyAfterBeingRaped, facedDowryAbuse, eligibility));
        return "homevisit/report/homevisitreportsearchresult";
    }

    @RequestMapping({"/eligiblemothermlist"})
    public String eligibleMotherList(Model model) {
        model.addAttribute("list", this.motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Eligible));
        return "homevisit/report/eligiblemothermlist";
    }

    @RequestMapping({"/noteligiblemotherlist"})
    public String notEligibleMotherList(Model model) {
        model.addAttribute("list", this.motherMasterDataRepository.findAllByeligibilityOrderByIdDesc(Eligibility.Not_Eligible));
        return "homevisit/report/noteligiblemotherlist";
    }

    @RequestMapping({"/approvemotherlist"})
    public String approveMotherList(Model model) {
        model.addAttribute("approval", this.m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve));
        return "homevisit/report/approvemotherlist";
    }

    @RequestMapping({"/notapprovemotherlist"})
    public String notapproveMotherList(Model model) {
        model.addAttribute("approval", this.m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Not_Approve));
        return "homevisit/report/notapprovemotherlist";
    }

    @RequestMapping({"/approvewithinstructionsmotherlist"})
    public String approveWithInstructionsMotherList(Model model) {
        model.addAttribute("approval", this.m_ApprovalRepository.findAllBydecissionOrderByIdDesc(Decision.Approve_With_Instruction));
        return "homevisit/report/approvwithinstructionemotherlist";
    }

    @RequestMapping({"/addresslist"})
    public String address(Model model) {
        model.addAttribute("addres", this.m_AddressRepository.findAll());
        return "homevisit/report/addresslist";
    }

    @RequestMapping({"/inductionlist"})
    public String inductionlist(Model model) {
        model.addAttribute("o_Induction", this.o_InductionRepository.findAll());
        return "homevisit/report/inductionlist";
    }

    @RequestMapping({"/admissionlist"})
    public String admissionlist(Model model) {
        model.addAttribute("o_MAddmission", this.o_MAddmissionRepository.findAll());
        return "homevisit/report/admissionlist";
    }

    @RequestMapping({"/motherbydistrict"})
    public String motherbydistrict(Model model, @RequestParam(required = false) District district, @RequestParam(required = false) Address_Type addressType) {
        model.addAttribute("addresstypes", Address_Type.values());
        model.addAttribute("districtlist", District.values());
        List<Tuple> address = this.m_AddressService.motherbydistrict(district, addressType);
        model.addAttribute("list", address);
        return "homevisit/report/motherbydistrict";
    }

    @RequestMapping({"/property"})
    public String property(Model model,
            @RequestParam(name = "motherMasterCode", required = false) String motherMasterCode,
            @RequestParam(name = "minSavingMoney", required = false) Float minSavingMoney,
            @RequestParam(name = "minHomelandQuantity", required = false) Float minHomelandQuantity,
            @RequestParam(name = "minCultivableLandQuantity", required = false) Float minCultivableLandQuantity,
            @RequestParam(name = "minJewelryQuantity", required = false) Float minJewelryQuantity,
            @RequestParam(name = "minAnimalsQuantity", required = false) Float minAnimalsQuantity,
            @RequestParam(name = "minInvestmentsSharesQuantity", required = false) Float minInvestmentsSharesQuantity,
            @RequestParam(name = "minLoanPersonQuantity", required = false) Float minLoanPersonQuantity,
            @RequestParam(name = "minOrganizationsLoanQuantity", required = false) Float minOrganizationsLoanQuantity
    ) {
        List<Tuple> property = m_PropertyService.motherproperty(
                motherMasterCode,
                minSavingMoney,
                minHomelandQuantity,
                minCultivableLandQuantity,
                minJewelryQuantity,
                minAnimalsQuantity,
                minInvestmentsSharesQuantity,
                minLoanPersonQuantity,
                minOrganizationsLoanQuantity
        );
        model.addAttribute("list", property);
        return "homevisit/report/property";
    }

    @RequestMapping({"/childinfo"})
    public String childinfo(Model model,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate,
            @RequestParam(required = false) String motherMasterCode,
            @RequestParam(required = false) String childMasterCode,
            @RequestParam(required = false) String motherName,
            @RequestParam(required = false) String childName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String dateOfBirth,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) EducationLevel educationLevel,
            @RequestParam(required = false) EducationType educationType,
            @RequestParam(required = false) Eligibility eligibility) {
        model.addAttribute("educationLevel", this.educationLevelRepository.findAll());
        model.addAttribute("educationType", this.educationTypeRepository.findAll());
        model.addAttribute("eligibility", Eligibility.values());
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("list", this.m_Child_infoService.childSearchResult(startDate, endDate, motherMasterCode, childMasterCode, motherName, childName, dateOfBirth, gender, educationLevel, educationType, eligibility));
        return "homevisit/report/childinfo";
    }

    @RequestMapping({"/income"})
    public String income(Model model, @RequestParam(name = "monthlyincome", required = false) String monthlyIncome) {
        List<Tuple> motherIcome = this.m_Income_InformationService.motherOtherIncome(monthlyIncome);
        model.addAttribute("list", motherIcome);
        return "homevisit/report/income";
    }

    @RequestMapping({"/house"})
    public String house(Model model, @RequestParam(name = "ownership", required = false) Ownershif_type ownership, @RequestParam(name = "houseType", required = false) House_Type houseType) {
        model.addAttribute("housetype", this.house_TypeRepository.findAll());
        model.addAttribute("ownershiftype", this.ownershif_typeRepository.findAll());
        List<Tuple> houseownership = this.m_House_InformationService.motherHouseownership(ownership, houseType);
        model.addAttribute("list", houseownership);
        return "homevisit/report/house";
    }

    @RequestMapping({"/currenthelp"})
    public String currenthelp(Model model) {
        List<Tuple> motherlist = this.m_Current_HelpService.motherList();
        model.addAttribute("list", motherlist);
        return "homevisit/report/currenthelp";
    }

    @RequestMapping({"/approved_mother_list"})
    public String approved_mother_list(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", approvalService.allApprovalMotherReport(Decision.Approve, startDate, endDate));
        return "homevisit/report/approval_mother_list";
    }

    @RequestMapping({"/community_information_list"})
    public String community_information_list(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", m_Community_InformationService.getCommunityInfoList(startDate, endDate));
        return "homevisit/report/community_information_list";
    }

    @RequestMapping({"/accessibility_list"})
    public String accessibility_list(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", m_AccessibilityService.findAllWithDateFilter(startDate, endDate));
        return "homevisit/report/accessibility_list";
    }

    @RequestMapping({"/lifestyle_list"})
    public String lifestyle_list(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", m_LifestyleService.getLifestyleInfoList(startDate, endDate));
        return "homevisit/report/lifestyle_list";
    }

    @RequestMapping({"/nutrition_list"})
    public String nutrition_list(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", m_NutritionService.getNutritionList(startDate, endDate));
        return "homevisit/report/nutrition_list";
    }

    @RequestMapping({"/local_govt_facilities_list"})
    public String local_govt_facilities_list(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", m_Local_Govt_FacilitiesService.getLocalGovtFacilitiesList(startDate, endDate));
        return "homevisit/report/local_govt_facilities_list";
    }

    @RequestMapping({"/family_information_list"})
    public String family_nformation_list(Model model,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate
    ) {
        model.addAttribute("list", m_Family_informationService.getFamilyInfoList(startDate, endDate));
        return "homevisit/report/family_nformation_list";
    }

}
