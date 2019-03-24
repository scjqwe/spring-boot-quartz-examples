package com.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.quartz.dao")
@SpringBootApplication
public class SpringBootQuartzExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootQuartzExamplesApplication.class, args);
	}

}
