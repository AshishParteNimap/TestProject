package com.JobPortal.Security.Security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import com.JobPortal.Entity.Role;
@Component
public interface ITokenProvider {

	AccessToken createToken(String username,Set<Role> roles);
	
	boolean validateToken(AccessToken accessToken);
	
	AccessToken getTokenFromHeader(HttpServletRequest httpServletRequest);
	
	Authentication getAuthentication(AccessToken accessToken);
	
}
