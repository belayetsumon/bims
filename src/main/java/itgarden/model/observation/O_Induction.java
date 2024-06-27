/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.observation;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
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
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class O_Induction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 200, message = "This field must between 1 and 200 characters")
    public String startDate;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 200, message = "This field must between 1 and 200 characters")
    public String endDate;

    @Lob
    public String immediateSupportOn;

    @Lob
    public String challagesOfCandidare;

    @NotNull(message = "This field cannot be blank.")
    public int possibleLength;

    @Lob
    public String remark;

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
    
   
    @OneToOne(mappedBy = "induction")
    public O_MHealthConditions omHealthConditions;
    
    
    @OneToOne(mappedBy = "induction")
    public O_Professional_Obserbations_Mother  oProfessionalObserbationsMother;
    
    

    public O_Induction() {
    }

    public O_Induction(Long id, MotherMasterData motherMasterCode, String startDate, String endDate, String immediateSupportOn, String challagesOfCandidare, int possibleLength, String remark, Users createdBy, Users updatedBy, O_MHealthConditions omHealthConditions, O_Professional_Obserbations_Mother oProfessionalObserbationsMother) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.immediateSupportOn = immediateSupportOn;
        this.challagesOfCandidare = challagesOfCandidare;
        this.possibleLength = possibleLength;
        this.remark = remark;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.omHealthConditions = omHealthConditions;
        this.oProfessionalObserbationsMother = oProfessionalObserbationsMother;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getImmediateSupportOn() {
        return immediateSupportOn;
    }

    public void setImmediateSupportOn(String immediateSupportOn) {
        this.immediateSupportOn = immediateSupportOn;
    }

    public String getChallagesOfCandidare() {
        return challagesOfCandidare;
    }

    public void setChallagesOfCandidare(String challagesOfCandidare) {
        this.challagesOfCandidare = challagesOfCandidare;
    }

    public int getPossibleLength() {
        return possibleLength;
    }

    public void setPossibleLength(int possibleLength) {
        this.possibleLength = possibleLength;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public O_MHealthConditions getOmHealthConditions() {
        return omHealthConditions;
    }

    public void setOmHealthConditions(O_MHealthConditions omHealthConditions) {
        this.omHealthConditions = omHealthConditions;
    }

    public O_Professional_Obserbations_Mother getoProfessionalObserbationsMother() {
        return oProfessionalObserbationsMother;
    }

    public void setoProfessionalObserbationsMother(O_Professional_Obserbations_Mother oProfessionalObserbationsMother) {
        this.oProfessionalObserbationsMother = oProfessionalObserbationsMother;
    }

    
}
