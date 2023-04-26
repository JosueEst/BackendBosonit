package com.formacion.bosonit.examen_JPA_cascada.domain;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.LineasFraInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.LineasFraOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LineasFra")
public class LineasFra {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int id;

    //Nombre producto
    @Column (nullable = false)
    private String proNomb;
    @Column
    private double cantidad;
    @Column
    private double precio;

    //id de cabecera factura
    @ManyToOne
    @JoinColumn (name = "cabeceraFra_codi",nullable = false)
    private CabeceraFra idFra;

    //NO hace falta el id porque se autogenera
    public LineasFra (LineasFraInputDto lineasFraInputDto){
        this.proNomb=lineasFraInputDto.getProNomb();
        this.cantidad=lineasFraInputDto.getCantidad();
        this.precio=lineasFraInputDto.getPrecio();
    }

    public LineasFraOutputDto lineasFraToLineasFraOutputDto (){
        return new LineasFraOutputDto(

                this.proNomb,
                this.cantidad,
                this.precio,
                this.idFra.getId()
        );
    }

}
