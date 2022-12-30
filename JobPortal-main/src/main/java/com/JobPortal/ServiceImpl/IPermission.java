package com.JobPortal.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortal.Dto.PermissionDto;
import com.JobPortal.Entity.Permission;
import com.JobPortal.Repositories.PermissionRepository;
import com.JobPortal.Service.PermissionService;

@Service
public class IPermission implements PermissionService {

	@Autowired
	private PermissionRepository repo;

	@Override
	public List<PermissionDto> getAllPermission() {
		List<Permission> permission = repo.findAll();
		List<PermissionDto> dto = new ArrayList<>();
		for (int i = 0; i < permission.size(); i++) {
			PermissionDto dtonew = new PermissionDto();
			dtonew.setId(permission.get(i).getId());
			dtonew.setPermissionName(permission.get(i).getPermissionName());
			dto.add(dtonew);
		}
		return dto;
	}

	@Override
	public String addPermission(PermissionDto permission) {
		Permission permissionNew = new Permission();
		permissionNew.setPermissionName(permission.getPermissionName());
		repo.save(permissionNew);
		return "permission saved";
	}

	@Override
	public String updatePermission(Long id, PermissionDto permission) {

		Permission permissionNew = repo.findById(id).orElse(null);
		 permission.setId(permission.getId());
		permissionNew.setPermissionName(permission.getPermissionName());
		repo.save(permissionNew);
		return "permission updated";
	}

	@Override
	public String deletePermission(Long id) {
		Permission permission = repo.findById(id).orElse(null);
		repo.delete(permission);
		return "permission deleted";
	}

}
