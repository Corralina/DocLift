package com.company.configuration;

import com.company.domian.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public WebSecurityConfig() {
    }

    protected void configure(HttpSecurity http) throws Exception {
        ((
                HttpSecurity
                )((
                FormLoginConfigurer
                )((
                HttpSecurity
                )((
                ExpressionUrlAuthorizationConfigurer.AuthorizedUrl
                )((
                ExpressionUrlAuthorizationConfigurer.AuthorizedUrl
                )
                http
                        .authorizeRequests()
                        .antMatchers(new String[]{"/static/**"})).permitAll()
                        .anyRequest()).authenticated()
                        .and())
                        .formLogin()
                        .loginPage("/login")
                        .permitAll())
                        .and())
                        .logout()
                        .permitAll();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.
                    userDetailsService(this.userService)
                    .passwordEncoder(passwordEncoder);

    }

}

