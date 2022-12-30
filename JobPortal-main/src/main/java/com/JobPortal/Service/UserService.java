package com.JobPortal.Service;

import java.util.List;

import com.JobPortal.Dto.RegisteredDto;
import com.JobPortal.Dto.UserDto;
import com.JobPortal.Entity.User;

public interface UserService {

	public List<UserDto> getAllUser();

	public String deleteId(Long id);

	String addUser(UserDto user);

	String upadteUser(Long id, UserDto dto);
	
	public List<RegisteredDto> getRegisteredUser();
	
	public String applyJob(String email,Long Jobid);
	
	

}
