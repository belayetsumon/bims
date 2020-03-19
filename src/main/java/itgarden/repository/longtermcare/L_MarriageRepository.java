/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.longtermcare;

import itgarden.model.homevisit.M_Child_info;
import itgarden.model.longtermcare.L_Job;
import itgarden.model.longtermcare.L_Marriage;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface L_MarriageRepository extends JpaRepository<L_Marriage, Long> {

    List<L_Marriage> findBychildMasterCode(M_Child_info m_Child_info);

    List<L_Marriage> findByCreatedBetween(LocalDate fromdate, LocalDate todate);
}
