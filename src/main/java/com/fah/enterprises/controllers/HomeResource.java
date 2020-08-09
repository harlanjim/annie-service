package com.fah.enterprises.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

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
}
