package com.fah.enterprises.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fah.enterprises.models.AuthenticationRequest;
import com.fah.enterprises.models.AuthenticationResponse;
import com.fah.enterprises.models.RegistrationRequest;
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

	@GetMapping("/test")
	public String home() {
		return "<h1>Hello</h1>";
	}

	@GetMapping("/user")
	public String user() {
		return "<h1>Hello User</h1>";
	}

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
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping(value = "/registration")
	public void createRegistration(@RequestBody RegistrationRequest registrationRequest)
			throws Exception {
		
		userDetailsService.userRegistration(registrationRequest);

	}
}
