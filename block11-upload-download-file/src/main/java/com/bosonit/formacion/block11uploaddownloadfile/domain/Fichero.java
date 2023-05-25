package com.bosonit.formacion.block11uploaddownloadfile.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fichero {
    @Id
    @GeneratedValue
    private int id;
    @Column (name = "nombre")
    private String nombre;
    @Column (name = "fecha_subida")
    private String fecha_subida;
    @Column (name = "categoria")
    private String categoria;

}
