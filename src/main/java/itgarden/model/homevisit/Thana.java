/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class Thana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
     @NotNull(message = "Please select district")
    @Enumerated(EnumType.ORDINAL)
    public District district;
    @NotNull(message = "Please Enter Name!")
    @Size(min = 2)
    public String name;

    public Thana() {
    }

    public Thana(Long id, District district, String name) {
        this.id = id;
        this.district = district;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    }
