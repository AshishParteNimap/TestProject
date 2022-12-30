package com.JobPortal.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.JobPortal.Entity.Role;
import com.JobPortal.Entity.User;
import com.JobPortal.Repositories.UserRepository;

public class CustomeUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username+"no email found"));
		Set<Role> roles=user.getRoles();
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities(getSimpleGrantedAuthorities(roles))
				.accountExpired(false)
				.accountLocked(false)
				.disabled(false)
				.credentialsExpired(false)
				.build();
	}
	private Set<GrantedAuthority> getSimpleGrantedAuthorities(Set<Role> roles){
		Set<GrantedAuthority>authorities=new HashSet<>();
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			
		}
		return authorities;
	}

}
