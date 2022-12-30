package com.JobPortal.Jwt;

import java.util.Set;

import com.JobPortal.Entity.Role;
import com.JobPortal.Security.Security.AccessToken;
import com.JobPortal.Security.Security.SecretKey;

public interface IJwtTokenHelper {

	String generateToken(SecretKey key, String username, Set<Role> roles);

	boolean validateJwtToken(SecretKey key, AccessToken accessToken);

	String getUserNameFromJwtToken(SecretKey key, AccessToken accessToken);
}
