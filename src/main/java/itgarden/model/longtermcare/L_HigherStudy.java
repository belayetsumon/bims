/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.longtermcare;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.EducationType;
import itgarden.model.homevisit.M_Child_info;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class L_HigherStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public M_Child_info childMasterCode;

    @NotNull(message = "This field cannot be blank.")

    @NotBlank(message = "This field cannot be blank.")

    @Lob
    public String address;
    @NotNull(message = "This field cannot be blank.")

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate higherStudyDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate addmissionDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public EducationType educationType;

    @NotNull(message = "This field cannot be blank.")

    public String instituteName;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public EducationLevel educationLavel;

    public String result;

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

    public L_HigherStudy() {
    }

    public L_HigherStudy(Long id, M_Child_info childMasterCode, String address, LocalDate higherStudyDate, LocalDate addmissionDate, LocalDate endDate, EducationType educationType, String instituteName, EducationLevel educationLavel, String result, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.childMasterCode = childMasterCode;
        this.address = address;
        this.higherStudyDate = higherStudyDate;
        this.addmissionDate = addmissionDate;
        this.endDate = endDate;
        this.educationType = educationType;
        this.instituteName = instituteName;
        this.educationLavel = educationLavel;
        this.result = result;
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

    public M_Child_info getChildMasterCode() {
        return childMasterCode;
    }

    public void setChildMasterCode(M_Child_info childMasterCode) {
        this.childMasterCode = childMasterCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getHigherStudyDate() {
        return higherStudyDate;
    }

    public void setHigherStudyDate(LocalDate higherStudyDate) {
        this.higherStudyDate = higherStudyDate;
    }

    public LocalDate getAddmissionDate() {
        return addmissionDate;
    }

    public void setAddmissionDate(LocalDate addmissionDate) {
        this.addmissionDate = addmissionDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public EducationLevel getEducationLavel() {
        return educationLavel;
    }

    public void setEducationLavel(EducationLevel educationLavel) {
        this.educationLavel = educationLavel;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
