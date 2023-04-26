package com.formacion.bosonit.examen_JPA_cascada.domain;

import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.CabeceraFraInputDto;
import com.formacion.bosonit.examen_JPA_cascada.controller.dtos.CabeceraFraOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Cabeceras")
public class CabeceraFra {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private double importeFra;//Importe total de la factura
    @ManyToOne
    @JoinColumn (name = "id_cliente", nullable = false, unique = true)
    private Cliente cli_codi;

    @OneToMany(mappedBy = "idFra", cascade = CascadeType.ALL)
    private List<LineasFra> lineasFraList;

    public CabeceraFra (CabeceraFraInputDto cabeceraFraInputDto){
        this.importeFra=cabeceraFraInputDto.getImporteFra();
        this.cli_codi=cabeceraFraInputDto.getCli_codi();
        this.lineasFraList=cabeceraFraInputDto.getLineasFraList();

    }


    public CabeceraFraOutputDto cabeceraFraToCabeceraFraOutputDto (){
        return new CabeceraFraOutputDto(
                this.id,
                this.importeFra,
                this.cli_codi.clienteToClienteOutpuDto(),
                this.lineasFraList.stream().map(LineasFra::lineasFraToLineasFraOutputDto).toList()
        );
    }
}
