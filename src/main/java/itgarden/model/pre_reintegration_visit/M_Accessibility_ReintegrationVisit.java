/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.pre_reintegration_visit;

import itgarden.model.homevisit.*;
import itgarden.model.auth.Users;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */

@Entity
@Table(name = "Re_M_ACCESSIBILITY")
public class M_Accessibility_ReintegrationVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false)
     @JoinColumn(nullable =false)
    public MotherMasterData motherMasterCode;
    
    @ManyToOne(optional = true)
    public Road_Type  roadType;
    
    @ManyToOne(optional = true)
    public Transport_Type  transportType;

    public String   distanceToMainRoad;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No  primarySchool;

    public String  primarySchoolDistance;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No  secondarySchool;

    public String  secondarySchoolDistance;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No  hospital;

    public String  hospitalDistance;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No  marketPlace;

    public String  marketPlaceDistance;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No  bank;

    public String  bankDistance;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No ngo;

    public String  ngoDistance;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No  wellfareInstitutions;

    public int  wellfareInstitutionsDistance;
    
@Enumerated(EnumType.ORDINAL)
    public Yes_No technicalInstiute;

    public String technicalInstiuteDistance;
    
@Lob
    public String REMARKS;

    /*********** Audit *******************************/
    
    @Column(insertable = true, updatable = false)
    public LocalDate created =  LocalDate.now();

    @ManyToOne(optional = true)
    
    public Users createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(insertable = false, updatable = true)
    public Date updated = new Date();

    @ManyToOne(optional = true)
 
    public Users updatedBy;

    public M_Accessibility_ReintegrationVisit() {
    }

    public M_Accessibility_ReintegrationVisit(Long id, MotherMasterData motherMasterCode, Road_Type roadType, Transport_Type transportType, String distanceToMainRoad, Yes_No primarySchool, String primarySchoolDistance, Yes_No secondarySchool, String secondarySchoolDistance, Yes_No hospital, String hospitalDistance, Yes_No marketPlace, String marketPlaceDistance, Yes_No bank, String bankDistance, Yes_No ngo, String ngoDistance, Yes_No wellfareInstitutions, int wellfareInstitutionsDistance, Yes_No technicalInstiute, String technicalInstiuteDistance, String REMARKS, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.roadType = roadType;
        this.transportType = transportType;
        this.distanceToMainRoad = distanceToMainRoad;
        this.primarySchool = primarySchool;
        this.primarySchoolDistance = primarySchoolDistance;
        this.secondarySchool = secondarySchool;
        this.secondarySchoolDistance = secondarySchoolDistance;
        this.hospital = hospital;
        this.hospitalDistance = hospitalDistance;
        this.marketPlace = marketPlace;
        this.marketPlaceDistance = marketPlaceDistance;
        this.bank = bank;
        this.bankDistance = bankDistance;
        this.ngo = ngo;
        this.ngoDistance = ngoDistance;
        this.wellfareInstitutions = wellfareInstitutions;
        this.wellfareInstitutionsDistance = wellfareInstitutionsDistance;
        this.technicalInstiute = technicalInstiute;
        this.technicalInstiuteDistance = technicalInstiuteDistance;
        this.REMARKS = REMARKS;
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

    public Road_Type getRoadType() {
        return roadType;
    }

    public void setRoadType(Road_Type roadType) {
        this.roadType = roadType;
    }

    public Transport_Type getTransportType() {
        return transportType;
    }

    public void setTransportType(Transport_Type transportType) {
        this.transportType = transportType;
    }

    public String getDistanceToMainRoad() {
        return distanceToMainRoad;
    }

    public void setDistanceToMainRoad(String distanceToMainRoad) {
        this.distanceToMainRoad = distanceToMainRoad;
    }

    public Yes_No getPrimarySchool() {
        return primarySchool;
    }

    public void setPrimarySchool(Yes_No primarySchool) {
        this.primarySchool = primarySchool;
    }

    public String getPrimarySchoolDistance() {
        return primarySchoolDistance;
    }

    public void setPrimarySchoolDistance(String primarySchoolDistance) {
        this.primarySchoolDistance = primarySchoolDistance;
    }

    public Yes_No getSecondarySchool() {
        return secondarySchool;
    }

    public void setSecondarySchool(Yes_No secondarySchool) {
        this.secondarySchool = secondarySchool;
    }

    public String getSecondarySchoolDistance() {
        return secondarySchoolDistance;
    }

    public void setSecondarySchoolDistance(String secondarySchoolDistance) {
        this.secondarySchoolDistance = secondarySchoolDistance;
    }

    public Yes_No getHospital() {
        return hospital;
    }

    public void setHospital(Yes_No hospital) {
        this.hospital = hospital;
    }

    public String getHospitalDistance() {
        return hospitalDistance;
    }

    public void setHospitalDistance(String hospitalDistance) {
        this.hospitalDistance = hospitalDistance;
    }

    public Yes_No getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(Yes_No marketPlace) {
        this.marketPlace = marketPlace;
    }

    public String getMarketPlaceDistance() {
        return marketPlaceDistance;
    }

    public void setMarketPlaceDistance(String marketPlaceDistance) {
        this.marketPlaceDistance = marketPlaceDistance;
    }

    public Yes_No getBank() {
        return bank;
    }

    public void setBank(Yes_No bank) {
        this.bank = bank;
    }

    public String getBankDistance() {
        return bankDistance;
    }

    public void setBankDistance(String bankDistance) {
        this.bankDistance = bankDistance;
    }

    public Yes_No getNgo() {
        return ngo;
    }

    public void setNgo(Yes_No ngo) {
        this.ngo = ngo;
    }

    public String getNgoDistance() {
        return ngoDistance;
    }

    public void setNgoDistance(String ngoDistance) {
        this.ngoDistance = ngoDistance;
    }

    public Yes_No getWellfareInstitutions() {
        return wellfareInstitutions;
    }

    public void setWellfareInstitutions(Yes_No wellfareInstitutions) {
        this.wellfareInstitutions = wellfareInstitutions;
    }

    public int getWellfareInstitutionsDistance() {
        return wellfareInstitutionsDistance;
    }

    public void setWellfareInstitutionsDistance(int wellfareInstitutionsDistance) {
        this.wellfareInstitutionsDistance = wellfareInstitutionsDistance;
    }

    public Yes_No getTechnicalInstiute() {
        return technicalInstiute;
    }

    public void setTechnicalInstiute(Yes_No technicalInstiute) {
        this.technicalInstiute = technicalInstiute;
    }

    public String getTechnicalInstiuteDistance() {
        return technicalInstiuteDistance;
    }

    public void setTechnicalInstiuteDistance(String technicalInstiuteDistance) {
        this.technicalInstiuteDistance = technicalInstiuteDistance;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
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
