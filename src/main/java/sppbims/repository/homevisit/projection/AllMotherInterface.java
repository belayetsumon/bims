/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit.projection;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.EducationLevel;
import sppbims.model.homevisit.EducationType;
import sppbims.model.homevisit.Eligibility;
import sppbims.model.homevisit.EthinicIdentity;
import sppbims.model.homevisit.HusbandsStatus;
import sppbims.model.homevisit.MaritalStatus;
import sppbims.model.homevisit.Occupation;
import sppbims.model.homevisit.Reasons;
import sppbims.model.homevisit.Relations;
import sppbims.model.homevisit.Religion;
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
