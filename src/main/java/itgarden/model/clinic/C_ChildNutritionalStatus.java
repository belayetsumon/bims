package itgarden.model.clinic;


import org.hibernate.validator.constraints.NotEmpty;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.homevisit.MotherMasterData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class C_ChildNutritionalStatus {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public MotherMasterData motherMasterCode;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public M_Child_info childMasterCode;

          
    @NotEmpty(message = "Date cannot be blank.")
    public String entryDate;
    
    public Double height;
    
    
    @NotNull(message = "Weight cannot be blank.")
    public Double weight;

    @NotEmpty(message = "BMI cannot be blank.")
    public String bmi;

	public C_ChildNutritionalStatus() {
		super();
	}

	public C_ChildNutritionalStatus(Long id, MotherMasterData motherMasterCode, M_Child_info childMasterCode,
			String entryDate, Double height, Double weight, String bmi) {
		super();
		this.id = id;
		this.motherMasterCode = motherMasterCode;
		this.childMasterCode = childMasterCode;
		this.entryDate = entryDate;
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

	public M_Child_info getChildMasterCode() {
		return childMasterCode;
	}

	public void setChildMasterCode(M_Child_info childMasterCode) {
		this.childMasterCode = childMasterCode;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
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
