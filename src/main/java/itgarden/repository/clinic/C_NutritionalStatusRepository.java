/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.clinic;

import itgarden.model.clinic.C_NutritionalStatus;

import itgarden.model.homevisit.MotherMasterData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface C_NutritionalStatusRepository extends JpaRepository<C_NutritionalStatus, Long> {
	
	List<C_NutritionalStatus> findBymotherMasterCode(MotherMasterData motherMasterData);
    
}
