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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
@Table(name = "motherMasterData")
public class MotherMasterData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "This field cannot be blank.")
    public String visitOfficersName;

    @NotEmpty(message = "This field cannot be blank.")
    public String dateReferral;

    @NotEmpty(message = "This field cannot be blank.")
    public String referredFrom;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Reasons resons;

    @NotEmpty(message = "This field cannot be blank.")
    public String homeVisitDate;

    @NotEmpty(message = "This field cannot be blank.")
    @Column(unique = true)
    public String motherMasterCode;

    @NotEmpty(message = "This field cannot be blank.")
    public String motherName;

    @NotEmpty(message = "This field cannot be blank.")
    public String dateOfBirth;

    public String age;

    @NotEmpty(message = "This field cannot be blank.")
    public String mMothersName;

    @NotEmpty(message = "This field cannot be blank.")
    public String fathersName;

    public String mobileNumber;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Religion religion;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public MaritalStatus maritalStatus;

    @NotEmpty(message = "This field cannot be blank.")
    public String husbandsName;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public HusbandsStatus husbandsStatus;

    @NotEmpty(message = "This field cannot be blank.")
    public String primeFamilyMemberName;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Relations relationWithPfm;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public EthinicIdentity ethnicIdentity;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public EducationLevel educationLevel;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
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

    @ManyToOne(optional = true)
    public Users createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    public Date updated = new Date();

    @ManyToOne(optional = true)
    public Users updatedBy;

    /*      Relations ship  Start */
 /*      Home Visit */
    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Address> mAddress = new ArrayList<>();

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Accessibility mAccessibility;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Approval mApproval;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Child_info> mChildinfo;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Community_Information mCommunityInformation;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Current_Help> mCurrentHelp = new ArrayList<>();

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Family_information> mFamilynformation = new ArrayList<>();

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_House_Information mHouseInformation;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Lifestyle mLifestyle;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Income_Information mIncomeInformation;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Local_Govt_Facilities mLocalGovtFacilities;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Nutrition mNutrition;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public M_Property mProperty;

    // objerbations
    
    
    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_Induction oInduction;
//

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_Inhouse_Inductions_Mother oInhouseInductionsMother;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_MHealthConditions oMHealthConditions;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_Professional_Obserbations_Mother oProfessionalObserbationsMother;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public O_MAddmission addmission;

    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public MotherImage mImage;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<O_ChildAdmission> childAdmission = new ArrayList<>();

    // Rehabilations 
    //@LazyCollection(LazyCollectionOption.TRUE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public R_C_HouseAllocations rCHouseAllocations;

    //@LazyCollection(LazyCollectionOption.TRUE)
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public R_M_HousAllocation rMHousAllocation;

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_M_WorkAllocation> rMWorkAllocation = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_IGA_Training> rIGATraining = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_Life_Skill_Trainning> rLifeSkillTrainning = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_OT> rOT = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_PT> rPT = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<R_PsychologyMother> rPsychologyMother = new ArrayList<>();

    //clinic  C_NutritionalStatus
    
    
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_NutritionalStatus> cNutritionalStatus = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_visit> cvisit = new ArrayList<>();

    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_Admission> cAdmission = new ArrayList<>();
    
     @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_Referral> cReferral = new ArrayList<>();
     
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<C_Release> cRelease = new ArrayList<>();

    //   pree RE intrigations
    
    
    @OneToOne(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public PreReintegrationVisit preReintegrationVisit;
    
     @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Accessibility_ReintegrationVisit> mAccessibilityReintegrationVisit = new ArrayList<>();
     
     @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Address_ReintegrationVisit> mAddressReintegrationVisit = new ArrayList<>();
     
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Community_Information_ReintegrationVisit> mCommunityInformationReintegrationVisit = new ArrayList<>();
    
     @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Family_information_ReintegrationVisit> mFamilyinformationReintegrationVisit = new ArrayList<>();
     
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_House_Information_ReintegrationVisit> mHouseInformationReintegrationVisit = new ArrayList<>();
    
    @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<M_Lifestyle_ReintegrationVisit> mLifestyleReintegrationVisit = new ArrayList<>();

    
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
    public List<FollowMotherPerCapitaIncome> followMotherPerCapitaIncome = new ArrayList<>();;
    
    
    
     @OneToMany(mappedBy = "motherMasterCode", fetch = FetchType.LAZY)
    public List<MotherCrisisMeetUp> motherCrisisMeetUp = new ArrayList<>();


    /*      Relations ship  end*/
    /**
     * audit end*********************
     */
    public MotherMasterData() {
    }

    public MotherMasterData(Long id, String visitOfficersName, String dateReferral, String referredFrom, Reasons resons, String homeVisitDate, String motherMasterCode, String motherName, String dateOfBirth, String age, String mMothersName, String fathersName, String mobileNumber, Religion religion, MaritalStatus maritalStatus, String husbandsName, HusbandsStatus husbandsStatus, String primeFamilyMemberName, Relations relationWithPfm, EthinicIdentity ethnicIdentity, EducationLevel educationLevel, EducationType educationType, Occupation occupation, String physicalStatus, String immunization, int numberOfSons, int numberOfDaughters, int numberOfEligibleChildren, String majorFindings, String otherRemarks, Eligibility eligibility, Users createdBy, Users updatedBy, M_Accessibility mAccessibility, M_Approval mApproval, List<M_Child_info> mChildinfo, M_Community_Information mCommunityInformation, M_House_Information mHouseInformation, M_Lifestyle mLifestyle, M_Income_Information mIncomeInformation, M_Local_Govt_Facilities mLocalGovtFacilities, M_Nutrition mNutrition, M_Property mProperty, O_Induction oInduction, O_Inhouse_Inductions_Mother oInhouseInductionsMother, O_MHealthConditions oMHealthConditions, O_Professional_Obserbations_Mother oProfessionalObserbationsMother, O_MAddmission addmission, MotherImage mImage, R_C_HouseAllocations rCHouseAllocations, R_M_HousAllocation rMHousAllocation, PreReintegrationVisit preReintegrationVisit, ReintegrationCheckList reintegrationCheckList, ReleaseMother releaseMother) {
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
        this.otherRemarks = otherRemarks;
        this.eligibility = eligibility;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.mAccessibility = mAccessibility;
        this.mApproval = mApproval;
        this.mChildinfo = mChildinfo;
        this.mCommunityInformation = mCommunityInformation;
        this.mHouseInformation = mHouseInformation;
        this.mLifestyle = mLifestyle;
        this.mIncomeInformation = mIncomeInformation;
        this.mLocalGovtFacilities = mLocalGovtFacilities;
        this.mNutrition = mNutrition;
        this.mProperty = mProperty;
        this.oInduction = oInduction;
        this.oInhouseInductionsMother = oInhouseInductionsMother;
        this.oMHealthConditions = oMHealthConditions;
        this.oProfessionalObserbationsMother = oProfessionalObserbationsMother;
        this.addmission = addmission;
        this.mImage = mImage;
        this.rCHouseAllocations = rCHouseAllocations;
        this.rMHousAllocation = rMHousAllocation;
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

    public String getDateReferral() {
        return dateReferral;
    }

    public void setDateReferral(String dateReferral) {
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

    public String getHomeVisitDate() {
        return homeVisitDate;
    }

    public void setHomeVisitDate(String homeVisitDate) {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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

    public List<M_Address> getmAddress() {
        return mAddress;
    }

    public void setmAddress(List<M_Address> mAddress) {
        this.mAddress = mAddress;
    }

    public M_Accessibility getmAccessibility() {
        return mAccessibility;
    }

    public void setmAccessibility(M_Accessibility mAccessibility) {
        this.mAccessibility = mAccessibility;
    }

    public M_Approval getmApproval() {
        return mApproval;
    }

    public void setmApproval(M_Approval mApproval) {
        this.mApproval = mApproval;
    }

    public List<M_Child_info> getmChildinfo() {
        return mChildinfo;
    }

    public void setmChildinfo(List<M_Child_info> mChildinfo) {
        this.mChildinfo = mChildinfo;
    }

    public M_Community_Information getmCommunityInformation() {
        return mCommunityInformation;
    }

    public void setmCommunityInformation(M_Community_Information mCommunityInformation) {
        this.mCommunityInformation = mCommunityInformation;
    }

    public List<M_Current_Help> getmCurrentHelp() {
        return mCurrentHelp;
    }

    public void setmCurrentHelp(List<M_Current_Help> mCurrentHelp) {
        this.mCurrentHelp = mCurrentHelp;
    }

    public List<M_Family_information> getmFamilynformation() {
        return mFamilynformation;
    }

    public void setmFamilynformation(List<M_Family_information> mFamilynformation) {
        this.mFamilynformation = mFamilynformation;
    }

    public M_House_Information getmHouseInformation() {
        return mHouseInformation;
    }

    public void setmHouseInformation(M_House_Information mHouseInformation) {
        this.mHouseInformation = mHouseInformation;
    }

    public M_Lifestyle getmLifestyle() {
        return mLifestyle;
    }

    public void setmLifestyle(M_Lifestyle mLifestyle) {
        this.mLifestyle = mLifestyle;
    }

    public M_Income_Information getmIncomeInformation() {
        return mIncomeInformation;
    }

    public void setmIncomeInformation(M_Income_Information mIncomeInformation) {
        this.mIncomeInformation = mIncomeInformation;
    }

    public M_Local_Govt_Facilities getmLocalGovtFacilities() {
        return mLocalGovtFacilities;
    }

    public void setmLocalGovtFacilities(M_Local_Govt_Facilities mLocalGovtFacilities) {
        this.mLocalGovtFacilities = mLocalGovtFacilities;
    }

    public M_Nutrition getmNutrition() {
        return mNutrition;
    }

    public void setmNutrition(M_Nutrition mNutrition) {
        this.mNutrition = mNutrition;
    }

    public M_Property getmProperty() {
        return mProperty;
    }

    public void setmProperty(M_Property mProperty) {
        this.mProperty = mProperty;
    }

    public O_Induction getoInduction() {
        return oInduction;
    }

    public void setoInduction(O_Induction oInduction) {
        this.oInduction = oInduction;
    }

    public O_Inhouse_Inductions_Mother getoInhouseInductionsMother() {
        return oInhouseInductionsMother;
    }

    public void setoInhouseInductionsMother(O_Inhouse_Inductions_Mother oInhouseInductionsMother) {
        this.oInhouseInductionsMother = oInhouseInductionsMother;
    }

    public O_MHealthConditions getoMHealthConditions() {
        return oMHealthConditions;
    }

    public void setoMHealthConditions(O_MHealthConditions oMHealthConditions) {
        this.oMHealthConditions = oMHealthConditions;
    }

    public O_Professional_Obserbations_Mother getoProfessionalObserbationsMother() {
        return oProfessionalObserbationsMother;
    }

    public void setoProfessionalObserbationsMother(O_Professional_Obserbations_Mother oProfessionalObserbationsMother) {
        this.oProfessionalObserbationsMother = oProfessionalObserbationsMother;
    }

    public O_MAddmission getAddmission() {
        return addmission;
    }

    public void setAddmission(O_MAddmission addmission) {
        this.addmission = addmission;
    }

    public MotherImage getmImage() {
        return mImage;
    }

    public void setmImage(MotherImage mImage) {
        this.mImage = mImage;
    }

    public List<O_ChildAdmission> getChildAdmission() {
        return childAdmission;
    }

    public void setChildAdmission(List<O_ChildAdmission> childAdmission) {
        this.childAdmission = childAdmission;
    }

    public R_C_HouseAllocations getrCHouseAllocations() {
        return rCHouseAllocations;
    }

    public void setrCHouseAllocations(R_C_HouseAllocations rCHouseAllocations) {
        this.rCHouseAllocations = rCHouseAllocations;
    }

    public R_M_HousAllocation getrMHousAllocation() {
        return rMHousAllocation;
    }

    public void setrMHousAllocation(R_M_HousAllocation rMHousAllocation) {
        this.rMHousAllocation = rMHousAllocation;
    }

    public List<R_M_WorkAllocation> getrMWorkAllocation() {
        return rMWorkAllocation;
    }

    public void setrMWorkAllocation(List<R_M_WorkAllocation> rMWorkAllocation) {
        this.rMWorkAllocation = rMWorkAllocation;
    }

    public List<R_IGA_Training> getrIGATraining() {
        return rIGATraining;
    }

    public void setrIGATraining(List<R_IGA_Training> rIGATraining) {
        this.rIGATraining = rIGATraining;
    }

    public List<R_Life_Skill_Trainning> getrLifeSkillTrainning() {
        return rLifeSkillTrainning;
    }

    public void setrLifeSkillTrainning(List<R_Life_Skill_Trainning> rLifeSkillTrainning) {
        this.rLifeSkillTrainning = rLifeSkillTrainning;
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

    public List<R_PsychologyMother> getrPsychologyMother() {
        return rPsychologyMother;
    }

    public void setrPsychologyMother(List<R_PsychologyMother> rPsychologyMother) {
        this.rPsychologyMother = rPsychologyMother;
    }

    public List<C_NutritionalStatus> getcNutritionalStatus() {
        return cNutritionalStatus;
    }

    public void setcNutritionalStatus(List<C_NutritionalStatus> cNutritionalStatus) {
        this.cNutritionalStatus = cNutritionalStatus;
    }

    public List<C_visit> getCvisit() {
        return cvisit;
    }

    public void setCvisit(List<C_visit> cvisit) {
        this.cvisit = cvisit;
    }

    public List<C_Admission> getcAdmission() {
        return cAdmission;
    }

    public void setcAdmission(List<C_Admission> cAdmission) {
        this.cAdmission = cAdmission;
    }

    public List<C_Referral> getcReferral() {
        return cReferral;
    }

    public void setcReferral(List<C_Referral> cReferral) {
        this.cReferral = cReferral;
    }

    public List<C_Release> getcRelease() {
        return cRelease;
    }

    public void setcRelease(List<C_Release> cRelease) {
        this.cRelease = cRelease;
    }

    public PreReintegrationVisit getPreReintegrationVisit() {
        return preReintegrationVisit;
    }

    public void setPreReintegrationVisit(PreReintegrationVisit preReintegrationVisit) {
        this.preReintegrationVisit = preReintegrationVisit;
    }

    public List<M_Accessibility_ReintegrationVisit> getmAccessibilityReintegrationVisit() {
        return mAccessibilityReintegrationVisit;
    }

    public void setmAccessibilityReintegrationVisit(List<M_Accessibility_ReintegrationVisit> mAccessibilityReintegrationVisit) {
        this.mAccessibilityReintegrationVisit = mAccessibilityReintegrationVisit;
    }

    public List<M_Address_ReintegrationVisit> getmAddressReintegrationVisit() {
        return mAddressReintegrationVisit;
    }

    public void setmAddressReintegrationVisit(List<M_Address_ReintegrationVisit> mAddressReintegrationVisit) {
        this.mAddressReintegrationVisit = mAddressReintegrationVisit;
    }

    public List<M_Community_Information_ReintegrationVisit> getmCommunityInformationReintegrationVisit() {
        return mCommunityInformationReintegrationVisit;
    }

    public void setmCommunityInformationReintegrationVisit(List<M_Community_Information_ReintegrationVisit> mCommunityInformationReintegrationVisit) {
        this.mCommunityInformationReintegrationVisit = mCommunityInformationReintegrationVisit;
    }

    public List<M_Family_information_ReintegrationVisit> getmFamilyinformationReintegrationVisit() {
        return mFamilyinformationReintegrationVisit;
    }

    public void setmFamilyinformationReintegrationVisit(List<M_Family_information_ReintegrationVisit> mFamilyinformationReintegrationVisit) {
        this.mFamilyinformationReintegrationVisit = mFamilyinformationReintegrationVisit;
    }

    public List<M_House_Information_ReintegrationVisit> getmHouseInformationReintegrationVisit() {
        return mHouseInformationReintegrationVisit;
    }

    public void setmHouseInformationReintegrationVisit(List<M_House_Information_ReintegrationVisit> mHouseInformationReintegrationVisit) {
        this.mHouseInformationReintegrationVisit = mHouseInformationReintegrationVisit;
    }

    public List<M_Lifestyle_ReintegrationVisit> getmLifestyleReintegrationVisit() {
        return mLifestyleReintegrationVisit;
    }

    public void setmLifestyleReintegrationVisit(List<M_Lifestyle_ReintegrationVisit> mLifestyleReintegrationVisit) {
        this.mLifestyleReintegrationVisit = mLifestyleReintegrationVisit;
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
