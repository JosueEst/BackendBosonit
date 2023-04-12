package com.bosonit.formacion.block6personcontrollers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {



	public static void main(String[] args) {

		SpringApplication.run(Main.class, args);
	}

	@Bean
	@Qualifier ("personBean1")
	public Person bean1 (){
		return new Person("Manuel", "Sevilla", "25");
	}
	@Bean
	@Qualifier ("personBean2")
	public Person bean2 (){
		return new Person ("Josue Luis", "Bilbao", "54");
	}
	@Bean
	@Qualifier ("personBean3")
	public Person bean3 (){
		return new Person("Sara", "Zaragoza", "95");
	}

}
