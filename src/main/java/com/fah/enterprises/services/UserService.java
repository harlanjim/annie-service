package com.fah.enterprises.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fah.enterprises.models.AnnieUserDetails;
import com.fah.enterprises.models.User;
import com.fah.enterprises.repositories.UserRepository;
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + " Not Found In Database"));
		return user.map(AnnieUserDetails::new).get();
	}

}
