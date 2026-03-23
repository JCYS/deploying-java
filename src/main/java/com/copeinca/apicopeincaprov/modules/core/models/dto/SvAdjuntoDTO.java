/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
//@Builder
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SvAdjuntoDTO extends BaseEntityDTO {

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

    @Size(max = 200)
    private String motivo;

    private String proveedorId;

    private String trabajadorId;

    private String tipoDocumentoPlanillaCode;

    private String solicitudVisitaId;

    private String tipoActividadAltoRiesgoCode;

    private String descTipoDocumentoPlanillaCode;

    private String descDepTipoDocumentoPlanillaCode;

}
