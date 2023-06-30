package com.bosonit.formacion.block1603eurekanamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Block1603EurekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block1603EurekaNamingServerApplication.class, args);
	}

}
