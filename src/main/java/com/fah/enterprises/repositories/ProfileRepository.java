package com.fah.enterprises.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fah.enterprises.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
