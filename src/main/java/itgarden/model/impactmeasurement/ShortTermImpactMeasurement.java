/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itgarden.model.impactmeasurement;

import itgarden.model.auth.Users;
import itgarden.model.reintegration_release.ReleaseMother;
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
public class ShortTermImpactMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private ReleaseMother releaseMotherId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "This field cannot be blank.")
    ImpactMeasurementYesNo igsstart;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "This field cannot be blank.")
    ImpactMeasurementYesNo childrenSchoolAdmission;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "This field cannot be blank.")
    ImpactMeasurementIndicator safeAccommodationLivingEnvironmen;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "This field cannot be blank.")
    ImpactMeasurementIndicator communityAcceptance;

    @Lob
    private String remark;

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

    public ShortTermImpactMeasurement() {
    }

    public ShortTermImpactMeasurement(Long id, ReleaseMother releaseMotherId, ImpactMeasurementYesNo igsstart, ImpactMeasurementYesNo childrenSchoolAdmission, ImpactMeasurementIndicator safeAccommodationLivingEnvironmen, ImpactMeasurementIndicator communityAcceptance, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.releaseMotherId = releaseMotherId;
        this.igsstart = igsstart;
        this.childrenSchoolAdmission = childrenSchoolAdmission;
        this.safeAccommodationLivingEnvironmen = safeAccommodationLivingEnvironmen;
        this.communityAcceptance = communityAcceptance;
        this.remark = remark;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReleaseMother getReleaseMotherId() {
        return releaseMotherId;
    }

    public void setReleaseMotherId(ReleaseMother releaseMotherId) {
        this.releaseMotherId = releaseMotherId;
    }

    public ImpactMeasurementYesNo getIgsstart() {
        return igsstart;
    }

    public void setIgsstart(ImpactMeasurementYesNo igsstart) {
        this.igsstart = igsstart;
    }

    public ImpactMeasurementYesNo getChildrenSchoolAdmission() {
        return childrenSchoolAdmission;
    }

    public void setChildrenSchoolAdmission(ImpactMeasurementYesNo childrenSchoolAdmission) {
        this.childrenSchoolAdmission = childrenSchoolAdmission;
    }

    public ImpactMeasurementIndicator getSafeAccommodationLivingEnvironmen() {
        return safeAccommodationLivingEnvironmen;
    }

    public void setSafeAccommodationLivingEnvironmen(ImpactMeasurementIndicator safeAccommodationLivingEnvironmen) {
        this.safeAccommodationLivingEnvironmen = safeAccommodationLivingEnvironmen;
    }

    public ImpactMeasurementIndicator getCommunityAcceptance() {
        return communityAcceptance;
    }

    public void setCommunityAcceptance(ImpactMeasurementIndicator communityAcceptance) {
        this.communityAcceptance = communityAcceptance;
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
