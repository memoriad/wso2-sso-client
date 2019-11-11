package co.prior.sso.oauth2loginsample.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfile {

	private String userCode;
	private String fullname;
	private String position;
	private String department;
	private String logonTime;
	
}
