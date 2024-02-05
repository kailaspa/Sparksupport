package com.sparksupport.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Sparksupport. Configures and launches the Spring
 * Boot application.
 */
@SpringBootApplication
public class SparksupportApplication {

	/**
	 * Entry point for the Sparksupport application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SparksupportApplication.class, args);
	}

}
