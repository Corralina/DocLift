package com.company.service;




import com.company.domian.Role;
import com.company.domian.User;
import com.company.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    public UserService() {
    }

    public UserService(UserRepo repository) {
        this.userRepo = repository;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByUsername(username);
    }

    public User findByUsername(String username){
        return userRepo.findFirstByUsername(username);
    }

    public Integer CountByUsername(String name){return userRepo.countUsersByUsername(name);}


    public Iterable<User> allUser(){
        return userRepo.findAll();
    }



    public void userSave(User user){
        userRepo.save(user);
    }

    public Iterable<User> userConfirms(Set<Role> roles){
        return userRepo.findAllByRolesIsIn(roles);
    }

}