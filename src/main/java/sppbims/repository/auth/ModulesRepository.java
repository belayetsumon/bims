/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package sppbims.repository.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sppbims.model.auth.Modules;

/**
 *
 * @author libertyerp_local
 */
public interface ModulesRepository extends JpaRepository<Modules, Long> {

    boolean existsBySlug(String slug);

    Optional<Modules> findBySlug(String slug);

}
