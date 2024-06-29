package com.petwellnes.petwellnes_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PetwellnesBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetwellnesBackendApplication.class, args);
	}

}
