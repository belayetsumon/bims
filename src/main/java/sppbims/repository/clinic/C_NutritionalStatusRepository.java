/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.clinic;

import sppbims.model.clinic.C_NutritionalStatus;

import sppbims.model.homevisit.MotherMasterData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface C_NutritionalStatusRepository extends JpaRepository<C_NutritionalStatus, Long> {

    List<C_NutritionalStatus> findBymotherMasterCode(MotherMasterData motherMasterData);

}
