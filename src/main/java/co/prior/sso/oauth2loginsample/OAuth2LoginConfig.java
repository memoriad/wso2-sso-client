package co.prior.sso.oauth2loginsample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

import co.prior.sso.oauth2loginsample.filter.CorsConfigFilter;

@Configuration
@EnableWebSecurity
public class OAuth2LoginConfig extends WebSecurityConfigurerAdapter {

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
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.WSO2ClientRegistration());
	}

	private ClientRegistration WSO2ClientRegistration() {
		return ClientRegistration.withRegistrationId("wso2").clientId(clientId).clientSecret(clientSecret)
				.clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
				.scope("openid", "profile", "email", "address", "phone").authorizationUri(authorizationUri)
				.tokenUri(tokenUri).userInfoUri(userInfoUri).userNameAttributeName(IdTokenClaimNames.SUB)
				.jwkSetUri(jwkSetUri).clientName("WSO2").build();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.ttf", "/**/*.woff", "/**/*.woff2", "/**/*.ico").permitAll()
				.anyRequest().authenticated()
				.and().oauth2Login().loginPage("/login")
				.clientRegistrationRepository(clientRegistrationRepository())
				.authorizedClientService(authorizedClientService())
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll();
	}

	@Bean
	public OAuth2AuthorizedClientService authorizedClientService() {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
	}

	@Bean
	public FilterRegistrationBean<CorsConfigFilter> requestResponseFilter() {
		FilterRegistrationBean<CorsConfigFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new CorsConfigFilter());
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}

}
