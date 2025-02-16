/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.homevisit;

import sppbims.model.auth.Users;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
@Table(name = "M_COMMUNITY_INFORMATION")
public class M_Community_Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank. ")
    @ManyToOne(optional = false)
    public Economic_Type economyType;

    @ElementCollection
    public List<String> avilableIga;

    @Lob
    public String avilableIgaNote;

    @ElementCollection
    public List<String> prospectiveIga;

    public String prospectiveIgaNote;

    @Enumerated(EnumType.STRING)
    public UnemploymentSessionEnum unemploymentSession;

    @Lob
    public String REMARKS;

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

    /**
     * audit end*********************
     */
    public M_Community_Information() {
    }

    public M_Community_Information(Long id, MotherMasterData motherMasterCode, Economic_Type economyType, List<String> avilableIga, String avilableIgaNote, List<String> prospectiveIga, String prospectiveIgaNote, UnemploymentSessionEnum unemploymentSession, String REMARKS, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.economyType = economyType;
        this.avilableIga = avilableIga;
        this.avilableIgaNote = avilableIgaNote;
        this.prospectiveIga = prospectiveIga;
        this.prospectiveIgaNote = prospectiveIgaNote;
        this.unemploymentSession = unemploymentSession;
        this.REMARKS = REMARKS;
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

    public Economic_Type getEconomyType() {
        return economyType;
    }

    public void setEconomyType(Economic_Type economyType) {
        this.economyType = economyType;
    }

    public List<String> getAvilableIga() {
        return avilableIga;
    }

    public void setAvilableIga(List<String> avilableIga) {
        this.avilableIga = avilableIga;
    }

    public String getAvilableIgaNote() {
        return avilableIgaNote;
    }

    public void setAvilableIgaNote(String avilableIgaNote) {
        this.avilableIgaNote = avilableIgaNote;
    }

    public List<String> getProspectiveIga() {
        return prospectiveIga;
    }

    public void setProspectiveIga(List<String> prospectiveIga) {
        this.prospectiveIga = prospectiveIga;
    }

    public String getProspectiveIgaNote() {
        return prospectiveIgaNote;
    }

    public void setProspectiveIgaNote(String prospectiveIgaNote) {
        this.prospectiveIgaNote = prospectiveIgaNote;
    }

    public UnemploymentSessionEnum getUnemploymentSession() {
        return unemploymentSession;
    }

    public void setUnemploymentSession(UnemploymentSessionEnum unemploymentSession) {
        this.unemploymentSession = unemploymentSession;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
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
