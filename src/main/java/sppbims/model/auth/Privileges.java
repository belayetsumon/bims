/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sppbims.model.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author libertyerp_local
 */
@Entity
@Table(name = "usermodule_privileges")
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_module_id", nullable = false)
    @NotNull(message = "*Sub module cannot be null.")
    private SubModules subModules;

    @NotEmpty(message = "*Name cannot be blank.")
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "*Slug cannot be blank.")
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_\\- ]+$")
    @Column(nullable = false, unique = true)
    private String slug;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
    private Set<Role> role = new HashSet<>();

    public Privileges() {
    }

    public Privileges(Long id, Long version, SubModules subModules, String name, String slug) {
        this.id = id;
        this.version = version;
        this.subModules = subModules;
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

    public SubModules getSubModules() {
        return subModules;
    }

    public void setSubModules(SubModules subModules) {
        this.subModules = subModules;
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

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

}
