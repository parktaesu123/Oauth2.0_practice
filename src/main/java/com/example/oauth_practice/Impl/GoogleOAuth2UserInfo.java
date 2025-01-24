package com.example.oauth_practice.Impl;

import com.example.oauth_practice.user.domain.enums.ProviderType;

import java.util.Map;

public class GoogleOAuth2UserInfo implements Oauth2UserInfo {
    private final Map<String, Object> attributes;
    private final String accessToken;
    private final String id;
    private final String email;
    private final String name;
    private final String profileImageUrl;

    public GoogleOAuth2UserInfo(String accessToken, Map<String, Object> attributes) {
        this.accessToken = accessToken;
        this.attributes = attributes;
        this.id = (String) attributes.get("id");
        this.email = (String) attributes.get("email");
        this.name = (String) attributes.get("name");
        this.profileImageUrl = (String) attributes.get("picture");
    }


    @Override
    public ProviderType getProvider() {
        return ProviderType.Google;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
