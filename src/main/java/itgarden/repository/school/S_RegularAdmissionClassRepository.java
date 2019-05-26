/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.school;

import itgarden.model.school.S_RegularAdmissionClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface S_RegularAdmissionClassRepository extends JpaRepository<S_RegularAdmissionClass, Long> {
    
}
