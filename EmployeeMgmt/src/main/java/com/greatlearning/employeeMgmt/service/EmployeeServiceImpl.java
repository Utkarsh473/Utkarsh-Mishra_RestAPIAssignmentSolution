package com.greatlearning.employeeMgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.HasMemberTypePatternFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.greatlearning.employeeMgmt.entity.Employee;
import com.greatlearning.employeeMgmt.entity.Role;
import com.greatlearning.employeeMgmt.entity.User;
import com.greatlearning.employeeMgmt.repository.EmployeeRepo;
import com.greatlearning.employeeMgmt.repository.RoleRepo;
import com.greatlearning.employeeMgmt.repository.UserRepo;

@Repository
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	BCryptPasswordEncoder enc;
	
	private EmployeeRepo employeeRepo;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepo theEmployeeRepository) {
		this.employeeRepo = theEmployeeRepository;
	}
	
	
	
	@Override
	public List<Employee> readAll() {
		// TODO Auto-generated method stub
		List<Employee> employee = employeeRepo.findAll();
		
		return employee;
	}

	@Override
	public Employee readById(int id) {
		
		Optional<Employee> result = employeeRepo.findById(id);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + id);
		}

		return theEmployee;
	}

	@Override
	public List<Employee> readByFirstName(String firstName) {
		// TODO Auto-generated method stub
		List<Employee> allEmployees = employeeRepo.findAll();
		
		List<Employee> employee = new ArrayList<Employee>();
		
		for(Employee emp: allEmployees)
		{
			if(emp.getFirstName().equalsIgnoreCase(firstName))
			{
				employee.add(emp);
			}
		}
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepo.save(employee);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Employee emp = employeeRepo.getById(id);
		
		employeeRepo.delete(emp);
	}

	@Override
	public User saveUser(User user) {
		
		user.setPassword(enc.encode(user.getPassword()));
		return userRepo.save(user);
		
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		roleRepo.save(role);
		
		return role;
	}

	@Override
	public List<Employee> sortByFirstName(String order) {
		// TODO Auto-generated method stub
		order = order.replace("\"","");
		List<Employee> emp = new ArrayList<Employee>();
		
		if(order.equalsIgnoreCase("Asc")== true)
		{
			emp = employeeRepo.findAllByOrderByFirstNameAsc();
			System.out.println("chal ja bsdk");
		}
		
		else 
			emp = employeeRepo.findAllByOrderByFirstNameDesc();
		
		return emp;
	}
}
	
