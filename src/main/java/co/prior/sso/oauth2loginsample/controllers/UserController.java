package co.prior.sso.oauth2loginsample.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.prior.sso.oauth2loginsample.model.UserProfile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@GetMapping("/user/me")
	public ResponseEntity<UserProfile> getProfile(OAuth2AuthenticationToken token) {
		log.info("principal attributes: {}", token.getPrincipal().getAttributes());
		
		Map<String, Object> principalAttributes = token.getPrincipal().getAttributes();
		UserProfile userProfile = UserProfile.builder()
				.userCode(token.getPrincipal().getName())
				.fullname(principalAttributes.get("given_name") + " " + principalAttributes.get("family_name"))
				.position("Developer")
				.department("IT")
				.logonTime(principalAttributes.get("iat").toString().substring(11, 19))
				.build();
		
		log.info("user profile: {}", userProfile);
		
		return ResponseEntity.ok(userProfile);
	}
	
}
