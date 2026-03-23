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
import java.time.LocalDateTime;

@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ViewProveedorReportDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @Size(max = 20)
    private String nroDocumentoIdentidad;

    @Size(max = 200)
    private String nombreFiscal;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 200)
    private String direccion;

    @Size(max = 20)
    private String telefono;

    private Boolean nacional;

    @Size(max = 200)
    private String contactoPrincipal;

    @Size(max = 20)
    private String contactoPrincipalTelefono;

    @Size(max = 50)
    private String origenRegistro;

    @Size(max = 5000)
    private String sedesIds;

    @Size(max = 5000)
    private String sedesNombres;

    @NotEmpty
    @Size(max = 20)
    private String estadoSsoCode;

    @NotNull
    private Boolean ssoTieneVigencia;

    private LocalDate ssoFechaInicioVigencia;

    @Size(max = 10)
    private String ssoFechaFinVigencia;

    @Size(max = 200)
    private String ssoMotivo;

    @Size(max = 50)
    private String ssoUsuarioEvaluador;

    private LocalDateTime ssoFechaEvaluacion;

    @Size(max = 100)
    private String ssoEstadoNombre;

    @Size(max = 200)
    private String ssoEstadoDescripcion;

    @Size(max = 100)
    private String ssoEstadoVigente;

    @NotEmpty
    @Size(max = 20)
    private String estadoCalidadAmbientalCode;

    @NotNull
    private Boolean caTieneVigencia;

    private LocalDate caFechaInicioVigencia;

    private LocalDate caFechaFinVigencia;

    @Size(max = 200)
    private String caMotivo;

    @Size(max = 50)
    private String caUsuarioEvaluador;

    private LocalDateTime caFechaEvaluacion;

    @Size(max = 100)
    private String caEstadoNombre;

    @Size(max = 200)
    private String caEstadoDescripcion;

    @Size(max = 100)
    private String caEstadoVigente;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nTrabajadoresAptos;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nTrabajadoresTotales;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsProvSso;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsCa;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsAar;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsAb;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsProvSsoValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsCaValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsAarValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsAbValid;

    @Digits(integer = 19, fraction = 0)
    @NotNull
    private Long nDocsTrabajSso;

    @NotNull
    private Boolean indRealizaActividadAltoRiesgo;

    @NotNull
    private Boolean indRealizaActividadABordo;

    @Size(max = 5000)
    private String tarCodigos;

    @Size(max = 5000)
    private String tarDescripciones;

    private LocalDate createdDate;

    private Boolean notificaCa;

    private Boolean notificaSso;


}
