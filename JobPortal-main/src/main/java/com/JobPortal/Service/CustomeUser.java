package com.JobPortal.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.JobPortal.Entity.Role;
import com.JobPortal.Entity.User;

public class CustomeUser implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private Role role;
	
	

	
	public CustomeUser(Role role) {
		super();
		this.role = role;
	}


	public CustomeUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public CustomeUser(User user) {
		super();
		this.user = user;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles=this.user.getRoles();
		Collection<GrantedAuthority> authorities=new ArrayList<>(roles.size());
		for(Role role2:roles) {
			authorities.addAll(role2.getPermissions().stream().map(a->new SimpleGrantedAuthority(a.getPermissionName())).collect(Collectors.toList()));
			System.out.println(role2);
		}
		System.out.println(authorities);
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
