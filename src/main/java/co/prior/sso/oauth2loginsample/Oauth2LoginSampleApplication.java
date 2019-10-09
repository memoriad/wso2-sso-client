package co.prior.sso.oauth2loginsample;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class Oauth2LoginSampleApplication {

	@Autowired
    private Params params;
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2LoginSampleApplication.class, args);
	}
	
	@PostConstruct
    void postConstruct() throws IOException {
        // set TrustStoreParams
        File trustStoreFilePath = new ClassPathResource(params.trustStorePath).getFile();
        String tsp = trustStoreFilePath.getAbsolutePath();
        System.setProperty("javax.net.ssl.trustStore", tsp);
        System.setProperty("javax.net.ssl.trustStorePassword", params.trustStorePassword);
        System.setProperty("javax.net.ssl.keyStoreType", params.defaultType);
        // set KeyStoreParams
        File keyStoreFilePath = new ClassPathResource(params.keyStorePath).getFile();
        String ksp = keyStoreFilePath.getAbsolutePath();
        System.setProperty("Security.KeyStore.Location", ksp);
        System.setProperty("Security.KeyStore.Password", params.keyStorePassword);
    }
	
}
