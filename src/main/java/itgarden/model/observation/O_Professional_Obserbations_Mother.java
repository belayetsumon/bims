/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.observation;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class O_Professional_Obserbations_Mother {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    public O_Induction induction;

      @Column(nullable = false)
    @NotNull(message = "OB start  date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate obStartDate;

    @Column(nullable = false)
    @NotNull(message = "Induction start  date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate inductionStartDate;

    public Yes_No physicalDisability;

    @Lob
    public String physicalDisabilityNote;

    public Yes_No therapy;

    @Lob
    public String therapyNote;

    public Yes_No adlPerformance;

    @Lob
    public String adlPerformanceNote;

    public Yes_No psychocialAssesmentNeeds;

    @Lob
    public String psychocialAssesmentNeedsNote;

    @Lob
    public String remarks;

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

    public O_Professional_Obserbations_Mother() {
    }

    public O_Professional_Obserbations_Mother(Long id, MotherMasterData motherMasterCode, O_Induction induction, LocalDate obStartDate, LocalDate inductionStartDate, Yes_No physicalDisability, String physicalDisabilityNote, Yes_No therapy, String therapyNote, Yes_No adlPerformance, String adlPerformanceNote, Yes_No psychocialAssesmentNeeds, String psychocialAssesmentNeedsNote, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.induction = induction;
        this.obStartDate = obStartDate;
        this.inductionStartDate = inductionStartDate;
        this.physicalDisability = physicalDisability;
        this.physicalDisabilityNote = physicalDisabilityNote;
        this.therapy = therapy;
        this.therapyNote = therapyNote;
        this.adlPerformance = adlPerformance;
        this.adlPerformanceNote = adlPerformanceNote;
        this.psychocialAssesmentNeeds = psychocialAssesmentNeeds;
        this.psychocialAssesmentNeedsNote = psychocialAssesmentNeedsNote;
        this.remarks = remarks;
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

    public O_Induction getInduction() {
        return induction;
    }

    public void setInduction(O_Induction induction) {
        this.induction = induction;
    }

    public LocalDate getObStartDate() {
        return obStartDate;
    }

    public void setObStartDate(LocalDate obStartDate) {
        this.obStartDate = obStartDate;
    }

    public LocalDate getInductionStartDate() {
        return inductionStartDate;
    }

    public void setInductionStartDate(LocalDate inductionStartDate) {
        this.inductionStartDate = inductionStartDate;
    }

    public Yes_No getPhysicalDisability() {
        return physicalDisability;
    }

    public void setPhysicalDisability(Yes_No physicalDisability) {
        this.physicalDisability = physicalDisability;
    }

    public String getPhysicalDisabilityNote() {
        return physicalDisabilityNote;
    }

    public void setPhysicalDisabilityNote(String physicalDisabilityNote) {
        this.physicalDisabilityNote = physicalDisabilityNote;
    }

    public Yes_No getTherapy() {
        return therapy;
    }

    public void setTherapy(Yes_No therapy) {
        this.therapy = therapy;
    }

    public String getTherapyNote() {
        return therapyNote;
    }

    public void setTherapyNote(String therapyNote) {
        this.therapyNote = therapyNote;
    }

    public Yes_No getAdlPerformance() {
        return adlPerformance;
    }

    public void setAdlPerformance(Yes_No adlPerformance) {
        this.adlPerformance = adlPerformance;
    }

    public String getAdlPerformanceNote() {
        return adlPerformanceNote;
    }

    public void setAdlPerformanceNote(String adlPerformanceNote) {
        this.adlPerformanceNote = adlPerformanceNote;
    }

    public Yes_No getPsychocialAssesmentNeeds() {
        return psychocialAssesmentNeeds;
    }

    public void setPsychocialAssesmentNeeds(Yes_No psychocialAssesmentNeeds) {
        this.psychocialAssesmentNeeds = psychocialAssesmentNeeds;
    }

    public String getPsychocialAssesmentNeedsNote() {
        return psychocialAssesmentNeedsNote;
    }

    public void setPsychocialAssesmentNeedsNote(String psychocialAssesmentNeedsNote) {
        this.psychocialAssesmentNeedsNote = psychocialAssesmentNeedsNote;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
