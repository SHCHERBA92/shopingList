package com.example.shopinglist.auth_model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleOfUser implements GrantedAuthority {
    USER,
    ADMIN;

    RoleOfUser() {
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
