package com.groupseven.classscheduling;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ClassSchedulingApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Manila"));
	}
	public static void main(String[] args) {
		SpringApplication.run(ClassSchedulingApplication.class, args);
	}

}
