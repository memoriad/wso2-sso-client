package com.github.ajanthan.spring.security.oauth2loginsample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

import com.github.ajanthan.spring.security.oauth2loginsample.filter.CorsConfigFilter;

@Configuration
public class OAuth2LoginConfig {

	@Value("${spring.security.oauth2.client.registration.wso2.client-id}")
	private String clientId;
	
	@Value("${spring.security.oauth2.client.registration.wso2.client-secret}")
	private String clientSecret;
	
	@Value("${spring.security.oauth2.client.provider.wso2.authorization-uri}")
	private String authorizationUri;
	
	@Value("${spring.security.oauth2.client.provider.wso2.token-uri}")
	private String tokenUri;
	
	@Value("${spring.security.oauth2.client.provider.wso2.user-info-uri}")
	private String userInfoUri;
	
	@Value("${spring.security.oauth2.client.provider.wso2.jwk-set-uri}")
	private String jwkSetUri;
	
    @Bean
    public ClientRegistrationRepository
    clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.WSO2ClientRegistration());
    }

    private ClientRegistration WSO2ClientRegistration() {
        return ClientRegistration.withRegistrationId("wso2")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("openid", "profile", "email", "address", "phone")
                .authorizationUri(authorizationUri)
                .tokenUri(tokenUri)
                .userInfoUri(userInfoUri)
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri(jwkSetUri)
                .clientName("WSO2")
                .build();
    }
    
    @Bean
	public FilterRegistrationBean<CorsConfigFilter> requestResponseFilter() {
		FilterRegistrationBean<CorsConfigFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new CorsConfigFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}
    
}
