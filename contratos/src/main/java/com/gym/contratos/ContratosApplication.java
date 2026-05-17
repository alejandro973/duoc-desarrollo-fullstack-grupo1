package com.gym.contratos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContratosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratosApplication.class, args);
	}

}
