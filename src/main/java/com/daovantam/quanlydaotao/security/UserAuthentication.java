package com.daovantam.quanlydaotao.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import javax.security.auth.Subject;
import java.util.Collection;

/*
* Để lấy thông tin của UserDetails khi parse
**/
public class UserAuthentication implements Authentication {
    private UserDetails user;

    public UserAuthentication(UserDetails user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this.user;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.user != null && !StringUtils.isEmpty(this.user.getUser().getId());
    }

    @Override
    public void setAuthenticated(boolean arg0) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

}
