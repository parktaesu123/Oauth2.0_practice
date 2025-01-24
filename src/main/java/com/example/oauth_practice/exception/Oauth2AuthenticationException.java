package com.example.oauth_practice.exception;

import org.springframework.security.core.AuthenticationException;

public class Oauth2AuthenticationException extends AuthenticationException {

    public Oauth2AuthenticationException(String msg) {
        super(msg, null);
    }

    public Oauth2AuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }


}
