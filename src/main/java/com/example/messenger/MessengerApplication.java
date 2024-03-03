package com.example.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = "com.example.Messenger.domain")
@SpringBootApplication
@Configuration
@EnableTransactionManagement
public class MessengerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MessengerApplication.class, args);
	  }
}
//http://localhost:8085/swagger-ui/index.html#/

