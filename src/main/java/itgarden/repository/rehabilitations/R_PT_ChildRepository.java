/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package itgarden.repository.rehabilitations;

import itgarden.model.homevisit.MotherMasterData;
import itgarden.model.rehabilitations.R_PT_Child;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author libertyerp_local
 */
public interface R_PT_ChildRepository extends JpaRepository<R_PT_Child, Long> {
    List<R_PT_Child> findBymotherMasterCode(MotherMasterData motherMasterData);
}
