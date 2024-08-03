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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity

public class S_RegularAdmissionClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @OneToOne(optional = false)
    M_Child_info childMasterCode;

     @Column(nullable = false)
    @NotNull(message = "Date admission date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateAdmission;
    

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

    public S_RegularAdmissionClass(Long id, M_Child_info childMasterCode, LocalDate dateAdmission, String admissionSession, EducationLevel admissionClass, String LastAttendedSession, EducationLevel lastAttendedClass, EducationType lastAttendedEducationType, String specialNeed, String remark, Users createdBy, Users updatedBy) {
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

    public LocalDate getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(LocalDate dateAdmission) {
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
