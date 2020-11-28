package com.fah.enterprises.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fah.enterprises.exceptions.UsernameFoundException;
import com.fah.enterprises.models.AnnieUserDetails;
import com.fah.enterprises.models.Phone;
import com.fah.enterprises.models.Profile;
import com.fah.enterprises.models.RegistrationRequest;
import com.fah.enterprises.models.Role;
import com.fah.enterprises.models.User;
import com.fah.enterprises.repositories.ProfileRepository;
import com.fah.enterprises.repositories.RoleRepository;
import com.fah.enterprises.repositories.UserRepository;
@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	RoleRepository roleRepository;

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
		
		String lRoleName = "ROLE_USER";
		if (profileRepository.count() < 1L) {
			lRoleName = "ROLE_ADMIN";
		}
		
		User tempUser = new User();
		tempUser.setUserName(request.getUsername());
		tempUser.setPassword(passwordEncoder.encode(request.getPassword()));
		
		tempUser.setActive(true);
		tempUser.setRoles(new ArrayList<Role>());
		tempUser.getRoles().add(new Role(lRoleName));
		
		Profile profile = new Profile();
		profile.setFirstName(request.getFirstName());
		profile.setLastName(request.getLastName());
		profile.setEmail(request.getEmail());
		profile.setPhone(new ArrayList<Phone>());
		profile.getPhone().add(new Phone(request.getPhone(), request.getPhoneType()));
		profile.setUser(tempUser);
		
		profileRepository.save(profile);
		return null;
	}

	/*
	 * Save information to the user table based on authentication success or failure
	 */
	public void saveAuthenticationResult(String username, boolean successFlag) {
		Optional<User> optUser = userRepository.findByUserName(username);
		if (optUser.isPresent()) {
			User user = optUser.get();
			if (successFlag) {
				user.setLastLoginTs(new Date());
				user.setBadAuthenticationRequests(0L);
			}
			else {
				long cnt = (user.getBadAuthenticationRequests() == null)? 0L : user.getBadAuthenticationRequests();
				user.setBadAuthenticationRequests(cnt + 1L);
				user.setLastLoginAttemptTs(new Date());
			}
			userRepository.save(user);
		}
	}
}
