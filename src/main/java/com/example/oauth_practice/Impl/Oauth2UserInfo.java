package com.example.oauth_practice.Impl;

import com.example.oauth_practice.user.domain.enums.ProviderType;

import java.util.Map;

public interface Oauth2UserInfo {
    ProviderType getProvider();

    String getAccessToken();

    Map<String, Object> getAttributes();

    String getId();

    String getEmail();

    String getName();

    String getProfileImageUrl();
}
