package com.nikitanov.demo.gcp.demoproject.chathistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The gitter message history application, to load message history from Cloud Spanner
 *
 * @author Nikita Novozhilov
 */
@SpringBootApplication
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

}
