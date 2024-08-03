/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package itgarden.repository.impactmeasurement;

import itgarden.model.impactmeasurement.ShortTermImpactMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author libertyerp_local
 */
public interface ShortTermImpactMeasurementRepository extends JpaRepository<ShortTermImpactMeasurement, Long> {
    
}
