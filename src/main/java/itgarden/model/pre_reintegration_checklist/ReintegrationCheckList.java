package itgarden.model.pre_reintegration_checklist;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
@Entity
public class ReintegrationCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private MotherMasterData motherMasterCode;

    @NotEmpty(message = "This field cannot be blank.")
    private String date;

    @NotEmpty(message = "This field cannot be blank.")
    private String tentativeDate;

    @Lob
    private String reintegrationEmployment;

    @Lob
    private String addresss;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Complete_incomplete individualRehabilitationPlanning;

    private String individualRehabilitationPlanningNote;

    private String talkOverMother;

    private String savingAmoun;

    private String savingUsed;

    private String sppOtherSupport;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No financialTrainingReceived;

    @Lob
    private String financialTrainingReceivedNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No financialPlanningDone;
    @Lob
    private String financialPlanningDoneNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No bankAccountOened;
    @Lob
    private String bankAccountOenedNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No incomeGeneratingTrainingReceived;

    @Lob
    private String incomeGeneratingTrainingReceivedNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No allNecessarySkillTrainingReceived;
    @Lob
    private String allNecessarySkillTrainingReceivedNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No receivedMedicalDocuments;
    @Lob
    private String receivedMedicalDocumentsNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No preReintegrationHomeVisit;
    @Lob
    private String preReintegrationHomeVisitNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No workSiteAssessment;
    @Lob
    private String workSiteAssessmentNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No healthOnDischarge;
    @Lob
    private String healthOnDischargeNote;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No reintegration;

    /**
     * ********* Audit ******************************
     */
    @Column(insertable = true, updatable = false)
    private LocalDate created = LocalDate.now();

    @ManyToOne(optional = true)
    private Users createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    private Date updated = new Date();
    @ManyToOne(optional = true)
    private Users updatedBy;

    public ReintegrationCheckList() {
    }

    public ReintegrationCheckList(Long id, MotherMasterData motherMasterCode, String date, String tentativeDate, String reintegrationEmployment, String addresss, Complete_incomplete individualRehabilitationPlanning, String individualRehabilitationPlanningNote, String talkOverMother, String savingAmoun, String savingUsed, String sppOtherSupport, Yes_No financialTrainingReceived, String financialTrainingReceivedNote, Yes_No financialPlanningDone, String financialPlanningDoneNote, Yes_No bankAccountOened, String bankAccountOenedNote, Yes_No incomeGeneratingTrainingReceived, String incomeGeneratingTrainingReceivedNote, Yes_No allNecessarySkillTrainingReceived, String allNecessarySkillTrainingReceivedNote, Yes_No receivedMedicalDocuments, String receivedMedicalDocumentsNote, Yes_No preReintegrationHomeVisit, String preReintegrationHomeVisitNote, Yes_No workSiteAssessment, String workSiteAssessmentNote, Yes_No healthOnDischarge, String healthOnDischargeNote, Yes_No reintegration, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.date = date;
        this.tentativeDate = tentativeDate;
        this.reintegrationEmployment = reintegrationEmployment;
        this.addresss = addresss;
        this.individualRehabilitationPlanning = individualRehabilitationPlanning;
        this.individualRehabilitationPlanningNote = individualRehabilitationPlanningNote;
        this.talkOverMother = talkOverMother;
        this.savingAmoun = savingAmoun;
        this.savingUsed = savingUsed;
        this.sppOtherSupport = sppOtherSupport;
        this.financialTrainingReceived = financialTrainingReceived;
        this.financialTrainingReceivedNote = financialTrainingReceivedNote;
        this.financialPlanningDone = financialPlanningDone;
        this.financialPlanningDoneNote = financialPlanningDoneNote;
        this.bankAccountOened = bankAccountOened;
        this.bankAccountOenedNote = bankAccountOenedNote;
        this.incomeGeneratingTrainingReceived = incomeGeneratingTrainingReceived;
        this.incomeGeneratingTrainingReceivedNote = incomeGeneratingTrainingReceivedNote;
        this.allNecessarySkillTrainingReceived = allNecessarySkillTrainingReceived;
        this.allNecessarySkillTrainingReceivedNote = allNecessarySkillTrainingReceivedNote;
        this.receivedMedicalDocuments = receivedMedicalDocuments;
        this.receivedMedicalDocumentsNote = receivedMedicalDocumentsNote;
        this.preReintegrationHomeVisit = preReintegrationHomeVisit;
        this.preReintegrationHomeVisitNote = preReintegrationHomeVisitNote;
        this.workSiteAssessment = workSiteAssessment;
        this.workSiteAssessmentNote = workSiteAssessmentNote;
        this.healthOnDischarge = healthOnDischarge;
        this.healthOnDischargeNote = healthOnDischargeNote;
        this.reintegration = reintegration;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTentativeDate() {
        return tentativeDate;
    }

    public void setTentativeDate(String tentativeDate) {
        this.tentativeDate = tentativeDate;
    }

    public String getReintegrationEmployment() {
        return reintegrationEmployment;
    }

    public void setReintegrationEmployment(String reintegrationEmployment) {
        this.reintegrationEmployment = reintegrationEmployment;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }

    public Complete_incomplete getIndividualRehabilitationPlanning() {
        return individualRehabilitationPlanning;
    }

    public void setIndividualRehabilitationPlanning(Complete_incomplete individualRehabilitationPlanning) {
        this.individualRehabilitationPlanning = individualRehabilitationPlanning;
    }

    public String getIndividualRehabilitationPlanningNote() {
        return individualRehabilitationPlanningNote;
    }

    public void setIndividualRehabilitationPlanningNote(String individualRehabilitationPlanningNote) {
        this.individualRehabilitationPlanningNote = individualRehabilitationPlanningNote;
    }

    public String getTalkOverMother() {
        return talkOverMother;
    }

    public void setTalkOverMother(String talkOverMother) {
        this.talkOverMother = talkOverMother;
    }

    public String getSavingAmoun() {
        return savingAmoun;
    }

    public void setSavingAmoun(String savingAmoun) {
        this.savingAmoun = savingAmoun;
    }

    public String getSavingUsed() {
        return savingUsed;
    }

    public void setSavingUsed(String savingUsed) {
        this.savingUsed = savingUsed;
    }

    public String getSppOtherSupport() {
        return sppOtherSupport;
    }

    public void setSppOtherSupport(String sppOtherSupport) {
        this.sppOtherSupport = sppOtherSupport;
    }

    public Yes_No getFinancialTrainingReceived() {
        return financialTrainingReceived;
    }

    public void setFinancialTrainingReceived(Yes_No financialTrainingReceived) {
        this.financialTrainingReceived = financialTrainingReceived;
    }

    public String getFinancialTrainingReceivedNote() {
        return financialTrainingReceivedNote;
    }

    public void setFinancialTrainingReceivedNote(String financialTrainingReceivedNote) {
        this.financialTrainingReceivedNote = financialTrainingReceivedNote;
    }

    public Yes_No getFinancialPlanningDone() {
        return financialPlanningDone;
    }

    public void setFinancialPlanningDone(Yes_No financialPlanningDone) {
        this.financialPlanningDone = financialPlanningDone;
    }

    public String getFinancialPlanningDoneNote() {
        return financialPlanningDoneNote;
    }

    public void setFinancialPlanningDoneNote(String financialPlanningDoneNote) {
        this.financialPlanningDoneNote = financialPlanningDoneNote;
    }

    public Yes_No getBankAccountOened() {
        return bankAccountOened;
    }

    public void setBankAccountOened(Yes_No bankAccountOened) {
        this.bankAccountOened = bankAccountOened;
    }

    public String getBankAccountOenedNote() {
        return bankAccountOenedNote;
    }

    public void setBankAccountOenedNote(String bankAccountOenedNote) {
        this.bankAccountOenedNote = bankAccountOenedNote;
    }

    public Yes_No getIncomeGeneratingTrainingReceived() {
        return incomeGeneratingTrainingReceived;
    }

    public void setIncomeGeneratingTrainingReceived(Yes_No incomeGeneratingTrainingReceived) {
        this.incomeGeneratingTrainingReceived = incomeGeneratingTrainingReceived;
    }

    public String getIncomeGeneratingTrainingReceivedNote() {
        return incomeGeneratingTrainingReceivedNote;
    }

    public void setIncomeGeneratingTrainingReceivedNote(String incomeGeneratingTrainingReceivedNote) {
        this.incomeGeneratingTrainingReceivedNote = incomeGeneratingTrainingReceivedNote;
    }

    public Yes_No getAllNecessarySkillTrainingReceived() {
        return allNecessarySkillTrainingReceived;
    }

    public void setAllNecessarySkillTrainingReceived(Yes_No allNecessarySkillTrainingReceived) {
        this.allNecessarySkillTrainingReceived = allNecessarySkillTrainingReceived;
    }

    public String getAllNecessarySkillTrainingReceivedNote() {
        return allNecessarySkillTrainingReceivedNote;
    }

    public void setAllNecessarySkillTrainingReceivedNote(String allNecessarySkillTrainingReceivedNote) {
        this.allNecessarySkillTrainingReceivedNote = allNecessarySkillTrainingReceivedNote;
    }

    public Yes_No getReceivedMedicalDocuments() {
        return receivedMedicalDocuments;
    }

    public void setReceivedMedicalDocuments(Yes_No receivedMedicalDocuments) {
        this.receivedMedicalDocuments = receivedMedicalDocuments;
    }

    public String getReceivedMedicalDocumentsNote() {
        return receivedMedicalDocumentsNote;
    }

    public void setReceivedMedicalDocumentsNote(String receivedMedicalDocumentsNote) {
        this.receivedMedicalDocumentsNote = receivedMedicalDocumentsNote;
    }

    public Yes_No getPreReintegrationHomeVisit() {
        return preReintegrationHomeVisit;
    }

    public void setPreReintegrationHomeVisit(Yes_No preReintegrationHomeVisit) {
        this.preReintegrationHomeVisit = preReintegrationHomeVisit;
    }

    public String getPreReintegrationHomeVisitNote() {
        return preReintegrationHomeVisitNote;
    }

    public void setPreReintegrationHomeVisitNote(String preReintegrationHomeVisitNote) {
        this.preReintegrationHomeVisitNote = preReintegrationHomeVisitNote;
    }

    public Yes_No getWorkSiteAssessment() {
        return workSiteAssessment;
    }

    public void setWorkSiteAssessment(Yes_No workSiteAssessment) {
        this.workSiteAssessment = workSiteAssessment;
    }

    public String getWorkSiteAssessmentNote() {
        return workSiteAssessmentNote;
    }

    public void setWorkSiteAssessmentNote(String workSiteAssessmentNote) {
        this.workSiteAssessmentNote = workSiteAssessmentNote;
    }

    public Yes_No getHealthOnDischarge() {
        return healthOnDischarge;
    }

    public void setHealthOnDischarge(Yes_No healthOnDischarge) {
        this.healthOnDischarge = healthOnDischarge;
    }

    public String getHealthOnDischargeNote() {
        return healthOnDischargeNote;
    }

    public void setHealthOnDischargeNote(String healthOnDischargeNote) {
        this.healthOnDischargeNote = healthOnDischargeNote;
    }

    public Yes_No getReintegration() {
        return reintegration;
    }

    public void setReintegration(Yes_No reintegration) {
        this.reintegration = reintegration;
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

    
}