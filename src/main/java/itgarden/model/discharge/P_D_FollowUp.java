/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.discharge;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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


public class P_D_FollowUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 11, message = "This field must between 1 and 200 characters")
    public String fDate;
    
    
       @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  motherPresentHealthStatus;
               
    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  childPresentHealthStatus;
    
    
      @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  childEducation;
      
      
         @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  motherOccupation;
   
         
   @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  motherMonthlyIncome;
   
   
   @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  followedBy;
   
   
   @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  conductedBy;
   
   @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String  remark;
   
   
   
   
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

    public P_D_FollowUp() {
    }

    public P_D_FollowUp(Long id, MotherMasterData motherMasterCode, String fDate, String motherPresentHealthStatus, String childPresentHealthStatus, String childEducation, String motherOccupation, String motherMonthlyIncome, String followedBy, String conductedBy, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.fDate = fDate;
        this.motherPresentHealthStatus = motherPresentHealthStatus;
        this.childPresentHealthStatus = childPresentHealthStatus;
        this.childEducation = childEducation;
        this.motherOccupation = motherOccupation;
        this.motherMonthlyIncome = motherMonthlyIncome;
        this.followedBy = followedBy;
        this.conductedBy = conductedBy;
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

    public String getfDate() {
        return fDate;
    }

    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    public String getMotherPresentHealthStatus() {
        return motherPresentHealthStatus;
    }

    public void setMotherPresentHealthStatus(String motherPresentHealthStatus) {
        this.motherPresentHealthStatus = motherPresentHealthStatus;
    }

    public String getChildPresentHealthStatus() {
        return childPresentHealthStatus;
    }

    public void setChildPresentHealthStatus(String childPresentHealthStatus) {
        this.childPresentHealthStatus = childPresentHealthStatus;
    }

    public String getChildEducation() {
        return childEducation;
    }

    public void setChildEducation(String childEducation) {
        this.childEducation = childEducation;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMotherMonthlyIncome() {
        return motherMonthlyIncome;
    }

    public void setMotherMonthlyIncome(String motherMonthlyIncome) {
        this.motherMonthlyIncome = motherMonthlyIncome;
    }

    public String getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(String followedBy) {
        this.followedBy = followedBy;
    }

    public String getConductedBy() {
        return conductedBy;
    }

    public void setConductedBy(String conductedBy) {
        this.conductedBy = conductedBy;
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
