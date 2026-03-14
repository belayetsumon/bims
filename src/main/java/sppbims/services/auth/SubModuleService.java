/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sppbims.services.auth;

import java.util.List;
import sppbims.model.auth.SubModules;

/**
 *
 * @author libertyerp_local
 */
public interface SubModuleService {

    List<SubModules> findAll();

    SubModules findById(Long id);

    SubModules save(SubModules subModule);

    SubModules update(Long id, SubModules updated);

    void delete(Long id);
}
