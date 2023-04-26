package com.formacion.bosonit.examen_JPA_cascada.domain;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.ClienteOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Clientes")
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int id_cliente;

    @Column (nullable = false)
    private String nombre;
    @OneToMany (mappedBy = "cli_codi")
    List<CabeceraFra> facturas;

    public Cliente (ClienteInputDto clienteInputDto){
        this.nombre=clienteInputDto.getNombre();
        this.facturas=clienteInputDto.getFacturas();
    }

    public ClienteOutputDto clienteToClienteOutpuDto(){
        return new ClienteOutputDto(
                this.id_cliente,
                this.nombre
        );
    }
}
