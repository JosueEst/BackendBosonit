package com.bosonit.formacion.block14springsecurity.repository;

import com.bosonit.formacion.block14springsecurity.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsRepository extends JpaRepository <Subject, Integer> {
}
