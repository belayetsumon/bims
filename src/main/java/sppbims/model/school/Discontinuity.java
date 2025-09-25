/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.school;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.M_Child_info;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import sppbims.model.homevisit.EducationLevel;
import sppbims.model.homevisit.EducationType;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class Discontinuity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Child master code cannot be blank.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "child_master_code_id", nullable = false)
    M_Child_info childMasterCode;

    @Column(nullable = false)
    @NotNull(message = " Dismissal date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateDismissal;

    @NotNull(message = "This field cannot be blank.Please enter minimum 2 character!")
    @Lob
    public String discontinuityReason;

    @NotEmpty(message = "Last attended session cannot be empty.")
    @Size(min = 2, max = 100, message = "min 2 max 100 characters")
    public String lastAttendedSession;

// ✅ This means the column last_attended_class_id references education_level(id)
    @NotNull(message = "Last attended class cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "last_attended_class_id", referencedColumnName = "id", nullable = false)
    private EducationLevel lastAttendedClass;

// ✅ This means the column last_attended_education_type_id references education_type(id)
    @NotNull(message = "Last attended education type cannot be blank.")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "last_attended_education_type_id", referencedColumnName = "id", nullable = false)
    private EducationType lastAttendedEducationType;

    @Lob
    @Column(columnDefinition = "TEXT")
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

    public Discontinuity() {
    }

    public Discontinuity(Long id, M_Child_info childMasterCode, LocalDate dateDismissal, String discontinuityReason, String lastAttendedSession, EducationLevel lastAttendedClass, EducationType lastAttendedEducationType, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.childMasterCode = childMasterCode;
        this.dateDismissal = dateDismissal;
        this.discontinuityReason = discontinuityReason;
        this.lastAttendedSession = lastAttendedSession;
        this.lastAttendedClass = lastAttendedClass;
        this.lastAttendedEducationType = lastAttendedEducationType;
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

    public LocalDate getDateDismissal() {
        return dateDismissal;
    }

    public void setDateDismissal(LocalDate dateDismissal) {
        this.dateDismissal = dateDismissal;
    }

    public String getDiscontinuityReason() {
        return discontinuityReason;
    }

    public void setDiscontinuityReason(String discontinuityReason) {
        this.discontinuityReason = discontinuityReason;
    }

    public String getLastAttendedSession() {
        return lastAttendedSession;
    }

    public void setLastAttendedSession(String lastAttendedSession) {
        this.lastAttendedSession = lastAttendedSession;
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
