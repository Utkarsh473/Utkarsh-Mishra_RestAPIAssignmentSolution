package com.greatlearning.employeeMgmt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.greatlearning.employeeMgmt.entity.Role;
import com.greatlearning.employeeMgmt.entity.User;
import com.greatlearning.employeeMgmt.repository.UserRepo;
import com.greatlearning.employeeMgmt.security.MyUserDetails;

public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepo usrRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = usrRepo.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		else {
			return new MyUserDetails(user);
		}
	}

	
	 

}
