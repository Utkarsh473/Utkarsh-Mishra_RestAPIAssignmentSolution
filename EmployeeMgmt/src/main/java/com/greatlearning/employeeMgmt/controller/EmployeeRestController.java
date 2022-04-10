package com.greatlearning.employeeMgmt.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeeMgmt.entity.Employee;
import com.greatlearning.employeeMgmt.entity.Role;
import com.greatlearning.employeeMgmt.entity.User;
import com.greatlearning.employeeMgmt.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService empSvc;
	
	@Autowired
	public EmployeeRestController(EmployeeService empSvc){
		
		this.empSvc = empSvc;
	}
	
	@PostMapping("/user")
	public User saveUser(@RequestBody User user)
	{
		return empSvc.saveUser(user);
	}
	
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role)
	{
		return empSvc.saveRole(role);
	}
	
	
	@GetMapping("/employees")
	public List<Employee> findAll()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = auth.getAuthorities();
		System.out.println(currentPrincipalName);
		return empSvc.readAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId)
	{
		Employee emp = empSvc.readById(employeeId);
		
		if (emp == null)
		{
			throw new RuntimeException("Employee ID not found"+ employeeId);
		}
		
		return emp;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee emp)
	{
		empSvc.save(emp);
		
		return emp;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee emp)
	{
		empSvc.save(emp);
		
		return emp;
	}
	
	
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmp(@PathVariable("id") int id)
	{
		Employee emp = empSvc.readById(id);
		
		if(emp ==null)
		{
			throw new RuntimeException("Employee id not found - " + id);
		}
		
		else
		{
		empSvc.delete(id);
		}
		
		return "Deleted employee id - " + id;

	}
	
	@GetMapping("/employees/search/{firstName}")
	public List<Employee> searchByFirstName(@PathVariable("firstName") String firstName)
	{
		List<Employee> emp = empSvc.readByFirstName(firstName);
		
		return emp;
	}
	
	@GetMapping("/employees/sort")
	public List<Employee> sortByFirstName(@RequestParam("order") String order)
	{
		List<Employee> emp = empSvc.sortByFirstName(order);
		
		return emp;
	}

}
