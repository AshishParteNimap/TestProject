package com.JobPortal.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.JobPortal.Service.CustomeUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	
      @Bean
      public UserDetailsService detailsService() {
    	  return new CustomeUserDetailsService();
      }
	
	@Bean
	 PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(customeUserDetailsService).passwordEncoder(encoder());
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(detailsService());
		auth.setPasswordEncoder(encoder());
		return auth;
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable()
		.authorizeRequests()
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		
	}

	

//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		// TODO Auto-generated method stub
//		return super.authenticationManagerBean();
//	}
//	
	
	
	
	
}
