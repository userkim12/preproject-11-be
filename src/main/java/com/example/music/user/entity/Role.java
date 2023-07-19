package com.example.music.user.entity;

import lombok.Getter;

public enum Role {
    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

    @Getter
    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

}
