/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.rehabilitations;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
public class R_OT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "motherMasterData", nullable = false)
    public MotherMasterData motherMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate therapeuticSessionDate;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public SessionType sessionType;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public Diagonosis diagonosis;

    public String treatment;

    public String conductedBy;

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

    public R_OT() {
    }

    public R_OT(Long id, MotherMasterData motherMasterCode, LocalDate therapeuticSessionDate, SessionType sessionType, Diagonosis diagonosis, String treatment, String conductedBy, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.therapeuticSessionDate = therapeuticSessionDate;
        this.sessionType = sessionType;
        this.diagonosis = diagonosis;
        this.treatment = treatment;
        this.conductedBy = conductedBy;
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

    public LocalDate getTherapeuticSessionDate() {
        return therapeuticSessionDate;
    }

    public void setTherapeuticSessionDate(LocalDate therapeuticSessionDate) {
        this.therapeuticSessionDate = therapeuticSessionDate;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public Diagonosis getDiagonosis() {
        return diagonosis;
    }

    public void setDiagonosis(Diagonosis diagonosis) {
        this.diagonosis = diagonosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getConductedBy() {
        return conductedBy;
    }

    public void setConductedBy(String conductedBy) {
        this.conductedBy = conductedBy;
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
