package com.bosonit.formacion.block5profiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class Block5ProfilesApplication  {

	@Autowired
	public ConfigurationP c;

	public static void main(String[] args) {

		SpringApplication.run(Block5ProfilesApplication.class, args);

	}

	@Bean
	public CommandLineRunner showProperties(){
		return p -> log.info("Enviroment: {}. Url: {}. ",c.getEnviroment(),c.getUrl() );
	}
	/*@Override
	public void run(String... args) throws Exception {
		log.info("Enviroment: {}. Url: {}",c.getEnviroment(),c.getUrl() );
		//System.out.println("Enviroment: "+c.getEnviroment()+"  "+"Url: "+c.getUrl());
	}*/
}
