package com.webshop.Webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WebshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}
}
