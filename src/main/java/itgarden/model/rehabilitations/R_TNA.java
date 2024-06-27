/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.rehabilitations;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
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
 * @author Md Belayet Hossin
 */
public class R_TNA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;
    public String tnaPersons;

   
    public int oraleCommunication;

   
    public int writingCommunication;

 
    public int interpersonalCommunicationSkill;

    
    public int influencingSkilll;

 
    public int conflictManagement;


    public int problemSolving;


    public int customerOparetion;
    

    public int  decisionMaking;
   

    public int  timeManagement;
   

    public int  planning;
   
 
    public int  famaliyManagement;
     

    public int  teamBuilding;
   

    public int  managingDiversity; 
      

    public int  performanceManagement; 



    public int  leadership;


    public String resultl;
    @Lob
    public String comments;

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

    public R_TNA() {
    }

    public R_TNA(Long id, MotherMasterData motherMasterCode, String tnaPersons, int oraleCommunication, int writingCommunication, int interpersonalCommunicationSkill, int influencingSkilll, int conflictManagement, int problemSolving, int customerOparetion, int decisionMaking, int timeManagement, int planning, int famaliyManagement, int teamBuilding, int managingDiversity, int performanceManagement, int leadership, String resultl, String comments, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.tnaPersons = tnaPersons;
        this.oraleCommunication = oraleCommunication;
        this.writingCommunication = writingCommunication;
        this.interpersonalCommunicationSkill = interpersonalCommunicationSkill;
        this.influencingSkilll = influencingSkilll;
        this.conflictManagement = conflictManagement;
        this.problemSolving = problemSolving;
        this.customerOparetion = customerOparetion;
        this.decisionMaking = decisionMaking;
        this.timeManagement = timeManagement;
        this.planning = planning;
        this.famaliyManagement = famaliyManagement;
        this.teamBuilding = teamBuilding;
        this.managingDiversity = managingDiversity;
        this.performanceManagement = performanceManagement;
        this.leadership = leadership;
        this.resultl = resultl;
        this.comments = comments;
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

    public String getTnaPersons() {
        return tnaPersons;
    }

    public void setTnaPersons(String tnaPersons) {
        this.tnaPersons = tnaPersons;
    }

    public int getOraleCommunication() {
        return oraleCommunication;
    }

    public void setOraleCommunication(int oraleCommunication) {
        this.oraleCommunication = oraleCommunication;
    }

    public int getWritingCommunication() {
        return writingCommunication;
    }

    public void setWritingCommunication(int writingCommunication) {
        this.writingCommunication = writingCommunication;
    }

    public int getInterpersonalCommunicationSkill() {
        return interpersonalCommunicationSkill;
    }

    public void setInterpersonalCommunicationSkill(int interpersonalCommunicationSkill) {
        this.interpersonalCommunicationSkill = interpersonalCommunicationSkill;
    }

    public int getInfluencingSkilll() {
        return influencingSkilll;
    }

    public void setInfluencingSkilll(int influencingSkilll) {
        this.influencingSkilll = influencingSkilll;
    }

    public int getConflictManagement() {
        return conflictManagement;
    }

    public void setConflictManagement(int conflictManagement) {
        this.conflictManagement = conflictManagement;
    }

    public int getProblemSolving() {
        return problemSolving;
    }

    public void setProblemSolving(int problemSolving) {
        this.problemSolving = problemSolving;
    }

    public int getCustomerOparetion() {
        return customerOparetion;
    }

    public void setCustomerOparetion(int customerOparetion) {
        this.customerOparetion = customerOparetion;
    }

    public int getDecisionMaking() {
        return decisionMaking;
    }

    public void setDecisionMaking(int decisionMaking) {
        this.decisionMaking = decisionMaking;
    }

    public int getTimeManagement() {
        return timeManagement;
    }

    public void setTimeManagement(int timeManagement) {
        this.timeManagement = timeManagement;
    }

    public int getPlanning() {
        return planning;
    }

    public void setPlanning(int planning) {
        this.planning = planning;
    }

    public int getFamaliyManagement() {
        return famaliyManagement;
    }

    public void setFamaliyManagement(int famaliyManagement) {
        this.famaliyManagement = famaliyManagement;
    }

    public int getTeamBuilding() {
        return teamBuilding;
    }

    public void setTeamBuilding(int teamBuilding) {
        this.teamBuilding = teamBuilding;
    }

    public int getManagingDiversity() {
        return managingDiversity;
    }

    public void setManagingDiversity(int managingDiversity) {
        this.managingDiversity = managingDiversity;
    }

    public int getPerformanceManagement() {
        return performanceManagement;
    }

    public void setPerformanceManagement(int performanceManagement) {
        this.performanceManagement = performanceManagement;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }

    public String getResultl() {
        return resultl;
    }

    public void setResultl(String resultl) {
        this.resultl = resultl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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