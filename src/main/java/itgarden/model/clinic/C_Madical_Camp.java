/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.clinic;

import itgarden.model.auth.Users;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class C_Madical_Camp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
    public String campType;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
    public String startDate;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 13, message = "This field must between 1 and 200 characters")
    public String endDate;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, max = 2000, message = "This field must between 1 and 200 characters")
    public String organizedBy;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 1, message  = "This field must between 1 and 200 characters")
    public String beneficiary;

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

    public C_Madical_Camp() {
    }

    public C_Madical_Camp(Long id, String campType, String startDate, String endDate, String organizedBy, String beneficiary, String remark, Users createdBy) {
        this.id = id;
        this.campType = campType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizedBy = organizedBy;
        this.beneficiary = beneficiary;
        this.remark = remark;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampType() {
        return campType;
    }

    public void setCampType(String campType) {
        this.campType = campType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrganizedBy() {
        return organizedBy;
    }

    public void setOrganizedBy(String organizedBy) {
        this.organizedBy = organizedBy;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
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
