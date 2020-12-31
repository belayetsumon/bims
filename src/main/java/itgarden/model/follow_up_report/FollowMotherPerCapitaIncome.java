package itgarden.model.follow_up_report;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.Income_Generating;
import itgarden.model.homevisit.MotherMasterData;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
@Entity
@Table(name = "f_mother_per_capita_income")
public class FollowMotherPerCapitaIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Income_Generating incomeGeneratingActivites;

    @NotNull(message = "This field cannot be blank.")
    @Range(min = 0, message = "Please input positive numbers only")
    public int monthlyIncome = 1;

    public String otherIncomeSource;

    @NotNull(message = "This field cannot be blank.")

    public int otherIncomeAmt;

    @NotNull(message = "This field cannot be blank.")
    @Digits(integer = 16, fraction = 5)
    public int monthlyTotalIncome = 1;

    @NotNull(message = "This field cannot be blank.")
    @Digits(integer = 16, fraction = 5)
    public int monthlyTotalExpence = 1;

    @NotNull(message = "This field cannot be blank.")
    @Digits(integer = 16, fraction = 5)
    public int totalHouseMember = 1;

    @Lob
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

    @ManyToOne(optional = true)

    public Users updatedBy;

 

    public FollowMotherPerCapitaIncome() {
    }

    public FollowMotherPerCapitaIncome(Long id, MotherMasterData motherMasterCode, Income_Generating incomeGeneratingActivites, String otherIncomeSource, int otherIncomeAmt, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.incomeGeneratingActivites = incomeGeneratingActivites;
        this.otherIncomeSource = otherIncomeSource;
        this.otherIncomeAmt = otherIncomeAmt;
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

    public Income_Generating getIncomeGeneratingActivites() {
        return incomeGeneratingActivites;
    }

    public void setIncomeGeneratingActivites(Income_Generating incomeGeneratingActivites) {
        this.incomeGeneratingActivites = incomeGeneratingActivites;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getOtherIncomeSource() {
        return otherIncomeSource;
    }

    public void setOtherIncomeSource(String otherIncomeSource) {
        this.otherIncomeSource = otherIncomeSource;
    }

    public int getOtherIncomeAmt() {
        return otherIncomeAmt;
    }

    public void setOtherIncomeAmt(int otherIncomeAmt) {
        this.otherIncomeAmt = otherIncomeAmt;
    }

    public int getMonthlyTotalIncome() {
        return monthlyTotalIncome;
    }

    public void setMonthlyTotalIncome(int monthlyTotalIncome) {
        this.monthlyTotalIncome = monthlyTotalIncome;
    }

    public int getMonthlyTotalExpence() {
        return monthlyTotalExpence;
    }

    public void setMonthlyTotalExpence(int monthlyTotalExpence) {
        this.monthlyTotalExpence = monthlyTotalExpence;
    }

    public int getTotalHouseMember() {
        return totalHouseMember;
    }

    public void setTotalHouseMember(int totalHouseMember) {
        this.totalHouseMember = totalHouseMember;
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
