/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package sppbims.repository.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sppbims.model.auth.SubModules;

/**
 *
 * @author libertyerp_local
 */
public interface SubModuleRepository extends JpaRepository<SubModules, Long> {

    long countByModules_Id(Long moduleId);

    boolean existsByModules_Id(Long moduleId);

    List<SubModules> findByModules_Id(Long moduleId);

    Optional<SubModules> findBySlug(String slug);

    boolean existsBySlug(String slug);

}
