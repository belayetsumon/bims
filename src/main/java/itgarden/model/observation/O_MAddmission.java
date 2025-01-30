/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.observation;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class O_MAddmission {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_master_code_id", nullable = false)
    public MotherMasterData motherMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Arrival date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateArrival;

    @Column(nullable = false)
    @NotNull(message = "Admission date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate dateAdmission;

    public String remarks;

    @OneToOne(mappedBy = "addmission",fetch = FetchType.LAZY)
    public MotherImage motherImage;

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

    public O_MAddmission() {
    }

    public O_MAddmission(Long id, MotherMasterData motherMasterCode, LocalDate dateArrival, LocalDate dateAdmission, String remarks, MotherImage motherImage, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.dateArrival = dateArrival;
        this.dateAdmission = dateAdmission;
        this.remarks = remarks;
        this.motherImage = motherImage;
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

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
    }

    public LocalDate getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(LocalDate dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public MotherImage getMotherImage() {
        return motherImage;
    }

    public void setMotherImage(MotherImage motherImage) {
        this.motherImage = motherImage;
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
