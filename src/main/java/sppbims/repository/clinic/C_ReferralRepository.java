/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.clinic;

import sppbims.model.clinic.C_Referral;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface C_ReferralRepository extends JpaRepository<C_Referral, Long> {

}
