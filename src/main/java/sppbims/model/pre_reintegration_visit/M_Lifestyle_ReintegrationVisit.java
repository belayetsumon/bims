/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.pre_reintegration_visit;

import sppbims.model.homevisit.Leisure_facilities;
import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.homevisit.Cooking_Fuel_Type;
import sppbims.model.homevisit.Water_Source;
import sppbims.model.homevisit.Light_Source_type;
import sppbims.model.homevisit.Toilet_Type;
import sppbims.model.homevisit.Personal_Habits;
import sppbims.model.homevisit.Drinking_Water_Source;
import sppbims.model.auth.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
@Table(name = "RE_M_LIFESTYLE")
public class M_Lifestyle_ReintegrationVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "mother_master_code_id", nullable = false)
    public MotherMasterData motherMasterCode;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Water_Source waterSource;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Drinking_Water_Source drinkingWaterSource;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Toilet_Type toilet_type;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Cooking_Fuel_Type cookingFuel;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Light_Source_type lightSource;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Leisure_facilities leisurFacilities;

    @Lob
    public String leisureEngagment;

    @NotNull(message = "This field cannot be blank.")
    @ManyToOne(optional = false)
    public Personal_Habits personalHabits;

    @Lob
    public String craftsManShip;

    @Lob
    public String remarks;

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

    public M_Lifestyle_ReintegrationVisit() {
    }

    public M_Lifestyle_ReintegrationVisit(Long id, MotherMasterData motherMasterCode, Water_Source waterSource, Drinking_Water_Source drinkingWaterSource, Toilet_Type toilet_type, Cooking_Fuel_Type cookingFuel, Light_Source_type lightSource, Leisure_facilities leisurFacilities, String leisureEngagment, Personal_Habits personalHabits, String craftsManShip, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.waterSource = waterSource;
        this.drinkingWaterSource = drinkingWaterSource;
        this.toilet_type = toilet_type;
        this.cookingFuel = cookingFuel;
        this.lightSource = lightSource;
        this.leisurFacilities = leisurFacilities;
        this.leisureEngagment = leisureEngagment;
        this.personalHabits = personalHabits;
        this.craftsManShip = craftsManShip;
        this.remarks = remarks;
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

    public Water_Source getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(Water_Source waterSource) {
        this.waterSource = waterSource;
    }

    public Drinking_Water_Source getDrinkingWaterSource() {
        return drinkingWaterSource;
    }

    public void setDrinkingWaterSource(Drinking_Water_Source drinkingWaterSource) {
        this.drinkingWaterSource = drinkingWaterSource;
    }

    public Toilet_Type getToilet_type() {
        return toilet_type;
    }

    public void setToilet_type(Toilet_Type toilet_type) {
        this.toilet_type = toilet_type;
    }

    public Cooking_Fuel_Type getCookingFuel() {
        return cookingFuel;
    }

    public void setCookingFuel(Cooking_Fuel_Type cookingFuel) {
        this.cookingFuel = cookingFuel;
    }

    public Light_Source_type getLightSource() {
        return lightSource;
    }

    public void setLightSource(Light_Source_type lightSource) {
        this.lightSource = lightSource;
    }

    public Leisure_facilities getLeisurFacilities() {
        return leisurFacilities;
    }

    public void setLeisurFacilities(Leisure_facilities leisurFacilities) {
        this.leisurFacilities = leisurFacilities;
    }

    public String getLeisureEngagment() {
        return leisureEngagment;
    }

    public void setLeisureEngagment(String leisureEngagment) {
        this.leisureEngagment = leisureEngagment;
    }

    public Personal_Habits getPersonalHabits() {
        return personalHabits;
    }

    public void setPersonalHabits(Personal_Habits personalHabits) {
        this.personalHabits = personalHabits;
    }

    public String getCraftsManShip() {
        return craftsManShip;
    }

    public void setCraftsManShip(String craftsManShip) {
        this.craftsManShip = craftsManShip;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
