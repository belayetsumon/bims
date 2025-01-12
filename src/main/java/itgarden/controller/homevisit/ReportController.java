package itgarden.controller.homevisit;

import itgarden.model.homevisit.Address_Type;
import itgarden.model.homevisit.Decision;
import itgarden.model.homevisit.District;
import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.EducationType;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.EthinicIdentity;
import itgarden.model.homevisit.Gender;
import itgarden.model.homevisit.House_Type;
import itgarden.model.homevisit.HusbandsStatus;
import itgarden.model.homevisit.MaritalStatus;
import itgarden.model.homevisit.Occupation;
import itgarden.model.homevisit.Ownershif_type;
import itgarden.model.homevisit.Reasons;
import itgarden.model.homevisit.Relations;
import itgarden.model.homevisit.Religion;
import itgarden.model.homevisit.Yes_No;
import itgarden.repository.homevisit.EducationLevelRepository;
import itgarden.repository.homevisit.EducationTypeRepository;
import itgarden.repository.homevisit.EthinicIdentityRepository;
import itgarden.repository.homevisit.House_TypeRepository;
import itgarden.repository.homevisit.HusbandsStatusRepository;
import itgarden.repository.homevisit.ImmunizationRepository;
import itgarden.repository.homevisit.M_AddressRepository;
import itgarden.repository.homevisit.M_ApprovalRepository;
import itgarden.repository.homevisit.MaritalStatusRepository;
import itgarden.repository.homevisit.MotherMasterDataRepository;
import itgarden.repository.homevisit.OccupationRepository;
import itgarden.repository.homevisit.Ownershif_typeRepository;
import itgarden.repository.homevisit.PhysicalStatusRepository;
import itgarden.repository.homevisit.ReasonsRepository;
import itgarden.repository.homevisit.RelationsRepository;
import itgarden.repository.homevisit.ReligionRepository;
import itgarden.repository.observation.O_InductionRepository;
import itgarden.repository.observation.O_MAddmissionRepository;
import itgarden.services.homevisit.M_AddressService;
import itgarden.services.homevisit.M_Child_infoService;
import itgarden.services.homevisit.M_Current_HelpService;
import itgarden.services.homevisit.M_House_InformationService;
import itgarden.services.homevisit.M_Income_InformationService;
import itgarden.services.homevisit.M_PropertyService;
import itgarden.services.homevisit.MotherMasterDataReportService;
import jakarta.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
