/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.model.homevisit;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Md Belayet Hossin
 */
@Entity
public class VisitsOfficersName {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
