/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.observation;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.M_Accessibility;
import itgarden.model.homevisit.MotherMasterData;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class O_Induction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public O_Induction(Long id, MotherMasterData motherMasterCode, String startDate, String endDate, String immediateSupportOn, String challagesOfCandidare, int possibleLength, String remark, Users createdBy, Users updatedBy) {
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
   
}
