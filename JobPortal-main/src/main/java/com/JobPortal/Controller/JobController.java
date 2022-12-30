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

import com.JobPortal.Dto.JobDto;
import com.JobPortal.ServiceImpl.Ijob;

@RestController
@RequestMapping("/Job")
public class JobController {

	@Autowired
	private Ijob service;

	@GetMapping("/getJob")
	public List<JobDto> getAllJob() {
		return service.getAllJobs();
	}

	@PostMapping("/addJob")
	public String addJob(@RequestBody JobDto jobs) {
		service.addJobs(jobs);
		return "job added";

	}

	@PutMapping("/editJob/{id}")
	public String editJob(@RequestBody JobDto jobs, @PathVariable Long id) {
		service.updateJob(jobs, id);
		return "job edited";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteJob(@PathVariable Long id) {
		service.deleteJobs(id);
		return "job deleted";
	}
	
	

}
