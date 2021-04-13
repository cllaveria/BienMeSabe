package com.bienmesabe.rest;

import com.bienmesabe.rest.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main method for the application
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.bienmesabe.rest")
public class BienmesabeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BienmesabeApplication.class, args);
	}

}
