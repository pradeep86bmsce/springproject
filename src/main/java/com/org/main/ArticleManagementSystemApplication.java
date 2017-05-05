package com.org.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.org.config.AppConfig;

@SpringBootApplication
@Import({ AppConfig.class })
public class ArticleManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleManagementSystemApplication.class, args);
	}
}
