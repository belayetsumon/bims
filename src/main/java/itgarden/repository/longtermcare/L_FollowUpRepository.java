/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.longtermcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.FollowUpType;
import itgarden.model.longtermcare.L_FollowUp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface L_FollowUpRepository extends JpaRepository<L_FollowUp, Long> {
   List<L_FollowUp> findByChildMasterCodeAndFollowUpType(M_Child_info m_Child_info,FollowUpType followUpType);
   
      List<L_FollowUp> findByCreatedBetween(LocalDate fromdate, LocalDate todate);
  }
