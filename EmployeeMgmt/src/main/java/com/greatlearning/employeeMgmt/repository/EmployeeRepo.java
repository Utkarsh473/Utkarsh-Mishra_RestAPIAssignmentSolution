package com.greatlearning.employeeMgmt.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.employeeMgmt.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	List<Employee> findAllByOrderByFirstNameAsc();
	
	List<Employee> findAllByOrderByFirstNameDesc();
}
