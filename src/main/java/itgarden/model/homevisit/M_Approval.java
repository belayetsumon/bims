/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import itgarden.model.auth.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */


//
//@NamedEntityGraph(
//        name = "approve_mother",
//        attributeNodes = {
//                @NamedAttributeNode("approveDate"),
//                @NamedAttributeNode("approveBy"),
//                @NamedAttributeNode("decission"),
//        }
//)


@NamedEntityGraph(
  name = "approve_mother",
        
  attributeNodes = {
    @NamedAttributeNode("approveDate"),
    @NamedAttributeNode("approveBy"),
    
    @NamedAttributeNode(value = "motherMasterCode", subgraph = "motherMasterCode"),
  },
  subgraphs = {
    @NamedSubgraph(
      name = "motherMasterCode-name",
      attributeNodes = {
        @NamedAttributeNode("motherName")
      }
    )
  }
)


@Entity
@Table(name = "M_APPROVAL")
public class M_Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(nullable =false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
     @Size(min = 1, max = 13, message = "Please select date.")
    public String  approveDate;

      @NotNull(message = "This field cannot be blank.")      
    public String  approveBy;
    
    @NotNull(message = "This field cannot be blank.")
    @Enumerated(EnumType.ORDINAL)
    public Decision decission;

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


    /** audit end**********************/

    public M_Approval() {
    }

    public M_Approval(Long id, MotherMasterData motherMasterCode, String approveDate, String approveBy, Decision decission, String REMARKS, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.approveDate = approveDate;
        this.approveBy = approveBy;
        this.decission = decission;
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

    public Decision getDecission() {
        return decission;
    }

    public void setDecission(Decision decission) {
        this.decission = decission;
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
