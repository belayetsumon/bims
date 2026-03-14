/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sppbims.model.auth;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author libertyerp_local
 */
@Entity
@Table(name = "usermodule_sub_module")
public class SubModules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    @NotNull(message = "*Module cannot be null.")
    private Modules modules;

    @NotEmpty(message = "*Name cannot be blank.")
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false, unique = true)
    private String slug;

    // OPTIONAL: For privilege relationship
    @OneToMany(mappedBy = "subModules", cascade = CascadeType.ALL)
    private List<Privileges> privileges = new ArrayList<>();

    public SubModules(Long id, Long version, Modules modules, String name, String slug) {
        this.id = id;
        this.version = version;
        this.modules = modules;
        this.name = name;
        this.slug = slug;
    }

    public SubModules() {
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

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
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

    public List<Privileges> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privileges> privileges) {
        this.privileges = privileges;
    }

}
