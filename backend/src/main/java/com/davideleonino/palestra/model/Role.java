package com.davideleonino.palestra.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum Role {
    USER, ADMIN;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return switch (this) {
            case ADMIN -> List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case USER -> List.of(new SimpleGrantedAuthority("ROLE_USER"));
        };
    }
}