package sppbims.model.leave;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.MotherMasterData;
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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author libertyerp_local
 */
@Entity
public class LeaveMother implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_master_code_id", nullable = false)
    public MotherMasterData motherMasterCode;

    public String sectionName;

    @Column(nullable = false)
    @NotNull(message = " Leave from field cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate leaveFrom;

    @Column(nullable = false)
    @NotNull(message = "Leave to date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate leaveTo;

    @NotEmpty(message = "Reason of leave cannot be blank.")
    public String reasonofleave;

    @NotEmpty(message = "Care of cannot be blank.")
    public String careof;

    @NotEmpty(message = "Contact number cannot be blank.")
    public String contactNumber;

    @Lob
    @NotEmpty(message = "Address cannot be blank.")
    public String address;

//    @Column(nullable = false)
//    @NotNull(message = "Joinning date date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate joinningDate;

    @Lob
    private String remark;

    /**
     * ********* Audit ******************************
     */
    @Column(insertable = true, updatable = false)
    public LocalDate created = LocalDate.now();

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public Users createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    public Date updated = new Date();

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    public Users updatedBy;

    public LeaveMother() {
    }

    public LeaveMother(Long id, MotherMasterData motherMasterCode, String sectionName, LocalDate leaveFrom, LocalDate leaveTo, String reasonofleave, String careof, String contactNumber, String address, LocalDate joinningDate, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.sectionName = sectionName;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.reasonofleave = reasonofleave;
        this.careof = careof;
        this.contactNumber = contactNumber;
        this.address = address;
        this.joinningDate = joinningDate;
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

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public LocalDate getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(LocalDate leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public LocalDate getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(LocalDate leaveTo) {
        this.leaveTo = leaveTo;
    }

    public String getReasonofleave() {
        return reasonofleave;
    }

    public void setReasonofleave(String reasonofleave) {
        this.reasonofleave = reasonofleave;
    }

    public String getCareof() {
        return careof;
    }

    public void setCareof(String careof) {
        this.careof = careof;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getJoinningDate() {
        return joinningDate;
    }

    public void setJoinningDate(LocalDate joinningDate) {
        this.joinningDate = joinningDate;
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
