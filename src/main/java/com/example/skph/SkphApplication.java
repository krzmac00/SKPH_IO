package com.example.skph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class SkphApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SkphApplication.class, args);
	}
}
