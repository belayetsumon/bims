/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.model.auth;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be blank.")
    public String name;

    @NotEmpty(message = "Slug cannot be blank.")
    public String Slug;

    @NotNull(message = "Please select minimum 1 privilege.")
    @ElementCollection(targetClass = Privilege.class)
    @CollectionTable(name = "tbl_privilege_role",
            joinColumns = @JoinColumn(name = "privilege_id"))
    @Enumerated(EnumType.STRING)
    public Set<Privilege> privilege;

    @ManyToMany(mappedBy = "role")
    private Set<Users> users;

    public Role() {
    }

    public Role(Long id, String name, String Slug, Set<Privilege> privilege, Set<Users> users) {
        this.id = id;
        this.name = name;
        this.Slug = Slug;
        this.privilege = privilege;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String Slug) {
        this.Slug = Slug;
    }

    public Set<Privilege> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Set<Privilege> privilege) {
        this.privilege = privilege;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

}
