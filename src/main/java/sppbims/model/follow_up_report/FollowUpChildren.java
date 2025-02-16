/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.follow_up_report;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.M_Child_info;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Yes_No;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@Table(name = "f_children")
public class FollowUpChildren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public M_Child_info childMasterCode;

    @NotBlank(message = "Visited Staff name field cannot be blank.")
    public String visitedStaffName;

    @NotNull(message = "Release date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @NotNull(message = "Visit date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate visitDate;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.STRING)
    private Yes_No involvedWork;

    private String workNote;

    private String childClass;

    @Lob
    private String remarks;

    /**
     * ********* Audit ******************************
     */
    @Column(insertable = true, updatable = false)
    private LocalDate created = LocalDate.now();

    @ManyToOne(optional = true)
    private Users createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    private Date updated = new Date();
    @ManyToOne(optional = true)
    private Users updatedBy;

    public FollowUpChildren() {
    }

    public FollowUpChildren(Long id, MotherMasterData motherMasterCode, M_Child_info childMasterCode, String visitedStaffName, LocalDate releaseDate, LocalDate visitDate, Yes_No involvedWork, String workNote, String childClass, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.childMasterCode = childMasterCode;
        this.visitedStaffName = visitedStaffName;
        this.releaseDate = releaseDate;
        this.visitDate = visitDate;
        this.involvedWork = involvedWork;
        this.workNote = workNote;
        this.childClass = childClass;
        this.remarks = remarks;
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

    public M_Child_info getChildMasterCode() {
        return childMasterCode;
    }

    public void setChildMasterCode(M_Child_info childMasterCode) {
        this.childMasterCode = childMasterCode;
    }

    public String getVisitedStaffName() {
        return visitedStaffName;
    }

    public void setVisitedStaffName(String visitedStaffName) {
        this.visitedStaffName = visitedStaffName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Yes_No getInvolvedWork() {
        return involvedWork;
    }

    public void setInvolvedWork(Yes_No involvedWork) {
        this.involvedWork = involvedWork;
    }

    public String getWorkNote() {
        return workNote;
    }

    public void setWorkNote(String workNote) {
        this.workNote = workNote;
    }

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
