/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit.DTO;

/**
 *
 * @author User
 */
public class ApproveMotherListDTO {

    public Long id;
    public String motherMasterCodeId;
    public String motherMasterCode;
    public String motherName;
    public String approveDate;
    public String approveBy;
    public String numberOfEligibleChildren;

    public ApproveMotherListDTO() {
    }

    public ApproveMotherListDTO(Long id, String motherMasterCodeId, String motherMasterCode, String motherName, String approveDate, String approveBy, String numberOfEligibleChildren) {
        this.id = id;
        this.motherMasterCodeId = motherMasterCodeId;
        this.motherMasterCode = motherMasterCode;
        this.motherName = motherName;
        this.approveDate = approveDate;
        this.approveBy = approveBy;
        this.numberOfEligibleChildren = numberOfEligibleChildren;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotherMasterCodeId() {
        return motherMasterCodeId;
    }

    public void setMotherMasterCodeId(String motherMasterCodeId) {
        this.motherMasterCodeId = motherMasterCodeId;
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

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public String getNumberOfEligibleChildren() {
        return numberOfEligibleChildren;
    }

    public void setNumberOfEligibleChildren(String numberOfEligibleChildren) {
        this.numberOfEligibleChildren = numberOfEligibleChildren;
    }
    
    
    

}
