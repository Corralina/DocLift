package com.company.domian;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(
        name = "dbuser"
)
public class User implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @NotEmpty(message = "Логін користувача обовязково повинен бути заповненим")
    @Size(min = 1, max = 30, message = "Довжина поля повина складати від 1 до 30 символів")
    private String username;
    @NotEmpty(message = "Пароль користувача обовязково повинен бути заповненим")
    private String password;
    @ElementCollection(
            targetClass = Role.class,
            fetch = FetchType.EAGER
    )
    @CollectionTable(
            name = "userRole",
            joinColumns = {@JoinColumn(
                    name = "userId"
            )}
    )
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "information"
    )
    private Information information;




    public User() {
    }

    public User(Long id, String username, String password, Set<Role> roles, Information information) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.information = information;


    }

    public boolean isUserRole() {
        return this.roles.contains(Role.USER);
    }

    public boolean isRecorted() {
        return this.roles.contains(Role.RECORTED);
    }

    public boolean isAdmin() {
        return this.roles.contains(Role.ADMIN);
    }

    public boolean isTablin() {
        return this.roles.contains(Role.TABLIN);
    }

    public boolean isConfirm() {
        return this.roles.contains(Role.CONFIRM);
    }

    public boolean isDrop(){return this.roles.contains(Role.DROP);}

    public boolean isLoc(){return this.roles.contains(Role.LOC);}

    public boolean isResolve(){return this.roles.contains(Role.RESOLVE);}


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }


}
