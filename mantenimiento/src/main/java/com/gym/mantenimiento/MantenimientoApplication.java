package com.gym.mantenimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MantenimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantenimientoApplication.class, args);
	}

}
