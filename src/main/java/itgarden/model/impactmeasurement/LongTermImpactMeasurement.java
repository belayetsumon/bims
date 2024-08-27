package itgarden.model.impactmeasurement;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.House_Type;
import itgarden.model.homevisit.Ownershif_type;
import itgarden.model.reintegration_release.ReleaseMother;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author libertyerp_local
 */
@Entity
public class LongTermImpactMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private ReleaseMother releaseMotherId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Present income source field cannot be blank.")
    public PresentIncomeSource presentIncomeSource;

    @NotEmpty(message = "Monthly expenditure amount field cannot be blank.")
    public String monthlyIncomeAmount;

    @NotEmpty(message = "This field cannot be blank.")
    public String monthlyExpenditureAmount;

    @NotEmpty(message = "Savings amount field cannot be blank.")
    public String savingsAmount;

    @NotNull(message = "Shelter status field cannot be blank.")
    @ManyToOne(optional = false)
    public Ownershif_type shelterStatus;

    @NotNull(message = "House type field cannot be blank.")
    @ManyToOne(optional = false)
    public House_Type housetype;

    @Enumerated(EnumType.STRING)
    public ContinueNotContinueEnum childOneEducation;

    @Enumerated(EnumType.STRING)
    public ContinueNotContinueEnum childTwoEducation;

    @Enumerated(EnumType.STRING)
    public ContinueNotContinueEnum childThreeEducation;

    @Enumerated(EnumType.STRING)
    public ContinueNotContinueEnum childFourEducation;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Relationship with neighbors field cannot be blank.")
    public ImpactMeasurementIndicator relationshipWithNeighbors;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Attends social sctivities field cannot be blank.")
    public ImpactMeasurementYesNo attendsSocialActivities;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Attends social gatherings field cannot be blank.")
    public ImpactMeasurementYesNo attendsSocialGatherings;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "AccessSocial safetynet program field cannot be blank.")
    public ImpactMeasurementYesNo accessSocialSafetynetProgram;
    
    

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

    public LongTermImpactMeasurement() {
    }

    public LongTermImpactMeasurement(Long id, ReleaseMother releaseMotherId, PresentIncomeSource presentIncomeSource, String monthlyIncomeAmount, String monthlyExpenditureAmount, String savingsAmount, Ownershif_type shelterStatus, House_Type housetype, ContinueNotContinueEnum childOneEducation, ContinueNotContinueEnum childTwoEducation, ContinueNotContinueEnum childThreeEducation, ContinueNotContinueEnum childFourEducation, ImpactMeasurementIndicator relationshipWithNeighbors, ImpactMeasurementYesNo attendsSocialActivities, ImpactMeasurementYesNo attendsSocialGatherings, ImpactMeasurementYesNo accessSocialSafetynetProgram, Users createdBy, Users updatedBy) {
        this.id = id;
        this.releaseMotherId = releaseMotherId;
        this.presentIncomeSource = presentIncomeSource;
        this.monthlyIncomeAmount = monthlyIncomeAmount;
        this.monthlyExpenditureAmount = monthlyExpenditureAmount;
        this.savingsAmount = savingsAmount;
        this.shelterStatus = shelterStatus;
        this.housetype = housetype;
        this.childOneEducation = childOneEducation;
        this.childTwoEducation = childTwoEducation;
        this.childThreeEducation = childThreeEducation;
        this.childFourEducation = childFourEducation;
        this.relationshipWithNeighbors = relationshipWithNeighbors;
        this.attendsSocialActivities = attendsSocialActivities;
        this.attendsSocialGatherings = attendsSocialGatherings;
        this.accessSocialSafetynetProgram = accessSocialSafetynetProgram;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReleaseMother getReleaseMotherId() {
        return releaseMotherId;
    }

    public void setReleaseMotherId(ReleaseMother releaseMotherId) {
        this.releaseMotherId = releaseMotherId;
    }

    public PresentIncomeSource getPresentIncomeSource() {
        return presentIncomeSource;
    }

    public void setPresentIncomeSource(PresentIncomeSource presentIncomeSource) {
        this.presentIncomeSource = presentIncomeSource;
    }

    public String getMonthlyIncomeAmount() {
        return monthlyIncomeAmount;
    }

    public void setMonthlyIncomeAmount(String monthlyIncomeAmount) {
        this.monthlyIncomeAmount = monthlyIncomeAmount;
    }

    public String getMonthlyExpenditureAmount() {
        return monthlyExpenditureAmount;
    }

    public void setMonthlyExpenditureAmount(String monthlyExpenditureAmount) {
        this.monthlyExpenditureAmount = monthlyExpenditureAmount;
    }

    public String getSavingsAmount() {
        return savingsAmount;
    }

    public void setSavingsAmount(String savingsAmount) {
        this.savingsAmount = savingsAmount;
    }

    public Ownershif_type getShelterStatus() {
        return shelterStatus;
    }

    public void setShelterStatus(Ownershif_type shelterStatus) {
        this.shelterStatus = shelterStatus;
    }

    public House_Type getHousetype() {
        return housetype;
    }

    public void setHousetype(House_Type housetype) {
        this.housetype = housetype;
    }

    public ContinueNotContinueEnum getChildOneEducation() {
        return childOneEducation;
    }

    public void setChildOneEducation(ContinueNotContinueEnum childOneEducation) {
        this.childOneEducation = childOneEducation;
    }

    public ContinueNotContinueEnum getChildTwoEducation() {
        return childTwoEducation;
    }

    public void setChildTwoEducation(ContinueNotContinueEnum childTwoEducation) {
        this.childTwoEducation = childTwoEducation;
    }

    public ContinueNotContinueEnum getChildThreeEducation() {
        return childThreeEducation;
    }

    public void setChildThreeEducation(ContinueNotContinueEnum childThreeEducation) {
        this.childThreeEducation = childThreeEducation;
    }

    public ContinueNotContinueEnum getChildFourEducation() {
        return childFourEducation;
    }

    public void setChildFourEducation(ContinueNotContinueEnum childFourEducation) {
        this.childFourEducation = childFourEducation;
    }

    public ImpactMeasurementIndicator getRelationshipWithNeighbors() {
        return relationshipWithNeighbors;
    }

    public void setRelationshipWithNeighbors(ImpactMeasurementIndicator relationshipWithNeighbors) {
        this.relationshipWithNeighbors = relationshipWithNeighbors;
    }

    public ImpactMeasurementYesNo getAttendsSocialActivities() {
        return attendsSocialActivities;
    }

    public void setAttendsSocialActivities(ImpactMeasurementYesNo attendsSocialActivities) {
        this.attendsSocialActivities = attendsSocialActivities;
    }

    public ImpactMeasurementYesNo getAttendsSocialGatherings() {
        return attendsSocialGatherings;
    }

    public void setAttendsSocialGatherings(ImpactMeasurementYesNo attendsSocialGatherings) {
        this.attendsSocialGatherings = attendsSocialGatherings;
    }

    public ImpactMeasurementYesNo getAccessSocialSafetynetProgram() {
        return accessSocialSafetynetProgram;
    }

    public void setAccessSocialSafetynetProgram(ImpactMeasurementYesNo accessSocialSafetynetProgram) {
        this.accessSocialSafetynetProgram = accessSocialSafetynetProgram;
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
