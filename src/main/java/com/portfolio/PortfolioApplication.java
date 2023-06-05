package com.portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class PortfolioApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PortfolioApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}
}