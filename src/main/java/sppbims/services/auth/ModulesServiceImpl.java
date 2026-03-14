/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import sppbims.model.auth.Modules;
import sppbims.repository.auth.ModulesRepository;
import sppbims.repository.auth.SubModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import sppbims.exception.DuplicateResourceException;
import sppbims.exception.ResourceNotFoundException;

/**
 *
 * @author libertyerp_local
 */
@Service
public class ModulesServiceImpl implements ModulesService {

    @Autowired
    private ModulesRepository repo;
    @Autowired
    private SubModuleRepository subModuleRepository;
    @Autowired
    private SlugService slugService;

    @Override
    public List<Modules> findAll() {
        return repo.findAll();
    }

    @Override
    public Modules findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Modules save(Modules modules) {
        return repo.save(modules);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    public Modules saveModule(Modules module) {

        boolean isNew = (module.getId() == null);

        if (isNew) {
            // 🔹 CREATE: generate unique slug from name
            String baseSlug = slugService.toSlug(module.getName());
            module.setSlug(generateUniqueSlug(baseSlug));

        } else {
            // 🔹 UPDATE
            Modules existing = repo.findById(module.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Module not found"));

            if (module.getSlug() == null
                    || module.getSlug().isBlank()
                    || module.getSlug().equals(existing.getSlug())) {

                // Keep existing slug if blank or unchanged
                module.setSlug(existing.getSlug());

            } else {
                // User changed slug manually → normalize & ensure uniqueness
                String normalized = slugService.toSlug(module.getSlug());

                Optional<Modules> m = repo.findBySlug(normalized);
                boolean exists = m.isPresent() && !m.get().getId().equals(module.getId());

                if (exists) {
                    throw new DuplicateResourceException("Slug already exists");
                }

                module.setSlug(normalized);
            }
        }

        try {
            return repo.save(module);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("Slug already exists");
        }
    }

    /**
     * Generates a unique slug by appending -1, -2, etc. if necessary.
     */
    private String generateUniqueSlug(String baseSlug) {
        String slug = baseSlug;
        int counter = 1;
        while (repo.findBySlug(slug).isPresent()) {
            slug = baseSlug + "-" + counter++;
        }
        return slug;
    }

    @Transactional
    public void deleteModule(Long moduleId) {
        long childCount = subModuleRepository.countByModules_Id(moduleId);
        if (childCount > 0) {
            throw new IllegalStateException("Cannot delete module: it has " + childCount + " submodule(s).");
        }
        // optionally double-check by loading then deleting:
        Modules m = repo.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found"));
        repo.delete(m);
    }
}
