package com.JobPortal.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import javax.naming.AuthenticationException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.UserLoginDto;
import com.JobPortal.Entity.Role;
import com.JobPortal.Entity.User;
import com.JobPortal.Exception.CustomeJwtException;
import com.JobPortal.Repositories.RoleRepository;
import com.JobPortal.Repositories.UserRepository;
import com.JobPortal.Security.Security.AccessToken;
import com.JobPortal.Security.Security.ApiMessage;
import com.JobPortal.Security.Security.ITokenProvider;
import com.JobPortal.Service.AuthService;

@Service
//@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService{

	@Autowired
	private ITokenProvider iTokenProvider;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository repository2;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AccessToken login(UserLoginDto userLoginDto) {
		String username=userLoginDto.getUsername();
		String password=userLoginDto.getPassword();
		try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            Set<Role> roles = repository.findByUsername(username).get().getRoles();
            return iTokenProvider.createToken(username,roles);

        }catch (Exception exception) {
            throw new CustomeJwtException(ApiMessage.BAD_CREDENTIALS, HttpStatus.BAD_REQUEST);
        
        }
	}
	
	

	private Set<Role> getRoles(String[] roles){
		Set<Role> userRole=new HashSet<>();
		for(String roleName:roles) {
			userRole.add(repository2.findByRoleName(roleName));
			
		}
		return userRole;

	

	}
	
	
	

}
