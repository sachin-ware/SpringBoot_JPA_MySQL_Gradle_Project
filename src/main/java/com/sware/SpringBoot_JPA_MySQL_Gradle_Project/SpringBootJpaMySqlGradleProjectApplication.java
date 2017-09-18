package com.sware.SpringBoot_JPA_MySQL_Gradle_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.sware.SpringBoot_JPA_MySQL_Gradle_Project.domains")
public class SpringBootJpaMySqlGradleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaMySqlGradleProjectApplication.class, args);
	}
}
