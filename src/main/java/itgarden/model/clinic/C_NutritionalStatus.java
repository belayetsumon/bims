/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.clinic;

import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
public class C_NutritionalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotEmpty(message = "Name cannot be blank.")
    public String name;
    

    
     @Column(nullable = false)
    @NotNull(message = "Admission cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate admissionDate;
    
    
    @Column(nullable = false)
    @NotNull(message = "Entry cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate entryDate;

    @Column(nullable = false)
    @NotNull(message = "Date cannot be blank.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    public Double height;
    @NotNull(message = "Weight cannot be blank.")

    public Double weight;

    @NotEmpty(message = "BMI cannot be blank.")
    public String bmi;

	public C_NutritionalStatus() {
		
	}

    public C_NutritionalStatus(Long id, MotherMasterData motherMasterCode, String name, LocalDate admissionDate, LocalDate entryDate, LocalDate dob, Double height, Double weight, String bmi) {
        this.id = id;
        this.motherMasterCode = motherMasterCode;
        this.name = name;
        this.admissionDate = admissionDate;
        this.entryDate = entryDate;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

	
}
	    