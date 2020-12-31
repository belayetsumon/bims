/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.clinic;

import itgarden.model.homevisit.MotherMasterData;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
public class C_NutritionalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;

    @NotEmpty(message = "Name cannot be blank.")
    public String name;
    
    @NotNull(message = "Admission cannot be blank.")
    public String admissionDate;
    
    @NotEmpty(message = "Date cannot be blank.")
    public String entryDate;
    
    @NotEmpty(message = "Date of birth cannot be blank.")
    public String dob;
    @NotNull(message = "Height cannot be blank.")

    public Double height;
    @NotNull(message = "Weight cannot be blank.")

    public Double weight;

    @NotEmpty(message = "BMI cannot be blank.")
    public String bmi;

	public C_NutritionalStatus() {
		
	}

	public C_NutritionalStatus(Long id, MotherMasterData motherMasterCode, String name, String admissionDate,
			String entryDate, String dob, Double height, Double weight, String bmi) {
		super();
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

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
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
	    