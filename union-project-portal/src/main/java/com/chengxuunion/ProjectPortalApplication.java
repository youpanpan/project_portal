package com.chengxuunion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.chengxuunion"})
public class ProjectPortalApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectPortalApplication.class, args);
	}

}
