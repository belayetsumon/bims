/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import itgarden.model.auth.Users;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
@Table(name = "M_INCOME_INFORMATION")
public class M_Income_Information {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
     @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Income_Generating incomeGeneratingActivites;

    @NotNull(message = "This field cannot be blank.")
  @Range(min = 0, message = "Please input positive numbers only")
    public int monthlyIncome=1;
    
   public String otherIncomeSource;

    @NotNull(message = "This field cannot be blank.")
   
    public int otherIncomeAmt;

    @NotNull(message = "This field cannot be blank.")
    @Digits(integer = 16, fraction = 5)
    public int monthlyTotalIncome=1;

    @NotNull(message = "This field cannot be blank.")
    @Digits(integer = 16, fraction = 5)
    public int totalHouseMember=1;

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

    public M_Income_Information() {
    }

    public M_Income_Information(Long id, MotherMasterData motherMasterCode, Income_Generating incomeGeneratingActivites, int monthlyIncome, String otherIncomeSource, int otherIncomeAmt, int monthlyTotalIncome, int totalHouseMember, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.incomeGeneratingActivites = incomeGeneratingActivites;
        this.monthlyIncome = monthlyIncome;
        this.otherIncomeSource = otherIncomeSource;
        this.otherIncomeAmt = otherIncomeAmt;
        this.monthlyTotalIncome = monthlyTotalIncome;
        this.totalHouseMember = totalHouseMember;
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
    /**
     * audit end*********************
     */

}
