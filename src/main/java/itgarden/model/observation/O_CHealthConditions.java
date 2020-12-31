/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.observation;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class O_CHealthConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @OneToOne(optional = false)
    public M_Child_info childMasterCode;

    @NotNull(message = "This field cannot be blank.")
    public int observationDuration;

    @Lob
    public String previousDisease;

    @Lob
    public String previousTreatment;

    @Lob
    public String preasentHealtsStatus;

    @Lob
    public String nutritionStatus;

    @NotNull(message = "This field cannot be blank.")
    @Digits(integer = 20, fraction = 3)
    public int height;

    @NotNull(message = "This field cannot be blank.")
    @Digits(integer = 20, fraction = 3)
    public int weight;

    @NotNull(message = "This field cannot be blank.")

    @Enumerated(EnumType.ORDINAL)
    public Yes_No bloodPressure;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No eyeProblem;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No earProblem;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No tt;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No heart_disease;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No disability;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No bonyFracture;

    @Lob
    public String note;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No neurologicalDisease;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No resporatoryProblem;

    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Yes_No uti;

    @Lob
    public String previousReport;

    @Lob
    public String planForFurtherAction;

    @Lob
    public String otherImportantFindings;

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

    public O_CHealthConditions() {
    }

    public O_CHealthConditions(Long id, MotherMasterData motherMasterCode, M_Child_info childMasterCode, int observationDuration, String previousDisease, String previousTreatment, String preasentHealtsStatus, String nutritionStatus, int height, int weight, Yes_No bloodPressure, Yes_No eyeProblem, Yes_No earProblem, Yes_No tt, Yes_No heart_disease, Yes_No disability, Yes_No bonyFracture, String note, Yes_No neurologicalDisease, Yes_No resporatoryProblem, Yes_No uti, String previousReport, String planForFurtherAction, String otherImportantFindings, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.childMasterCode = childMasterCode;
        this.observationDuration = observationDuration;
        this.previousDisease = previousDisease;
        this.previousTreatment = previousTreatment;
        this.preasentHealtsStatus = preasentHealtsStatus;
        this.nutritionStatus = nutritionStatus;
        this.height = height;
        this.weight = weight;
        this.bloodPressure = bloodPressure;
        this.eyeProblem = eyeProblem;
        this.earProblem = earProblem;
        this.tt = tt;
        this.heart_disease = heart_disease;
        this.disability = disability;
        this.bonyFracture = bonyFracture;
        this.note = note;
        this.neurologicalDisease = neurologicalDisease;
        this.resporatoryProblem = resporatoryProblem;
        this.uti = uti;
        this.previousReport = previousReport;
        this.planForFurtherAction = planForFurtherAction;
        this.otherImportantFindings = otherImportantFindings;
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

    public int getObservationDuration() {
        return observationDuration;
    }

    public void setObservationDuration(int observationDuration) {
        this.observationDuration = observationDuration;
    }

    public String getPreviousDisease() {
        return previousDisease;
    }

    public void setPreviousDisease(String previousDisease) {
        this.previousDisease = previousDisease;
    }

    public String getPreviousTreatment() {
        return previousTreatment;
    }

    public void setPreviousTreatment(String previousTreatment) {
        this.previousTreatment = previousTreatment;
    }

    public String getPreasentHealtsStatus() {
        return preasentHealtsStatus;
    }

    public void setPreasentHealtsStatus(String preasentHealtsStatus) {
        this.preasentHealtsStatus = preasentHealtsStatus;
    }

    public String getNutritionStatus() {
        return nutritionStatus;
    }

    public void setNutritionStatus(String nutritionStatus) {
        this.nutritionStatus = nutritionStatus;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Yes_No getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(Yes_No bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Yes_No getEyeProblem() {
        return eyeProblem;
    }

    public void setEyeProblem(Yes_No eyeProblem) {
        this.eyeProblem = eyeProblem;
    }

    public Yes_No getEarProblem() {
        return earProblem;
    }

    public void setEarProblem(Yes_No earProblem) {
        this.earProblem = earProblem;
    }

    public Yes_No getTt() {
        return tt;
    }

    public void setTt(Yes_No tt) {
        this.tt = tt;
    }

    public Yes_No getHeart_disease() {
        return heart_disease;
    }

    public void setHeart_disease(Yes_No heart_disease) {
        this.heart_disease = heart_disease;
    }

    public Yes_No getDisability() {
        return disability;
    }

    public void setDisability(Yes_No disability) {
        this.disability = disability;
    }

    public Yes_No getBonyFracture() {
        return bonyFracture;
    }

    public void setBonyFracture(Yes_No bonyFracture) {
        this.bonyFracture = bonyFracture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Yes_No getNeurologicalDisease() {
        return neurologicalDisease;
    }

    public void setNeurologicalDisease(Yes_No neurologicalDisease) {
        this.neurologicalDisease = neurologicalDisease;
    }

    public Yes_No getResporatoryProblem() {
        return resporatoryProblem;
    }

    public void setResporatoryProblem(Yes_No resporatoryProblem) {
        this.resporatoryProblem = resporatoryProblem;
    }

    public Yes_No getUti() {
        return uti;
    }

    public void setUti(Yes_No uti) {
        this.uti = uti;
    }

    public String getPreviousReport() {
        return previousReport;
    }

    public void setPreviousReport(String previousReport) {
        this.previousReport = previousReport;
    }

    public String getPlanForFurtherAction() {
        return planForFurtherAction;
    }

    public void setPlanForFurtherAction(String planForFurtherAction) {
        this.planForFurtherAction = planForFurtherAction;
    }

    public String getOtherImportantFindings() {
        return otherImportantFindings;
    }

    public void setOtherImportantFindings(String otherImportantFindings) {
        this.otherImportantFindings = otherImportantFindings;
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
