/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
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
public class SvSolicitudVisitaDetalleDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    @Size(max = 200)
    private String proveedorComentario;

    private String solicitudVisitaId;

    private String trabajadorId;

    private String tipoDocumento;

    private String numDocumento;

    private String nombre;

    private String estadoSSODesc;

    private String codVisita;

    //private String fechaVigencia;

    private LocalDate ssoFechaFinVigencia;

}
