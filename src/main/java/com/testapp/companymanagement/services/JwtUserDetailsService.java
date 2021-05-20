package com.testapp.companymanagement.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.testapp.companymanagement.entities.User;
import com.testapp.companymanagement.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = ur.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList());
	}

	public User getByUsername(String username) {
		return ur.findByUsername(username);
	}

	public User firstLoginSave(String username, String password) throws Exception {

		try {
			String encodedPassword = new BCryptPasswordEncoder().encode(password);
			User user = ur.save(new User(username, encodedPassword));
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Failed to save user");
		}

	}

}
