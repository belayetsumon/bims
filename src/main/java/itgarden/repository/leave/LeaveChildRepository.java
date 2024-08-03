/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package itgarden.repository.leave;

import itgarden.model.leave.LeaveChild;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author libertyerp_local
 */
public interface LeaveChildRepository extends JpaRepository<LeaveChild, Long> {
    
}
