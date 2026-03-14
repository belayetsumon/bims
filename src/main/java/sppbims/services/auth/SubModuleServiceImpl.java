/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.auth;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sppbims.exception.DuplicateResourceException;
import sppbims.exception.ResourceNotFoundException;
import sppbims.model.auth.Modules;
import sppbims.model.auth.SubModules;
import sppbims.repository.auth.ModulesRepository;
import sppbims.repository.auth.PrivilegesRepository;
import sppbims.repository.auth.SubModuleRepository;

/**
 *
 * @author libertyerp_local
 */
@Service
public class SubModuleServiceImpl implements SubModuleService {

    @Autowired
    private ModulesRepository moduleRepo;
    @Autowired
    private SubModuleRepository repo;
    @Autowired
    private SlugService slugService;
    @Autowired
    PrivilegesRepository privilegesRepository;

    @Override
    public List<SubModules> findAll() {
        return repo.findAll();
    }

    @Override
    public SubModules findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SubModule not found."));
    }

    @Override
    public SubModules save(SubModules sm) {
        return saveOrUpdate(null, sm);
    }

    @Override
    public SubModules update(Long id, SubModules sm) {
        return saveOrUpdate(id, sm);
    }

    private SubModules saveOrUpdate(Long id, SubModules sm) {

        SubModules entity = (id == null)
                ? new SubModules()
                : repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Sub-module not found"));

        // 🔹 Generate slug ONLY if new or name changed
        if (id == null || !sm.getName().equals(entity.getName())) {

            String baseSlug = sm.getName();
            String slug = normalizeSlug(baseSlug);

            // 🔹 Ensure uniqueness
            int count = 1;
            String uniqueSlug = slug;

            while (repo.existsBySlug(uniqueSlug)) {
                uniqueSlug = slug + "-" + count++;
            }

            entity.setSlug(uniqueSlug);
        }

        entity.setName(sm.getName());
        entity.setModules(
                moduleRepo.findById(sm.getModules().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Module not found"))
        );

        return repo.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (privilegesRepository.existsBySubModules_Id(id)) {
            throw new IllegalStateException("Cannot delete. SubModule has assigned privileges.");
        }

        SubModules sm = findById(id);
        repo.delete(sm);
    }

    private void validateSlug(String slug, Long excludeId) {
        repo.findBySlug(slug.trim().toLowerCase().replace(" ", "-")).ifPresent(existing -> {
            if (excludeId == null || !existing.getId().equals(excludeId)) {
                throw new DuplicateResourceException("Slug already exists!");
            }
        });
    }

    private String normalizeSlug(String slug) {
        return slug.trim().toLowerCase().replace(" ", "-");
    }
}
