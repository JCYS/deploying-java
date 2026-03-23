/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SvSolicitudVisitaEquipoDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @NotEmpty
    @Size(max = 100)
    private String descripcion;

    @Size(max = 50)
    private String marca;

    @Size(max = 50)
    private String modelo;

    @Size(max = 30)
    private String numeroSerie;

    @Size(max = 50)
    private String tipoEquipo;

    @Size(max = 50)
    private String codigoEquipo;

    @Digits(integer = 7, fraction = 3)
    private BigDecimal cantidad;

    private String solicitudVisitaId;

}
