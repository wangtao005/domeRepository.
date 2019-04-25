package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class JpaMysqlActiviti7Application {

	public static void main(String[] args) {
		SpringApplication.run(JpaMysqlActiviti7Application.class, args);
		System.out.println("------------启动完成!-----------");
	}

}
