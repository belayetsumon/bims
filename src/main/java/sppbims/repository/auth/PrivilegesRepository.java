/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package sppbims.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import sppbims.model.auth.Privileges;

/**
 *
 * @author libertyerp_local
 */
public interface PrivilegesRepository extends JpaRepository<Privileges, Long> {

    boolean existsBySubModules_Id(Long subModuleId);
}
