package com.init.construccion1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages= {"com.init.construccion1.rest"})
public class Construccion1Application {

	public static void main(String[] args) {
		SpringApplication.run(Construccion1Application.class, args);
	}

}
