/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.follow_up_report;

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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@Table(name = "f_mother")
public class FollowUpMother {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "Release date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @NotBlank(message = "Visited Staff name field cannot be blank.")
    public String visitedStaffName;

     @NotNull(message = "Follow up date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate FollowUpDate;

    @NotNull(message = "Last follow date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastFollowUpDate;

    @NotBlank(message = "Number of follow up  field cannot be blank.")
    public String numberOfFollowUp;

    @NotBlank(message = "Duration of stay community field cannot be blank.")
    public String durationOfStayCommunity;

    @NotBlank(message = "Address with contact field cannot be blank.")
    public String addressWithContact;

    @NotBlank(message = "Home environment field cannot be blank.")
    public String homeEnvironment;

    @NotBlank(message = "Worksite environment field cannot be blank.")
    public String worksiteEnvironment;

    @NotBlank(message = "Current Property field cannot be blank.")
    public String currentProperty;

    @NotBlank(message = "Link with any GO/NGO support field cannot be blank.")
    public String goNgoSupport;

    @NotBlank(message = "SPP saving money field cannot be blank.")
    @Lob
    public String sppsavingMoney;

    @NotBlank(message = "Present carry on IGA field cannot be blank.")
    @Lob
    public String presentIga;

    @NotBlank(message = "Present challenges field cannot be blank.")
    @Lob
    public String presentChallenges;

    @NotBlank(message = "Action taken for resolved these challenges field cannot be blank.")
    public String resolvedChallenges;

    @NotBlank(message = "Expectations from SPP field cannot be blank.")
    @Lob
    public String expectationsFromSpp;

    @NotBlank(message = "How many times went to clinic/doctor field cannot be blank.")
    public String clinic;

    @NotBlank(message = "Used to hand wash after toileting /before meal field cannot be blank.")

    public String handWash;

    @NotBlank(message = "Recommendation of visited staff field cannot be blank.")
    @Lob
    public String recommendation;
    @Lob
    public String remarks;

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

    public FollowUpMother() {
    }

    public FollowUpMother(Long id, MotherMasterData motherMasterCode, LocalDate releaseDate, String visitedStaffName, LocalDate FollowUpDate, LocalDate lastFollowUpDate, String numberOfFollowUp, String durationOfStayCommunity, String addressWithContact, String homeEnvironment, String worksiteEnvironment, String currentProperty, String goNgoSupport, String sppsavingMoney, String presentIga, String presentChallenges, String resolvedChallenges, String expectationsFromSpp, String clinic, String handWash, String recommendation, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.releaseDate = releaseDate;
        this.visitedStaffName = visitedStaffName;
        this.FollowUpDate = FollowUpDate;
        this.lastFollowUpDate = lastFollowUpDate;
        this.numberOfFollowUp = numberOfFollowUp;
        this.durationOfStayCommunity = durationOfStayCommunity;
        this.addressWithContact = addressWithContact;
        this.homeEnvironment = homeEnvironment;
        this.worksiteEnvironment = worksiteEnvironment;
        this.currentProperty = currentProperty;
        this.goNgoSupport = goNgoSupport;
        this.sppsavingMoney = sppsavingMoney;
        this.presentIga = presentIga;
        this.presentChallenges = presentChallenges;
        this.resolvedChallenges = resolvedChallenges;
        this.expectationsFromSpp = expectationsFromSpp;
        this.clinic = clinic;
        this.handWash = handWash;
        this.recommendation = recommendation;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVisitedStaffName() {
        return visitedStaffName;
    }

    public void setVisitedStaffName(String visitedStaffName) {
        this.visitedStaffName = visitedStaffName;
    }

    public LocalDate getFollowUpDate() {
        return FollowUpDate;
    }

    public void setFollowUpDate(LocalDate FollowUpDate) {
        this.FollowUpDate = FollowUpDate;
    }

    public LocalDate getLastFollowUpDate() {
        return lastFollowUpDate;
    }

    public void setLastFollowUpDate(LocalDate lastFollowUpDate) {
        this.lastFollowUpDate = lastFollowUpDate;
    }

    public String getNumberOfFollowUp() {
        return numberOfFollowUp;
    }

    public void setNumberOfFollowUp(String numberOfFollowUp) {
        this.numberOfFollowUp = numberOfFollowUp;
    }

    public String getDurationOfStayCommunity() {
        return durationOfStayCommunity;
    }

    public void setDurationOfStayCommunity(String durationOfStayCommunity) {
        this.durationOfStayCommunity = durationOfStayCommunity;
    }

    public String getAddressWithContact() {
        return addressWithContact;
    }

    public void setAddressWithContact(String addressWithContact) {
        this.addressWithContact = addressWithContact;
    }

    public String getHomeEnvironment() {
        return homeEnvironment;
    }

    public void setHomeEnvironment(String homeEnvironment) {
        this.homeEnvironment = homeEnvironment;
    }

    public String getWorksiteEnvironment() {
        return worksiteEnvironment;
    }

    public void setWorksiteEnvironment(String worksiteEnvironment) {
        this.worksiteEnvironment = worksiteEnvironment;
    }

    public String getCurrentProperty() {
        return currentProperty;
    }

    public void setCurrentProperty(String currentProperty) {
        this.currentProperty = currentProperty;
    }

    public String getGoNgoSupport() {
        return goNgoSupport;
    }

    public void setGoNgoSupport(String goNgoSupport) {
        this.goNgoSupport = goNgoSupport;
    }

    public String getSppsavingMoney() {
        return sppsavingMoney;
    }

    public void setSppsavingMoney(String sppsavingMoney) {
        this.sppsavingMoney = sppsavingMoney;
    }

    public String getPresentIga() {
        return presentIga;
    }

    public void setPresentIga(String presentIga) {
        this.presentIga = presentIga;
    }

    public String getPresentChallenges() {
        return presentChallenges;
    }

    public void setPresentChallenges(String presentChallenges) {
        this.presentChallenges = presentChallenges;
    }

    public String getResolvedChallenges() {
        return resolvedChallenges;
    }

    public void setResolvedChallenges(String resolvedChallenges) {
        this.resolvedChallenges = resolvedChallenges;
    }

    public String getExpectationsFromSpp() {
        return expectationsFromSpp;
    }

    public void setExpectationsFromSpp(String expectationsFromSpp) {
        this.expectationsFromSpp = expectationsFromSpp;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getHandWash() {
        return handWash;
    }

    public void setHandWash(String handWash) {
        this.handWash = handWash;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
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
