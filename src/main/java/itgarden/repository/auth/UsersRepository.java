/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.repository.auth;

import itgarden.model.auth.Users;
import itgarden.model.homevisit.Yes_No;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Md Belayet Hossin
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Users findByEmail(String email);
    Users findByEmailAndActive(String email,Yes_No yesno);
    
}
