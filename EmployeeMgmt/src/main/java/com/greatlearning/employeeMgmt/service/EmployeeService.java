package com.greatlearning.employeeMgmt.service;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.stereotype.Service;

import com.greatlearning.employeeMgmt.entity.Employee;
import com.greatlearning.employeeMgmt.entity.Role;
import com.greatlearning.employeeMgmt.entity.User;

@Service
public interface EmployeeService {
	
	public User saveUser(User user);
	
	public Role saveRole(Role role);
	
	public List<Employee> readAll();
	
	public Employee readById(int id);
	
	public List<Employee> readByFirstName(String firstName);
	
	public void save(Employee employee);
	
	public void delete(int id);
	
	public List<Employee> sortByFirstName(String order);
		
}
