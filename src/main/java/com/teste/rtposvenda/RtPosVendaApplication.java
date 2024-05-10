package com.teste.rtposvenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RtPosVendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RtPosVendaApplication.class, args);
	}

}
