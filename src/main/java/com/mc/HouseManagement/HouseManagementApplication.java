package com.mc.HouseManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HouseManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(HouseManagementApplication.class, args);

		final Logger logger = LoggerFactory.getLogger(HouseManagementApplication.class);
		//TODO finish rest of controllers with thymeleaf
		// TODO finish version change docker compose atd

		//logger.info("asdfasdf");
		//logger.error("dfsa");

	}

}
