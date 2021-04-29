package com.company.repository;



import com.company.domian.Role;
import com.company.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findFirstByUsername(String name);

    Iterable<User> findAllByUsername(String name);

    Iterable<User> findAllByRolesIsIn(Set<Role> roles);

    Integer countUsersByUsername(String name);


}
