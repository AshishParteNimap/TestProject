package com.JobPortal.Service;

import java.util.List;

import com.JobPortal.Dto.PermissionDto;
import com.JobPortal.Entity.Permission;

public interface PermissionService {

	public List<PermissionDto> getAllPermission();

	public String deletePermission(Long id);

	String updatePermission(Long id, PermissionDto permission);

	String addPermission(PermissionDto permission);

}
