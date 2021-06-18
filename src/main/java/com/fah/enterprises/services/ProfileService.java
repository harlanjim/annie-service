package com.fah.enterprises.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fah.enterprises.models.Profile;
import com.fah.enterprises.repositories.ProfileRepository;


@Service
public class ProfileService {
  
  @Autowired
  ProfileRepository profileRepository;

  
  public Profile getProfileById(Integer id) {
    return profileRepository.findById(id).get();
  }

  public List<Profile> getProfiles() {
    return profileRepository.findAll();
  }

}
