package com.fah.enterprises.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fah.enterprises.models.Profile;
import com.fah.enterprises.services.ProfileService;

@RestController
public class ProfileController {

  	@Autowired
	private ProfileService profileService;
	

	@GetMapping(value = "/profile/{id}")
	public Profile getProfile(@PathVariable("id") int id)
			throws Exception {
		return profileService.getProfileById(id);
	}
	
	@GetMapping(value = "/profiles")
	public List<Profile> getProfiles()
			throws Exception {
		return profileService.getProfiles();
	}
}
