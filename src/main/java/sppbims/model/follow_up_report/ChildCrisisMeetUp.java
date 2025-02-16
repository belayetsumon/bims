/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.follow_up_report;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.M_Child_info;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
public class ChildCrisisMeetUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public M_Child_info childMasterCode;

    public String entrydate;

    public String location;

    public String reason;

    public String challenge;

    public String action;

    public String victimnumber;

    public String supports;

    public String expectedsolvingdate;

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

    public ChildCrisisMeetUp() {
    }

    public ChildCrisisMeetUp(Long id, M_Child_info childMasterCode, String entrydate, String location, String reason, String challenge, String action, String victimnumber, String supports, String expectedsolvingdate, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.childMasterCode = childMasterCode;
        this.entrydate = entrydate;
        this.location = location;
        this.reason = reason;
        this.challenge = challenge;
        this.action = action;
        this.victimnumber = victimnumber;
        this.supports = supports;
        this.expectedsolvingdate = expectedsolvingdate;
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

    public M_Child_info getChildMasterCode() {
        return childMasterCode;
    }

    public void setChildMasterCode(M_Child_info childMasterCode) {
        this.childMasterCode = childMasterCode;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVictimnumber() {
        return victimnumber;
    }

    public void setVictimnumber(String victimnumber) {
        this.victimnumber = victimnumber;
    }

    public String getSupports() {
        return supports;
    }

    public void setSupports(String supports) {
        this.supports = supports;
    }

    public String getExpectedsolvingdate() {
        return expectedsolvingdate;
    }

    public void setExpectedsolvingdate(String expectedsolvingdate) {
        this.expectedsolvingdate = expectedsolvingdate;
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
