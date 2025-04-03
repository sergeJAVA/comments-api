package com.example.comments_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CommentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentsApiApplication.class, args);
	}

}
