package com.greatlearning.employeeMgmt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.employeeMgmt.service.UserServiceImpl;



@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	  @Bean public UserDetailsService userDetailsService()
	  {
		  return new UserServiceImpl();
	  }
	  

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/user","/api/role").hasAuthority("admin")
		.antMatchers(HttpMethod.POST,"/api/employees").hasAuthority("admin")
		.antMatchers(HttpMethod.PUT,"/api/employees").permitAll()
		.antMatchers(HttpMethod.DELETE,"/api/employees").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
				/*
				 * .and().formLogin().loginProcessingUrl("/login")
				 * .successForwardUrl("/employee/list").permitAll().and().logout().
				 * logoutSuccessUrl("/login").permitAll()
				 * .and().exceptionHandling().accessDeniedPage("/student/403")
				 */
		.and().cors().and().csrf().disable();
	}
}
