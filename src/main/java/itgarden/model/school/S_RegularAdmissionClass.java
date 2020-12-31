/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.school;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.EducationType;
import itgarden.model.homevisit.M_Child_info;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

public class S_RegularAdmissionClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @OneToOne(optional = false)
    M_Child_info childMasterCode;

    @NotNull(message = "This field cannot be blank.Please enter minimum 2 character!")
    @Size(min = 2, max = 100, message = "This field cannot be blank.")
    public String dateAdmission;

    @NotNull(message = "This field cannot be blank.")
    @Size(min = 2, max = 100, message = "This field cannot be blank.")
    public String  admissionSession;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    EducationLevel admissionClass;

     @NotNull(message = "This field cannot be blank.")
    @Size(min = 2, max = 100, message = "This field cannot be blank.")
    public String LastAttendedSession;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    EducationLevel lastAttendedClass;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    EducationType lastAttendedEducationType;

    public String specialNeed;

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

    public S_RegularAdmissionClass() {
    }

    public S_RegularAdmissionClass(Long id, M_Child_info childMasterCode, String dateAdmission, String admissionSession, EducationLevel admissionClass, String LastAttendedSession, EducationLevel lastAttendedClass, EducationType lastAttendedEducationType, String specialNeed, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.childMasterCode = childMasterCode;
        this.dateAdmission = dateAdmission;
        this.admissionSession = admissionSession;
        this.admissionClass = admissionClass;
        this.LastAttendedSession = LastAttendedSession;
        this.lastAttendedClass = lastAttendedClass;
        this.lastAttendedEducationType = lastAttendedEducationType;
        this.specialNeed = specialNeed;
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

    public String getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(String dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public String getAdmissionSession() {
        return admissionSession;
    }

    public void setAdmissionSession(String admissionSession) {
        this.admissionSession = admissionSession;
    }

    public EducationLevel getAdmissionClass() {
        return admissionClass;
    }

    public void setAdmissionClass(EducationLevel admissionClass) {
        this.admissionClass = admissionClass;
    }

    public String getLastAttendedSession() {
        return LastAttendedSession;
    }

    public void setLastAttendedSession(String LastAttendedSession) {
        this.LastAttendedSession = LastAttendedSession;
    }

    public EducationLevel getLastAttendedClass() {
        return lastAttendedClass;
    }

    public void setLastAttendedClass(EducationLevel lastAttendedClass) {
        this.lastAttendedClass = lastAttendedClass;
    }

    public EducationType getLastAttendedEducationType() {
        return lastAttendedEducationType;
    }

    public void setLastAttendedEducationType(EducationType lastAttendedEducationType) {
        this.lastAttendedEducationType = lastAttendedEducationType;
    }

    public String getSpecialNeed() {
        return specialNeed;
    }

    public void setSpecialNeed(String specialNeed) {
        this.specialNeed = specialNeed;
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
