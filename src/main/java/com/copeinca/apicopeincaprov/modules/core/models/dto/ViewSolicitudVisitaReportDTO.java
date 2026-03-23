/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.*;
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
public class ViewSolicitudVisitaReportDTO extends BaseEntityDTO {

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

    @NotEmpty
    @Size(max = 20)
    private String estadoSsoCode;

    @NotEmpty
    @Size(max = 20)
    private String estadoCalidadAmbientalCode;

    @NotEmpty
    @Size(max = 20)
    private String estadoSolicitudVisitaCode;

    @NotEmpty
    @Size(max = 36)
    private String sedeId;

    @NotEmpty
    @Size(max = 36)
    private String proveedorId;

    @Size(max = 36)
    private String personalId;

    @Size(max = 100)
    private String sedeNombre;

    @Size(max = 20)
    private String sedeAmbito;

    @Size(max = 200)
    private String personalNombreUsuario;

    @Size(max = 100)
    private String personalUsuario;

    @Size(max = 200)
    private String proveedorNombreFiscal;

    @Size(max = 20)
    private String proveedorNroDocumentoIdentidad;

    @Email
    @Size(max = 100)
    private String proveedorEmail;

    @Size(max = 200)
    private String proveedorContactoPrincipal;

    @Size(max = 100)
    private String esvNombre;

    @Size(max = 100)
    private String ssoEstadoNombre;

    @Size(max = 200)
    private String ssoEstadoDescripcion;

    @Size(max = 100)
    private String caEstadoNombre;

    @Size(max = 200)
    private String caEstadoDescripcion;

    @Size(max = 5000)
    private String tarDescripciones;

    @Digits(integer = 19, fraction = 0)
    private Long nDias;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nTrabajadores;

    @Size(max = 5000)
    private String areasIds;

    @Size(max = 5000)
    private String areasNombres;

    @Size(max = 5000)
    private String zonasIds;

    @Size(max = 5000)
    private String zonasNombres;

    @Size(max = 5000)
    private String contactoPrincipalTelefono;


}
