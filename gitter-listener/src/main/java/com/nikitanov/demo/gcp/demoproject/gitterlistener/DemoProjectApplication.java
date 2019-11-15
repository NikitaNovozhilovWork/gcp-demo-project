package com.nikitanov.demo.gcp.demoproject.gitterlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The gitter listener application, listening new messages from gitter chat,
 * translate them and send to PubSub topic
 *
 * @author Nikita Novozhilov
 */
@SpringBootApplication
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

}
