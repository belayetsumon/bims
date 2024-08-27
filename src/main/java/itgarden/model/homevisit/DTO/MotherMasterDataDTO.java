package itgarden.model.homevisit.DTO;

import itgarden.model.homevisit.Reasons;
import itgarden.model.homevisit.Yes_No;
import java.time.LocalDate;

/**
 *
 * @author libertyerp_local
 */
public class MotherMasterDataDTO {

    private Long id;

    public String visitOfficersName;

    public LocalDate dateReferral;

    public String referredFrom;

    public Reasons resons;

    public LocalDate homeVisitDate;

    public String motherMasterCode;

    public String motherName;

    public LocalDate dateOfBirth;

    public String age;

    public String mMothersName;

    public String fathersName;

    public String mobileNumber;

    public String religion;

    public Long religionId;

    public String maritalStatus;

    public Long maritalStatusId;

    public String husbandsName;

    public String husbandsStatus;

    public Long husbandsStatusId;

    public String primeFamilyMemberName;

    public String relationWithPfm;

    public Long relationWithPfmId;

    public String ethnicIdentity;

    public Long ethnicIdentityId;

    public String educationLevel;

    public Long educationLevelId;

    public String educationType;

    public Long educationTypeId;

    public String occupation;

    public Long occupationId;

    public String physicalStatus;

    public String immunization;

    public int numberOfSons;

    public int numberOfDaughters;

    public int numberOfEligibleChildren;

    public String majorFindings;
    
     //  New version aded  


    public Yes_No socialviolence;


    public Yes_No childrenFacedSocialViolence;


    public Yes_No sexualAbuse;


    public Yes_No childrenSexualAbuse;

 
    public Yes_No earlyMarriage;
    

    public Yes_No pregnancyAfterBeingRaped;
     

    public Yes_No  facedDowryAbuse; 
    
    // End new version property
    

    public String otherRemarks;

    public String eligibility;

    public MotherMasterDataDTO() {
    }

    public MotherMasterDataDTO(Long id, String visitOfficersName, LocalDate dateReferral, String referredFrom, Reasons resons, LocalDate homeVisitDate, String motherMasterCode, String motherName, LocalDate dateOfBirth, String age, String mMothersName, String fathersName, String mobileNumber, String religion, Long religionId, String maritalStatus, Long maritalStatusId, String husbandsName, String husbandsStatus, Long husbandsStatusId, String primeFamilyMemberName, String relationWithPfm, Long relationWithPfmId, String ethnicIdentity, Long ethnicIdentityId, String educationLevel, Long educationLevelId, String educationType, Long educationTypeId, String occupation, Long occupationId, String physicalStatus, String immunization, int numberOfSons, int numberOfDaughters, int numberOfEligibleChildren, String majorFindings, Yes_No socialviolence, Yes_No childrenFacedSocialViolence, Yes_No sexualAbuse, Yes_No childrenSexualAbuse, Yes_No earlyMarriage, Yes_No pregnancyAfterBeingRaped, Yes_No facedDowryAbuse, String otherRemarks, String eligibility) {
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
        this.religionId = religionId;
        this.maritalStatus = maritalStatus;
        this.maritalStatusId = maritalStatusId;
        this.husbandsName = husbandsName;
        this.husbandsStatus = husbandsStatus;
        this.husbandsStatusId = husbandsStatusId;
        this.primeFamilyMemberName = primeFamilyMemberName;
        this.relationWithPfm = relationWithPfm;
        this.relationWithPfmId = relationWithPfmId;
        this.ethnicIdentity = ethnicIdentity;
        this.ethnicIdentityId = ethnicIdentityId;
        this.educationLevel = educationLevel;
        this.educationLevelId = educationLevelId;
        this.educationType = educationType;
        this.educationTypeId = educationTypeId;
        this.occupation = occupation;
        this.occupationId = occupationId;
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

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Long getReligionId() {
        return religionId;
    }

    public void setReligionId(Long religionId) {
        this.religionId = religionId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getHusbandsName() {
        return husbandsName;
    }

    public void setHusbandsName(String husbandsName) {
        this.husbandsName = husbandsName;
    }

    public String getHusbandsStatus() {
        return husbandsStatus;
    }

    public void setHusbandsStatus(String husbandsStatus) {
        this.husbandsStatus = husbandsStatus;
    }

    public Long getHusbandsStatusId() {
        return husbandsStatusId;
    }

    public void setHusbandsStatusId(Long husbandsStatusId) {
        this.husbandsStatusId = husbandsStatusId;
    }

    public String getPrimeFamilyMemberName() {
        return primeFamilyMemberName;
    }

    public void setPrimeFamilyMemberName(String primeFamilyMemberName) {
        this.primeFamilyMemberName = primeFamilyMemberName;
    }

    public String getRelationWithPfm() {
        return relationWithPfm;
    }

    public void setRelationWithPfm(String relationWithPfm) {
        this.relationWithPfm = relationWithPfm;
    }

    public Long getRelationWithPfmId() {
        return relationWithPfmId;
    }

    public void setRelationWithPfmId(Long relationWithPfmId) {
        this.relationWithPfmId = relationWithPfmId;
    }

    public String getEthnicIdentity() {
        return ethnicIdentity;
    }

    public void setEthnicIdentity(String ethnicIdentity) {
        this.ethnicIdentity = ethnicIdentity;
    }

    public Long getEthnicIdentityId() {
        return ethnicIdentityId;
    }

    public void setEthnicIdentityId(Long ethnicIdentityId) {
        this.ethnicIdentityId = ethnicIdentityId;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Long getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Long educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public Long getEducationTypeId() {
        return educationTypeId;
    }

    public void setEducationTypeId(Long educationTypeId) {
        this.educationTypeId = educationTypeId;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Long getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(Long occupationId) {
        this.occupationId = occupationId;
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

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    
}
