/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.rehabilitations;

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
import jakarta.persistence.ManyToOne;
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
public class R_PT_Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "motherMasterData", nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public M_Child_info childMasterCode;

    @Column(nullable = false)
    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate therapeuticSessionDate;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No pain;

    public String painNote;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public Tenderness Tenderness;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No PhysicalDisability;

    public String physicalDisabilityNote;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    JointMobility jointMobility;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    Mucsuloskeletal musculoskeletal;

    @NotNull(message = "*This field cannot be blank")
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    DegenerativeDiseases degenerativeDiseases;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No previouslyTherapyTaken;

    public String previouslyTherapyTakenNote;

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

    public R_PT_Child() {
    }

    public R_PT_Child(Long id, MotherMasterData motherMasterCode, M_Child_info childMasterCode, LocalDate therapeuticSessionDate, Yes_No pain, String painNote, Tenderness Tenderness, Yes_No PhysicalDisability, String physicalDisabilityNote, JointMobility jointMobility, Mucsuloskeletal musculoskeletal, DegenerativeDiseases degenerativeDiseases, Yes_No previouslyTherapyTaken, String previouslyTherapyTakenNote, String remark, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.childMasterCode = childMasterCode;
        this.therapeuticSessionDate = therapeuticSessionDate;
        this.pain = pain;
        this.painNote = painNote;
        this.Tenderness = Tenderness;
        this.PhysicalDisability = PhysicalDisability;
        this.physicalDisabilityNote = physicalDisabilityNote;
        this.jointMobility = jointMobility;
        this.musculoskeletal = musculoskeletal;
        this.degenerativeDiseases = degenerativeDiseases;
        this.previouslyTherapyTaken = previouslyTherapyTaken;
        this.previouslyTherapyTakenNote = previouslyTherapyTakenNote;
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

    public M_Child_info getChildMasterCode() {
        return childMasterCode;
    }

    public void setChildMasterCode(M_Child_info childMasterCode) {
        this.childMasterCode = childMasterCode;
    }

    public LocalDate getTherapeuticSessionDate() {
        return therapeuticSessionDate;
    }

    public void setTherapeuticSessionDate(LocalDate therapeuticSessionDate) {
        this.therapeuticSessionDate = therapeuticSessionDate;
    }

    public Yes_No getPain() {
        return pain;
    }

    public void setPain(Yes_No pain) {
        this.pain = pain;
    }

    public String getPainNote() {
        return painNote;
    }

    public void setPainNote(String painNote) {
        this.painNote = painNote;
    }

    public Tenderness getTenderness() {
        return Tenderness;
    }

    public void setTenderness(Tenderness Tenderness) {
        this.Tenderness = Tenderness;
    }

    public Yes_No getPhysicalDisability() {
        return PhysicalDisability;
    }

    public void setPhysicalDisability(Yes_No PhysicalDisability) {
        this.PhysicalDisability = PhysicalDisability;
    }

    public String getPhysicalDisabilityNote() {
        return physicalDisabilityNote;
    }

    public void setPhysicalDisabilityNote(String physicalDisabilityNote) {
        this.physicalDisabilityNote = physicalDisabilityNote;
    }

    public JointMobility getJointMobility() {
        return jointMobility;
    }

    public void setJointMobility(JointMobility jointMobility) {
        this.jointMobility = jointMobility;
    }

    public Mucsuloskeletal getMusculoskeletal() {
        return musculoskeletal;
    }

    public void setMusculoskeletal(Mucsuloskeletal musculoskeletal) {
        this.musculoskeletal = musculoskeletal;
    }

    public DegenerativeDiseases getDegenerativeDiseases() {
        return degenerativeDiseases;
    }

    public void setDegenerativeDiseases(DegenerativeDiseases degenerativeDiseases) {
        this.degenerativeDiseases = degenerativeDiseases;
    }

    public Yes_No getPreviouslyTherapyTaken() {
        return previouslyTherapyTaken;
    }

    public void setPreviouslyTherapyTaken(Yes_No previouslyTherapyTaken) {
        this.previouslyTherapyTaken = previouslyTherapyTaken;
    }

    public String getPreviouslyTherapyTakenNote() {
        return previouslyTherapyTakenNote;
    }

    public void setPreviouslyTherapyTakenNote(String previouslyTherapyTakenNote) {
        this.previouslyTherapyTakenNote = previouslyTherapyTakenNote;
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
