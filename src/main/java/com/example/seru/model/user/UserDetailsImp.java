package com.example.seru.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImp implements UserDetails {

    private String username;
    private String password;
    private Boolean authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.authorities){
            return Arrays.stream(new String[]{"ADMIN"}).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }else{
            return Arrays.stream(new String[]{"USER"}).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
    }

    public UserDetailsImp(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getIs_admin();
    }

    @Override
    public String getPassword() {
        return password;
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
}
