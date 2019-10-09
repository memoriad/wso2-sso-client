package com.github.ajanthan.spring.security.oauth2loginsample;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oauth2LoginSampleApplication {

	@Autowired
    private Params params;
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2LoginSampleApplication.class, args);
	}
	
	@PostConstruct
    void postConstruct() {
        // set TrustStoreParams
        File trustStoreFilePath = new File(params.trustStorePath);
        String tsp = trustStoreFilePath.getAbsolutePath();
        System.setProperty("javax.net.ssl.trustStore", tsp);
        System.setProperty("javax.net.ssl.trustStorePassword", params.trustStorePassword);
        System.setProperty("javax.net.ssl.keyStoreType", params.defaultType);
        // set KeyStoreParams
        File keyStoreFilePath = new File(params.keyStorePath);
        String ksp = keyStoreFilePath.getAbsolutePath();
        System.setProperty("Security.KeyStore.Location", ksp);
        System.setProperty("Security.KeyStore.Password", params.keyStorePassword);
    }
	
}
