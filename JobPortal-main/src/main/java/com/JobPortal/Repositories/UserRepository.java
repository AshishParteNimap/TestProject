package com.JobPortal.Repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JobPortal.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	User findByUsername(String username);
}
