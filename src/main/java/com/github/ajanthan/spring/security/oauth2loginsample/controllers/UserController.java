package com.github.ajanthan.spring.security.oauth2loginsample.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

	@GetMapping("/user/me")
	public ResponseEntity<String> getProfile(OAuth2AuthenticationToken token) {
		log.info("principal: {}", token.getPrincipal().getAttributes());
		
		return ResponseEntity.ok(token.getPrincipal().getName());
	}
	
}
