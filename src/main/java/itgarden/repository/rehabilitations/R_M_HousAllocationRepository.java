/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_M_HousAllocation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface R_M_HousAllocationRepository extends JpaRepository<R_M_HousAllocation, Long> {
    List<R_M_HousAllocation> findBymotherMasterCode(MotherMasterData motherMasterData  );
}
