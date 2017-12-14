package com.sakksoftware.web.bbrestapioauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    //@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        User user = (User) users.username("user").password("password").roles("USER").build();
        User admin = (User) users.username("admin").password("password").authorities("ROLE_ADMIN").build();

        authenticationManagerBuilder.inMemoryAuthentication() // creating in memory
            .withUser(user).withUser(admin);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin().disable() // disable form authentication
                .anonymous().disable() // disable from anonymous user
                .authorizeRequests().anyRequest().denyAll(); // denying all access
    }

}
