package com.JobPortal.Config.Bean.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.JobPortal.Exception.CustomeJwtException;
import com.JobPortal.Security.Security.AccessToken;
import com.JobPortal.Security.Security.ITokenProvider;

@Component
public class TokenFilter  extends OncePerRequestFilter{
	
	@Autowired
	 ITokenProvider iTokenProvider;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		AccessToken accessToken=iTokenProvider.getTokenFromHeader(request);
		try {
			
			if(checkAccessToken(accessToken)) {
				Authentication authentication=iTokenProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
				
			}catch (CustomeJwtException customSecurityException) {
	            SecurityContextHolder.clearContext();
	            // throw again
	            throw customSecurityException;
	        }
		
		
}


	 private boolean checkAccessToken(AccessToken accessToken) {
	        if (accessToken == null) return false;
	        return iTokenProvider.validateToken(accessToken);
	    }
}
