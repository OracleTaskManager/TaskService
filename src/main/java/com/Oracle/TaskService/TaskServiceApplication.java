package com.Oracle.TaskService;
import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Oracle.TaskService")
public class TaskServiceApplication {

	public static void main(String[] args) {
		System.out.println("TaskServiceApplication Running");
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing() // Optional: avoids crash if .env is missing
				.load();

		String jwtSecret = dotenv.get("JWT_SECRET_ORACLE");
		String dbPass = dotenv.get("DB_PASSWORD");
		if (jwtSecret == null) {
			System.err.println("⚠️ JWT_SECRET_ORACLE not found in .env file!");
		} else {
			System.setProperty("JWT_SECRET_ORACLE", dotenv.get("JWT_SECRET_ORACLE"));
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		}
		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
