package com.nikitanov.demo.gcp.demoproject.gitterlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The gitter listener application, to listen new messages from gitter chat
 * and store them into cloud SQL db
 *
 * @author Nikita Novozhilov
 */
@SpringBootApplication
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

}
