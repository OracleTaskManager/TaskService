package com.Oracle.TaskService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Oracle.TaskService")
public class TaskServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(TaskServiceApplication.class, args);
	}

}
