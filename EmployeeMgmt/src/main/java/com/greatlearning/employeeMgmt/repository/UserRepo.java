package com.greatlearning.employeeMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatlearning.employeeMgmt.entity.Role;
import com.greatlearning.employeeMgmt.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User getUserByUsername(String username);

}
