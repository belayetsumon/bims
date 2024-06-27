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
    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;
     
    public String  bankAccount;
    
    public String  savingMoney;
    
    public String  homelandQuantity;

    public String  homeLandValue;

    public String  cultivableLandQuantity;

    public String  cultivableLandValue;

    public String  jewelryQuentity;
    
    public String jewelryValue;
    
    public String  animalsQuantity;
    
    public String  animalsValue;
    
    public String  investmentsSharesQuentity;
    
    public String  investmentsSharesValue;
    
    public String  loanPersonQuantity;
    
    public String  loanPersonName;
    
    public String  organizationsLoanQuantity;
    
    public String  organizationName ;
    
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

    public M_Property(Long id, MotherMasterData motherMasterCode, String bankAccount, String savingMoney, String homelandQuantity, String homeLandValue, String cultivableLandQuantity, String cultivableLandValue, String jewelryQuentity, String jewelryValue, String animalsQuantity, String animalsValue, String investmentsSharesQuentity, String investmentsSharesValue, String loanPersonQuantity, String loanPersonName, String organizationsLoanQuantity, String organizationName, String remark, Users createdBy, Users updatedBy) {
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

    public String getSavingMoney() {
        return savingMoney;
    }

    public void setSavingMoney(String savingMoney) {
        this.savingMoney = savingMoney;
    }

    public String getHomelandQuantity() {
        return homelandQuantity;
    }

    public void setHomelandQuantity(String homelandQuantity) {
        this.homelandQuantity = homelandQuantity;
    }

    public String getHomeLandValue() {
        return homeLandValue;
    }

    public void setHomeLandValue(String homeLandValue) {
        this.homeLandValue = homeLandValue;
    }

    public String getCultivableLandQuantity() {
        return cultivableLandQuantity;
    }

    public void setCultivableLandQuantity(String cultivableLandQuantity) {
        this.cultivableLandQuantity = cultivableLandQuantity;
    }

    public String getCultivableLandValue() {
        return cultivableLandValue;
    }

    public void setCultivableLandValue(String cultivableLandValue) {
        this.cultivableLandValue = cultivableLandValue;
    }

    public String getJewelryQuentity() {
        return jewelryQuentity;
    }

    public void setJewelryQuentity(String jewelryQuentity) {
        this.jewelryQuentity = jewelryQuentity;
    }

    public String getJewelryValue() {
        return jewelryValue;
    }

    public void setJewelryValue(String jewelryValue) {
        this.jewelryValue = jewelryValue;
    }

    public String getAnimalsQuantity() {
        return animalsQuantity;
    }

    public void setAnimalsQuantity(String animalsQuantity) {
        this.animalsQuantity = animalsQuantity;
    }

    public String getAnimalsValue() {
        return animalsValue;
    }

    public void setAnimalsValue(String animalsValue) {
        this.animalsValue = animalsValue;
    }

    public String getInvestmentsSharesQuentity() {
        return investmentsSharesQuentity;
    }

    public void setInvestmentsSharesQuentity(String investmentsSharesQuentity) {
        this.investmentsSharesQuentity = investmentsSharesQuentity;
    }

    public String getInvestmentsSharesValue() {
        return investmentsSharesValue;
    }

    public void setInvestmentsSharesValue(String investmentsSharesValue) {
        this.investmentsSharesValue = investmentsSharesValue;
    }

    public String getLoanPersonQuantity() {
        return loanPersonQuantity;
    }

    public void setLoanPersonQuantity(String loanPersonQuantity) {
        this.loanPersonQuantity = loanPersonQuantity;
    }

    public String getLoanPersonName() {
        return loanPersonName;
    }

    public void setLoanPersonName(String loanPersonName) {
        this.loanPersonName = loanPersonName;
    }

    public String getOrganizationsLoanQuantity() {
        return organizationsLoanQuantity;
    }

    public void setOrganizationsLoanQuantity(String organizationsLoanQuantity) {
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
    /**
     * audit end*********************
     */
    
    
}
