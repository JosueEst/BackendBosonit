package com.bosonit.formacion.block7crudvalidation;

import jakarta.annotation.PostConstruct;
import org.hibernate.grammars.hql.HqlParser;
import org.hibernate.type.descriptor.java.CalendarDateJavaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

//Gracias a esta anotación, todos los clientes de feign y los subpaquetes serán implementados.
@EnableFeignClients //habilitará el escaneo de clases que se declaran como clientes de feign.

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}


}
