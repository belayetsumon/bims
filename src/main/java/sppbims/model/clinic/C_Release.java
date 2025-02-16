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
public class C_Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Release date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 200, message = "This field must between 1 and 200 characters")
    public String releaseBy;

    public String nextFollowUpDate;

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

    public C_Release() {
    }

    public C_Release(Long id, MotherMasterData motherMasterCode, LocalDate releaseDate, String releaseBy, String nextFollowUpDate, String remarks, Users createdBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.releaseDate = releaseDate;
        this.releaseBy = releaseBy;
        this.nextFollowUpDate = nextFollowUpDate;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseBy() {
        return releaseBy;
    }

    public void setReleaseBy(String releaseBy) {
        this.releaseBy = releaseBy;
    }

    public String getNextFollowUpDate() {
        return nextFollowUpDate;
    }

    public void setNextFollowUpDate(String nextFollowUpDate) {
        this.nextFollowUpDate = nextFollowUpDate;
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
