/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.M_Address;
import sppbims.model.homevisit.MotherMasterData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_AddressRepository extends JpaRepository<M_Address, Long> {

    List<M_Address> findBymotherMasterCode(MotherMasterData motherMasterData);
}
