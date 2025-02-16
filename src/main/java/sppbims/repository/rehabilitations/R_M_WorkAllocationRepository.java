/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.rehabilitations;

import sppbims.model.homevisit.MotherMasterData;
import sppbims.model.rehabilitations.R_M_WorkAllocation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface R_M_WorkAllocationRepository extends JpaRepository<R_M_WorkAllocation, Long> {

    List<R_M_WorkAllocation> findBymotherMasterCode(MotherMasterData motherMasterData);
}
