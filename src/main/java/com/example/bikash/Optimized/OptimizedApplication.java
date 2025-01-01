package com.example.bikash.Optimized;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OptimizedApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OptimizedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Database is Connected");
	}
}
