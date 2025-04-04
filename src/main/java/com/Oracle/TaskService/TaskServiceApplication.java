package com.Oracle.TaskService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Oracle.TaskService")
public class TaskServiceApplication {

	public static void main(String[] args) {

		System.out.println("${jwt.secret.oracle}");
		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
