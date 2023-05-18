package com.bosonit.formacion.block10dockerizeapp.repository;

import com.bosonit.formacion.block10dockerizeapp.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Persona, Integer> {
    Persona findByName (String name);
}
