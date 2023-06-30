package com.bosonit.formacion.block1602appticket;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Block1602AppTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block1602AppTicketApplication.class, args);
	}

}
