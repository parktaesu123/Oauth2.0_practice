package com.example.oauth_practice.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderType {

    Local("local"),
    Google("google");

    private final String registrationId;

}
