package com.anjana.hotel_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HotelManagementSystemApplication {

	public static void main(String[] args) {
		String rawPassword = "Chaga@12";
		String hashedPassword = "$2a$12$QK0WuTTBrDSOjKfJCvT5Je62ELexo2at0c6W64arJ2lv9lJGKzUNG";

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.matches(rawPassword, hashedPassword));
		SpringApplication.run(HotelManagementSystemApplication.class, args);

	}

}
