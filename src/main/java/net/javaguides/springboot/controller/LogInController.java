package net.javaguides.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
	
	@GetMapping("/showloginpage")
	public String showLoginPage()
	{
		return "login";
	}
	
	


}
