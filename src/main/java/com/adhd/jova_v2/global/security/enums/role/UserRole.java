package com.adhd.jova_v2.global.security.enums.role;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.Set;

public enum UserRole implements Authentication, GrantedAuthority {
    ADMIN {
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
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            // Do nothing
        }

        @Override
        public String getName() {
            return "ADMIN";
        }
    },
    DEVELOPER {
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
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            // Do nothing
        }

        @Override
        public String getName() {
            return "DEVELOPER";
        }
    },
    USER {
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
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            // Do nothing
        }

        @Override
        public String getName() {
            return "USER";
        }
    };

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}