/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import itgarden.model.auth.Users;
import itgarden.model.follow_up_report.ChildCrisisMeetUp;
import itgarden.model.follow_up_report.FollowUpChildren;
import itgarden.model.longtermcare.L_FollowUp;
import itgarden.model.longtermcare.L_Foste;
import itgarden.model.longtermcare.L_HigherStudy;
import itgarden.model.longtermcare.L_Job;
import itgarden.model.longtermcare.L_Marriage;
import itgarden.model.observation.Child_image;
import itgarden.model.observation.O_CHealthConditions;
import itgarden.model.observation.O_ChildAdmission;
import itgarden.model.observation.O_Inhouse_Inductions_child;
import itgarden.model.observation.O_Professional_Obserbations_Child;
import itgarden.model.rehabilitations.R_C_HouseAllocations;
import itgarden.model.rehabilitations.R_OtChild;
import itgarden.model.rehabilitations.R_PsychologyChild;
import itgarden.model.reintegration_release.ReleaseChild;
import itgarden.model.school.Discontinuity;
import itgarden.model.school.EligibilityStudent;
import itgarden.model.school.S_RegularAdmissionClass;
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class M_Child_info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Mother master code field cannot be blank.")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotEmpty(message = "Child master code field cannot be blank.")
    @Column(unique = true)
    String childMasterCode;

    @NotEmpty(message = "Mother name field cannot be blank.")
    public String motherName;

    @NotEmpty(message = "Fathers name field cannot be blank.")
    public String fathersName;

    @NotEmpty(message = "Prime Family Member name field cannot be blank.")
    String primeFamilyMemberName;

    @NotEmpty(message = "Name field cannot be blank.")
    String name;

    @NotEmpty(message = "Date of birth field cannot be blank.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public String dateOfBirth;

    public String age;

    @NotNull(message = "Gender field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    Gender gender;

    @NotNull(message = "Work field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    Yes_No work;

    @NotNull(message = "Religion field cannot be blank.")
    @ManyToOne(optional = false)
    public Religion religion;

    @NotNull(message = "EthnicIdentity field cannot be blank.")
    @ManyToOne(optional = false)
    public EthinicIdentity ethnicIdentity;

    @NotNull(message = "Education level field cannot be blank.")
    @ManyToOne(optional = false)
    public EducationLevel educationLevel;

    @NotNull(message = "Education type  field cannot be blank.")
    @ManyToOne(optional = false)
    public EducationType educationType;

    @NotEmpty(message = "Physical status field cannot be blank.")
    @Lob
    public String physicalStatus;

    @Lob
    @NotEmpty(message = "Immunization field cannot be blank.")
    public String immunization;

    @Lob
    @NotEmpty(message = "Intersted skill area field cannot be blank.")
    public String interstedSkillArea;

    @Lob
    @NotEmpty(message = "Behavior and emotion field cannot be blank.")
    public String behavior_Emotion;

    @Lob
    public String majorFindings;

    @Lob
    public String otherRemarks;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Eligibility eligibility;

    // @Temporal(TemporalType.DATE)
    // @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = true, updatable = false)
    public LocalDate created = LocalDate.now();

    @ManyToOne(optional = true)
    // @Column(insertable = true,updatable = false)
    public Users createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    public Date updated = new Date();

    @ManyToOne(optional = true)
    // @Column(insertable = false, updatable = true)
    public Users updatedBy;

    // Home Visit
    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<M_Child_Address> mChildAddress = new ArrayList<>();

    // Observation
    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<O_Inhouse_Inductions_child> oInhouseInductionschild = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<O_CHealthConditions> oCHealthConditions = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<O_Professional_Obserbations_Child> oProfessionalObserbationsChild = new ArrayList<>();

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public O_ChildAdmission childAdmission;

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<Child_image> childimage = new ArrayList<>();

    // Rehabilitations 
    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<R_C_HouseAllocations> rCHouseAllocations = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<R_OtChild> rOtChild = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<R_PsychologyChild> rPsychologyChild = new ArrayList<>();

    // Long term care
    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<L_Foste> lfoste = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<L_HigherStudy> higherStudy = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<L_Job> job = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<L_Marriage> marriage = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<L_FollowUp> lFollowUp = new ArrayList<>();

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<ChildCrisisMeetUp> childCrisisMeetUp = new ArrayList<>();

    // School
    @OneToOne(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public S_RegularAdmissionClass regularAdmissionClass;

    @OneToOne(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public Discontinuity discontinuity;

    @OneToOne(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public EligibilityStudent eligibilityStudent;

    // @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public ReleaseChild releaseChild;

    @OneToMany(mappedBy = "childMasterCode", fetch = FetchType.LAZY)
    public List<FollowUpChildren> followUpChildren = new ArrayList<>();

    ;

    public M_Child_info() {
    }

    public M_Child_info(Long id, MotherMasterData motherMasterCode, String childMasterCode, String motherName, String fathersName, String primeFamilyMemberName, String name, String dateOfBirth, String age, Gender gender, Yes_No work, Religion religion, EthinicIdentity ethnicIdentity, EducationLevel educationLevel, EducationType educationType, String physicalStatus, String immunization, String interstedSkillArea, String behavior_Emotion, String majorFindings, String otherRemarks, Eligibility eligibility, Users createdBy, Users updatedBy, O_ChildAdmission childAdmission, S_RegularAdmissionClass regularAdmissionClass, Discontinuity discontinuity, EligibilityStudent eligibilityStudent, ReleaseChild releaseChild) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.childMasterCode = childMasterCode;
        this.motherName = motherName;
        this.fathersName = fathersName;
        this.primeFamilyMemberName = primeFamilyMemberName;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.work = work;
        this.religion = religion;
        this.ethnicIdentity = ethnicIdentity;
        this.educationLevel = educationLevel;
        this.educationType = educationType;
        this.physicalStatus = physicalStatus;
        this.immunization = immunization;
        this.interstedSkillArea = interstedSkillArea;
        this.behavior_Emotion = behavior_Emotion;
        this.majorFindings = majorFindings;
        this.otherRemarks = otherRemarks;
        this.eligibility = eligibility;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.childAdmission = childAdmission;
        this.regularAdmissionClass = regularAdmissionClass;
        this.discontinuity = discontinuity;
        this.eligibilityStudent = eligibilityStudent;
        this.releaseChild = releaseChild;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MotherMasterData getMotherMasterCode() {
        return motherMasterCode;
    }

    public void setMotherMasterCode(MotherMasterData motherMasterCode) {
        this.motherMasterCode = motherMasterCode;
    }

    public String getChildMasterCode() {
        return childMasterCode;
    }

    public void setChildMasterCode(String childMasterCode) {
        this.childMasterCode = childMasterCode;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getPrimeFamilyMemberName() {
        return primeFamilyMemberName;
    }

    public void setPrimeFamilyMemberName(String primeFamilyMemberName) {
        this.primeFamilyMemberName = primeFamilyMemberName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Yes_No getWork() {
        return work;
    }

    public void setWork(Yes_No work) {
        this.work = work;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
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

    public String getInterstedSkillArea() {
        return interstedSkillArea;
    }

    public void setInterstedSkillArea(String interstedSkillArea) {
        this.interstedSkillArea = interstedSkillArea;
    }

    public String getBehavior_Emotion() {
        return behavior_Emotion;
    }

    public void setBehavior_Emotion(String behavior_Emotion) {
        this.behavior_Emotion = behavior_Emotion;
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

    public List<M_Child_Address> getmChildAddress() {
        return mChildAddress;
    }

    public void setmChildAddress(List<M_Child_Address> mChildAddress) {
        this.mChildAddress = mChildAddress;
    }

    public List<O_Inhouse_Inductions_child> getoInhouseInductionschild() {
        return oInhouseInductionschild;
    }

    public void setoInhouseInductionschild(List<O_Inhouse_Inductions_child> oInhouseInductionschild) {
        this.oInhouseInductionschild = oInhouseInductionschild;
    }

    public List<O_CHealthConditions> getoCHealthConditions() {
        return oCHealthConditions;
    }

    public void setoCHealthConditions(List<O_CHealthConditions> oCHealthConditions) {
        this.oCHealthConditions = oCHealthConditions;
    }

    public List<O_Professional_Obserbations_Child> getoProfessionalObserbationsChild() {
        return oProfessionalObserbationsChild;
    }

    public void setoProfessionalObserbationsChild(List<O_Professional_Obserbations_Child> oProfessionalObserbationsChild) {
        this.oProfessionalObserbationsChild = oProfessionalObserbationsChild;
    }

    public O_ChildAdmission getChildAdmission() {
        return childAdmission;
    }

    public void setChildAdmission(O_ChildAdmission childAdmission) {
        this.childAdmission = childAdmission;
    }

    public List<Child_image> getChildimage() {
        return childimage;
    }

    public void setChildimage(List<Child_image> childimage) {
        this.childimage = childimage;
    }

    public List<R_C_HouseAllocations> getrCHouseAllocations() {
        return rCHouseAllocations;
    }

    public void setrCHouseAllocations(List<R_C_HouseAllocations> rCHouseAllocations) {
        this.rCHouseAllocations = rCHouseAllocations;
    }

    public List<R_OtChild> getrOtChild() {
        return rOtChild;
    }

    public void setrOtChild(List<R_OtChild> rOtChild) {
        this.rOtChild = rOtChild;
    }

    public List<R_PsychologyChild> getrPsychologyChild() {
        return rPsychologyChild;
    }

    public void setrPsychologyChild(List<R_PsychologyChild> rPsychologyChild) {
        this.rPsychologyChild = rPsychologyChild;
    }

    public List<L_Foste> getLfoste() {
        return lfoste;
    }

    public void setLfoste(List<L_Foste> lfoste) {
        this.lfoste = lfoste;
    }

    public List<L_HigherStudy> getHigherStudy() {
        return higherStudy;
    }

    public void setHigherStudy(List<L_HigherStudy> higherStudy) {
        this.higherStudy = higherStudy;
    }

    public List<L_Job> getJob() {
        return job;
    }

    public void setJob(List<L_Job> job) {
        this.job = job;
    }

    public List<L_Marriage> getMarriage() {
        return marriage;
    }

    public void setMarriage(List<L_Marriage> marriage) {
        this.marriage = marriage;
    }

    public List<L_FollowUp> getlFollowUp() {
        return lFollowUp;
    }

    public void setlFollowUp(List<L_FollowUp> lFollowUp) {
        this.lFollowUp = lFollowUp;
    }

    public List<ChildCrisisMeetUp> getChildCrisisMeetUp() {
        return childCrisisMeetUp;
    }

    public void setChildCrisisMeetUp(List<ChildCrisisMeetUp> childCrisisMeetUp) {
        this.childCrisisMeetUp = childCrisisMeetUp;
    }

    public S_RegularAdmissionClass getRegularAdmissionClass() {
        return regularAdmissionClass;
    }

    public void setRegularAdmissionClass(S_RegularAdmissionClass regularAdmissionClass) {
        this.regularAdmissionClass = regularAdmissionClass;
    }

    public Discontinuity getDiscontinuity() {
        return discontinuity;
    }

    public void setDiscontinuity(Discontinuity discontinuity) {
        this.discontinuity = discontinuity;
    }

    public EligibilityStudent getEligibilityStudent() {
        return eligibilityStudent;
    }

    public void setEligibilityStudent(EligibilityStudent eligibilityStudent) {
        this.eligibilityStudent = eligibilityStudent;
    }

    public ReleaseChild getReleaseChild() {
        return releaseChild;
    }

    public void setReleaseChild(ReleaseChild releaseChild) {
        this.releaseChild = releaseChild;
    }

    public List<FollowUpChildren> getFollowUpChildren() {
        return followUpChildren;
    }

    public void setFollowUpChildren(List<FollowUpChildren> followUpChildren) {
        this.followUpChildren = followUpChildren;
    }

}
