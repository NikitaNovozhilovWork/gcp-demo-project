package com.nikitanov.demo.gcp.demoproject.chatwall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The chat wall application, listening new messages from gitter chat
 * and display them on ui, also has availability to read message
 * history from db
 *
 * @author Nikita Novozhilov
 */
@SpringBootApplication
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

}
