package com.JobPortal.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.RoleDto;
import com.JobPortal.Entity.Role;
import com.JobPortal.Repositories.RoleRepository;
import com.JobPortal.Service.RoleService;

@Service
public class IRole implements RoleService {

	@Autowired
	private RoleRepository repo;

	@Override
	public List<RoleDto> getAllRole() {
		List<Role> roles = repo.findAll();
		List<RoleDto> roleNew = new ArrayList<>();
		for (int i = 0; i < roles.size(); i++) {
			RoleDto dto = new RoleDto();
			dto.setId(roles.get(i).getId());
			dto.setRoleName(roles.get(i).getRoleName());
			dto.setEnabled(roles.get(i).isEnabled());
			dto.setPermissions(roles.get(i).getPermissions());
			roleNew.add(dto);

		}
		return roleNew;
	}

	public String addRole(RoleDto role) {
		Role roles=new Role();
		
		roles.setRoleName(role.getRoleName());
		roles.setEnabled(true);
		roles.setPermissions(new HashSet<>(role.getPermissions()));
		repo.save(roles);
		return "role saved";
	}

	@Override
	public String updateRole(final Long id,final RoleDto role) {
		Role rolenew = repo.findById(id).orElse(null);
		rolenew.setId(role.getId());
		rolenew.setRoleName(role.getRoleName());
		rolenew.setEnabled(role.isEnabled());
		rolenew.setPermissions(new HashSet<>(role.getPermissions()));
		repo.save(rolenew);
		return "role updated";
	}

	@Override
	public String deleteRole(Long id) {
		Role role = repo.findById(id).orElse(null);
		repo.delete(role);
		return "role deleted";
	}

	

}
