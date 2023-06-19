package com.bosonit.formacion.block14springsecurity.repository;

import com.bosonit.formacion.block14springsecurity.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository <Student, Integer> {
    /*@Query(value = "SELECT * FROM Estudiantes WHERE id_estudiante =?1", nativeQuery = true)
    Student getByIdLikeNativeSimple (int id);*/

    @Query(value = "SELECT * FROM (SELECT * FROM Estudiantes E WHERE E.id_estudiante =?1) as EST" +
            " LEFT JOIN Personas P ON EST.id_personaFK = P.id_persona", nativeQuery = true)
    Student getStudentByIdLikeNativeFull (int id);

}
