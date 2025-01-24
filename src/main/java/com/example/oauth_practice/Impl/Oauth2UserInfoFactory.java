package com.example.oauth_practice.Impl;

import com.example.oauth_practice.exception.Oauth2AuthenticationException;
import com.example.oauth_practice.user.domain.enums.ProviderType;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

public class Oauth2UserInfoFactory {

    public static Oauth2UserInfo getOauth2UserInfo(String registrationId,
                                                   String accessToken, Map<String, Object> attributes) {
        if (ProviderType.Google.getRegistrationId().equals(registrationId)) {
            return new GoogleOAuth2UserInfo(accessToken, attributes);
        } else {
            throw new Oauth2AuthenticationException("Oauth Authentication Failed");
        }
    }
}
