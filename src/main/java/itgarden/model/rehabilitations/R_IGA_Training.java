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
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class R_IGA_Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "motherMasterData", nullable = false)
    public MotherMasterData motherMasterCode;

    public String name;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public TrainingName trainingName;

    public String prerequisiteSkill;

    public String location;

    @Column(nullable = false)
    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate endDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate extentionDate;

    public String conductedBy;

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

    public R_IGA_Training() {
    }

    public R_IGA_Training(Long id, MotherMasterData motherMasterCode, String name, TrainingName trainingName, String prerequisiteSkill, String location, LocalDate startDate, LocalDate endDate, LocalDate extentionDate, String conductedBy, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.name = name;
        this.trainingName = trainingName;
        this.prerequisiteSkill = prerequisiteSkill;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extentionDate = extentionDate;
        this.conductedBy = conductedBy;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingName getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(TrainingName trainingName) {
        this.trainingName = trainingName;
    }

    public String getPrerequisiteSkill() {
        return prerequisiteSkill;
    }

    public void setPrerequisiteSkill(String prerequisiteSkill) {
        this.prerequisiteSkill = prerequisiteSkill;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getExtentionDate() {
        return extentionDate;
    }

    public void setExtentionDate(LocalDate extentionDate) {
        this.extentionDate = extentionDate;
    }

    public String getConductedBy() {
        return conductedBy;
    }

    public void setConductedBy(String conductedBy) {
        this.conductedBy = conductedBy;
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
