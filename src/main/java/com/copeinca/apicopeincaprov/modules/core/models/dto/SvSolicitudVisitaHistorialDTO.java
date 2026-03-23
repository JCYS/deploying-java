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

import java.time.LocalDateTime;

@Getter
@Setter
//@Builder
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SvSolicitudVisitaHistorialDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    private String solicitudVisitaId;

    private String estadoSolicitudVisitaCode;

    private LocalDateTime fechaHora;

    @Size(max = 100)
    private String usuario;

    @Size(max = 200)
    private String ambito;

    @Size(max = 200)
    private String revision;

    @Size(max = 200)
    private String rptaByUsuario;

}
