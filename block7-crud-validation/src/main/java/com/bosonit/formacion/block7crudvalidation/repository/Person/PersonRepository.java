package com.bosonit.formacion.block7crudvalidation.repository.Person;

import com.bosonit.formacion.block7crudvalidation.controller.Dtos.person.PersonOutputDto;
import com.bosonit.formacion.block7crudvalidation.domain.Person;
import com.bosonit.formacion.block7crudvalidation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);
    @Query(value = "SELECT * FROM (SELECT * FROM Personas P WHERE P.id_persona =?1) as P" +
            " LEFT JOIN Estudiantes E ON P.id_persona = E.id_personaFK", nativeQuery = true)
    Person getPersonFullStudent (int id);
    @Query(value = "SELECT * FROM (SELECT * FROM Personas P WHERE P.id_persona =?1) as P" +
            " LEFT JOIN Profesores PRO ON P.id_persona = PRO.id_personaFK", nativeQuery = true)
    Person getPersonFullProfessor (int id);
    @Query(value = "SELECT * FROM (SELECT * FROM Personas P WHERE P.nombre =?1) as P" +
            " LEFT JOIN Estudiantes E ON P.id_persona = E.id_personaFK", nativeQuery = true)
    Person getPersonFullStudent (String name);
    @Query(value = "SELECT * FROM (SELECT * FROM Personas P WHERE P.nombre =?1) as P" +
            " LEFT JOIN Profesores PRO ON P.id_persona = PRO.id_personaFK", nativeQuery = true)
    Person getPersonFullProfessor (String name);

    List<PersonOutputDto> getData (HashMap <String, Object> conditions);
    @Query(value = "SELECT * FROM Personas as P" +
            " LEFT JOIN Profesores PRO ON P.id_persona = PRO.id_personaFK", nativeQuery = true)
    List<Person> getAllPersonsFull ();

}
