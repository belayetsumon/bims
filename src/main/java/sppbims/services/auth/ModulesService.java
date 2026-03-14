/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sppbims.services.auth;

import java.util.List;
import sppbims.model.auth.Modules;

/**
 *
 * @author libertyerp_local
 */
public interface ModulesService {

    List<Modules> findAll();

    Modules findById(Long id);

    Modules save(Modules modules);

    void delete(Long id);
}
