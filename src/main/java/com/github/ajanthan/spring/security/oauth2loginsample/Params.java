package com.github.ajanthan.spring.security.oauth2loginsample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("params")
@Data
public class Params {

	//default values, can be override by external settings
    public String trustStorePath;
    public String trustStorePassword;
    public String keyStorePath;
    public String keyStorePassword;
    public String defaultType;  
}
