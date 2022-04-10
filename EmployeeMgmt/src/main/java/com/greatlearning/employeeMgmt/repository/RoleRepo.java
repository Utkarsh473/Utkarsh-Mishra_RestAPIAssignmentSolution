package com.greatlearning.employeeMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeeMgmt.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
