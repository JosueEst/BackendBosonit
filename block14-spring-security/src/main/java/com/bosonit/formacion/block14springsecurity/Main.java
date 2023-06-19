package com.bosonit.formacion.block14springsecurity;

import com.bosonit.formacion.block14springsecurity.domain.Person;
import com.bosonit.formacion.block14springsecurity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Main implements CommandLineRunner {
	@Autowired
	PersonRepository personRepository;
	private final static String ADMIN = "AdminPerson";
	private final static String USER = "UserPerson";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		Person person = new Person();

		person.setUsuario(ADMIN);
		person.setPassword(ADMIN);
		person.setName(ADMIN);
		person.setSurname(ADMIN);
		person.setCompanyEmail("j@gmail.com");
		person.setPersonalEmail("j@gmail.com");
		person.setCity("Burgos");
		person.setActive(true);
		person.setCreatedDate(new Date(2023, 6 , 19));
		person.setImageUrl("www."+ADMIN+".com");
		person.setTerminationDate(new Date(2023, 9, 19));
		person.setAdmin(true);

		personRepository.save(person);

		Person person2 = new Person();

		person2.setUsuario(USER);
		person2.setPassword(USER);
		person2.setName(USER);
		person2.setSurname(USER);
		person2.setCompanyEmail("j@gmail.com");
		person2.setPersonalEmail("j@gmail.com");
		person2.setCity("Burgos");
		person2.setActive(true);
		person2.setCreatedDate(new Date(2023, 6 , 19));
		person2.setImageUrl("www."+USER+".com");
		person2.setTerminationDate(new Date(2023, 9, 19));
		person2.setAdmin(false);

		personRepository.save(person2);
	}


}
