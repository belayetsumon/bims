/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import itgarden.model.auth.Users;
import itgarden.model.clinic.C_Admission;
import itgarden.model.clinic.C_NutritionalStatus;
import itgarden.model.clinic.C_Referral;
import itgarden.model.clinic.C_Release;
import itgarden.model.clinic.C_visit;
import itgarden.model.follow_up_report.FollowMotherPerCapitaIncome;
import itgarden.model.follow_up_report.FollowUpMother;
import itgarden.model.follow_up_report.MotherCrisisMeetUp;
import itgarden.model.leave.LeaveMother;
import itgarden.model.literacy.LiteracyDigitalLiteracy;
import itgarden.model.literacy.LiteracyHigherEducationAdmission;
import itgarden.model.literacy.LiteracyNumeracy;
import itgarden.model.literacy.LiteracyTalkingScience;
import itgarden.model.observation.MotherImage;
import itgarden.model.observation.O_ChildAdmission;
import itgarden.model.observation.O_Induction;
import itgarden.model.observation.O_Inhouse_Inductions_Mother;
import itgarden.model.observation.O_MAddmission;
import itgarden.model.observation.O_MHealthConditions;
import itgarden.model.observation.O_Professional_Obserbations_Mother;
import itgarden.model.pre_reintegration_checklist.ReintegrationCheckList;
import itgarden.model.pre_reintegration_visit.M_Accessibility_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.M_Address_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.M_Community_Information_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.M_Family_information_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.M_House_Information_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.M_Lifestyle_ReintegrationVisit;
import itgarden.model.pre_reintegration_visit.PreReintegrationVisit;
import itgarden.model.rehabilitations.R_C_HouseAllocations;
import itgarden.model.rehabilitations.R_IGA_Training;
import itgarden.model.rehabilitations.R_Life_Skill_Trainning;
import itgarden.model.rehabilitations.R_M_HousAllocation;
import itgarden.model.rehabilitations.R_M_WorkAllocation;
import itgarden.model.rehabilitations.R_OT;
import itgarden.model.rehabilitations.R_PT;
import itgarden.model.rehabilitations.R_PsychologyMother;
import itgarden.model.reintegration_release.ReleaseMother;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
@Table(name = "motherMasterData")
public class MotherMasterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotEmpty(message = "This field cannot be blank.")
    public String visitOfficersName;

    @Column(nullable = false)
    @NotNull(message = " Referral date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate dateReferral;

    @NotEmpty(message = "This field cannot be blank.")
    public String referredFrom;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Reasons resons;

    @Column(nullable = false)
    @NotNull(message = " Home Visit date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate homeVisitDate;

    @NotEmpty(message = "This field cannot be blank.")
    @Column(unique = true)
    public String motherMasterCode;

    @NotEmpty(message = "This field cannot be blank.")
    public String motherName;

    @Column(nullable = false)
    @NotNull(message = " Date of birth cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate dateOfBirth;

    public String age;

    @NotEmpty(message = "This field cannot be blank.")
    public String mMothersName;

    @NotEmpty(message = "This field cannot be blank.")
    public String fathersName;

    public String mobileNumber;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Religion religion;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public MaritalStatus maritalStatus;

    @NotEmpty(message = "This field cannot be blank.")
    public String husbandsName;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public HusbandsStatus husbandsStatus;

    @NotEmpty(message = "This field cannot be blank.")
    public String primeFamilyMemberName;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Relations relationWithPfm;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EthinicIdentity ethnicIdentity;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EducationLevel educationLevel;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EducationType educationType;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Occupation occupation;

    @NotEmpty(message = "This field cannot be blank.")
    @Lob
    public String physicalStatus;

    @NotEmpty(message = "This field cannot be blank.")
    @Lob
    public String immunization;

    @NotNull(message = "This field cannot be blank.")
    public int numberOfSons;

    @NotNull(message = "This field cannot be blank.")
    public int numberOfDaughters;

    @NotNull(message = "This field cannot be blank.")
    public int numberOfEligibleChildren;

    @Lob
    public String majorFindings;
    //  New version aded  

    @Enumerated(EnumType.STRING)
    public Yes_No socialviolence;

    @Enumerated(EnumType.STRING)
    public Yes_No childrenFacedSocialViolence;

    @Enumerated(EnumType.STRING)
    public Yes_No sexualAbuse;

    @Enumerated(EnumType.STRING)
    public Yes_No childrenSexualAbuse;

    @Enumerated(EnumType.STRING)
    public Yes_No earlyMarriage;
    
     @Enumerated(EnumType.STRING)
    public Yes_No pregnancyAfterBeingRaped;
     
    @Enumerated(EnumType.STRING)
    public Yes_No  facedDowryAbuse; 
    
    // End new version property

    @Lob
    public String otherRemarks;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Eligibility eligibility;

    /**
     * ********* Audit ******************************
     */
    @Column(insertable = true, updatable = false)
    public LocalDate created = LocalDate.now();

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public Users createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    public Date updated = new Date();

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public Users updatedBy;

    /*      Relations ship  Start */
 /*      Home Visit */
    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Address> maddress = new ArrayList<>();

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Accessibility maccessibility;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Approval mapproval;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Child_info> mchildinfo;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Community_Information mcommunityInformation;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Current_Help> mcurrentHelp = new ArrayList<>();

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Family_information> mfamilynformation = new ArrayList<>();

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_House_Information mhouseInformation;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Lifestyle mlifestyle;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Income_Information mincomeInformation;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Local_Govt_Facilities mlocalGovtFacilities;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Nutrition mnutrition;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Property mproperty;

    // objerbations
    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_Induction oinduction;
//

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_Inhouse_Inductions_Mother oinhouseInductionsMother;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_MHealthConditions omHealthConditions;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_Professional_Obserbations_Mother oprofessionalObserbationsMother;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_MAddmission addmission;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public MotherImage mimage;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<O_ChildAdmission> childAdmission = new ArrayList<>();

//     Rehabilations 
//    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_C_HouseAllocations> rcHouseAllocations;

    //@LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_M_HousAllocation> rmHousAllocation;

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_M_WorkAllocation> rmWorkAllocation = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_IGA_Training> riGATraining = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_Life_Skill_Trainning> rlifeSkillTrainning = new ArrayList<>();

    // Leave 
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<LeaveMother> LeaveMother = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_OT> rOT = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_PT> rPT = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_PsychologyMother> rpsychologyMother = new ArrayList<>();

    //clinic  C_NutritionalStatus
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_NutritionalStatus> cnutritionalStatus = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_visit> cvisit = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_Admission> cadmission = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_Referral> creferral = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_Release> crelease = new ArrayList<>();

    // Literacy
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public LiteracyDigitalLiteracy literacyDigitalLiteracy;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public LiteracyHigherEducationAdmission literacyHigherEducationAdmission;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public LiteracyNumeracy literacyRegularAdmission;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public LiteracyTalkingScience literacyTalkingScience;

    //   pree RE intrigations
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public PreReintegrationVisit preReintegrationVisit;

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Accessibility_ReintegrationVisit> maccessibilityReintegrationVisit = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Address_ReintegrationVisit> maddressReintegrationVisit = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Community_Information_ReintegrationVisit> mcommunityInformationReintegrationVisit = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Family_information_ReintegrationVisit> mfamilyinformationReintegrationVisit = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_House_Information_ReintegrationVisit> mhouseInformationReintegrationVisit = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Lifestyle_ReintegrationVisit> mlifestyleReintegrationVisit = new ArrayList<>();

    // re intrigration check list
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public ReintegrationCheckList reintegrationCheckList;

    // relase
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public ReleaseMother releaseMother;

    // fllow up
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<FollowUpMother> followUpMother = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<FollowMotherPerCapitaIncome> followMotherPerCapitaIncome = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<MotherCrisisMeetUp> motherCrisisMeetUp = new ArrayList<>();


    /*      Relations ship  end*/
    /**
     * audit end*********************
     */
    public MotherMasterData() {
    }

    public MotherMasterData(Long id, String visitOfficersName, LocalDate dateReferral, String referredFrom, Reasons resons, LocalDate homeVisitDate, String motherMasterCode, String motherName, LocalDate dateOfBirth, String age, String mMothersName, String fathersName, String mobileNumber, Religion religion, MaritalStatus maritalStatus, String husbandsName, HusbandsStatus husbandsStatus, String primeFamilyMemberName, Relations relationWithPfm, EthinicIdentity ethnicIdentity, EducationLevel educationLevel, EducationType educationType, Occupation occupation, String physicalStatus, String immunization, int numberOfSons, int numberOfDaughters, int numberOfEligibleChildren, String majorFindings, Yes_No socialviolence, Yes_No childrenFacedSocialViolence, Yes_No sexualAbuse, Yes_No childrenSexualAbuse, Yes_No earlyMarriage, Yes_No pregnancyAfterBeingRaped, Yes_No facedDowryAbuse, String otherRemarks, Eligibility eligibility, Users createdBy, Users updatedBy, M_Accessibility maccessibility, M_Approval mapproval, List<M_Child_info> mchildinfo, M_Community_Information mcommunityInformation, M_House_Information mhouseInformation, M_Lifestyle mlifestyle, M_Income_Information mincomeInformation, M_Local_Govt_Facilities mlocalGovtFacilities, M_Nutrition mnutrition, M_Property mproperty, O_Induction oinduction, O_Inhouse_Inductions_Mother oinhouseInductionsMother, O_MHealthConditions omHealthConditions, O_Professional_Obserbations_Mother oprofessionalObserbationsMother, O_MAddmission addmission, MotherImage mimage, List<R_C_HouseAllocations> rcHouseAllocations, List<R_M_HousAllocation> rmHousAllocation, LiteracyDigitalLiteracy literacyDigitalLiteracy, LiteracyHigherEducationAdmission literacyHigherEducationAdmission, LiteracyNumeracy literacyRegularAdmission, LiteracyTalkingScience literacyTalkingScience, PreReintegrationVisit preReintegrationVisit, ReintegrationCheckList reintegrationCheckList, ReleaseMother releaseMother) {
        this.id = id;
        this.visitOfficersName = visitOfficersName;
        this.dateReferral = dateReferral;
        this.referredFrom = referredFrom;
        this.resons = resons;
        this.homeVisitDate = homeVisitDate;
        this.motherMasterCode = motherMasterCode;
        this.motherName = motherName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.mMothersName = mMothersName;
        this.fathersName = fathersName;
        this.mobileNumber = mobileNumber;
        this.religion = religion;
        this.maritalStatus = maritalStatus;
        this.husbandsName = husbandsName;
        this.husbandsStatus = husbandsStatus;
        this.primeFamilyMemberName = primeFamilyMemberName;
        this.relationWithPfm = relationWithPfm;
        this.ethnicIdentity = ethnicIdentity;
        this.educationLevel = educationLevel;
        this.educationType = educationType;
        this.occupation = occupation;
        this.physicalStatus = physicalStatus;
        this.immunization = immunization;
        this.numberOfSons = numberOfSons;
        this.numberOfDaughters = numberOfDaughters;
        this.numberOfEligibleChildren = numberOfEligibleChildren;
        this.majorFindings = majorFindings;
        this.socialviolence = socialviolence;
        this.childrenFacedSocialViolence = childrenFacedSocialViolence;
        this.sexualAbuse = sexualAbuse;
        this.childrenSexualAbuse = childrenSexualAbuse;
        this.earlyMarriage = earlyMarriage;
        this.pregnancyAfterBeingRaped = pregnancyAfterBeingRaped;
        this.facedDowryAbuse = facedDowryAbuse;
        this.otherRemarks = otherRemarks;
        this.eligibility = eligibility;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.maccessibility = maccessibility;
        this.mapproval = mapproval;
        this.mchildinfo = mchildinfo;
        this.mcommunityInformation = mcommunityInformation;
        this.mhouseInformation = mhouseInformation;
        this.mlifestyle = mlifestyle;
        this.mincomeInformation = mincomeInformation;
        this.mlocalGovtFacilities = mlocalGovtFacilities;
        this.mnutrition = mnutrition;
        this.mproperty = mproperty;
        this.oinduction = oinduction;
        this.oinhouseInductionsMother = oinhouseInductionsMother;
        this.omHealthConditions = omHealthConditions;
        this.oprofessionalObserbationsMother = oprofessionalObserbationsMother;
        this.addmission = addmission;
        this.mimage = mimage;
        this.rcHouseAllocations = rcHouseAllocations;
        this.rmHousAllocation = rmHousAllocation;
        this.literacyDigitalLiteracy = literacyDigitalLiteracy;
        this.literacyHigherEducationAdmission = literacyHigherEducationAdmission;
        this.literacyRegularAdmission = literacyRegularAdmission;
        this.literacyTalkingScience = literacyTalkingScience;
        this.preReintegrationVisit = preReintegrationVisit;
        this.reintegrationCheckList = reintegrationCheckList;
        this.releaseMother = releaseMother;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitOfficersName() {
        return visitOfficersName;
    }

    public void setVisitOfficersName(String visitOfficersName) {
        this.visitOfficersName = visitOfficersName;
    }

    public LocalDate getDateReferral() {
        return dateReferral;
    }

    public void setDateReferral(LocalDate dateReferral) {
        this.dateReferral = dateReferral;
    }

    public String getReferredFrom() {
        return referredFrom;
    }

    public void setReferredFrom(String referredFrom) {
        this.referredFrom = referredFrom;
    }

    public Reasons getResons() {
        return resons;
    }

    public void setResons(Reasons resons) {
        this.resons = resons;
    }

    public LocalDate getHomeVisitDate() {
        return homeVisitDate;
    }

    public void setHomeVisitDate(LocalDate homeVisitDate) {
        this.homeVisitDate = homeVisitDate;
    }

    public String getMotherMasterCode() {
        return motherMasterCode;
    }

    public void setMotherMasterCode(String motherMasterCode) {
        this.motherMasterCode = motherMasterCode;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getmMothersName() {
        return mMothersName;
    }

    public void setmMothersName(String mMothersName) {
        this.mMothersName = mMothersName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getHusbandsName() {
        return husbandsName;
    }

    public void setHusbandsName(String husbandsName) {
        this.husbandsName = husbandsName;
    }

    public HusbandsStatus getHusbandsStatus() {
        return husbandsStatus;
    }

    public void setHusbandsStatus(HusbandsStatus husbandsStatus) {
        this.husbandsStatus = husbandsStatus;
    }

    public String getPrimeFamilyMemberName() {
        return primeFamilyMemberName;
    }

    public void setPrimeFamilyMemberName(String primeFamilyMemberName) {
        this.primeFamilyMemberName = primeFamilyMemberName;
    }

    public Relations getRelationWithPfm() {
        return relationWithPfm;
    }

    public void setRelationWithPfm(Relations relationWithPfm) {
        this.relationWithPfm = relationWithPfm;
    }

    public EthinicIdentity getEthnicIdentity() {
        return ethnicIdentity;
    }

    public void setEthnicIdentity(EthinicIdentity ethnicIdentity) {
        this.ethnicIdentity = ethnicIdentity;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getPhysicalStatus() {
        return physicalStatus;
    }

    public void setPhysicalStatus(String physicalStatus) {
        this.physicalStatus = physicalStatus;
    }

    public String getImmunization() {
        return immunization;
    }

    public void setImmunization(String immunization) {
        this.immunization = immunization;
    }

    public int getNumberOfSons() {
        return numberOfSons;
    }

    public void setNumberOfSons(int numberOfSons) {
        this.numberOfSons = numberOfSons;
    }

    public int getNumberOfDaughters() {
        return numberOfDaughters;
    }

    public void setNumberOfDaughters(int numberOfDaughters) {
        this.numberOfDaughters = numberOfDaughters;
    }

    public int getNumberOfEligibleChildren() {
        return numberOfEligibleChildren;
    }

    public void setNumberOfEligibleChildren(int numberOfEligibleChildren) {
        this.numberOfEligibleChildren = numberOfEligibleChildren;
    }

    public String getMajorFindings() {
        return majorFindings;
    }

    public void setMajorFindings(String majorFindings) {
        this.majorFindings = majorFindings;
    }

    public Yes_No getSocialviolence() {
        return socialviolence;
    }

    public void setSocialviolence(Yes_No socialviolence) {
        this.socialviolence = socialviolence;
    }

    public Yes_No getChildrenFacedSocialViolence() {
        return childrenFacedSocialViolence;
    }

    public void setChildrenFacedSocialViolence(Yes_No childrenFacedSocialViolence) {
        this.childrenFacedSocialViolence = childrenFacedSocialViolence;
    }

    public Yes_No getSexualAbuse() {
        return sexualAbuse;
    }

    public void setSexualAbuse(Yes_No sexualAbuse) {
        this.sexualAbuse = sexualAbuse;
    }

    public Yes_No getChildrenSexualAbuse() {
        return childrenSexualAbuse;
    }

    public void setChildrenSexualAbuse(Yes_No childrenSexualAbuse) {
        this.childrenSexualAbuse = childrenSexualAbuse;
    }

    public Yes_No getEarlyMarriage() {
        return earlyMarriage;
    }

    public void setEarlyMarriage(Yes_No earlyMarriage) {
        this.earlyMarriage = earlyMarriage;
    }

    public Yes_No getPregnancyAfterBeingRaped() {
        return pregnancyAfterBeingRaped;
    }

    public void setPregnancyAfterBeingRaped(Yes_No pregnancyAfterBeingRaped) {
        this.pregnancyAfterBeingRaped = pregnancyAfterBeingRaped;
    }

    public Yes_No getFacedDowryAbuse() {
        return facedDowryAbuse;
    }

    public void setFacedDowryAbuse(Yes_No facedDowryAbuse) {
        this.facedDowryAbuse = facedDowryAbuse;
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks;
    }

    public Eligibility getEligibility() {
        return eligibility;
    }

    public void setEligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Users getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Users updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<M_Address> getMaddress() {
        return maddress;
    }

    public void setMaddress(List<M_Address> maddress) {
        this.maddress = maddress;
    }

    public M_Accessibility getMaccessibility() {
        return maccessibility;
    }

    public void setMaccessibility(M_Accessibility maccessibility) {
        this.maccessibility = maccessibility;
    }

    public M_Approval getMapproval() {
        return mapproval;
    }

    public void setMapproval(M_Approval mapproval) {
        this.mapproval = mapproval;
    }

    public List<M_Child_info> getMchildinfo() {
        return mchildinfo;
    }

    public void setMchildinfo(List<M_Child_info> mchildinfo) {
        this.mchildinfo = mchildinfo;
    }

    public M_Community_Information getMcommunityInformation() {
        return mcommunityInformation;
    }

    public void setMcommunityInformation(M_Community_Information mcommunityInformation) {
        this.mcommunityInformation = mcommunityInformation;
    }

    public List<M_Current_Help> getMcurrentHelp() {
        return mcurrentHelp;
    }

    public void setMcurrentHelp(List<M_Current_Help> mcurrentHelp) {
        this.mcurrentHelp = mcurrentHelp;
    }

    public List<M_Family_information> getMfamilynformation() {
        return mfamilynformation;
    }

    public void setMfamilynformation(List<M_Family_information> mfamilynformation) {
        this.mfamilynformation = mfamilynformation;
    }

    public M_House_Information getMhouseInformation() {
        return mhouseInformation;
    }

    public void setMhouseInformation(M_House_Information mhouseInformation) {
        this.mhouseInformation = mhouseInformation;
    }

    public M_Lifestyle getMlifestyle() {
        return mlifestyle;
    }

    public void setMlifestyle(M_Lifestyle mlifestyle) {
        this.mlifestyle = mlifestyle;
    }

    public M_Income_Information getMincomeInformation() {
        return mincomeInformation;
    }

    public void setMincomeInformation(M_Income_Information mincomeInformation) {
        this.mincomeInformation = mincomeInformation;
    }

    public M_Local_Govt_Facilities getMlocalGovtFacilities() {
        return mlocalGovtFacilities;
    }

    public void setMlocalGovtFacilities(M_Local_Govt_Facilities mlocalGovtFacilities) {
        this.mlocalGovtFacilities = mlocalGovtFacilities;
    }

    public M_Nutrition getMnutrition() {
        return mnutrition;
    }

    public void setMnutrition(M_Nutrition mnutrition) {
        this.mnutrition = mnutrition;
    }

    public M_Property getMproperty() {
        return mproperty;
    }

    public void setMproperty(M_Property mproperty) {
        this.mproperty = mproperty;
    }

    public O_Induction getOinduction() {
        return oinduction;
    }

    public void setOinduction(O_Induction oinduction) {
        this.oinduction = oinduction;
    }

    public O_Inhouse_Inductions_Mother getOinhouseInductionsMother() {
        return oinhouseInductionsMother;
    }

    public void setOinhouseInductionsMother(O_Inhouse_Inductions_Mother oinhouseInductionsMother) {
        this.oinhouseInductionsMother = oinhouseInductionsMother;
    }

    public O_MHealthConditions getOmHealthConditions() {
        return omHealthConditions;
    }

    public void setOmHealthConditions(O_MHealthConditions omHealthConditions) {
        this.omHealthConditions = omHealthConditions;
    }

    public O_Professional_Obserbations_Mother getOprofessionalObserbationsMother() {
        return oprofessionalObserbationsMother;
    }

    public void setOprofessionalObserbationsMother(O_Professional_Obserbations_Mother oprofessionalObserbationsMother) {
        this.oprofessionalObserbationsMother = oprofessionalObserbationsMother;
    }

    public O_MAddmission getAddmission() {
        return addmission;
    }

    public void setAddmission(O_MAddmission addmission) {
        this.addmission = addmission;
    }

    public MotherImage getMimage() {
        return mimage;
    }

    public void setMimage(MotherImage mimage) {
        this.mimage = mimage;
    }

    public List<O_ChildAdmission> getChildAdmission() {
        return childAdmission;
    }

    public void setChildAdmission(List<O_ChildAdmission> childAdmission) {
        this.childAdmission = childAdmission;
    }

    public List<R_C_HouseAllocations> getRcHouseAllocations() {
        return rcHouseAllocations;
    }

    public void setRcHouseAllocations(List<R_C_HouseAllocations> rcHouseAllocations) {
        this.rcHouseAllocations = rcHouseAllocations;
    }

    public List<R_M_HousAllocation> getRmHousAllocation() {
        return rmHousAllocation;
    }

    public void setRmHousAllocation(List<R_M_HousAllocation> rmHousAllocation) {
        this.rmHousAllocation = rmHousAllocation;
    }

    public List<R_M_WorkAllocation> getRmWorkAllocation() {
        return rmWorkAllocation;
    }

    public void setRmWorkAllocation(List<R_M_WorkAllocation> rmWorkAllocation) {
        this.rmWorkAllocation = rmWorkAllocation;
    }

    public List<R_IGA_Training> getRiGATraining() {
        return riGATraining;
    }

    public void setRiGATraining(List<R_IGA_Training> riGATraining) {
        this.riGATraining = riGATraining;
    }

    public List<R_Life_Skill_Trainning> getRlifeSkillTrainning() {
        return rlifeSkillTrainning;
    }

    public void setRlifeSkillTrainning(List<R_Life_Skill_Trainning> rlifeSkillTrainning) {
        this.rlifeSkillTrainning = rlifeSkillTrainning;
    }

    public List<LeaveMother> getLeaveMother() {
        return LeaveMother;
    }

    public void setLeaveMother(List<LeaveMother> LeaveMother) {
        this.LeaveMother = LeaveMother;
    }

    public List<R_OT> getrOT() {
        return rOT;
    }

    public void setrOT(List<R_OT> rOT) {
        this.rOT = rOT;
    }

    public List<R_PT> getrPT() {
        return rPT;
    }

    public void setrPT(List<R_PT> rPT) {
        this.rPT = rPT;
    }

    public List<R_PsychologyMother> getRpsychologyMother() {
        return rpsychologyMother;
    }

    public void setRpsychologyMother(List<R_PsychologyMother> rpsychologyMother) {
        this.rpsychologyMother = rpsychologyMother;
    }

    public List<C_NutritionalStatus> getCnutritionalStatus() {
        return cnutritionalStatus;
    }

    public void setCnutritionalStatus(List<C_NutritionalStatus> cnutritionalStatus) {
        this.cnutritionalStatus = cnutritionalStatus;
    }

    public List<C_visit> getCvisit() {
        return cvisit;
    }

    public void setCvisit(List<C_visit> cvisit) {
        this.cvisit = cvisit;
    }

    public List<C_Admission> getCadmission() {
        return cadmission;
    }

    public void setCadmission(List<C_Admission> cadmission) {
        this.cadmission = cadmission;
    }

    public List<C_Referral> getCreferral() {
        return creferral;
    }

    public void setCreferral(List<C_Referral> creferral) {
        this.creferral = creferral;
    }

    public List<C_Release> getCrelease() {
        return crelease;
    }

    public void setCrelease(List<C_Release> crelease) {
        this.crelease = crelease;
    }

    public LiteracyDigitalLiteracy getLiteracyDigitalLiteracy() {
        return literacyDigitalLiteracy;
    }

    public void setLiteracyDigitalLiteracy(LiteracyDigitalLiteracy literacyDigitalLiteracy) {
        this.literacyDigitalLiteracy = literacyDigitalLiteracy;
    }

    public LiteracyHigherEducationAdmission getLiteracyHigherEducationAdmission() {
        return literacyHigherEducationAdmission;
    }

    public void setLiteracyHigherEducationAdmission(LiteracyHigherEducationAdmission literacyHigherEducationAdmission) {
        this.literacyHigherEducationAdmission = literacyHigherEducationAdmission;
    }

    public LiteracyNumeracy getLiteracyRegularAdmission() {
        return literacyRegularAdmission;
    }

    public void setLiteracyRegularAdmission(LiteracyNumeracy literacyRegularAdmission) {
        this.literacyRegularAdmission = literacyRegularAdmission;
    }

    public LiteracyTalkingScience getLiteracyTalkingScience() {
        return literacyTalkingScience;
    }

    public void setLiteracyTalkingScience(LiteracyTalkingScience literacyTalkingScience) {
        this.literacyTalkingScience = literacyTalkingScience;
    }

    public PreReintegrationVisit getPreReintegrationVisit() {
        return preReintegrationVisit;
    }

    public void setPreReintegrationVisit(PreReintegrationVisit preReintegrationVisit) {
        this.preReintegrationVisit = preReintegrationVisit;
    }

    public List<M_Accessibility_ReintegrationVisit> getMaccessibilityReintegrationVisit() {
        return maccessibilityReintegrationVisit;
    }

    public void setMaccessibilityReintegrationVisit(List<M_Accessibility_ReintegrationVisit> maccessibilityReintegrationVisit) {
        this.maccessibilityReintegrationVisit = maccessibilityReintegrationVisit;
    }

    public List<M_Address_ReintegrationVisit> getMaddressReintegrationVisit() {
        return maddressReintegrationVisit;
    }

    public void setMaddressReintegrationVisit(List<M_Address_ReintegrationVisit> maddressReintegrationVisit) {
        this.maddressReintegrationVisit = maddressReintegrationVisit;
    }

    public List<M_Community_Information_ReintegrationVisit> getMcommunityInformationReintegrationVisit() {
        return mcommunityInformationReintegrationVisit;
    }

    public void setMcommunityInformationReintegrationVisit(List<M_Community_Information_ReintegrationVisit> mcommunityInformationReintegrationVisit) {
        this.mcommunityInformationReintegrationVisit = mcommunityInformationReintegrationVisit;
    }

    public List<M_Family_information_ReintegrationVisit> getMfamilyinformationReintegrationVisit() {
        return mfamilyinformationReintegrationVisit;
    }

    public void setMfamilyinformationReintegrationVisit(List<M_Family_information_ReintegrationVisit> mfamilyinformationReintegrationVisit) {
        this.mfamilyinformationReintegrationVisit = mfamilyinformationReintegrationVisit;
    }

    public List<M_House_Information_ReintegrationVisit> getMhouseInformationReintegrationVisit() {
        return mhouseInformationReintegrationVisit;
    }

    public void setMhouseInformationReintegrationVisit(List<M_House_Information_ReintegrationVisit> mhouseInformationReintegrationVisit) {
        this.mhouseInformationReintegrationVisit = mhouseInformationReintegrationVisit;
    }

    public List<M_Lifestyle_ReintegrationVisit> getMlifestyleReintegrationVisit() {
        return mlifestyleReintegrationVisit;
    }

    public void setMlifestyleReintegrationVisit(List<M_Lifestyle_ReintegrationVisit> mlifestyleReintegrationVisit) {
        this.mlifestyleReintegrationVisit = mlifestyleReintegrationVisit;
    }

    public ReintegrationCheckList getReintegrationCheckList() {
        return reintegrationCheckList;
    }

    public void setReintegrationCheckList(ReintegrationCheckList reintegrationCheckList) {
        this.reintegrationCheckList = reintegrationCheckList;
    }

    public ReleaseMother getReleaseMother() {
        return releaseMother;
    }

    public void setReleaseMother(ReleaseMother releaseMother) {
        this.releaseMother = releaseMother;
    }

    public List<FollowUpMother> getFollowUpMother() {
        return followUpMother;
    }

    public void setFollowUpMother(List<FollowUpMother> followUpMother) {
        this.followUpMother = followUpMother;
    }

    public List<FollowMotherPerCapitaIncome> getFollowMotherPerCapitaIncome() {
        return followMotherPerCapitaIncome;
    }

    public void setFollowMotherPerCapitaIncome(List<FollowMotherPerCapitaIncome> followMotherPerCapitaIncome) {
        this.followMotherPerCapitaIncome = followMotherPerCapitaIncome;
    }

    public List<MotherCrisisMeetUp> getMotherCrisisMeetUp() {
        return motherCrisisMeetUp;
    }

    public void setMotherCrisisMeetUp(List<MotherCrisisMeetUp> motherCrisisMeetUp) {
        this.motherCrisisMeetUp = motherCrisisMeetUp;
    }

}
