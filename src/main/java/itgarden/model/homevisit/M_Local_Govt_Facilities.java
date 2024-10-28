/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import itgarden.model.auth.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
@Table(name = "M_LOCAL_GOVT_FACILITIES")
public class M_Local_Govt_Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
     @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public LocalContactPersion localContactPersons;

    public String name;

    public String contactNumber;

    @Enumerated(EnumType.ORDINAL)
    public Yes_No votingParticipation;

    @NotEmpty(message = "Please provide beneficiary  NID number.")
    @Size(min = 10, max = 17, message = " Please provide value minimum 10 digit maximum 17 digit")
    @Column(unique = true)
    public String boNID;

    public String vgfCard;

    public String govtSupport;

    public int sppMotherNo;
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

    public M_Local_Govt_Facilities() {
    }

    public M_Local_Govt_Facilities(Long id, MotherMasterData motherMasterCode, LocalContactPersion localContactPersons, String name, String contactNumber, Yes_No votingParticipation, String boNID, String vgfCard, String govtSupport, int sppMotherNo, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.localContactPersons = localContactPersons;
        this.name = name;
        this.contactNumber = contactNumber;
        this.votingParticipation = votingParticipation;
        this.boNID = boNID;
        this.vgfCard = vgfCard;
        this.govtSupport = govtSupport;
        this.sppMotherNo = sppMotherNo;
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

    public LocalContactPersion getLocalContactPersons() {
        return localContactPersons;
    }

    public void setLocalContactPersons(LocalContactPersion localContactPersons) {
        this.localContactPersons = localContactPersons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Yes_No getVotingParticipation() {
        return votingParticipation;
    }

    public void setVotingParticipation(Yes_No votingParticipation) {
        this.votingParticipation = votingParticipation;
    }

    public String getBoNID() {
        return boNID;
    }

    public void setBoNID(String boNID) {
        this.boNID = boNID;
    }

    public String getVgfCard() {
        return vgfCard;
    }

    public void setVgfCard(String vgfCard) {
        this.vgfCard = vgfCard;
    }

    public String getGovtSupport() {
        return govtSupport;
    }

    public void setGovtSupport(String govtSupport) {
        this.govtSupport = govtSupport;
    }

    public int getSppMotherNo() {
        return sppMotherNo;
    }

    public void setSppMotherNo(int sppMotherNo) {
        this.sppMotherNo = sppMotherNo;
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
