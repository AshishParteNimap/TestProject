package com.JobPortal.Service;

import java.util.List;

import com.JobPortal.Dto.JobDto;
import com.JobPortal.Entity.Jobs;

public interface JobIService {

	public List<JobDto> getAllJobs();
	
	public String addJobs(JobDto jobs);
	
	public String updateJob(JobDto jobs,Long id);
	
	public String deleteJobs(Long id);
}
