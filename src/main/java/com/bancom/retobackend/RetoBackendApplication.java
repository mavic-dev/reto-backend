package com.bancom.retobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RetoBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoBackendApplication.class, args);
	}

}
