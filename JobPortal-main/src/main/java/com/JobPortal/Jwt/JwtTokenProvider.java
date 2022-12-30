package com.JobPortal.Jwt;

import java.net.http.HttpRequest;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.JobPortal.Entity.Role;
import com.JobPortal.Security.Security.AccessToken;
import com.JobPortal.Security.Security.ITokenProvider;
import com.JobPortal.Security.Security.SecretKey;

@Component
public class JwtTokenProvider implements ITokenProvider {

	@Autowired
	private IJwtTokenHelper helper;

	@Autowired
	private UserDetailsService detailsService;

	@Value("${security.jwt.token.secret-key}")
	private String secretKeyValue;
	@Value("${security.jwt.token.expiration}")
	private long expiration;

	@Override
	public AccessToken createToken(String username, Set<Role> roles) {
		SecretKey key=new SecretKey(secretKeyValue, expiration);
		String token=helper.generateToken(key, username, roles);
		return new AccessToken(token);
	}

	@Override
	public boolean validateToken(AccessToken accessToken) {
		SecretKey key=new SecretKey(secretKeyValue,expiration);
		
		return helper.validateJwtToken(key, accessToken);
	}

	@Override
	public AccessToken getTokenFromHeader(HttpServletRequest httpServletRequest) {
		String bearerToken=httpServletRequest.getHeader("Authorization");
		if(bearerToken==null) {
			return null;
		}
		 if (!bearerToken.startsWith("Bearer ")) return null;
	        return new AccessToken(bearerToken.substring(7));
	}

	@Override
	public Authentication getAuthentication(AccessToken accessToken) {
		SecretKey secretKey = new SecretKey(secretKeyValue,expiration);
        String username = helper.getUserNameFromJwtToken(secretKey,accessToken);

        UserDetails userDetails = detailsService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        return authentication;
	}

}
