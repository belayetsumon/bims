/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.follow_up_report;

import sppbims.model.follow_up_report.ChildCrisisMeetUp;
import sppbims.model.homevisit.M_Child_info;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ChildCrisisMeetUpRepository extends JpaRepository<ChildCrisisMeetUp, Long> {

    ChildCrisisMeetUp findBychildMasterCode(M_Child_info m_Child_info);
}
