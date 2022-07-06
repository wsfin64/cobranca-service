package com.example.cobrancaservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class CobrancaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobrancaServiceApplication.class, args);
	}

}
