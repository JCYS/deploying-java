/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SvSolicitudVisistaDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @NotEmpty
    @Size(max = 20)
    private String codigoVisita;

    @Size(max = 50)
    private String codigoSistemaExterno;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    @Size(max = 200)
    private String motivoVisita;

    private LocalDateTime fechaAprobacionPersonaVisitada;

    private LocalDateTime fechaObservacionAdministrador;

    @Size(max = 250)
    private String observaciones;

    @NotEmpty
    @Size(max = 50)
    private String nroOrdenServicio;

    @NotNull
    private Boolean indActividadAltoRiesgo;

    @NotNull
    private Boolean indActividadABordo;

    @NotNull
    private Boolean indRequiereAndamios;

    @NotNull
    private Boolean indRequiereGrua;

    @NotNull
    private Boolean indRequiereEquiposMoviles;

    @NotNull
    private Boolean indTrabajoBuceo;

    @Size(max = 200)
    private String ssoMotivo;

    @Size(max = 200)
    private String ssoDescargo;

    @Size(max = 200)
    private String caMotivo;

    @Size(max = 200)
    private String caDescargo;

    private String estadoSsoCode;

    private String estadoCalidadAmbientalCode;

    private String estadoSolicitudVisitaCode;

    private String sedeId;

    private String proveedorId;

    private String personalId;

    private Long correlativo;

}
