/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
@Entity
@Table(name = "usermodule_module")
public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @NotEmpty(message = "*Name cannot be blank.")
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String name;

//    @NotEmpty(message = "*Slug cannot be blank.")
//    @Size(min = 3, max = 50)
//    @Pattern(regexp = "^[a-zA-Z0-9_\\- ]+$")
    @Column(nullable = false, unique = true)
    private String slug;

    @OneToMany(mappedBy = "modules", orphanRemoval = true)
    private List<SubModules> subModules = new ArrayList<>();

    public Modules() {
    }

    public Modules(Long id, Long version, String name, String slug) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.slug = slug;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<SubModules> getSubModules() {
        return subModules;
    }

    public void setSubModules(List<SubModules> subModules) {
        this.subModules = subModules;
    }

}
