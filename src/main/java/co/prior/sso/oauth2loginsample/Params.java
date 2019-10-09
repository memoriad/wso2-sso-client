package co.prior.sso.oauth2loginsample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("params")
public class Params {

	//default values, can be override by external settings
    public String trustStorePath;
    public String trustStorePassword;
    public String keyStorePath;
    public String keyStorePassword;
    public String defaultType;  
}
