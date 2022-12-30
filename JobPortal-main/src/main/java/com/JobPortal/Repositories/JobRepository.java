package com.JobPortal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobPortal.Entity.Jobs;
@Repository
public interface JobRepository extends JpaRepository<Jobs, Long>{

}
