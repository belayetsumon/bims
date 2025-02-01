/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import itgarden.model.auth.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
@Table(name = "M_PROPERTY")
public class M_Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    public String bankAccount;

    public float savingMoney;

    public float homelandQuantity;

    public float homeLandValue;

    public float cultivableLandQuantity;

    public float cultivableLandValue;

    public float jewelryQuentity;

    public float jewelryValue;

    public float animalsQuantity;

    public float animalsValue;

    public float investmentsSharesQuentity;

    public float investmentsSharesValue;

    public float loanPersonQuantity;

    public String loanPersonName;

    public float organizationsLoanQuantity;

    public String organizationName;

    @Lob
    String remark;
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

    public M_Property() {
    }

    public M_Property(Long id, MotherMasterData motherMasterCode, String bankAccount, float savingMoney, float homelandQuantity, float homeLandValue, float cultivableLandQuantity, float cultivableLandValue, float jewelryQuentity, float jewelryValue, float animalsQuantity, float animalsValue, float investmentsSharesQuentity, float investmentsSharesValue, float loanPersonQuantity, String loanPersonName, float organizationsLoanQuantity, String organizationName, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.bankAccount = bankAccount;
        this.savingMoney = savingMoney;
        this.homelandQuantity = homelandQuantity;
        this.homeLandValue = homeLandValue;
        this.cultivableLandQuantity = cultivableLandQuantity;
        this.cultivableLandValue = cultivableLandValue;
        this.jewelryQuentity = jewelryQuentity;
        this.jewelryValue = jewelryValue;
        this.animalsQuantity = animalsQuantity;
        this.animalsValue = animalsValue;
        this.investmentsSharesQuentity = investmentsSharesQuentity;
        this.investmentsSharesValue = investmentsSharesValue;
        this.loanPersonQuantity = loanPersonQuantity;
        this.loanPersonName = loanPersonName;
        this.organizationsLoanQuantity = organizationsLoanQuantity;
        this.organizationName = organizationName;
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

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public float getSavingMoney() {
        return savingMoney;
    }

    public void setSavingMoney(float savingMoney) {
        this.savingMoney = savingMoney;
    }

    public float getHomelandQuantity() {
        return homelandQuantity;
    }

    public void setHomelandQuantity(float homelandQuantity) {
        this.homelandQuantity = homelandQuantity;
    }

    public float getHomeLandValue() {
        return homeLandValue;
    }

    public void setHomeLandValue(float homeLandValue) {
        this.homeLandValue = homeLandValue;
    }

    public float getCultivableLandQuantity() {
        return cultivableLandQuantity;
    }

    public void setCultivableLandQuantity(float cultivableLandQuantity) {
        this.cultivableLandQuantity = cultivableLandQuantity;
    }

    public float getCultivableLandValue() {
        return cultivableLandValue;
    }

    public void setCultivableLandValue(float cultivableLandValue) {
        this.cultivableLandValue = cultivableLandValue;
    }

    public float getJewelryQuentity() {
        return jewelryQuentity;
    }

    public void setJewelryQuentity(float jewelryQuentity) {
        this.jewelryQuentity = jewelryQuentity;
    }

    public float getJewelryValue() {
        return jewelryValue;
    }

    public void setJewelryValue(float jewelryValue) {
        this.jewelryValue = jewelryValue;
    }

    public float getAnimalsQuantity() {
        return animalsQuantity;
    }

    public void setAnimalsQuantity(float animalsQuantity) {
        this.animalsQuantity = animalsQuantity;
    }

    public float getAnimalsValue() {
        return animalsValue;
    }

    public void setAnimalsValue(float animalsValue) {
        this.animalsValue = animalsValue;
    }

    public float getInvestmentsSharesQuentity() {
        return investmentsSharesQuentity;
    }

    public void setInvestmentsSharesQuentity(float investmentsSharesQuentity) {
        this.investmentsSharesQuentity = investmentsSharesQuentity;
    }

    public float getInvestmentsSharesValue() {
        return investmentsSharesValue;
    }

    public void setInvestmentsSharesValue(float investmentsSharesValue) {
        this.investmentsSharesValue = investmentsSharesValue;
    }

    public float getLoanPersonQuantity() {
        return loanPersonQuantity;
    }

    public void setLoanPersonQuantity(float loanPersonQuantity) {
        this.loanPersonQuantity = loanPersonQuantity;
    }

    public String getLoanPersonName() {
        return loanPersonName;
    }

    public void setLoanPersonName(String loanPersonName) {
        this.loanPersonName = loanPersonName;
    }

    public float getOrganizationsLoanQuantity() {
        return organizationsLoanQuantity;
    }

    public void setOrganizationsLoanQuantity(float organizationsLoanQuantity) {
        this.organizationsLoanQuantity = organizationsLoanQuantity;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

   
}
