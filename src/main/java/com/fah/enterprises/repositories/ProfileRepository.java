package com.fah.enterprises.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fah.enterprises.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	
	Optional<Profile> findByUserId(Integer userId);

}
