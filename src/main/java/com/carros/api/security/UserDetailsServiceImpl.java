package com.carros.api.security;

import com.carros.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carros.domain.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRep.findByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");

		}
		
		return user;
	}

}
