/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sppbims.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Metamodel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class MetaService {

    @Autowired
    private EntityManager entityManager;

    public List<String> getAllEntityNames() {
        Metamodel metamodel = entityManager.getMetamodel();
        return metamodel.getEntities()
                .stream()
                .map(entityType -> entityType.getJavaType().getSimpleName())
                .toList();
    }
}
