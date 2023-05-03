package com.bosonit.formacion.block7crudvalidation.repository;

import com.bosonit.formacion.block7crudvalidation.domain.Professor;
import com.bosonit.formacion.block7crudvalidation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessorRepository extends JpaRepository<Professor,Integer> {
    @Query(value = "SELECT * FROM (SELECT * FROM Profesores P WHERE P.id_estudiante =?1) as PROF" +
            " LEFT JOIN Personas P ON PROF.id_personaFK = P.id_persona", nativeQuery = true)
    Professor getProfessorByIdLikeNativeFull (int id);
}
