package com.fah.enterprises.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fah.enterprises.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);

}
