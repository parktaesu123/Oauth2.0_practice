package com.example.oauth_practice.application;

import com.example.oauth_practice.Impl.Oauth2UserInfo;
import com.example.oauth_practice.Impl.Oauth2UserInfoFactory;
import com.example.oauth_practice.exception.Oauth2AuthenticationException;
import com.example.oauth_practice.user.domain.SessionUser;
import com.example.oauth_practice.user.domain.User;
import com.example.oauth_practice.user.domain.enums.ProviderType;
import com.example.oauth_practice.user.domain.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        try {
            return process(request, oAuth2User);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest request, OAuth2User oAuth2User) {
        ProviderType providerType = ProviderType.valueOf(request.getClientRegistration().getRegistrationId());

        String accessToken = request.getAccessToken().getTokenValue();

        Oauth2UserInfo oauth2UserInfo = Oauth2UserInfoFactory.getOauth2UserInfo(providerType.getRegistrationId(),
                accessToken,
                oAuth2User.getAttributes());

        if (!StringUtils.hasText(oauth2UserInfo.getEmail())) {
            throw new Oauth2AuthenticationException("Email Not Found");
        }

        User user = userRepository.findByEmail(oauth2UserInfo.getEmail())
                .map(entity -> updateUser(entity, oauth2UserInfo))
                .orElse(createUser(oauth2UserInfo, providerType));

        httpSession.setAttribute("user", new SessionUser(user));

        return new Oauth2UserPrinciple(oauth2UserInfo);
    }

    private User createUser(Oauth2UserInfo oauth2UserInfo, ProviderType providerType) {

        User user = User.builder()
                .name(oauth2UserInfo.getName())
                .email(oauth2UserInfo.getEmail())
                .providerType(providerType)
                .build();

        return userRepository.save(user);
    }

    private User updateUser(User user, Oauth2UserInfo oauth2UserInfo) {
        if (oauth2UserInfo.getName() != null && !user.getName().equals(oauth2UserInfo.getName())) {
            user.update(user.getName());
        }

        return userRepository.save(user);
    }
}
