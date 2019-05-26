/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.clinic;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class C_Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
    public String referralDate;
    
    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
    public String reffrredTo;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
    public String reasons;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
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

    public C_Referral() {
    }

    public C_Referral(Long id, MotherMasterData motherMasterCode, String referralDate, String reffrredTo, String reasons, String remarks, Users createdBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.referralDate = referralDate;
        this.reffrredTo = reffrredTo;
        this.reasons = reasons;
        this.remarks = remarks;
        this.createdBy = createdBy;
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

    public String getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(String referralDate) {
        this.referralDate = referralDate;
    }

    public String getReffrredTo() {
        return reffrredTo;
    }

    public void setReffrredTo(String reffrredTo) {
        this.reffrredTo = reffrredTo;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
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
    
    
}
