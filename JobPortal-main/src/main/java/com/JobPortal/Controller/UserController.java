package com.JobPortal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortal.Dto.RegisteredDto;
import com.JobPortal.Dto.UserDto;
import com.JobPortal.Entity.User;
import com.JobPortal.Repositories.UserRepository;
import com.JobPortal.ServiceImpl.IUserService;



@RestController
@RequestMapping("/User")
@javax.transaction.Transactional

public class UserController {

	@Autowired
	private IUserService service;

	@Autowired
	private UserRepository repo;

	@GetMapping("/all")
	public List<UserDto> getAllUser() {
		return service.getAllUser();
	}

	@GetMapping("/admin")
	public List<User> getUser() {
		return repo.findAll();
	}

	@PostMapping("/add")
	public String addUser(@RequestBody UserDto user) {
		service.addUser(user);
		return "user added";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		service.deleteId(id);
		return "deleted";
	}

	@PutMapping("/edit/{id}")
	public String updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
		service.upadteUser(id, dto);
		return "user edited";
	}

	@PostMapping("/assign")
	public String assign(Long userId, Long roleId) {
		service.assignUserRole(userId, roleId);
		return "role aassinged";
	}
	
	@GetMapping("/reg")
	public List<RegisteredDto> get(){
		return service.getRegisteredUser();
	}
	
	@PostMapping("/apply")
	public String applyJob(String email,Long jobId) {
		service.applyJob(email, jobId);
		return "job applied";
	}
}
