package com.fah.enterprises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fah.enterprises.repositories.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class AnnieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnieServiceApplication.class, args);
	}

}
