/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.clinic;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class C_Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Referral date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate referralDate;

    @NotNull(message = "Reffrred to field cannot be blank.")
//    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
    @Lob
    @Column(columnDefinition = "TEXT")
    public String reffrredTo;

    @NotNull(message = "Reasons field cannot be blank.")
//    @Size(min = 1, message = "This field must between 1 and 200 characters")
    @Column(columnDefinition = "TEXT")
    public String reasons;

//    @NotNull(message = "This field cannot be blank.")
//    @Size(min = 1, message = "This field must between 1 and 200 characters")
    @Column(columnDefinition = "TEXT")
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

    public C_Referral(Long id, MotherMasterData motherMasterCode, LocalDate referralDate, String reffrredTo, String reasons, String remarks, Users createdBy) {
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

    public LocalDate getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(LocalDate referralDate) {
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
