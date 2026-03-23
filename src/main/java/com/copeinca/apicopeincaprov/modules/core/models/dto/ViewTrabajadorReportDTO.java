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

import java.time.LocalDate;

@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ViewTrabajadorReportDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @NotEmpty
    @Size(max = 36)
    private String proveedorId;

    @Size(max = 20)
    private String provNroDocumentoIdentidad;

    @Size(max = 200)
    private String provNombreFiscal;

    @NotEmpty
    @Size(max = 50)
    private String tipoDocumentoIdentidadCode;

    @Size(max = 50)
    private String nroDocumentoIdentidad;

    @Size(max = 100)
    private String tdiNombre;

    @NotEmpty
    @Size(max = 100)
    private String nombre;

    @Size(max = 20)
    private String telefono;

    @Email
    @Size(max = 100)
    private String email;

    @NotEmpty
    @Size(max = 20)
    private String estadoSsoCode;

    @Size(max = 200)
    private String ssoMotivo;

    @NotNull
    private Boolean soTieneVigencia;

    private LocalDate ssoFechaInicioVigencia;

    private LocalDate ssoFechaFinVigencia;

    @NotNull
    private Boolean isActive;

    @Size(max = 100)
    private String ssoEstadoNombre;

    @Size(max = 100)
    private String isValid;

    @Size(max = 100)
    private String ssoEstadoVigente;

    @NotNull
    private Boolean indTrabajoAltoRiesgo;

    @NotNull
    private Boolean indPrevencionista;

    @Size(max = 5000)
    private String tarCodigos;

    @Size(max = 5000)
    private String tarDescripciones;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsSsoAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsCalidadAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsAarAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsAbAdjuntos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsPrevAdjuntos;

    private String solicitudVisitaDetalleObs;

    private String solicitudVisitaDetalleId;

    private Long solicitudVisitaDetalleVersion;

    @Digits(integer = 19, fraction = 0)
    private Long nDocsTotalesValid;

    @Digits(integer = 19, fraction = 0)
    private Long nDocsTotales;

}
