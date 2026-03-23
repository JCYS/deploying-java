/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class SvProveedorDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    private String estadoCalidadAmbientalCode;

    @Size(max = 20)
    private String nroDocumentoIdentidad;

    @Size(max = 200)
    private String nombreFiscal;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 200)
    private String direccion;

    @Size(max = 200)
    private String contactoPrincipal;

    @Size(max = 20)
    private String contactoPrincipalTelefono;

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

    @NotNull
    private Boolean indRealizaActividadAltoRiesgo;

    @NotNull
    private Boolean indRealizaActividadABordo;

    @NotNull
    private Boolean caTieneVigencia;

    private LocalDate caFechaInicioVigencia;

    private LocalDate caFechaFinVigencia;

    @Size(max = 200)
    private String caMotivo;

    @Size(max = 50)
    private String caUsuarioEvaluador;

    private LocalDateTime caFechaEvaluacion;

    @Size(max = 50)
    private String origenRegistro;

    private String estadoSsoCode;

    private Boolean indProveedorNotificadoCreacion;

    @Size(max = 20)
    private String telefono;

    private Boolean nacional;

    private Boolean isExists;

    private String sedesNombres;

    private String observacion;

    private Boolean notificaCa;

    private Boolean notificaSso;
}
