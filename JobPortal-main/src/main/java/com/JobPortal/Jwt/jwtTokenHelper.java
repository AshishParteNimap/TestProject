package com.JobPortal.Jwt;

import java.sql.Date;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.repository.query.QueryLookupStrategy.Key;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.JobPortal.Entity.Role;
import com.JobPortal.Exception.CustomeJwtException;
import com.JobPortal.Security.Security.AccessToken;
import com.JobPortal.Security.Security.ApiMessage;
import com.JobPortal.Security.Security.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class jwtTokenHelper implements IJwtTokenHelper{

	@Override
	public String generateToken(SecretKey key, String username, Set<Role> roles) {
		Claims claims=Jwts.claims().setSubject(username);
		claims.put("authorities",roles.stream().map(role->{return new SimpleGrantedAuthority(role.getRoleName());}).collect(Collectors.toList()));
		
		java.util.Date issuedAt=new java.util.Date();
		java.util.Date validUntil=new java.util.Date(issuedAt.getTime()+key.getExpirationInMilSec());
		String secretKeyEncode=Base64.getEncoder().encodeToString(key.getSecretKey().getBytes());
		return Jwts
				.builder()
				.setClaims(claims)
				.setIssuedAt(issuedAt)
				.setExpiration(validUntil)
				.signWith(SignatureAlgorithm.HS256, secretKeyEncode)
				.compact();
	
	}

	@Override
	public boolean validateJwtToken(SecretKey key, AccessToken accessToken) {
		try {
			String SecretKeyEncoded= Base64.getEncoder().encodeToString(key.getSecretKey().getBytes());
            Jwts.parser().setSigningKey(SecretKeyEncoded).parseClaimsJws(accessToken.getToken());
            return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new CustomeJwtException(ApiMessage.INVALID_TOKEN,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

	@Override
	public String getUserNameFromJwtToken(SecretKey key, AccessToken accessToken) {
		
		String secretKeyEncoded=Base64.getEncoder().encodeToString(key.getSecretKey().getBytes());
		return Jwts.parser().setSigningKey(secretKeyEncoded).parseClaimsJwt(accessToken.getToken()).getBody().getSubject();
	}

	
}
