/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.services;

import itgarden.model.auth.Privilege;
import itgarden.model.auth.Role;
import itgarden.model.auth.Users;
import itgarden.model.homevisit.Yes_No;
import itgarden.repository.auth.UsersRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Md Belayet Hossin
 */
@Service
public class UserDetails implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    @Transactional(readOnly = true)
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmailAndActive(username,Yes_No.Yes);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        
        for (Role role : user.getRole()) {
            
            for( Privilege privilegelist : role.getPrivilege())
            {
            grantedAuthorities.add(new SimpleGrantedAuthority( privilegelist.name()));
            }
            
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}
