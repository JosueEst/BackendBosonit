package com.bosonit.formacion.block7crudvalidation.repository;

import com.bosonit.formacion.block7crudvalidation.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository <Subject, Integer> {
}
