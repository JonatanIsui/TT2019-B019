package com.tt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.tt2.restcontroller","com.tt2.service"})
public class Tt2Application {

	public static void main(String[] args) {
		SpringApplication.run(Tt2Application.class, args);
	}

}
