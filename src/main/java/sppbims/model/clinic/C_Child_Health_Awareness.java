/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sppbims.model.clinic;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.M_Child_info;
import sppbims.model.homevisit.MotherMasterData;
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
public class C_Child_Health_Awareness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "child_master_code_id", nullable = false)
    public M_Child_info childMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Visit date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate insdate;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public HealthAwareness healthAwareness;

    public String remark;

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

    public C_Child_Health_Awareness() {
    }

    public C_Child_Health_Awareness(Long id, M_Child_info childMasterCode, LocalDate insdate, HealthAwareness healthAwareness, String remark, Users createdBy) {
        this.id = id;
        this.childMasterCode = childMasterCode;
        this.insdate = insdate;
        this.healthAwareness = healthAwareness;
        this.remark = remark;
        this.createdBy = createdBy;
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

    public LocalDate getInsdate() {
        return insdate;
    }

    public void setInsdate(LocalDate insdate) {
        this.insdate = insdate;
    }

    public HealthAwareness getHealthAwareness() {
        return healthAwareness;
    }

    public void setHealthAwareness(HealthAwareness healthAwareness) {
        this.healthAwareness = healthAwareness;
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
}
