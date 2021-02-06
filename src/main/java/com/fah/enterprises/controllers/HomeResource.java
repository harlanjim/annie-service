package com.fah.enterprises.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fah.enterprises.models.Profile;
import com.fah.enterprises.models.security.AuthenticationRequest;
import com.fah.enterprises.models.security.AuthenticationResponse;
import com.fah.enterprises.models.security.RegistrationRequest;
import com.fah.enterprises.services.UserService;
import com.fah.enterprises.utils.JwtUtil;

@RestController
public class HomeResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;


	@GetMapping("/admin")
	public String admin() {
		return "<h1>Hello Admin</h1>";
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			userDetailsService.saveAuthenticationResult(authenticationRequest.getUsername(), false);
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		userDetailsService.saveAuthenticationResult(authenticationRequest.getUsername(), true);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping(value = "/registration")
	public void createRegistration(@RequestBody RegistrationRequest registrationRequest)
			throws Exception {
		
		userDetailsService.userRegistration(registrationRequest);

	}
	
	// Get the logged in user.
	@GetMapping(value = "/user")
	public Profile getUser(Authentication authentication)
			throws Exception {
		return userDetailsService.getProfileByUsername(authentication.getName());
	}
	
	@GetMapping(value = "/profile/{id}")
	public Profile getProfile(@PathVariable("id") int id)
			throws Exception {
		return userDetailsService.getProfileById(id);
	}
	
	@GetMapping(value = "/profiles")
	public List<Profile> getProfiles()
			throws Exception {
		return userDetailsService.getProfiles();
	}
}
