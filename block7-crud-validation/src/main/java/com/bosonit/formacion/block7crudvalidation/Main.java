package com.bosonit.formacion.block7crudvalidation;

import jakarta.annotation.PostConstruct;
import org.hibernate.grammars.hql.HqlParser;
import org.hibernate.type.descriptor.java.CalendarDateJavaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}


}
