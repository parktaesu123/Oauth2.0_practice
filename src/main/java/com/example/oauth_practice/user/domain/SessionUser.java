package com.example.oauth_practice.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUser implements Serializable {
    private String email;
    private String name;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
