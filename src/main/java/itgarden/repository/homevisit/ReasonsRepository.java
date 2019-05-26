/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.homevisit;

import itgarden.model.homevisit.Reasons;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface ReasonsRepository extends CrudRepository<Reasons, Long> {
    
}
