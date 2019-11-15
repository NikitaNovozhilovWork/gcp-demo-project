package com.nikitanov.demo.gcp.demoproject.chatwall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The chat wall application, listening new messages from GCP Pub/Sub
 * and display them on ui
 *
 * @author Nikita Novozhilov
 */
@SpringBootApplication
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

}
