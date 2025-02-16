/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.homevisit;

import sppbims.model.homevisit.M_Child_Address;
import sppbims.model.homevisit.M_Child_info;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface M_Child_AddressRepository extends JpaRepository<M_Child_Address, Long> {

    List<M_Child_Address> findBychildMasterCode(M_Child_info childMasterCode);

}
