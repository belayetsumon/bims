/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.homevisit;

import sppbims.model.auth.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class M_Child_Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    M_Child_info childMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    Address_Type AddressType;

    @Enumerated(EnumType.ORDINAL)
    Yes_No same;

    public String co;

    public String vill;

    public String po;

    @Enumerated(EnumType.ORDINAL)
    District district;

    @ManyToOne(optional = true)
    Thana thana;

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
    public M_Child_Address() {
    }

    public M_Child_Address(Long id, MotherMasterData motherMasterCode, M_Child_info childMasterCode, Address_Type AddressType, Yes_No same, String co, String vill, String po, District district, Thana thana, Users createdBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.childMasterCode = childMasterCode;
        this.AddressType = AddressType;
        this.same = same;
        this.co = co;
        this.vill = vill;
        this.po = po;
        this.district = district;
        this.thana = thana;
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

    public M_Child_info getChildMasterCode() {
        return childMasterCode;
    }

    public void setChildMasterCode(M_Child_info childMasterCode) {
        this.childMasterCode = childMasterCode;
    }

    public Address_Type getAddressType() {
        return AddressType;
    }

    public void setAddressType(Address_Type AddressType) {
        this.AddressType = AddressType;
    }

    public Yes_No getSame() {
        return same;
    }

    public void setSame(Yes_No same) {
        this.same = same;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getVill() {
        return vill;
    }

    public void setVill(String vill) {
        this.vill = vill;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Thana getThana() {
        return thana;
    }

    public void setThana(Thana thana) {
        this.thana = thana;
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
