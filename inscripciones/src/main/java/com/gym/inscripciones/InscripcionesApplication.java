package com.gym.inscripciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InscripcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InscripcionesApplication.class, args);
	}

}
