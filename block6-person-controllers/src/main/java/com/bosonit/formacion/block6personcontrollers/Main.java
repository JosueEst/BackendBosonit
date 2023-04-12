package com.bosonit.formacion.block6personcontrollers;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

	//List <City> cityLIst;

	public static void main(String[] args) {

		SpringApplication.run(Main.class, args);
	}

	/*@Bean
	public CommandLineRunner initList () {

		return p -> cityLIst = new ArrayList<>();
	}*/



}
