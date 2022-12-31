package com.JobPortal.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JobPortal.Entity.Permission;
import com.JobPortal.Entity.Role;
import com.JobPortal.Entity.User;
import com.JobPortal.Repositories.RoleRepository;
import com.JobPortal.Repositories.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	private RoleRepository repository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + "no email found"));
		System.out.println(user);
		Set<Role> roles = user.getRoles();
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).authorities(getSimpleGrantedAuthorities(user)).accountExpired(false)
				.accountLocked(false).disabled(false).credentialsExpired(false).build();

	}

//	private Set<GrantedAuthority> getSimpleGrantedAuthorities(Set<Role> roles) {
//		Set<GrantedAuthority> authorities = new HashSet<>();
//		for (Role role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//
//		}
//		return authorities;
//	}
	
	private Collection<GrantedAuthority>getSimpleGrantedAuthorities(User user){
		Set<GrantedAuthority> gAuth=new HashSet<>();
		//Set<Role> roles=new HashSet<>();
		for(Role role:user.getRoles()) {
			gAuth.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//			for(Permission permission:role.getPermissions()) {
//				gAuth.add(new SimpleGrantedAuthority(permission.getPermissionName()));
//			}
		}
		System.out.println(gAuth);
		return gAuth;
	}

}
