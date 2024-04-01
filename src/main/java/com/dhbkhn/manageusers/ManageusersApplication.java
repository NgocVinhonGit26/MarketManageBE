package com.dhbkhn.manageusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dhbkhn.manageusers.enums.Role;
import com.dhbkhn.manageusers.model.User;
import com.dhbkhn.manageusers.repository.UserRepository;

@SpringBootApplication
public class ManageusersApplication {
	// implements CommandLineRunner
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManageusersApplication.class, args);
	}

	// public void run(String... args) throws Exception {
	// User adminAccount = userRepository.findByRole(Role.ADMIN);
	// if (adminAccount == null) {
	// User admin = new User();
	// admin.setName("admin");
	// admin.setEmail("admin@mail.com");
	// admin.setRole(Role.ADMIN);
	// admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
	// userRepository.save(admin);
	// }
	// }
}
