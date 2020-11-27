package com.fah.enterprises.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fah.enterprises.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {


}
