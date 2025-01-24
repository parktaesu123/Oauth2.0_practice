package com.example.oauth_practice.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class OAuth2AuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        OAuth2AuthenticationException oauth2Exception = (OAuth2AuthenticationException) exception;

        String errorMessage = oauth2Exception.getMessage();

        String redirectUrl = UriComponentsBuilder.fromUriString("/login")
                .queryParam("error", true)
                .queryParam("error_message", errorMessage)
                .toUriString();

        response.sendRedirect(redirectUrl);
    }
}
