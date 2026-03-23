/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/models/dto/EntityDTO.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.models.dto;

import com.copeinca.apicopeincaprov.commons.models.BaseEntityDTO;
import jakarta.validation.constraints.NotEmpty;
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
public class SvPersonaRestringidaDTO extends BaseEntityDTO {

    //****************************
    // Atributos
    //****************************

    private String id;

    private String tipoDocumentoIdentidadCode;

    @NotEmpty
    @Size(max = 50)
    private String numeroDocumento;

    @Size(max = 50)
    private String licenciaConducir;

    @Size(max = 200)
    private String nombreCompleto;

    @Size(max = 200)
    private String usuarioReporta;

    private LocalDateTime restringidoEl;

    private LocalDateTime restringidoHasta;

    @Size(max = 250)
    private String observaciones;

    private String sedeOriginadoraId;

    private Boolean isExists;

    private String observacion;


}
