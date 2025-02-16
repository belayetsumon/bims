/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.rehabilitations;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.rehabilitations.R_C_HouseAllocations;
import sppbims.model.rehabilitations.R_M_HousAllocation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface R_C_HouseAllocationsRepository extends JpaRepository<R_C_HouseAllocations, Long> {

    List<R_C_HouseAllocations> findBymotherMasterCode(MotherMasterData motherMasterData);

}
