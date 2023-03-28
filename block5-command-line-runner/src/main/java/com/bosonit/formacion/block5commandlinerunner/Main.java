package com.bosonit.formacion.block5commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	@Qualifier ("secondFunction")
	String secondFunction;
	@Autowired
	@Qualifier ("thirdFunction")
	String thirdFunction;
	@Autowired
	@Qualifier ("initialFun")
	String initialFun;

	public static void main(String[] args) {
		System.out.println("Iniciando aplicación");
		SpringApplication.run(Main.class, args);
		System.out.println("Aplicación finalizada");
	}
	@PostConstruct
	public void initialFunction (){
		 System.out.println(initialFun+" desdePOstConstruct");

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(secondFunction+" desdeRun");
		System.out.println(thirdFunction+" desdeRun");

	}
}
