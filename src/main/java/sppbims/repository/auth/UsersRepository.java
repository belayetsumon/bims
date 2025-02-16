/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sppbims.repository.auth;

import sppbims.model.auth.Users;
import sppbims.model.homevisit.Yes_No;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Md Belayet Hossin
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    Users findByEmailAndActive(String email, Yes_No yesno);

}
