/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.Digits;
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
public class ViewAdjuntoReportDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @NotEmpty
    @Size(max = 200)
    private String nombreArchivo;

    @Size(max = 50)
    private String idRepositorio;

    @NotEmpty
    @Size(max = 50)
    private String clasification;

    private LocalDate fechaDocumento;

    private LocalDate fechaVencimiento;

    @Size(max = 200)
    private String rutaRelativa;

    @Size(max = 200)
    private String ssoComentarioRevision;

    @Size(max = 200)
    private String caComentarioRevision;

    @Size(max = 36)
    private String proveedorId;

    @Size(max = 36)
    private String trabajadorId;

    @Size(max = 20)
    private String tipoDocumentoPlanillaCode;

    @Size(max = 36)
    private String solicitudVisitaId;

    @Size(max = 50)
    private String tipoActividadAltoRiesgoCode;

    @Size(max = 200)
    private String tdpDescripcion;

    @Size(max = 50)
    private String tdpAmbito;

    @Size(max = 50)
    private String tdpInfluencia;

    @Size(max = 200)
    private String tdpDependencia;

    @Size(max = 200)
    private String tdpCampoPlantillaLf;

    private Boolean tdpObligatorio;

    private Boolean tdpVencimiento;

    @NotEmpty
    @Size(max = 16)
    private String tipoPropietario;

    @NotNull
    private Boolean documentoVencido;

    @Digits(integer = 19, fraction = 0)
    private Long diasHastaVencimiento;

}
