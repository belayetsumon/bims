/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.reintegration_release;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class ReleaseMotherDTO {

    private Long id;
    private String motherMasterCode;
    private String motherName;
    private Long motherMastrCodeID;
    private LocalDate releaseDate;
    private String Address;
    private String postDischargeVisitDate;
    private String remark;

    public ReleaseMotherDTO() {

    }

    public ReleaseMotherDTO(Long id, String motherMasterCode, String motherName, Long motherMastrCodeID, LocalDate releaseDate, String Address, String postDischargeVisitDate, String remark) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.motherName = motherName;
        this.motherMastrCodeID = motherMastrCodeID;
        this.releaseDate = releaseDate;
        this.Address = Address;
        this.postDischargeVisitDate = postDischargeVisitDate;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotherMasterCode() {
        return motherMasterCode;
    }

    public void setMotherMasterCode(String motherMasterCode) {
        this.motherMasterCode = motherMasterCode;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Long getMotherMastrCodeID() {
        return motherMastrCodeID;
    }

    public void setMotherMastrCodeID(Long motherMastrCodeID) {
        this.motherMastrCodeID = motherMastrCodeID;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPostDischargeVisitDate() {
        return postDischargeVisitDate;
    }

    public void setPostDischargeVisitDate(String postDischargeVisitDate) {
        this.postDischargeVisitDate = postDischargeVisitDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
