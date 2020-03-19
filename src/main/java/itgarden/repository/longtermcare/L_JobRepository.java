/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.longtermcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.L_HigherStudy;
import itgarden.model.longtermcare.L_Job;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface L_JobRepository extends JpaRepository<L_Job, Long> {
    List<L_Job> findBychildMasterCode(M_Child_info m_Child_info);
    
    List<L_Job> findByCreatedBetween(LocalDate fromdate, LocalDate todate);
}
