/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.rehabilitations;

import itgarden.model.rehabilitations.SessionType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface SessionTypeRepository extends JpaRepository<SessionType, Long> {
    
}
