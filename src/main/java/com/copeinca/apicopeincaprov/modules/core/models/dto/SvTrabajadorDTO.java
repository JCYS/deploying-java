/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class SvTrabajadorDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    private String proveedorId;

    private String tipoDocumentoIdentidadCode;

    @Size(max = 50)
    private String nroDocumentoIdentidad;

    @NotEmpty
    @Size(max = 100)
    private String nombre;

    @Size(max = 20)
    private String telefono;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 200)
    private String ssoMotivo;

    @NotNull
    private Boolean soTieneVigencia;

    private LocalDate ssoFechaInicioVigencia;

    private LocalDate ssoFechaFinVigencia;

    @NotNull
    private Boolean indTrabajoAltoRiesgo;

    @NotNull
    private Boolean indPrevencionista;

    private String estadoSsoCode;

    @NotNull
    private Boolean isActive;

    private Boolean isExists;

    private String observacion;

    private String solicitudVisitaDetalleObs;

    private String solicitudVisitaDetalleId;

    private Long solicitudVisitaDetalleVersion;

    private String tipoDocumentoIdentidadDescription;

    private String estadoSsoDescription;

}
