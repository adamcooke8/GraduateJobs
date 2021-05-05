package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springemailclient.EmailSenderService;

@SpringBootApplication
@ComponentScan(basePackages = { "com.*" })
public class SpringDataDemoApplication {
	
	@Autowired
	private EmailSenderService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	public void triggerMail() {

		service.sendSimpleEmail("adamcooke@live.ie",
				"This is Email Body with Attachment...",
				"This email has attachment");

	}

}