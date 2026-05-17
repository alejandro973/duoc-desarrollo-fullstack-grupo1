package com.gym.socios;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class SociosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SociosApplication.class, args);
	}

}
