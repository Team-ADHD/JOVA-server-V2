package com.adhd.jova_v2.global.security.enums.role;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.Set;

public enum UserRole implements Authentication, GrantedAuthority {
    ADMIN, DEVELOPER, USER;

    private boolean authenticated = false;

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return Collections.singleton(this);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}