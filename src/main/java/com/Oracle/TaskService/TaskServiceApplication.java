package com.Oracle.TaskService;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Oracle.TaskService")
public class TaskServiceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// Set env vars programmatically (optional)
		System.setProperty("JWT_SECRET_ORACLE", dotenv.get("JWT_SECRET_ORACLE"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
