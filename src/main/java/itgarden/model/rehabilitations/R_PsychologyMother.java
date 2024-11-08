/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.rehabilitations;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.homevisit.Yes_No;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
public class R_PsychologyMother {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "motherMasterData", nullable = false)
    public MotherMasterData motherMasterCode;

    /// Mood/(Emotion ///////////////
    public int anger;

    public int sad;

    public int guilt;

    public int happy;

    public int tensed;

    public int stress;

    @Enumerated(EnumType.STRING)
    public EmotionStatusEnum emotionStatus;

    ///////////////////// Behavioral /////////////////
    @Enumerated(EnumType.STRING)
    public Yes_No selfHerm;

    @Enumerated(EnumType.STRING)
    public CopingStrategyEnum copingStrategy;

    @Enumerated(EnumType.STRING)
    public Presence_traumaEnum presenceTrauma;

    @Enumerated(EnumType.STRING)
    public Family_Relationship_Enum familyRelationship;

    @Enumerated(EnumType.STRING)
    public Yes_No hallucination;

    @Lob
    public String hallucinationNote;

    @Enumerated(EnumType.STRING)
    public Yes_No delusion;

    @Lob
    public String delusionNote;

    @Enumerated(EnumType.STRING)
    public Yes_No sleepDisturbance;

    @Enumerated(EnumType.STRING)
    public IqEnum iQ;

    //Behavior:
    @Lob
    public String fivebBasicNeed;

    @Lob
    public String protection;

    @Lob
    public String performanceWorkingSection;

    @Lob
    public String performanceLiteracy;

    @Lob
    public String performanceEconomy;
    
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

    public R_PsychologyMother() {
    }

    public R_PsychologyMother(Long id, MotherMasterData motherMasterCode, int anger, int sad, int guilt, int happy, int tensed, int stress, EmotionStatusEnum emotionStatus, Yes_No selfHerm, CopingStrategyEnum copingStrategy, Presence_traumaEnum presenceTrauma, Family_Relationship_Enum familyRelationship, Yes_No hallucination, String hallucinationNote, Yes_No delusion, String delusionNote, Yes_No sleepDisturbance, IqEnum iQ, String fivebBasicNeed, String protection, String performanceWorkingSection, String performanceLiteracy, String performanceEconomy, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.anger = anger;
        this.sad = sad;
        this.guilt = guilt;
        this.happy = happy;
        this.tensed = tensed;
        this.stress = stress;
        this.emotionStatus = emotionStatus;
        this.selfHerm = selfHerm;
        this.copingStrategy = copingStrategy;
        this.presenceTrauma = presenceTrauma;
        this.familyRelationship = familyRelationship;
        this.hallucination = hallucination;
        this.hallucinationNote = hallucinationNote;
        this.delusion = delusion;
        this.delusionNote = delusionNote;
        this.sleepDisturbance = sleepDisturbance;
        this.iQ = iQ;
        this.fivebBasicNeed = fivebBasicNeed;
        this.protection = protection;
        this.performanceWorkingSection = performanceWorkingSection;
        this.performanceLiteracy = performanceLiteracy;
        this.performanceEconomy = performanceEconomy;
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

    public int getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }

    public int getSad() {
        return sad;
    }

    public void setSad(int sad) {
        this.sad = sad;
    }

    public int getGuilt() {
        return guilt;
    }

    public void setGuilt(int guilt) {
        this.guilt = guilt;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getTensed() {
        return tensed;
    }

    public void setTensed(int tensed) {
        this.tensed = tensed;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public EmotionStatusEnum getEmotionStatus() {
        return emotionStatus;
    }

    public void setEmotionStatus(EmotionStatusEnum emotionStatus) {
        this.emotionStatus = emotionStatus;
    }

    public Yes_No getSelfHerm() {
        return selfHerm;
    }

    public void setSelfHerm(Yes_No selfHerm) {
        this.selfHerm = selfHerm;
    }

    public CopingStrategyEnum getCopingStrategy() {
        return copingStrategy;
    }

    public void setCopingStrategy(CopingStrategyEnum copingStrategy) {
        this.copingStrategy = copingStrategy;
    }

    public Presence_traumaEnum getPresenceTrauma() {
        return presenceTrauma;
    }

    public void setPresenceTrauma(Presence_traumaEnum presenceTrauma) {
        this.presenceTrauma = presenceTrauma;
    }

    public Family_Relationship_Enum getFamilyRelationship() {
        return familyRelationship;
    }

    public void setFamilyRelationship(Family_Relationship_Enum familyRelationship) {
        this.familyRelationship = familyRelationship;
    }

    public Yes_No getHallucination() {
        return hallucination;
    }

    public void setHallucination(Yes_No hallucination) {
        this.hallucination = hallucination;
    }

    public String getHallucinationNote() {
        return hallucinationNote;
    }

    public void setHallucinationNote(String hallucinationNote) {
        this.hallucinationNote = hallucinationNote;
    }

    public Yes_No getDelusion() {
        return delusion;
    }

    public void setDelusion(Yes_No delusion) {
        this.delusion = delusion;
    }

    public String getDelusionNote() {
        return delusionNote;
    }

    public void setDelusionNote(String delusionNote) {
        this.delusionNote = delusionNote;
    }

    public Yes_No getSleepDisturbance() {
        return sleepDisturbance;
    }

    public void setSleepDisturbance(Yes_No sleepDisturbance) {
        this.sleepDisturbance = sleepDisturbance;
    }

    public IqEnum getiQ() {
        return iQ;
    }

    public void setiQ(IqEnum iQ) {
        this.iQ = iQ;
    }

    public String getFivebBasicNeed() {
        return fivebBasicNeed;
    }

    public void setFivebBasicNeed(String fivebBasicNeed) {
        this.fivebBasicNeed = fivebBasicNeed;
    }

    public String getProtection() {
        return protection;
    }

    public void setProtection(String protection) {
        this.protection = protection;
    }

    public String getPerformanceWorkingSection() {
        return performanceWorkingSection;
    }

    public void setPerformanceWorkingSection(String performanceWorkingSection) {
        this.performanceWorkingSection = performanceWorkingSection;
    }

    public String getPerformanceLiteracy() {
        return performanceLiteracy;
    }

    public void setPerformanceLiteracy(String performanceLiteracy) {
        this.performanceLiteracy = performanceLiteracy;
    }

    public String getPerformanceEconomy() {
        return performanceEconomy;
    }

    public void setPerformanceEconomy(String performanceEconomy) {
        this.performanceEconomy = performanceEconomy;
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
