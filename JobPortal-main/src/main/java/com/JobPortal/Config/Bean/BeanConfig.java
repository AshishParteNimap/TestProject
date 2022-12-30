package com.JobPortal.Config.Bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.JobPortal.Service.CustomeUserDetailsService;

@Configuration
public class BeanConfig {

//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public UserDetailsService detailsService() {
		return new CustomeUserDetailsService();
	}
	
	
}
