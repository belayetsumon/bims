/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package sppbims.services.auth;

import java.util.function.Function;
import org.springframework.stereotype.Service;

/**
 *
 * @author libertyerp_local
 */
@Service
public class SlugService {

    public String toSlug(String name) {
        if (name == null) {
            return "";
        }
        String slug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-+", "-");
        if (slug.length() > 120) {
            slug = slug.substring(0, 120);
        }
        return slug;
    }

    public String generateUniqueSlug(String baseName, Function<String, Boolean> existsBySlug) {

        String base = toSlug(baseName);
        if (base.isEmpty()) {
            base = "item";
        }

        String candidate = base;
        int counter = 0;

        while (existsBySlug.apply(candidate)) {
            counter++;
            candidate = base + "-" + counter;

            if (counter > 1000) {
                throw new IllegalStateException("Unable to generate unique slug");
            }
        }

        return candidate;
    }
}
