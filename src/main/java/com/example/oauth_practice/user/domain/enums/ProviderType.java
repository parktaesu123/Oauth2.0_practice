package com.example.oauth_practice.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderType {
    LOCAL,
    GOOGLE

    private final String registrationId;

}
