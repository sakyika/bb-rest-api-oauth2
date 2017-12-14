package com.sakksoftware.web.bbrestapioauth2.model;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Account implements UserDetails {

    @Id
    private String id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String password;

    private Date lastpasswordResetDate;

    private List<Role> roles;

    private List<GrantedAuthority> grantedAuthorityList;

    public Account() {}


    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void grantAuthority(Role authority) {
        if ( roles == null ) roles = new ArrayList<>();
        roles.add(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return getUsername();
    }

    public String getFullName() {
        return (getFirstname() + " " + getLastname());
    }
}
