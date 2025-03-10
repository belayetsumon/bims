/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.reintegration_release;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.impactmeasurement.LongTermImpactMeasurement;
import sppbims.model.impactmeasurement.MidTermImpactMeasurement;
import sppbims.model.impactmeasurement.ShortTermImpactMeasurement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
public class ReleaseMother {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_master_code_id", nullable = false)
    private MotherMasterData motherMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Release date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @Lob
    private String address;

    @NotNull(message = "Release date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate postDischargeVisitDate;

    @Lob
    private String remark;

    /**
     * ********* Audit ******************************
     */
    @Column(insertable = true, updatable = false)
    private LocalDate created = LocalDate.now();

    @ManyToOne(optional = true)
    private Users createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    private Date updated = new Date();
    @ManyToOne(optional = true)
    private Users updatedBy;

    @OneToMany(mappedBy = "releaseMotherId", fetch = FetchType.LAZY)
    public List<ShortTermImpactMeasurement> shortTermImpactMeasurement = new ArrayList<>();

    @OneToMany(mappedBy = "releaseMotherId", fetch = FetchType.LAZY)
    public List<MidTermImpactMeasurement> midTermImpactMeasurement = new ArrayList<>();

    @OneToMany(mappedBy = "releaseMotherId", fetch = FetchType.LAZY)
    public List<LongTermImpactMeasurement> longTermImpactMeasurement = new ArrayList<>();

    public ReleaseMother() {
    }

    public ReleaseMother(Long id, MotherMasterData motherMasterCode, LocalDate releaseDate, String address, LocalDate postDischargeVisitDate, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.releaseDate = releaseDate;
        this.address = address;
        this.postDischargeVisitDate = postDischargeVisitDate;
        this.remark = remark;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getPostDischargeVisitDate() {
        return postDischargeVisitDate;
    }

    public void setPostDischargeVisitDate(LocalDate postDischargeVisitDate) {
        this.postDischargeVisitDate = postDischargeVisitDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<ShortTermImpactMeasurement> getShortTermImpactMeasurement() {
        return shortTermImpactMeasurement;
    }

    public void setShortTermImpactMeasurement(List<ShortTermImpactMeasurement> shortTermImpactMeasurement) {
        this.shortTermImpactMeasurement = shortTermImpactMeasurement;
    }

    public List<MidTermImpactMeasurement> getMidTermImpactMeasurement() {
        return midTermImpactMeasurement;
    }

    public void setMidTermImpactMeasurement(List<MidTermImpactMeasurement> midTermImpactMeasurement) {
        this.midTermImpactMeasurement = midTermImpactMeasurement;
    }

    public List<LongTermImpactMeasurement> getLongTermImpactMeasurement() {
        return longTermImpactMeasurement;
    }

    public void setLongTermImpactMeasurement(List<LongTermImpactMeasurement> longTermImpactMeasurement) {
        this.longTermImpactMeasurement = longTermImpactMeasurement;
    }

}
