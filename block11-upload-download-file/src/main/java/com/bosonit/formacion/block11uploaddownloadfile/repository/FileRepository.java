package com.bosonit.formacion.block11uploaddownloadfile.repository;

import com.bosonit.formacion.block11uploaddownloadfile.domain.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository <Fichero, Integer> {

    Optional<Fichero> findFileByNombre(String nombre);
}
