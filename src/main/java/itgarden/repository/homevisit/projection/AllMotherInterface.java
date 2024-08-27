/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit.projection;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.EducationLevel;
import itgarden.model.homevisit.EducationType;
import itgarden.model.homevisit.Eligibility;
import itgarden.model.homevisit.EthinicIdentity;
import itgarden.model.homevisit.HusbandsStatus;
import itgarden.model.homevisit.MaritalStatus;
import itgarden.model.homevisit.Occupation;
import itgarden.model.homevisit.Reasons;
import itgarden.model.homevisit.Relations;
import itgarden.model.homevisit.Religion;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author User
 */
public interface AllMotherInterface {

    public Long getId();

    public String getVisitOfficersName();

    public LocalDate getDateReferral();

    public String getReferredFrom();

    public Reasons getResons();

    public LocalDate getHomeVisitDate();

    public String getMotherMasterCode();

    public String getMotherName();

    public LocalDate getDateOfBirth();

    public String getAge();

    public String getmMothersName();

    public String getFathersName();

    public String getMobileNumber();

    public Religion getReligion();

    public MaritalStatus getMaritalStatus();

    public String getHusbandsName();

    public HusbandsStatus getHusbandsStatus();

    public String getPrimeFamilyMemberName();

    public Relations getRelationWithPfm();

    public EthinicIdentity getEthnicIdentity();

    public EducationLevel getEducationLevel();

    public EducationType getEducationType();

    public Occupation getOccupation();

    public String getPhysicalStatus();

    public String getImmunization();

    public int getNumberOfSons();

    public int getNumberOfDaughters();

    public int getNumberOfEligibleChildren();

    public String getMajorFindings();

    public String getOtherRemarks();

    public Eligibility getEligibility();

    public LocalDate getCreated();

    public Users getCreatedBy();

    public Date getUpdated();

    public Users getUpdatedBy();

}
