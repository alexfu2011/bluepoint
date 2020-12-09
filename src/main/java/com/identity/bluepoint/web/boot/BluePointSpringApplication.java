package com.identity.bluepoint.web.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootConfiguration
public class BluePointSpringApplication extends AbstractBluePointSpringApplication {
	
	public static void main(String[] args)
	{
		SpringApplication.run(BluePointSpringApplication.class, args);
	}
}
