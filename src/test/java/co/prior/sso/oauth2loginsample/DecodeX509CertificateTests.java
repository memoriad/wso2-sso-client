package co.prior.sso.oauth2loginsample;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.junit.Test;

public class DecodeX509CertificateTests {

	@Test
	public void testDecodeX509Certificate() throws CertificateException {
		String certB64 = "MIIDajCCAlICCQCFn8ZM+Rj1qDANBgkqhkiG9w0BAQsFADB3MQswCQYDVQQGEwJ0aDEQMA4GA1UECAwHYmFuZ2tvazEKMAgGA1UEBwwBejEOMAwGA1UECgwFcHJpb3IxDDAKBgNVBAsMA2RldjENMAsGA1UEAwwEZGVtbzEdMBsGCSqGSIb3DQEJARYOZGVtb0BnbWFpbC5jb20wHhcNMTkxMDA5MDYzNDQyWhcNMjAxMDA4MDYzNDQyWjB3MQswCQYDVQQGEwJ0aDEQMA4GA1UECAwHYmFuZ2tvazEKMAgGA1UEBwwBejEOMAwGA1UECgwFcHJpb3IxDDAKBgNVBAsMA2RldjENMAsGA1UEAwwEZGVtbzEdMBsGCSqGSIb3DQEJARYOZGVtb0BnbWFpbC5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDA8tkOfiaSGdmDiIYz8enaI9uZVsGLF0bG8LIVKsQEGrztJ39hsCX4lHwFupGv8/xAPVZsJ3lcwCtqUhDr842LoAwIY6ekD3RR7BLPei1eO/3hFsG7JpfGIekHJ8evsktepQAnNSXKy67xSbS3PXDqbdSAkXKJ7s8XFje59rJ3p+g0NTV+8YIaKmn4SS3cvTu9AV1tG3wDg8Yrtmc2msMYr0KmjG0GuvrhkEISI7GFlpwb4r5OhpPVNIYPJZhAJoQyTo/GsJhc6YsjopYSoq+cLz26SPFxK7dVePtFzuMRArIMaN8YZAw2/PWt3yrJWBIq3pTvIWskDmSML12P+ngxAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAJMbmOIhox2sKc7Kpd//pJ+XK9qObUMsbkGK6sqUykBRC6S1vW9INQPz05aAPCb2x571Ryx38IxH+P8hm8jgaaNtfNpOQoNbLtiI3TapswP1+cbAsU//AlEWD8Zq2FpOmeTTq+tqlA7QvSUFyPlOj7hDpWrG5yes/5PAG6K9PYqYFrJpCRM1AK8z4XPPDTL70eaeaAaEFIwl9/kU+7bBW7hpZdTzRlJGtPTioXWr6gvMybXxwMfETGcnhSL12ONG1Y61evWKVZLYZxoy4JSuOgBRlOsPVvSUWCKLZQsq3HmBXy7T1uQJjw4e7D9tPzCv0/hlAW0YEUAI5ehtuiX/G74=";
		byte encodedCert[] = Base64.getDecoder().decode(certB64);
		ByteArrayInputStream inputStream  =  new ByteArrayInputStream(encodedCert);

		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate)certFactory.generateCertificate(inputStream);
		String subjectDN = cert.getSubjectDN().getName();
		System.out.println("subjectDN: " + cert.getSubjectDN().getName());
		HashMap<String, String> map = (HashMap<String, String>) Arrays.asList(subjectDN.split(", ")).stream().map(s -> s.split("=")).collect(Collectors.toMap(e -> e[0], e -> e[1]));
		System.out.println("X509 Certs Attr: " + map);
	}
	
}
