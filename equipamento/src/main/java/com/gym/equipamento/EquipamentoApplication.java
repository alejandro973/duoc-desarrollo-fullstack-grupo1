package com.gym.equipamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EquipamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipamentoApplication.class, args);
	}

}
