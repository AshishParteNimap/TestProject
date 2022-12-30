package com.JobPortal.Service;

import java.util.List;

import com.JobPortal.Dto.RoleDto;
import com.JobPortal.Entity.Role;

public interface RoleService {

	public List<RoleDto> getAllRole();

	//public String addRole(Role role);

	

	public String deleteRole(Long id);

	String updateRole(Long id, RoleDto role);

	String addRole(RoleDto role);
}
