/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itgarden.model.literacy;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author libertyerp_local
 */
@Entity
public class LiteracyRegularAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private MotherMasterData motherMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Admission date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate admissionDate;

    @NotNull(message = "Present literacy level field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EducationLevel presentliteracylevel;

    @NotNull(message = "Admission levelcannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EducationLevel admissionLevel;

    /**
     * ********* Audit ******************************
     */
    @Column(insertable = true, updatable = false)
    public LocalDate created = LocalDate.now();

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public Users createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    public Date updated = new Date();

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public Users updatedBy;

    public LiteracyRegularAdmission() {
    }

    public LiteracyRegularAdmission(Long id, MotherMasterData motherMasterCode, LocalDate admissionDate, EducationLevel presentliteracylevel, EducationLevel admissionLevel, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.admissionDate = admissionDate;
        this.presentliteracylevel = presentliteracylevel;
        this.admissionLevel = admissionLevel;
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

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public EducationLevel getPresentliteracylevel() {
        return presentliteracylevel;
    }

    public void setPresentliteracylevel(EducationLevel presentliteracylevel) {
        this.presentliteracylevel = presentliteracylevel;
    }

    public EducationLevel getAdmissionLevel() {
        return admissionLevel;
    }

    public void setAdmissionLevel(EducationLevel admissionLevel) {
        this.admissionLevel = admissionLevel;
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
