package com.ensa.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevOpsCycleApplication {
	public static void main(String[] args) {
		run();
		SpringApplication.run(DevOpsCycleApplication.class, args);
	}
	public static void run(){
		System.out.println("App is running...");
	}
}
