package com.daovantam.quanlydaotao.security;

import com.daovantam.quanlydaotao.security.jwt.model.JwtPayload;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    /*
    *GrantedAuthority: là role(quyền) được đi vào API nào đó
    **/
    private HashSet<GrantedAuthority> authorities = new HashSet<>();
    private JwtPayload user;

    UserDetails(){
        //USER là quyền được mặc định đi vào
        this.authorities.add(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return this.user.getFullName();
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

    public JwtPayload getUser(){
        return this.user;
    }

    void setUser(JwtPayload user){
        this.user = user;
        String[] roles = user.getRole().split(",");
        for (String role : roles){
            this.authorities.add(new SimpleGrantedAuthority(role));
        }
    }
}
