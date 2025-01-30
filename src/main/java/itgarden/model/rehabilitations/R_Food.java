/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itgarden.model.rehabilitations;

import itgarden.model.auth.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author libertyerp_local
 */
@Entity
public class R_Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "*Date field cannot be blank")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate todaydate;

     @NotNull(message = "*House field cannot be blank")
    @Enumerated(EnumType.STRING)
    private FoodByHouse foodByHouse;

    private int totalPersons;
    private int sick;
    private int onLeave;
    private int underObservation;
    private int onJob;
    private int others;
    private int totalPresent;
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

    public R_Food() {
    }

    public R_Food(Long id, LocalDate todaydate, FoodByHouse foodByHouse, int totalPersons, int sick, int onLeave, int underObservation, int onJob, int others, int totalPresent, Users createdBy, Users updatedBy) {
        this.id = id;
        this.todaydate = todaydate;
        this.foodByHouse = foodByHouse;
        this.totalPersons = totalPersons;
        this.sick = sick;
        this.onLeave = onLeave;
        this.underObservation = underObservation;
        this.onJob = onJob;
        this.others = others;
        this.totalPresent = totalPresent;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(LocalDate todaydate) {
        this.todaydate = todaydate;
    }

    public FoodByHouse getFoodByHouse() {
        return foodByHouse;
    }

    public void setFoodByHouse(FoodByHouse foodByHouse) {
        this.foodByHouse = foodByHouse;
    }

    public int getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public int getSick() {
        return sick;
    }

    public void setSick(int sick) {
        this.sick = sick;
    }

    public int getOnLeave() {
        return onLeave;
    }

    public void setOnLeave(int onLeave) {
        this.onLeave = onLeave;
    }

    public int getUnderObservation() {
        return underObservation;
    }

    public void setUnderObservation(int underObservation) {
        this.underObservation = underObservation;
    }

    public int getOnJob() {
        return onJob;
    }

    public void setOnJob(int onJob) {
        this.onJob = onJob;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(int totalPresent) {
        this.totalPresent = totalPresent;
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
