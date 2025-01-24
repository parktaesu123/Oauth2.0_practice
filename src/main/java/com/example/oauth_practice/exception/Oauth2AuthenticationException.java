package com.example.oauth_practice.exception;

import org.springframework.security.core.AuthenticationException;

public class Oauth2AuthenticationException extends AuthenticationException {
    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }

}
