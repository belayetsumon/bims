/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package itgarden.repository.clinic;

import itgarden.model.clinic.HealthAwareness;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author libertyerp_local
 */
public interface HealthAwarenessRepository extends JpaRepository<HealthAwareness, Long> {
    
}
