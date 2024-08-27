package com.example.demo;

import com.example.demo.dtos.RegisterDto;
import com.example.demo.service.MedicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AplicatieProgramariMedicaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieProgramariMedicaleApplication.class, args);
	}

	@Bean
	CommandLineRunner init(MedicService medicService) {
		return args -> {
			//medici
			RegisterDto registerDtoAdmin= RegisterDto.builder()
					.nume("admin")
					.prenume("admin")
					.phoneNumber("0700000000")
					.role("admin")
					.email("admin@yahoo.com")
					.password("admin")
					.build();
			medicService.register(registerDtoAdmin);
		};
	}
}