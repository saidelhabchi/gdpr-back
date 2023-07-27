package com.example.Application.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ADMIN";
    }
}
