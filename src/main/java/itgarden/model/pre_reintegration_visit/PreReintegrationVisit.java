package itgarden.model.pre_reintegration_visit;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
@Entity
public class PreReintegrationVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
   @JoinColumn(name = "mother_master_code_id", nullable = false)
    public MotherMasterData motherMasterCode;

    @NotEmpty(message = "This field cannot be blank.")
    public String visitOfficersName;

  
    
    
    @Column(nullable = false)
    @NotNull(message = "Visit  date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate visitDate;
    

    @Lob
    public String currentSupport;

    @Lob
    public String challengers;

    @Lob
    public String shortTermPlan;

    @NotEmpty(message = "This field cannot be blank.")
    public String shortTermPlanResolveDate;

    @Lob
    public String longTermPlan;

    @NotEmpty(message = "This field cannot be blank.")
    public String longTermPlanResolveDate;

    @Lob
    public String planForFurther;

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

    public PreReintegrationVisit() {
    }

    public PreReintegrationVisit(Long id, MotherMasterData motherMasterCode, String visitOfficersName, LocalDate visitDate, String currentSupport, String challengers, String shortTermPlan, String shortTermPlanResolveDate, String longTermPlan, String longTermPlanResolveDate, String planForFurther, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.visitOfficersName = visitOfficersName;
        this.visitDate = visitDate;
        this.currentSupport = currentSupport;
        this.challengers = challengers;
        this.shortTermPlan = shortTermPlan;
        this.shortTermPlanResolveDate = shortTermPlanResolveDate;
        this.longTermPlan = longTermPlan;
        this.longTermPlanResolveDate = longTermPlanResolveDate;
        this.planForFurther = planForFurther;
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

    public String getVisitOfficersName() {
        return visitOfficersName;
    }

    public void setVisitOfficersName(String visitOfficersName) {
        this.visitOfficersName = visitOfficersName;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getCurrentSupport() {
        return currentSupport;
    }

    public void setCurrentSupport(String currentSupport) {
        this.currentSupport = currentSupport;
    }

    public String getChallengers() {
        return challengers;
    }

    public void setChallengers(String challengers) {
        this.challengers = challengers;
    }

    public String getShortTermPlan() {
        return shortTermPlan;
    }

    public void setShortTermPlan(String shortTermPlan) {
        this.shortTermPlan = shortTermPlan;
    }

    public String getShortTermPlanResolveDate() {
        return shortTermPlanResolveDate;
    }

    public void setShortTermPlanResolveDate(String shortTermPlanResolveDate) {
        this.shortTermPlanResolveDate = shortTermPlanResolveDate;
    }

    public String getLongTermPlan() {
        return longTermPlan;
    }

    public void setLongTermPlan(String longTermPlan) {
        this.longTermPlan = longTermPlan;
    }

    public String getLongTermPlanResolveDate() {
        return longTermPlanResolveDate;
    }

    public void setLongTermPlanResolveDate(String longTermPlanResolveDate) {
        this.longTermPlanResolveDate = longTermPlanResolveDate;
    }

    public String getPlanForFurther() {
        return planForFurther;
    }

    public void setPlanForFurther(String planForFurther) {
        this.planForFurther = planForFurther;
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
