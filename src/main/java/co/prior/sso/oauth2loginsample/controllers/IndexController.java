package co.prior.sso.oauth2loginsample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/**/{[path:[^\\.]*}")
	public String redirectUi() {
		return "forward:/index.html";
	}
	
}
