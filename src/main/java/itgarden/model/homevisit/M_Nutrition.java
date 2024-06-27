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
import jakarta.persistence.OneToOne;
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
@Table(name = "M_NUTRITION")
public class M_Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    @NotNull(message = "This field cannot be blank.")
     @OneToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @Enumerated(EnumType.ORDINAL)
    public Daily_Meal dailyMeal;

    @Enumerated(EnumType.ORDINAL)

    public Nutritions_trems  fish;

    @Enumerated(EnumType.ORDINAL)

    public Nutritions_trems meat;

    @Enumerated(EnumType.ORDINAL)

    public Nutritions_trems milk;

    @Enumerated(EnumType.ORDINAL)

    public Nutritions_trems fruits;
    @Lob
    public String  remarks;

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

    public M_Nutrition() {
    }

    public M_Nutrition(Long id, MotherMasterData motherMasterCode, Daily_Meal dailyMeal, Nutritions_trems fish, Nutritions_trems meat, Nutritions_trems milk, Nutritions_trems fruits, String remarks, Users createdBy, Users updatedBy) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.dailyMeal = dailyMeal;
        this.fish = fish;
        this.meat = meat;
        this.milk = milk;
        this.fruits = fruits;
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

    public Daily_Meal getDailyMeal() {
        return dailyMeal;
    }

    public void setDailyMeal(Daily_Meal dailyMeal) {
        this.dailyMeal = dailyMeal;
    }

    public Nutritions_trems getFish() {
        return fish;
    }

    public void setFish(Nutritions_trems fish) {
        this.fish = fish;
    }

    public Nutritions_trems getMeat() {
        return meat;
    }

    public void setMeat(Nutritions_trems meat) {
        this.meat = meat;
    }

    public Nutritions_trems getMilk() {
        return milk;
    }

    public void setMilk(Nutritions_trems milk) {
        this.milk = milk;
    }

    public Nutritions_trems getFruits() {
        return fruits;
    }

    public void setFruits(Nutritions_trems fruits) {
        this.fruits = fruits;
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

    /**
     * audit end*********************
     */
    
}
