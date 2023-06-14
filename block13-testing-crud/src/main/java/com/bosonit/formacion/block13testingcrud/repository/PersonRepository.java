package com.bosonit.formacion.block13testingcrud.repository;

import com.bosonit.formacion.block13testingcrud.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
}
