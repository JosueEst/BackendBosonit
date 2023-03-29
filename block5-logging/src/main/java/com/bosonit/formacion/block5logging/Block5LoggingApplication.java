package com.bosonit.formacion.block5logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class Block5LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block5LoggingApplication.class, args);
		log.trace("Mensaje a nivel de TRACE");
		log.debug("Mensaje a nivel de DEBUG");
		log.info("Mensaje a nivel de INFO");
		log.warn("Mensaje a nivel de WARNING");
		log.error("Mensaje a nivel de ERROR");
	}

}
