package com.fah.enterprises.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fah.enterprises.exceptions.UsernameFoundException;
import com.fah.enterprises.models.AnnieUserDetails;
import com.fah.enterprises.models.RegistrationRequest;
import com.fah.enterprises.models.User;
import com.fah.enterprises.repositories.UserRepository;
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username + " Not Found In Database"));
		return user.map(AnnieUserDetails::new).get();
	}
	
	public Integer userRegistration(RegistrationRequest request) throws UsernameFoundException {
		
		Optional<User> user = userRepository.findByUserName(request.getUsername());
		if (user.isPresent()) {
			throw new UsernameFoundException(request.getUsername() + "Found in Database");
		}
		
		User tempUser = new User();
		tempUser.setUserName(request.getUsername());
		tempUser.setPassword(passwordEncoder.encode(request.getPassword()));
		
		tempUser.setActive(true);
		
		tempUser.setRoles("ROLE_USER");
		
		userRepository.save(tempUser);
		return null;
	}

}
