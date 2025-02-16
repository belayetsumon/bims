/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package sppbims.repository.leave;

import sppbims.model.leave.LeaveMother;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author libertyerp_local
 */
public interface LeaveMotherRepository extends JpaRepository<LeaveMother, Long> {

    Long findTopByOrderByIdDesc();

}
