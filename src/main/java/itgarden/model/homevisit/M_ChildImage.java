/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import itgarden.model.auth.Users;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class M_ChildImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
  @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public M_Child_info motherMasterCode;
  
  @NotNull
    @Size(min = 1, max = 200, message = "Name must between 1 and 200 characters")
    String child_image;
          
          
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

    public M_ChildImage() {
    }

    public M_ChildImage(Long id, M_Child_info motherMasterCode, String child_image, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.child_image = child_image;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public M_Child_info getMotherMasterCode() {
        return motherMasterCode;
    }

    public void setMotherMasterCode(M_Child_info motherMasterCode) {
        this.motherMasterCode = motherMasterCode;
    }

    public String getChild_image() {
        return child_image;
    }

    public void setChild_image(String child_image) {
        this.child_image = child_image;
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

  
    /** audit end**********************/
    
    
   
}
